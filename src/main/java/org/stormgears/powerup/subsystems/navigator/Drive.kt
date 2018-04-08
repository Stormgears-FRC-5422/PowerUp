package org.stormgears.powerup.subsystems.navigator

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.yield
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionManager
import org.stormgears.powerup.subsystems.navigator.motionprofile.TrapezoidalProfile
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.sensordrivers.NavX
import java.util.*
import kotlin.math.PI
import kotlin.math.abs

object Drive : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Drive::class.java)

	private const val MAX_VELOCITY_ENCODER_TICKS = 6300
	private val MODE = ControlMode.Velocity

	private const val TURN_SENSITIVITY_FACTOR = 0.6

	// IN RPM!!!
	private const val MAX_VELOCITY = 5000
	private const val MAX_ACCELERATION = 2500

	private val driveTalons = Robot.driveTalons!!
	private val talons = driveTalons.talons
	private val sensors = Robot.sensors!!
	private val vels = DoubleArray(talons.size)

	var useAbsoluteControl = true        // default mode - should be a config file setting
	var overrideAbsoluteControl = false  // to change when button is pressed
	var useTractionControl = false

	private val motions: Array<MotionMagic?> = arrayOfNulls(driveTalons.talons.size)
	private val motionManager: MotionManager = MotionManager()

	private val sunProfile = SunProfile()

	fun joystickMove() {
		val x = Robot.dsio!!.joystickX
		val y = Robot.dsio!!.joystickY
		val z = Robot.dsio!!.joystickZ

		//		logger.debug("x: {} y: {} z: {}", box(x), box(y), box(z));

		var theta = Math.atan2(x, y)
		if (theta < 0) theta += 2 * Math.PI

		if (x == 0.0 && y == 0.0 && z == 0.0) {
			setDriveTalonsZeroVelocity()
		} else {
			mecMove(MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y + z * z),
				x, y, z * TURN_SENSITIVITY_FACTOR,
				theta,
				if (overrideAbsoluteControl) !useAbsoluteControl else useAbsoluteControl)
		}
	}

	// Run mecanum math on each raw speed and set talons accordingly
	private fun mecMove(tgtVel: Double,
						x: Double,
						y: Double,
						changeVel: Double,
						theta: Double,
						absolute: Boolean) {
		var theta = theta

		for (i in talons.indices) {
			talons[i].inverted = true
		}

//		talons[3].setSensorPhase(true)
//		talons[0].setSensorPhase(true)
//		talons[2].setSensorPhase(true)

		if (absolute) {
			val navxTheta = sensors.navX.getTheta()
			theta = theta - navxTheta
		}

		// If +/- 15 degrees of a special angle, assume that angle was the intended direction
		// TODO: constrain theta to be from -pi to pi
		if (Math.abs(theta - 0) <= Math.PI / 12.0 || Math.abs(theta - 2.0 * Math.PI) <= Math.PI / 12.0) {
			theta = 0.0
		}

		if (Math.abs(theta - Math.PI / 2.0) <= Math.PI / 12.0) {
			theta = Math.PI / 2.0
		}

		if (Math.abs(theta - Math.PI) <= Math.PI / 12.0) {
			theta = Math.PI
		}

		if (Math.abs(theta - 3.0 * Math.PI / 2.0) <= Math.PI / 12.0) {
			theta = 3.0 * Math.PI / 2.0
		}


		vels[0] = Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0)
		vels[1] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0))
		vels[2] = Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0)
		vels[3] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0))
		//TODO: Flip signs for REAL BOT

		if (Math.abs(changeVel) > 0.5) {
			for (i in vels.indices) {
				vels[i] += changeVel
			}
		}

		while (Math.abs(vels[0]) > 1.0 ||
			Math.abs(vels[1]) > 1.0 ||
			Math.abs(vels[2]) > 1.0 ||
			Math.abs(vels[3]) > 1.0) {
			val max = Math.max(Math.max(Math.max(Math.abs(vels[0]),
				Math.abs(vels[1])),
				Math.abs(vels[2])),
				Math.abs(vels[3]))

			for (i in vels.indices) {
				vels[i] /= max
			}
		}

		//Turning in place
		if (x == 0.0 && y == 0.0) {
			for (i in vels.indices) {
				vels[i] = changeVel
			}
		}

		for (i in vels.indices) {
			vels[i] *= tgtVel
		}

		// Traction control
		if (useTractionControl && driverInputEligibleForTractionControl()) {
			//			NavX navX = Robot.sensors.getNavX();
			//			float nx = navX.getVelocityX();
			//			float ny = navX.getVelocityY();
			//			double actualVelocity = convertToEncoderUnits(Math.sqrt(nx * nx + ny * ny));
			//			double stickVelocity = MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y);
			//
			//			SmartDashboard.putNumber("stickVelocity", stickVelocity);
			//			SmartDashboard.putNumber("actualVelocity", actualVelocity);
			//			SmartDashboard.putNumber("tractiontest", ((actualVelocity - stickVelocity) / stickVelocity));
			//
			//			if (stickVelocity > 700 && Math.abs((actualVelocity - stickVelocity) / stickVelocity) > 0.1) {
			//				logger.info("Using traction control...");
			//
			//				double multiplier = 0.5; // (actualVelocity + 0.1) / (vels[0] + 0.1) * 1.1;
			//				for (int i = 0; i < vels.length; i++) {
			//					vels[i] *= multiplier;
			//				}
			//			}
		}

		for (i in talons.indices) {
			talons[i].set(MODE, vels[i])
		}
	}

	private fun convertToEncoderUnits(speedInMetersPerSecond: Double): Double {
		val inPer100ms = speedInMetersPerSecond * 100 / 2.54 / 10.0
		return inPer100ms / Robot.config.wheelRadius / 2.0 / Math.PI * 8192
	}

	private fun setDriveTalonsZeroVelocity() {
		for (t in talons) {
			t.set(ControlMode.Velocity, 0.0)
		}
	}

	fun driveMotionProfile(rotations: Double, theta: Double) {
		var theta = theta
		val navXTheta = sensors.navX.getTheta()
		theta = theta - navXTheta - Math.PI / 2.0

		val profile = TrapezoidalProfile.getTrapezoidZero(rotations, 300.0, theta, 0.0)
		motionManager.pushProfile(profile, false, true)
		motionManager.startProfile()
	}

	fun debug() {
		for (t in talons) {
			logger.debug("Real Velocities: {}", box(t.sensorCollection.quadratureVelocity))
		}
	}

	private fun driverInputEligibleForTractionControl(): Boolean {
		return Math.abs(Robot.dsio!!.joystickX) < 0.4 && Math.abs(Robot.dsio!!.joystickZ) < 0.4

	}

	/**
	 * The intended purpose of this method is to move the robot at a given
	 * angle (theta) for a given distance (distance). The distance should be
	 * given in inches, and the theta in radians. The method will convert the
	 * distance into encoder ticks in order to facilitate motion magic. Ensure
	 * that when calling this method.
	 *
	 * @param distance - the distance to move the robot in inches
	 * @param theta    - the angle at which to move the robot
	 */
	suspend fun moveStraight(distance: Double, theta: Double) {
		var distance = distance

		distance *= if (Math.abs(theta) == Math.PI / 2.0) FieldPositions.STRAFING_FACTOR else FieldPositions.STRAIGHT_FACTOR

		logger.trace("distance: {}", box(distance))
		logger.trace(box(distance * 8192 / (2.0 * Robot.config.wheelRadius * Math.PI)))

		val wheelCircumference = 2.0 * Math.PI * Robot.config.wheelRadius
		val ticks = distance / wheelCircumference * 8192.0
		// motions[0].runMotionMagic((int) ticks);


		val modifiers = DoubleArray(motions.size)

		//From the mecMove method...
		//TODO: test and see if this works
		modifiers[0] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0))
		modifiers[1] = Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0)
		modifiers[2] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0))
		modifiers[3] = Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0)

		var max = 0
		for (i in modifiers.indices) {
			if (Math.abs(modifiers[i]) > Math.abs(modifiers[max])) {
				max = i
			}
		}

		var currentDistance: Double
		var t1: Double
		var totTime = 0.0
		var vmax2: Double
		var a2: Double

		val maxDistance = Math.abs(modifiers[max] * distance) * 8192 / (2.0 * Math.PI * Robot.config.wheelRadius)
		for (i in 0 until talons.size) {

			currentDistance = Math.abs(modifiers[i] * distance) * 8192 / (2.0 * Math.PI * Robot.config.wheelRadius)


			t1 = (MAX_VELOCITY / MAX_ACCELERATION).toDouble()
			totTime = t1 + maxDistance / MAX_VELOCITY / 10.0 //TODO: FIND TOTAL TIME
			vmax2 = currentDistance / (totTime - t1) / 10.0
			a2 = vmax2 / t1

			val multiplier = if (Math.sin(theta) != 0.0) 0.5 else 1.0
			logger.trace("sin(θ): {}, drive straight multiplier: {}", Math.sin(theta), multiplier)

			if (Math.abs(modifiers[i] * distance) != maxDistance) {
				logger.trace("Vmax2 {}", box(vmax2))
				logger.trace("A2 {}", box(a2))
				motions[i] = MotionMagic(talons[i], vmax2 * multiplier, a2 * multiplier)
			} else {
				logger.trace("MAX VEL {}", box(MAX_VELOCITY))
				logger.trace("MAX ACCEL {}", box(MAX_ACCELERATION))
				motions[i] = MotionMagic(talons[i], MAX_VELOCITY.toDouble() * multiplier, MAX_ACCELERATION.toDouble() * multiplier)
			}
		}

//		Robot.talonDebugger?.dump();

		for (talon in talons) {
			talon.set(ControlMode.PercentOutput, 0.0)
			talon.sensorCollection.setQuadraturePosition(0, 250)
//			talon.set(ControlMode.MotionMagic, 0.0)
		}

//		Robot.talonDebugger?.dump();
		for (i in motions.indices) {
			logger.trace("Talon {} Commanded: {}", box(i), box(ticks * modifiers[i]))
			val talon = talons[i]
//			talon.set(ControlMode.PercentOutput, 0.0)
//			talon.sensorCollection.setQuadraturePosition(0, 250);
			motions[i]?.runMotionMagic((ticks * modifiers[i]).toInt())
		}

		// TODO: wtf is this doing?
		logger.trace("totTime: {}", totTime)
		delay((totTime * 1250).toInt())

//		Robot.talonDebugger?.dump();

		logger.trace("Resetting talons")
		for (talon in talons) {
			talon.set(ControlMode.PercentOutput, 0.0)
			talon.sensorCollection.setQuadraturePosition(0, 250)
			talon.setConfig(driveTalons.driveTalonConfig)
//			talon.set(ControlMode.MotionMagic, 0.0)
		}

//		Robot.talonDebugger?.dump();
	}

	/**
	 * Moves the robot forwards or backwards
	 * @param dist in inches
	 */
	suspend fun moveStraightNavX(dist: Double, progressListener: ((progress: Double) -> Unit)? = null, maxAMultiplier: Double = 1.0) {
		val sign = Math.signum(dist)
		val distance = abs(dist)

		val wheelCircumference = 2.0 * Math.PI * Robot.config.wheelRadius
		val distanceTicks = distance / wheelCircumference * 8192.0

		for (talon in talons) {
			talon.setConfig(driveTalons.driveTalonConfig)
		}
		driveTalons.velocityPIDMode()

		val talonFL = talons[0]
		val talonFR = talons[1]
		val talonRL = talons[2]
		val talonRR = talons[3]

		val initTheta = -sensors.navX.getTheta(NavX.AngleUnit.Degrees, false) // degrees
		val initPosFL = -talonFL.sensorCollection.quadraturePosition
		val initPosFR = talonFR.sensorCollection.quadraturePosition

		var avgPos: Double
		var progress: Double
		do {
			avgPos = ((-talonFL.sensorCollection.quadraturePosition - initPosFL + talonFR.sensorCollection.quadraturePosition - initPosFR) / 2).toDouble()

			progress = sign * avgPos / distanceTicks

//			val currVel = Math.sin((progress * 0.82 + 0.1) * Math.PI) * maxVel // ticks/100ms
			val currVel = sunProfile.profile(progress * distance, distance, maxAMultiplier)

			val currTheta = -sensors.navX.getTheta(NavX.AngleUnit.Degrees, false) // degrees TODO why is this inverted
			val delta = currTheta - initTheta // degrees

			val compensation = sign * delta / 30
//			val compensation = 0

			logger.trace("progress = {} currTheta = {} delta = {} compensation = {} currVel = {}", box(progress), box(currTheta), box(delta), box(compensation), box(currVel));

			talonFL.set(ControlMode.Velocity, sign * -currVel * (1 - compensation))
			talonFR.set(ControlMode.Velocity, sign * currVel * (1 + compensation))
			talonRL.set(ControlMode.Velocity, sign * -currVel * (1 - compensation))
			talonRR.set(ControlMode.Velocity, sign * currVel * (1 + compensation))

//			delay(10)
			if (progressListener != null) {
				progressListener(progress)
			}
			yield()
		} while (progress < 1.0);

		setDriveTalonsZeroVelocity()
	}

	/**
	 * Moves the robot left or right
	 *
	 * @param dist in inches
	 */
	suspend fun strafeNavX(dist: Double, maxAMultiplier: Double = 1.0) {
		val sign = Math.signum(dist)
		val distance = abs(dist)

		val wheelCircumference = 2.0 * Math.PI * Robot.config.wheelRadius
		val distanceTicks = distance / wheelCircumference * 8192.0
		val theta = if (dist >= 0.0) PI / 2 else -PI / 2

		val modifiers = DoubleArray(motions.size)
		modifiers[0] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0))
		modifiers[1] = Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0)
		modifiers[2] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0))
		modifiers[3] = Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0)

		val targets = modifiers.map { distanceTicks * it }.toDoubleArray()

		for (talon in talons) {
			talon.setConfig(driveTalons.driveTalonConfig)
		}
		driveTalons.velocityPIDMode()

		val talonFL = talons[0]
		val talonFR = talons[1]
		val talonRL = talons[2]
		val talonRR = talons[3]

		val initTheta = -sensors.navX.getTheta(NavX.AngleUnit.Degrees, false) // degrees
		val initPos = talons.map { it.sensorCollection.quadraturePosition }.toIntArray()

		logger.trace("initPos = {} targets = {}", Arrays.toString(initPos), Arrays.toString(targets))

		val maxVel = if (dist < 150) 2000 else 4000
//		val maxVel = 4000

		var avgPos: Double
		var progress: Double
		do {
			progress = 0.0
			targets.forEachIndexed { i, target -> progress += abs(((abs(talons[i].sensorCollection.quadraturePosition - initPos[i]))) / abs(target)) }
			progress /= 4

//			val currVel = Math.sin((progress * 0.82 + 0.1) * Math.PI) * maxVel // ticks/100ms
			val currVel = sunProfile.profile(progress * distance, distance, maxAMultiplier)

			val currTheta = -sensors.navX.getTheta(NavX.AngleUnit.Degrees, false) // degrees TODO why is this inverted
			val delta = currTheta - initTheta // degrees

			val compensation = sign * delta / 30
//			val compensation = 0

			logger.trace("progress = {} currTheta = {} delta = {} compensation = {} currVel = {}", box(progress), box(currTheta), box(delta), box(compensation), box(currVel));

			talonFL.set(ControlMode.Velocity, sign * -currVel * (1 - compensation))
			talonFR.set(ControlMode.Velocity, sign * -currVel * (1 - compensation))
			talonRL.set(ControlMode.Velocity, sign * currVel * (1 + compensation))
			talonRR.set(ControlMode.Velocity, sign * currVel * (1 + compensation))

//			delay(10)
			yield()
		} while (progress < 1.0)

		setDriveTalonsZeroVelocity()
	}

	/**
	 * Turns the robot using the NavX
	 *
	 * @param theta in radians
	 */
	suspend fun turnNavX(theta: Double) {
		val sign = -Math.signum(theta) // I don't know why this works...
		val theta = abs(theta)

		for (talon in talons) {
			talon.setConfig(driveTalons.driveTalonConfig)
		}

		driveTalons.velocityPIDMode()

		val talonFL = talons[0]
		val talonFR = talons[1]
		val talonRL = talons[2]
		val talonRR = talons[3]

		val initTheta = sensors.navX.getTheta(NavX.AngleUnit.Radians, false) // radians

		var progress: Double
		do {
			progress = abs(sensors.navX.getTheta(NavX.AngleUnit.Radians, false) - initTheta) / theta

			val currVel = sunProfile.profile(progress * 60, 60.0) * 0.7

//			logger.trace("progress = {} currTheta = {} delta = {} compensation = {} currVel = {}", box(progress), box(currTheta), box(delta), box(compensation), box(currVel));

			talonFL.set(ControlMode.Velocity, sign * currVel)
			talonFR.set(ControlMode.Velocity, sign * currVel)
			talonRL.set(ControlMode.Velocity, sign * currVel)
			talonRR.set(ControlMode.Velocity, sign * currVel)

//			delay(10)
			yield()
		} while (progress < 1.0);

		setDriveTalonsZeroVelocity()
	}


	/**
	 * MoveToPos Method; Moves robot between two positions
	 *
	 * @param p1 first position
	 * @param p2 second position
	 */
	suspend fun moveToPos(p1: Position, p2: Position) {
		logger.info("p1: {}, p2: {}", p1, p2)

		val deltaX = (p2.x - p1.x)  //TODO: Strafing Factor really belongs here!!
		val deltaY = p2.y - p1.y

		logger.info("deltaX: {}, deltaY: {}", deltaX, deltaY)

		// Robot theta is degrees from vertical clockwise
		val theta = -(Math.atan2(deltaY, deltaX) - Math.PI / 2)

		val hyp = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0))
		logger.info("hyp: {}, theta: {}", box(hyp), box(theta))

//		moveStraight(hyp, theta)
		if (abs(theta) < 0.001) {
			moveStraightNavX(hyp)
		} else if (abs(theta) in (PI / 2 - 0.001)..(PI / 2 + 0.001)) {
			strafeNavX(Math.copySign(hyp, theta))
		} else {
			moveStraight(hyp, theta)
		}
	}
}
