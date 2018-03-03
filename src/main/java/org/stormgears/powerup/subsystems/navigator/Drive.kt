package org.stormgears.powerup.subsystems.navigator

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionManager
import org.stormgears.powerup.subsystems.navigator.motionprofile.TrapezoidalProfile
import org.stormgears.utils.concurrency.TerminableSubsystem

object Drive : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Drive::class.java)

	private const val MAX_VELOCITY_ENCODER_TICKS = 6300
	private val MODE = ControlMode.Velocity


	private const val MAX_VELOCITY = 25000
	private const val MAX_ACCELERATION = 1200 / 2
	private const val TURN_SENSITIVITY_FACTOR = 0.8


	private val talons = Robot.driveTalons.talons
	private val vels = DoubleArray(talons.size)

	var useAbsoluteControl = false
	var useTractionControl = false

	private val motions: Array<MotionMagic?> = arrayOfNulls(Robot.driveTalons.talons.size)
	private val motionManager: MotionManager = MotionManager()

	fun move() {
		val x = Robot.dsio.joystickX
		val y = Robot.dsio.joystickY
		val z = Robot.dsio.joystickZ

		//		logger.debug("x: {} y: {} z: {}", box(x), box(y), box(z));

		var theta = Math.atan2(x, y)
		if (theta < 0) theta = 2 * Math.PI + theta

		if (x == 0.0 && y == 0.0 && z == 0.0) {
			setDriveTalonsZeroVelocity()
		} else {
			mecMove(MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y + z * z),
				x, y, z * TURN_SENSITIVITY_FACTOR,
				theta,
				useAbsoluteControl)
		}
	}

	fun setVelocityPID() {
		for (t in Robot.driveTalons.talons) {
			t.config_kP(0, Robot.config.velocityP, 10)
			t.config_kI(0, Robot.config.velocityI, 10)
			t.config_kD(0, Robot.config.velocityD, 10)
			t.config_IntegralZone(0, Robot.config.velocityIzone, 10)
		}
	}

	// Run mecanum math on each raw speed and set talons accordingly
	private fun mecMove(tgtVel: Double,
						x: Double,
						y: Double,
						changeVel: Double,
						theta: Double,
						useAbsoluteControl: Boolean) {
		var theta = theta

		for (i in talons.indices) {
			talons[i].inverted = true
		}

		talons[3].setSensorPhase(true)
		talons[0].setSensorPhase(true)
		talons[2].setSensorPhase(true)

		if (useAbsoluteControl) {
			val navX_theta = Robot.sensors.navX.theta
			theta = theta - navX_theta - Math.PI / 2
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
		val talons = Robot.driveTalons.talons
		for (t in talons) {
			t.set(ControlMode.PercentOutput, 0.0)
		}
	}

	fun driveMotionProfile(rotations: Double, theta: Double) {
		var theta = theta
		val navX_theta = Robot.sensors.navX.theta
		theta = theta - navX_theta - Math.PI / 2.0

		val profile = TrapezoidalProfile.getTrapezoidZero(rotations, 300.0, theta, 0.0)
		motionManager.pushProfile(profile, false, true)
		motionManager.startProfile()
	}

	fun debug() {
		val talons = Robot.driveTalons.talons
		for (t in talons) {
			logger.debug("Real Velocities: {}", t.sensorCollection.quadratureVelocity)
		}
	}

	private fun driverInputEligibleForTractionControl(): Boolean {
		return Math.abs(Robot.dsio.joystickX) < 0.4 && Math.abs(Robot.dsio.joystickZ) < 0.4

	}

	private fun average(data: DoubleArray): Double {
		var total = 0.0
		for (d in data) {
			total += d
		}

		return total / data.size
	}

	/**
	 * The inteded purpose of this method is to move the robot at a given
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

		//		if (useAbsoluteControl) {
		//			double navX_theta = Robot.sensors.getNavX().getTheta();
		//			theta = theta - navX_theta;
		//		}

		if (Math.abs(theta) == Math.PI / 2.0) {
			distance = distance / 0.833333
			println(distance)
			println(distance * 8192 / (2.0 * Robot.config.wheelRadius * Math.PI))
		}

		//TODO: make wheel diameter and other constants that im just making up
		val wheelCircumference = 2.0 * Math.PI * Robot.config.wheelRadius
		//TODO: constant for encoder ticks
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
		for (i in 0 until Robot.driveTalons.talons.size) {

			currentDistance = Math.abs(modifiers[i] * distance) * 8192 / (2.0 * Math.PI * Robot.config.wheelRadius)


			t1 = (MAX_VELOCITY / MAX_ACCELERATION).toDouble()
			totTime = t1 + maxDistance / MAX_VELOCITY / 10.0 //TODO: FIND TOTAL TIME
			vmax2 = currentDistance / (totTime - t1) / 10.0
			a2 = vmax2 / t1


			if (Math.abs(modifiers[i] * distance) != maxDistance) {
				println("Vmax2 $vmax2")
				println("A2 $a2")
				motions[i] = MotionMagic(Robot.driveTalons.talons[i], vmax2, a2)
			} else {
				println("MAX VEL $MAX_VELOCITY")
				println("MAX ACCEL $MAX_ACCELERATION")
				motions[i] = MotionMagic(Robot.driveTalons.talons[i], MAX_VELOCITY.toDouble(), MAX_ACCELERATION.toDouble())
			}
		}
		Robot.timer.start()
		for (i in motions.indices) {
			println("Talon " + i + " Commanded: " + ticks * modifiers[i])
			motions[i]?.runMotionMagic((ticks * modifiers[i]).toInt())
		}

		while (!Robot.timer.hasPeriodPassed(totTime / 10.0)) {
			delay(20)
		}
		Robot.timer.stop()
		Robot.timer.reset()
	}


	/**
	 * This method takes in an angle, theta, and turns the robot to the
	 * corresponding direction so that its front faces the angle passed in.
	 *
	 * @param theta Angle desired for robot turn
	 */
	suspend fun turnTo(theta: Double) {
		var theta = theta
		if (useAbsoluteControl) {
			val navX_theta = Robot.sensors.navX.theta
			theta = theta - navX_theta
		}

		var negative = -1.0


		theta = theta % (2 * Math.PI)
		if (theta < 0) {
			theta += 2 * Math.PI
			println("Theta 2 $theta")
		}

		if (theta > Math.PI) {
			negative = 1.0
			println("Theta 1 $theta")
			theta -= Math.PI

			println(theta)
		}

		val robotLength = java.lang.Double.parseDouble(Robot.config.robotLength)
		val robotWidth = java.lang.Double.parseDouble(Robot.config.robotWidth)

		val r = (robotLength + robotWidth) / 3.8

		val s = r * theta


		var encoderTicks = s / (2.0 * Math.PI * Robot.config.wheelRadius) * 8192.0
		encoderTicks *= Math.sqrt(2.0)


		val t1 = (MAX_VELOCITY / MAX_ACCELERATION).toDouble()
		val totTime = t1 + s / MAX_VELOCITY / 10.0 //TODO: FIND TOTAL


		for (i in motions.indices) {
			motions[i] = MotionMagic(Robot.driveTalons.talons[i], (MAX_VELOCITY / 2).toDouble(), (MAX_ACCELERATION / 2).toDouble())
		}

		println(encoderTicks.toString() + "")
		Robot.timer.start()
		for (i in motions.indices) {
			if (i == 0 || i == 2) {
				println("Talon $i Commanded: $encoderTicks")
				motions[i]?.runMotionMagic((negative * encoderTicks).toInt())
			} else {
				println("Talon $i Commanded: $encoderTicks")
				motions[i]?.runMotionMagic((negative * encoderTicks).toInt())

			}
		}

		while (!Robot.timer.hasPeriodPassed(totTime / 10.0)) {
			delay(20)
		}
		Robot.timer.stop()
		Robot.timer.reset()
	}


	/**
	 * MoveToPos Method; Moves robot between two positions
	 *
	 * @param p1 first position
	 * @param p2 second position
	 */
	fun moveToPos(p1: Position, p2: Position): Job {

		val deltaX = p2.x - p1.x
		val deltaY = p2.y - p1.y

		val theta = -Math.atan(deltaY / deltaX) + Math.PI / 2

		val hyp = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0))
		println(hyp)

		return launch { moveStraight(hyp, theta) }
	}

	override fun initDefaultCommand() {

	}

}
