package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.talons.ITalon
import org.stormgears.utils.talons.createTalon

object Intake : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Intake::class.java)
	var instance: Intake? = null
		private set

	// Positions
	const val VERTICAL = 0
	const val HORIZONTAL = 1

	private const val CUBE_GRAB_CURRENT = 30.0

	private val LEFT_TALON_ID = Robot.config.intakeLeftTalonId
	private val RIGHT_TALON_ID = Robot.config.intakeRightTalonId
	private val ARTICULATOR_TALON_ID = Robot.config.intakeArticulatorTalonId

	private val leftTalon: ITalon
	private val rightTalon: ITalon
	private val rotationMotor: ITalon

	private var position = VERTICAL
	private var rotationJob: Job? = null
	private var wheelsJob: Job? = null

	init {
		leftTalon = createTalon(LEFT_TALON_ID)
		rightTalon = createTalon(RIGHT_TALON_ID)
		rotationMotor = createTalon(ARTICULATOR_TALON_ID)

		if (leftTalon.dummy || rightTalon.dummy) {
			logger.warn("Requires physical talon, disabling Intake!")
			this.disabled = true
		}
	}

	fun setVelocity(output: Double) {
//		leftTalon.set(ControlMode.PercentOutput, output)
//		rightTalon.set(ControlMode.PercentOutput, -output)
	}

	fun startWheelsIn(output: Double = 1.0) {
		logger.info("Intake wheels pulling in")

		leftTalon.set(ControlMode.PercentOutput, output)
		rightTalon.set(ControlMode.PercentOutput, -output)
	}

	fun startWheelsOut(output: Double = 1.0) {
		logger.info("Intake wheels pushing out")

		leftTalon.set(ControlMode.PercentOutput, -output)
		rightTalon.set(ControlMode.PercentOutput, output)
	}

	fun stopWheels() {
		logger.info("Intake wheels off")

		leftTalon.set(ControlMode.PercentOutput, 0.0)
		rightTalon.set(ControlMode.PercentOutput, 0.0)
	}

	fun grab(limit: Int = 200): Job {
		if (wheelsJob != null) {
			wheelsJob!!.cancel()
			println("Canceled intake wheels job")
		}

		val job = launch("Intake Grab") {
			startWheelsIn()
			var i = 0
			while (i < limit && !rightTalon.sensorCollection.isRevLimitSwitchClosed) {
//				println(i)
				i++
				delay(10)
			}
			stopWheels()
		}

		this.wheelsJob = job
		return job
	}

	fun eject(output: Double = 1.0, checkLimitSwitch: Boolean = false): Job? {
		if (wheelsJob != null) {
			wheelsJob!!.cancel()
			println("Canceled intake wheels job")
		}

		if (checkLimitSwitch && !rightTalon.sensorCollection.isRevLimitSwitchClosed) {
			stopWheels()
			return null
		}

		val job = launch("Intake Eject") {
			startWheelsOut(output = output)
			delay(1000)
			stopWheels()
		}

		this.wheelsJob = job
		return job
	}

	fun moveIntakeToPosition(position: Int): Job {
		if (rotationJob != null) {
			rotationJob!!.cancel()
			println("Canceled intake rotation job")
		}

		val job = launch("Intake Rotation") {
			moveIntakeToPositionSuspend(position)
		}

		this.rotationJob = job
		return job
	}

	private suspend fun moveIntakeToPositionSuspend(position: Int) {
		if (rotationMotor.dummy) return

		val multiplier: Double
		val time: Int
		when (position) {
			HORIZONTAL -> {
				logger.info("Moving to horizontal position.")
				multiplier = 0.7
				time = 7
			}
			VERTICAL -> {
				logger.info("Moving to vertical position.")
				multiplier = -1.0
				time = 12
			}
			else -> {
				logger.info("Position value for intake rotation does not match a valid position.")
				return
			}
		}

		logger.trace("Articulator moving with {}", multiplier)
		rotationMotor.set(ControlMode.PercentOutput, multiplier)
		delay(time * 20)
		rotationMotor.set(ControlMode.PercentOutput, 0.0)

		this.position = position
	}

	fun debug() {
		SmartDashboard.putNumber("Rotation motor encoder position", rotationMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Rotation motor output current", rotationMotor.outputCurrent)
		SmartDashboard.putBoolean("Right motor proximity sensor engaged (fwd)", rightTalon.sensorCollection.isFwdLimitSwitchClosed)
		SmartDashboard.putBoolean("Right motor proximity sensor engaged (rev)", rightTalon.sensorCollection.isRevLimitSwitchClosed)
	}
}
