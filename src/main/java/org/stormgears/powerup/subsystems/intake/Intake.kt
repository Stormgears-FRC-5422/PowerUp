package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.talons.IBaseTalon
import org.stormgears.utils.talons.createTalon
import java.lang.Math.abs

object Intake : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Intake::class.java)
	var instance: Intake? = null
		private set

	// Positions
	const val VERTICAL = 0
	const val HORIZONTAL = 1

//	private const val POS_VERTICAL = 0
//	private const val POS_HORIZONTAL = 70000

	private val LEFT_TALON_ID = Robot.config.intakeLeftTalonId
	private val RIGHT_TALON_ID = Robot.config.intakeRightTalonId
	private val ARTICULATOR_TALON_ID = Robot.config.intakeArticulatorTalonId

	//	private const val WHEEL_SPEED = 8000
	private const val POWER = 1.0
	private const val CURRENT_LIMIT = 90

	private val leftTalon: IBaseTalon
	private val rightTalon: IBaseTalon
	private val articulatorTalon: IBaseTalon

	private var position = VERTICAL
	private var job: Job? = null

	val isUp: Boolean
		get() = position == VERTICAL

	init {
		leftTalon = createTalon(LEFT_TALON_ID)
		rightTalon = createTalon(RIGHT_TALON_ID)
		articulatorTalon = createTalon(ARTICULATOR_TALON_ID)

		if (leftTalon.dummy || rightTalon.dummy || articulatorTalon.dummy) {
			logger.warn("Requires physical talon, disabling Intake!")
			this.disabled = true
		}
	}

	fun startWheelsIn() {
		logger.info("Intake wheels pulling in")

		leftTalon.set(ControlMode.PercentOutput, 1.0)
		rightTalon.set(ControlMode.PercentOutput, -1.0)
	}

	fun startWheelsOut() {
		logger.info("Intake wheels pushing out")

		leftTalon.set(ControlMode.PercentOutput, -1.0)
		rightTalon.set(ControlMode.PercentOutput, 1.0)
	}

	fun stopWheels() {
		logger.info("Intake wheels off")

		leftTalon.set(ControlMode.PercentOutput, 0.0)
		rightTalon.set(ControlMode.PercentOutput, 0.0)
	}

	fun moveIntakeToPosition(position: Int): Job {
		if (job != null) {
			job!!.cancel()
			println("Canceled intake rotation job")
		}

		val job = launch("Articulator Mover") {
			moveIntakeToPositionSuspend(position)
		}

		this.job = job
		return job
	}

	private suspend fun moveIntakeToPositionSuspend(position: Int) {
//		if (position == this@Intake.position) return

		val multiplier: Double
		val time: Int
		when (position) {
			VERTICAL -> {
				logger.info("Moving to vertical position.")
				multiplier = 1.0
				time = 23
			}
			HORIZONTAL -> {
				logger.info("Moving to horizontal position.")
				multiplier = -0.7
				time = 6
			}
			else -> {
				logger.info("Position value for intake rotation does not match a valid position.")
				return
			}
		}

		var stopped = false
		var iteration = 0

		println("Articulator moving with ${POWER * multiplier}")
		articulatorTalon.set(ControlMode.PercentOutput, POWER * multiplier)
		while (articulatorTalon.outputCurrent < CURRENT_LIMIT && !stopped) {
			iteration++

			if (abs(articulatorTalon.sensorCollection.quadratureVelocity) < 100 && iteration > 60) {
				stopped = true
				println("Articulator stopped")
			} else if (iteration > time) {
				articulatorTalon.set(ControlMode.PercentOutput, 0.0)
				println("Articulator power set to 0")
			}

			delay(20)
		}

		this@Intake.position = position

		articulatorTalon.set(ControlMode.PercentOutput, 0.0)
	}

	fun controlWithThrottle() {
		articulatorTalon.set(ControlMode.PercentOutput, Robot.dsio.joystick.throttleV)
		if (articulatorTalon.outputCurrent > CURRENT_LIMIT) articulatorTalon.set(ControlMode.PercentOutput, 0.0)
	}

	fun applyPower(power: Double, timeMs: Int): Job {
		logger.info("Applying power to intake rotation motor")

		if (job != null) {
			job!!.cancel()
			println("Canceled intake rotation job")
		}

		val job = launch("Articulator Apply Power") {
			articulatorTalon.set(ControlMode.PercentOutput, power)
			delay(timeMs)
		}

		this.job = job
		return job
	}

	fun debug() {
		SmartDashboard.putNumber("Articulator encoder position", articulatorTalon.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Left wheel position", leftTalon.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Right wheel position", rightTalon.sensorCollection.quadraturePosition.toDouble())
	}

	fun joystickify() {
		articulatorTalon.set(ControlMode.PercentOutput, Robot.dsio.joystick.joystickY)
	}
}
