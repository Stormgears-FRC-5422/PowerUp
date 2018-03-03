package org.stormgears.powerup.subsystems.intake

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminableSubsystem

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

	private const val SPEED = 8000

	private val leftTalon: StormTalon
	private val rightTalon: StormTalon
	private val articulatorTalon: StormTalon

	private var job: Job? = null

	init {
		leftTalon = StormTalon(LEFT_TALON_ID)
		rightTalon = StormTalon(RIGHT_TALON_ID)
		articulatorTalon = StormTalon(ARTICULATOR_TALON_ID)
	}

	fun startWheelsIn() {
		logger.info("Intake wheels pulling in")

		leftTalon.set(ControlMode.Velocity, SPEED.toDouble())
		rightTalon.set(ControlMode.Velocity, (-SPEED).toDouble())
	}

	fun startWheelsOut() {
		logger.info("Intake wheels pushing out")

		leftTalon.set(ControlMode.Velocity, (-SPEED).toDouble())
		rightTalon.set(ControlMode.Velocity, SPEED.toDouble())
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

		launch("") {
			when (position) {
				VERTICAL -> {
					logger.info("Moving to vertical position.")
				}
				HORIZONTAL -> {
					logger.info("Moving to horizontal position.")
				}
				else -> logger.info("Position value for intake rotation does not match a valid position.")
			}
		}
	}

	override fun initDefaultCommand() {

	}
}
