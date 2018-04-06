package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.yield
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.navigator.SunProfile
import org.stormgears.utils.concurrency.TerminableSubsystem
import kotlin.math.abs

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	var useGartnerRate = true
	private val logger = LogManager.getLogger(this::class.java)

	private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons!!

	private const val TICKS_PER_INCH = 13443  // 8192 / (3 * 13/64)
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // Because the encoder is reversed

	var elevatorZeroed = false

	private val currentPositionTicks: Int
		get() = talons.masterMotor.sensorCollection.quadraturePosition

	private const val ZERO_POWER = 0.2
	private const val ZERO_CURRENT_LIMIT = 8.0 // 8.7

	// Elevator button positions (inches)
	val SWITCH_POSITIONS = intArrayOf(22, 37, 40) // first one = 22
	val SCALE_POSITIONS = intArrayOf(56, 70, 81, 90, 92) // first one = 56

	// Jobs
	private var elevatorJob: Job? = null

	init {
	}

	/**
	 * Move the elevator to a position
	 *
	 * @param position = inches from the bottom of the elevator
	 */
	fun moveElevatorToPosition(position: Int): Job {
		if (elevatorJob != null) {
			elevatorJob!!.cancel()
			logger.trace("Cancelled elevator job")
		}

		val elevatorJob = launch("Elevator Auto Move") {
			elevatorAutoMove(position)
		}

		this.elevatorJob = elevatorJob

		return elevatorJob
	}

	private val sunProfile = SunProfile()

	private suspend fun elevatorAutoMove(position: Int) {
		val lowering: Boolean
		val destinationTicks = toEncoderTicks(position.toDouble())
		val multiplier: Int

		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
		lowering = if (destinationTicks < currentPositionTicks) {     // Raising elevator
			logger.info("Using raise elevator PID values")
			talons.masterMotor.selectProfileSlot(0, 0)
			multiplier = -1
			false
		} else {    // Lowering elevator
			logger.info("Using lower elevator PID values")
			talons.masterMotor.selectProfileSlot(1, 0)
			multiplier = 1
			true
		}
		var power = if (lowering) 0.5 else 1.0

		SmartDashboard.putNumber("Desired encoder position", destinationTicks.toDouble())
		SmartDashboard.putBoolean("Elevator lowering", lowering)

		logger.trace("Elevator moving!")
		talons.masterMotor.set(ControlMode.PercentOutput, multiplier * power)

		var shouldStop = false

		while (!shouldStop) {
			if (abs(destinationTicks - currentPositionTicks) < 100000 && power > 0.1) {
				power -= 0.07 * 13 / DriverStation.getInstance().batteryVoltage
//				val power = sunProfile.profile(abs((currentPositionTicks.toDouble() - destinationTicks) / destinationTicks) * 100, 100.0) / 5000
				talons.masterMotor.set(ControlMode.PercentOutput, power * multiplier)
			}

			// If within 10000 ticks (0.75 in) of destination, stop
			shouldStop = (if (lowering) currentPositionTicks > destinationTicks - 2000
			else currentPositionTicks < destinationTicks + 2000) ||
				talons.masterMotor.sensorCollection.isRevLimitSwitchClosed

			delay(10)
		}

		logger.trace(Timer.getFPGATimestamp().toString() + " Elevator has moved to encoder position: {}", currentPositionTicks)
//		holdElevator()
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
	}

	/**
	 * Stop all motion
	 */
	fun stop(): Job {
		return launch("Override slow down", parent = null) {
			talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

//			holdElevator()
		}
	}

	suspend fun zeroElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, ZERO_POWER)
		logger.trace("Zero-ing Elevator. Watch out!")

		var iteration = 0
		while (iteration < 40 || talons.masterMotor.outputCurrent < ZERO_CURRENT_LIMIT) {
			delay(20)
			logger.trace("Current limit: ${talons.masterMotor.outputCurrent}")

			iteration++
		}
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

		zeroElevatorEncoder()
		elevatorZeroed = true
		logger.trace("Elevator zeroed")
	}

	fun zeroElevatorEncoder() {
		logger.trace("Zero elevator encoder")
		talons.masterMotor.sensorCollection.setQuadraturePosition(0, 10)
	}

	fun turnOffElevator() {
		logger.trace("Turning off elevator")
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
	}

	private fun holdElevator() {
		logger.trace("Holding elevator")
		this.elevatorJob?.cancel()

//		delay(300)

		// Hold current elevator position
		talons.masterMotor.set(ControlMode.Position, currentPositionTicks.toDouble())
	}

	fun moveUpManual() {
		if (currentPositionTicks < -1100000) {
			talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
//			holdElevator()
		} else {
			talons.masterMotor.set(ControlMode.PercentOutput, -0.8)
		}
	}

	private var downPower = 0.33

	fun moveDownManual() {
//		if (currentPositionTicks > -110000 && elevatorZeroed && useGartnerRate) downPower *= 0.95
//		else downPower = 0.33

		logger.trace("downPower = {}", downPower)

		talons.masterMotor.set(ControlMode.PercentOutput, downPower)
	}

	fun debug() {
		SmartDashboard.putNumber("Elevator encoder position", talons.masterMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Elevator encoder velocity", talons.masterMotor.sensorCollection.quadratureVelocity.toDouble())
		SmartDashboard.putNumber("Elevator encoder position_", talons.masterMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Elevator encoder velocity_", talons.masterMotor.sensorCollection.quadratureVelocity.toDouble())

		SmartDashboard.putNumber("Elevator output current", talons.masterMotor.outputCurrent)
		SmartDashboard.putNumber("Elevator output voltage", talons.masterMotor.motorOutputVoltage)
	}

	/**
	 * @param inches number of inches
	 * @return number of encoder ticks necessary to go that many inches
	 */
	private fun toEncoderTicks(inches: Double): Int =
		Math.round(inches * TICKS_PER_INCH * ELEVATOR_DISTANCE_MULTIPLIER).toInt()

}
