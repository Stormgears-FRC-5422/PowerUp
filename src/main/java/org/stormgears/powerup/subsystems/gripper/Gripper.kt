package org.stormgears.powerup.subsystems.gripper

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.TalonIds
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon

object Gripper : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

	var instance: Gripper? = null
		private set

	//TODO: Change to correct value
	private const val TALON_ID = TalonIds.GRIPPER

	private const val GRIPPER_POWER = 0.70

	private const val CLOSE_CURRENT_LIMIT = 10
	private const val OPEN_CURRENT_LIMIT = 10
	private const val CURRENT_CHECK_START_TIME = 5
	private const val BREAK_JAM_SPEED = 0.75
	private const val BRAKE_SPEED = -0.05

	private val talon: ITalon

	private var gripperClosing = false
	private var gripperOpening = false

	private var job: Job? = null

	init {
		talon = createTalon(TALON_ID)

		if (talon.dummy) {
			logger.warn("Requires physical talon, disabling Gripper!")
			this.disabled = true
		}
	}

	private var iteration = 0

	fun openGripper() {
		if (job != null) {
			job!!.cancel()
			logger.trace("Canceled gripper job")
		}

		job = launch("Gripper Open") {
			openGripperSuspend()
		}
	}

	suspend fun openGripperSuspend() {
		logger.info("Gripper Opening")
		talon.set(ControlMode.PercentOutput, BREAK_JAM_SPEED)

		iteration = 0
		while (!talon.sensorCollection.isFwdLimitSwitchClosed) {
			delay(10)

			if (iteration++ > CURRENT_CHECK_START_TIME) {
				talon.set(ControlMode.PercentOutput, GRIPPER_POWER)
				if (talon.outputCurrent > OPEN_CURRENT_LIMIT) break
			}
		}

		logger.info("Gripper limit is reached or terminated early")
		talon.set(ControlMode.PercentOutput, BRAKE_SPEED)
		delay(300)
		talon.set(ControlMode.PercentOutput, 0.0)

		gripperOpening = false
	}

	fun closeGripper() {
		if (job != null) {
			job!!.cancel()
			logger.trace("Canceled gripper job")
		}

		job = launch("Gripper Close") {
			closeGripperSuspend()
		}
	}

	suspend fun closeGripperSuspend() {
		logger.info("Gripper Closing")
		talon.set(ControlMode.PercentOutput, -BREAK_JAM_SPEED)

		iteration = 0
		while (!talon.sensorCollection.isRevLimitSwitchClosed) {
			delay(10)

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

	fun debug() {
		println("Forward switch: ${talon.sensorCollection.isFwdLimitSwitchClosed}")
		println("Reverse switch: ${talon.sensorCollection.isRevLimitSwitchClosed}")
		SmartDashboard.putNumber("Gripper current", talon.outputCurrent)
//		println("Output current: ${talon.outputCurrent}")
	}
}
