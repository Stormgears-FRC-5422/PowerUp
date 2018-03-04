package org.stormgears.powerup.subsystems.gripper

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminableSubsystem

object Gripper : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

	var instance: Gripper? = null
		private set

	//TODO: Change to correct value
	private val TALON_ID = 21

	private const val GRIPPER_POWER = 0.5
	private const val CLOSE_CURRENT_LIMIT = 7
	private const val OPEN_CURRENT_LIMIT = 4.5
	private const val CURRENT_CHECK_START_TIME = 15
	private const val BREAK_JAM_SPEED = 0.75
	private const val BRAKE_SPEED = -0.05

	private val talon: StormTalon

	private var gripperClosing = false
	private var gripperOpening = false

	private var job: Job? = null

	init {
		talon = StormTalon(TALON_ID)
	}

	private var iteration = 0

	fun openGripper() {
		if (job != null) {
			job!!.cancel()
			logger.trace("Canceled gripper job")
		}

		job = launch("Gripper Open") {
			logger.info("Gripper Opening")
			talon.set(ControlMode.PercentOutput, BREAK_JAM_SPEED)

			iteration = 0
			while (!talon.sensorCollection.isFwdLimitSwitchClosed) {
				delay(20)

				if (iteration++ > CURRENT_CHECK_START_TIME) {
					talon.set(ControlMode.PercentOutput, GRIPPER_POWER)
					if (talon.outputCurrent > OPEN_CURRENT_LIMIT) break;
				}
			}

			logger.info("Gripper limit is reached or terminated early")
			talon.set(ControlMode.PercentOutput, BRAKE_SPEED)
			delay(300)
			talon.set(ControlMode.PercentOutput, 0.0)

			gripperOpening = false
		}
	}

	fun closeGripper() {
		if (job != null) {
			job!!.cancel()
			logger.trace("Canceled gripper job")
		}

		job = launch("Gripper Close") {
			logger.info("Gripper Closing")
			talon.set(ControlMode.PercentOutput, -BREAK_JAM_SPEED)

			iteration = 0
			while (!talon.sensorCollection.isRevLimitSwitchClosed) {
				delay(20)

				if (iteration++ > CURRENT_CHECK_START_TIME) {
					talon.set(ControlMode.PercentOutput, -GRIPPER_POWER)
					if (talon.outputCurrent > CLOSE_CURRENT_LIMIT) break
				}
			}

			logger.info("Cube is being hugged or terminated early")
			talon.set(ControlMode.PercentOutput, -BRAKE_SPEED)
			delay(300)
			talon.set(ControlMode.PercentOutput, 0.0)

			gripperClosing = false
		}
	}

	fun debug() {
		println("Forward switch: ${talon.sensorCollection.isFwdLimitSwitchClosed}")
		println("Reverse switch: ${talon.sensorCollection.isRevLimitSwitchClosed}")
//		println("Output current: ${talon.outputCurrent}")
	}

	override fun initDefaultCommand() {

	}
}
