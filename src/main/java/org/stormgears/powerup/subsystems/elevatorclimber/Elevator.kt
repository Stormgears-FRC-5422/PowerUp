package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

	private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons!!

	private const val TICKS_PER_INCH = 13443  // 8192 / (3 * 13/64)
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // Because the encoder is reversed

	var elevatorZeroed = false

	private val currentPositionTicks: Int
		get() = talons.masterMotor.sensorCollection.quadraturePosition

	private val ZERO_POWER = 0.07 * Math.pow(Robot.config.elevatorStiffness, 1.52)
	private val ZERO_CURRENT_LIMIT = 0.4 * Math.pow(Robot.config.elevatorStiffness, 2.5) //2.5 // 8.0 // 8.7

	// Elevator button positions (inches)
	val SWITCH_POSITIONS = intArrayOf(27, 35, 40) // first one = 22
	val SCALE_POSITIONS = intArrayOf(56, 70, 75, 81, 89) // first one = 56

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

	private suspend fun elevatorAutoMove(position: Int) {
		val lowering: Boolean
		val destinationTicks = toEncoderTicks(position.toDouble())
		val multiplier: Int

		if (abs(destinationTicks - currentPositionTicks) < TICKS_PER_INCH * 2) {
			logger.info("Elevator already within 4 inches of destination; doing nothing")
			return
		}

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
		val basePower = if (lowering) 0.7 else 1.0

		SmartDashboard.putNumber("Desired encoder position", destinationTicks.toDouble())
		SmartDashboard.putBoolean("Elevator lowering", lowering)

		logger.trace("Elevator moving!")
		talons.masterMotor.set(ControlMode.PercentOutput, multiplier * basePower)

		var shouldStop: Boolean
		do {
			delay(10)

			val cpt = currentPositionTicks
			val relDist = abs((cpt.toDouble() - destinationTicks) / destinationTicks)
			val powerMul = 1.0 //relDist.pow(1.0 / 3.0) + 0.15
			val power = if (lowering && cpt > -100000) 0.07 * Math.pow(Robot.config.elevatorStiffness, 2.5) else max(min(basePower * powerMul * multiplier, 1.0), -1.0)
			talons.masterMotor.set(ControlMode.PercentOutput, power)

//			logger.trace("relDist = {}; powerMul = {}; currentPositionTicks = {}; destinationTicks = {}; power = {}", box(relDist), box(powerMul), box(currentPositionTicks), box(destinationTicks), box(basePower * powerMul * multiplier))

			shouldStop = (if (lowering) currentPositionTicks > destinationTicks - 13000
			else currentPositionTicks < destinationTicks + 13000)

//			shouldStop = relDist < 0.05

		} while (!shouldStop)

		logger.trace("{} Elevator has moved to encoder position: {}", box(Timer.getFPGATimestamp()), box(currentPositionTicks))
//		holdElevator()
		talons.masterMotor.set(ControlMode.PercentOutput, -0.06)
	}

	/**
	 * Stop all motion
	 */
	fun stop(): Job {
		return launch("Override slow down", parent = null) {
			talons.masterMotor.set(ControlMode.PercentOutput, -0.06)

//			holdElevator()
		}
	}

	fun launchZeroElevator(): Job {
		this.elevatorJob?.cancel()

		val job = launch("Zero elevator") {
			zeroElevator()
		}

		this.elevatorJob = job
		return job
	}

	suspend fun zeroElevator(zeroEncoder: Boolean = true) {
		talons.masterMotor.set(ControlMode.PercentOutput, ZERO_POWER)

		logger.trace("Zero-ing Elevator. Watch out!")

		var iteration = 0
		while (iteration < 40 || talons.masterMotor.outputCurrent < ZERO_CURRENT_LIMIT) {
			delay(20)
			logger.trace("Current limit: ${talons.masterMotor.outputCurrent}")

			iteration++
		}
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

		if (zeroEncoder) {
			zeroElevatorEncoder()
		}

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
		if (currentPositionTicks < -1190000) {
			talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
//			holdElevator()
		} else {
			talons.masterMotor.set(ControlMode.PercentOutput, -0.9)
		}
	}

	fun moveDownManual() {
//		if (currentPositionTicks > -110000 && elevatorZeroed && useGartnerRate) downPower *= 0.95
//		else downPower = 0.33
		val downPower = if (currentPositionTicks > -100000) 0.07 * Math.pow(Robot.config.elevatorStiffness, 1.75) else 0.4 * Robot.config.elevatorStiffness

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
