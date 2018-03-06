package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminableSubsystem
import java.lang.Math.abs

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
	private const val CURRENT_LIMIT = 40

	private val leftTalon: StormTalon
	private val rightTalon: StormTalon
	private val articulatorTalon: StormTalon

	private var position = VERTICAL
	private var job: Job? = null

	init {
		leftTalon = StormTalon(LEFT_TALON_ID)
		rightTalon = StormTalon(RIGHT_TALON_ID)
		articulatorTalon = StormTalon(ARTICULATOR_TALON_ID)
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

		if (position == this.position) return

		launch("") {
			val positionTicks: Int
			val multiplier: Int
			when (position) {
				VERTICAL -> {
					logger.info("Moving to vertical position.")
					positionTicks = POS_VERTICAL
					multiplier = 1
				}
				HORIZONTAL -> {
					logger.info("Moving to horizontal position.")
					positionTicks = POS_HORIZONTAL
					multiplier = -1
				}
				else -> {
					logger.info("Position value for intake rotation does not match a valid position.")
					return@launch
				}
			}

			var currentLimitReached = false

			articulatorTalon.set(ControlMode.PercentOutput, POWER * multiplier)
			println("Articulator moving")
			while (abs(articulatorTalon.sensorCollection.quadraturePosition - positionTicks) > 500 && !currentLimitReached) {
				if (articulatorTalon.outputCurrent > CURRENT_LIMIT) {
					currentLimitReached = true
					println("Articulator reached current limit")
				}

				delay(20)
			}

			this@Intake.position = position

			articulatorTalon.set(ControlMode.PercentOutput, 0.0)
			println()
		}
	}

	fun debug() {
		println("Articulator position ticks: ${articulatorTalon.sensorCollection.quadraturePosition}")
	}
}
