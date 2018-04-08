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
import kotlin.math.abs

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
	private val rotationMotor: ITalon

	private var position = VERTICAL
	private var job: Job? = null

	init {
		leftTalon = createTalon(LEFT_TALON_ID)
		rightTalon = createTalon(RIGHT_TALON_ID)
		rotationMotor = createTalon(ARTICULATOR_TALON_ID)

		if (leftTalon.dummy || rightTalon.dummy) {
			logger.warn("Requires physical talon, disabling Intake!")
			this.disabled = true
		}
	}

	fun startWheelsIn(output: Double = 1.0) {
		logger.info("Intake wheels pulling in")

		leftTalon.set(ControlMode.PercentOutput, output)
		rightTalon.set(ControlMode.PercentOutput, -output)
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

	fun eject(): Job {
		return launch("Intake Eject") {
			startWheelsOut()
			delay(2000)
			stopWheels()
		}
	}

	fun moveIntakeToPosition(position: Int): Job {
		if (job != null) {
			job!!.cancel()
			println("Canceled intake rotation job")
		}

		val job = launch("Intake Rotation") {
			moveIntakeToPositionSuspend(position)
		}

		this.job = job
		return job
	}

	private suspend fun moveIntakeToPositionSuspend(position: Int) {
//		if (position == this@Intake.position) return

		/*
		 * TODO: REMOVE THIS ONCE THE MOTOR IS PLACED ON THE ROBOT
		 */
		if (rotationMotor.dummy) return

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

		logger.trace("Articulator moving with {}", multiplier * POWER)
		rotationMotor.set(ControlMode.PercentOutput, POWER * multiplier)
		while (rotationMotor.outputCurrent < CURRENT_LIMIT && !stopped) {
			iteration++

			if (abs(rotationMotor.sensorCollection.quadratureVelocity) < 100 && iteration > 60) {
				stopped = true
				println("Articulator stopped")
			} else if (iteration > time) {
				rotationMotor.set(ControlMode.PercentOutput, 0.0)
				println("Articulator power set to 0")
			}

			delay(20)
		}

		this@Intake.position = position

		rotationMotor.set(ControlMode.PercentOutput, 0.0)
	}

	fun debug() {
		SmartDashboard.putNumber("Articulator encoder position", rotationMotor.sensorCollection.quadraturePosition.toDouble())
	}
}
