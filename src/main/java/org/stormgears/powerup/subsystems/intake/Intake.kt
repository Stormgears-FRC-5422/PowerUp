package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon

object Intake : TerminableSubsystem() {
	private val logger = LogManager.getLogger(Intake::class.java)
	var instance: Intake? = null
		private set

	// Positions
	const val VERTICAL = 0
	const val HORIZONTAL = 1

	private const val POS_VERTICAL = 0
	private const val POS_HORIZONTAL = 70000

	private const val LEFT_TALON_ID = 23
	private const val RIGHT_TALON_ID = 3
	private const val ARTICULATOR_TALON_ID = 20

	private const val WHEEL_SPEED = 8000
	private const val POWER = 1.0
	private const val CURRENT_LIMIT = 30

	private val leftTalon: ITalon
	private val rightTalon: ITalon
	private val articulatorTalon: ITalon

	private var position = VERTICAL
	private var job: Job? = null

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

		leftTalon.set(ControlMode.Velocity, WHEEL_SPEED.toDouble())
		rightTalon.set(ControlMode.Velocity, (-WHEEL_SPEED).toDouble())
	}

	fun startWheelsOut() {
		logger.info("Intake wheels pushing out")

		leftTalon.set(ControlMode.Velocity, (-WHEEL_SPEED).toDouble())
		rightTalon.set(ControlMode.Velocity, WHEEL_SPEED.toDouble())
	}

	fun stopWheels() {
		logger.info("Intake wheels off")

		leftTalon.set(ControlMode.Velocity, 0.0)
		rightTalon.set(ControlMode.Velocity, 0.0)
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

		val positionTicks: Int
		var multiplier: Double
		when (position) {
			VERTICAL -> {
				logger.info("Moving to vertical position.")
				positionTicks = POS_VERTICAL
				multiplier = 15.0
			}
			HORIZONTAL -> {
				logger.info("Moving to horizontal position.")
				positionTicks = POS_HORIZONTAL
				multiplier = -4.0
			}
			else -> {
				logger.info("Position value for intake rotation does not match a valid position.")
				return
			}
		}

		var currentLimitReached = false

		var power = 0.0
		var iteration = 0
		val increment = 0.005
		var incrementMultiplier = 1.0

		println("Articulator moving with ${POWER * multiplier}")
		while (!currentLimitReached) {
			power += increment * incrementMultiplier
			articulatorTalon.set(ControlMode.PercentOutput, power * multiplier)
			if (articulatorTalon.outputCurrent > CURRENT_LIMIT && ++iteration > 100) {
				currentLimitReached = true
				println("Articulator reached current limit")
			}

			if (power > 0.5) {
				multiplier = 0.0
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
		println("Articulator output current: ${articulatorTalon.outputCurrent}")
	}

	fun joystickify() {
		articulatorTalon.set(ControlMode.PercentOutput, Robot.dsio.joystick.joystickY)
	}
}
