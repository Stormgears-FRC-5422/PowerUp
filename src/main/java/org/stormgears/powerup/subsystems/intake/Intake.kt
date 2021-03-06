package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.talons.FactoryTalonConfig
import org.stormgears.utils.talons.ITalon
import org.stormgears.utils.talons.createTalon

object Intake : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Intake::class.java)
	var instance: Intake? = null
		private set

	// Positions
	const val VERTICAL = 0
	const val HORIZONTAL = 1
	private const val VERTICAL_TICKS = 0
	private const val HORIZONTAL_TICKS = 2048

	private val LEFT_TALON_ID = Robot.config.intakeLeftTalonId
	private val RIGHT_TALON_ID = Robot.config.intakeRightTalonId
	private val ARTICULATOR_TALON_ID = Robot.config.intakeArticulatorTalonId

	private val leftTalon: ITalon
	private val rightTalon: ITalon
	private val rotationMotor: ITalon

	private var position = VERTICAL
	private var rotationJob: Job? = null
	private var wheelsJob: Job? = null

	class RotationMotorConfig : FactoryTalonConfig() {
		override val enableVoltageCompensation = true
		override val voltageCompSaturation = 8.0
	}

	init {
		leftTalon = createTalon(LEFT_TALON_ID)
		rightTalon = createTalon(RIGHT_TALON_ID)
		rotationMotor = createTalon(ARTICULATOR_TALON_ID)

		rotationMotor.setConfig(RotationMotorConfig())

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

	fun getPosition(): Int {
		return position
	}

	fun stopWheels() {
		logger.info("Intake wheels off")

		leftTalon.set(ControlMode.PercentOutput, 0.0)
		rightTalon.set(ControlMode.PercentOutput, 0.0)
	}

	fun grab(limit: Int = 200, forceVertical: Boolean = true): Job {
		if (wheelsJob != null) {
			wheelsJob!!.cancel()
			logger.info("Canceled intake wheels job")
		}

		val job = launch("Intake Grab") {
			startWheelsIn()

			var i = 0
			while (i < limit && !rightTalon.sensorCollection.isRevLimitSwitchClosed) {
				if (!rightTalon.sensorCollection.isRevLimitSwitchClosed) {
					logger.info("Do not see cube, iteration: {}", i)
				}

				delay(10)
				i++
			}

			stopWheels()
			if (rightTalon.sensorCollection.isRevLimitSwitchClosed && forceVertical) {
				moveIntakeToPosition(Intake.VERTICAL)
			}
		}

		this.wheelsJob = job
		return job
	}

	fun eject(output: Double = 1.0, checkLimitSwitch: Boolean = false, forceHorizontal: Boolean = false): Job? {
		if (wheelsJob != null) {
			wheelsJob!!.cancel()
			logger.info("Canceled intake wheels job")
		}

		if (checkLimitSwitch && !rightTalon.sensorCollection.isRevLimitSwitchClosed) {
			logger.info("No cube inside, not ejecting anything")
			stopWheels()
			return null
		}

		val job = launch("Intake Eject") {
			if (forceHorizontal && this@Intake.position == VERTICAL) {
				moveIntakeToPosition(HORIZONTAL).join()
			}
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
			logger.info("Canceled intake rotation job")
		}

		val job = launch("Intake Rotation") {
			moveIntakeToPositionSuspend(position)
		}

		this.rotationJob = job
		return job
	}

	private suspend fun moveIntakeToPositionSuspend(position: Int) {
		if (rotationMotor.dummy) return

		val power: Double
		val time: Int
		when (position) {
			HORIZONTAL -> {
				logger.info("Moving to horizontal position.")
				power = 0.8
				time = 17
			}
			VERTICAL -> {
				logger.info("Moving to vertical position.")
				power = -0.8
				time = 24
			}
			else -> {
				logger.info("Position value for intake rotation does not match a valid position.")
				return
			}
		}

		logger.trace("Articulator moving with {}", power)
		rotationMotor.set(ControlMode.PercentOutput, power)
		delay(time * 20)
//		rotationMotor.set(ControlMode.PercentOutput, -power / 5)
//		delay(150)
		rotationMotor.set(ControlMode.PercentOutput, power * 0.1)

		this.position = position
	}

	fun debug() {
		SmartDashboard.putNumber("Rotation motor encoder position", rotationMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Rotation motor output current", rotationMotor.outputCurrent)
		SmartDashboard.putBoolean("Right motor proximity sensor engaged (fwd)", rightTalon.sensorCollection.isFwdLimitSwitchClosed)
		SmartDashboard.putBoolean("Right motor proximity sensor engaged (rev)", rightTalon.sensorCollection.isRevLimitSwitchClosed)
	}
}
