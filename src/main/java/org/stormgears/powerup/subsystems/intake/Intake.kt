package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon
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

	private val leftTalon: ITalon
	private val rightTalon: ITalon
	private val articulatorTalon: ITalon

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

	fun moveIntakeToPosition(position: Int) {
		if (job != null) {
			job!!.cancel()
			println("Canceled intake rotation job")
		}

		launch("Articulator Mover") {
			moveIntakeToPositionSuspend(position)
		}
	}

	suspend fun moveIntakeToPositionSuspend(position: Int) {
		if (position == this@Intake.position) return

		var multiplier: Double
		when (position) {
			VERTICAL -> {
				logger.info("Moving to vertical position.")
				multiplier = 1.0
			}
			HORIZONTAL -> {
				logger.info("Moving to horizontal position.")
				multiplier = -1.0
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
			if (abs(articulatorTalon.sensorCollection.quadratureVelocity) < 100 && ++iteration > 170) {
				stopped = true
				println("Articulator stopped")
			} else if (iteration > 130) {
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

	fun debug() {
		SmartDashboard.putNumber("Articulator encoder position", articulatorTalon.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Left wheel position", leftTalon.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Right wheel position", rightTalon.sensorCollection.quadraturePosition.toDouble())
	}

	fun joystickify() {
		articulatorTalon.set(ControlMode.PercentOutput, Robot.dsio.joystick.joystickY)
	}
}
