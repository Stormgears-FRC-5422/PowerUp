package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	var useGartnerRate = true
	private val logger = LogManager.getLogger(this::class.java)

	private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons!!
	val sideShiftTalon: ITalon
	private var sideShiftPosition = 0

	private const val TICKS_PER_INCH = 13000
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // Because the encoder is reversed
	private const val INTAKE_HEIGHT = -200000 // Ticks

	var elevatorZeroed = false

	private val currentPositionTicks: Int
		get() = talons.masterMotor.sensorCollection.quadraturePosition

	private const val ZERO_POWER = 0.2
	private const val ZERO_CURRENT_LIMIT = 8.0 // 8.7

	// Elevator button positions (inches)
	val SWITCH_POSITIONS = intArrayOf(22, 37, 40) // first one = 22
	val SCALE_POSITIONS = intArrayOf(56, 70, 81, 90, 91) // first one = 56

	// Side shift stuff
	private val SIDE_SHIFT_TALON_ID = Robot.config.sideshiftTalonId
	const val LEFT = -1
	const val CENTER = 0
	const val RIGHT = 1
	private const val SIDE_SHIFT_POWER = 1.0
	private const val SLOW_DOWN = -0.05
	private const val LEFT_TICKS = 170000
	private const val CENTER_TICKS = 0
	private const val RIGHT_TICKS = -170000

	// Jobs
	private var sideShiftJob: Job? = null
	private var elevatorJob: Job? = null

	init {
		sideShiftTalon = createTalon(SIDE_SHIFT_TALON_ID);
		sideShiftTalon.inverted = true
		sideShiftTalon.sensorCollection.setQuadraturePosition(0, 10); //set enc pos to 0

		if (sideShiftTalon.dummy) {
			logger.warn("Requires physical talon, disabling Elevator!")
			this.disabled = true
		}
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

		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
		lowering = if (destinationTicks < currentPositionTicks) {     // Raising elevator
			logger.info("Using raise elevator PID values")
			talons.masterMotor.config_kP(0, Robot.config.elevatorRaiseP, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, Robot.config.elevatorRaiseI, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, Robot.config.elevatorRaiseD, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			false
		} else {    // Lowering elevator
			logger.info("Using lower elevator PID values")
			talons.masterMotor.config_kP(0, Robot.config.elevatorLowerP, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, Robot.config.elevatorLowerI, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, Robot.config.elevatorLowerD, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			true
		}
		talons.masterMotor.config_kF(0, 0.0, ElevatorSharedTalons.TALON_FPID_TIMEOUT)

		SmartDashboard.putNumber("Desired encoder position", destinationTicks.toDouble())
		SmartDashboard.putBoolean("Elevator lowering", lowering)

		var openedGripper = false
		var shouldStop = false

		logger.trace("Elevator moving!")
		talons.masterMotor.set(ControlMode.Position, destinationTicks.toDouble())

		// Wait until elevator finishes
		while (!shouldStop) {
			// If within 10000 ticks (0.75 in) of destination, stop
			shouldStop = if (lowering) currentPositionTicks > destinationTicks - 10000
			else currentPositionTicks < destinationTicks + 10000

//			if (lowering && Intake.isUp && currentPositionTicks > INTAKE_HEIGHT) {
//				holdElevator()
//				break
//			} else if (lowering && !Intake.isUp && !openedGripper) {
//				Gripper.openGripper()
//				openedGripper = true
//			}
//
			delay(20)
		}
		logger.trace("Elevator has moved to encoder position: {}", currentPositionTicks)
		holdElevator()    // Replace this with talons.masterMotor.set(ControlMode.Position, currentPositionTicks.toDouble()) if it doesn't work well
	}

	private var overrodeSide = false

	/**
	 * Stop all motion
	 */
	fun stop(): Job {
		return launch("Override slow down", parent = null) {
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)

			if (!overrodeSide) {
				talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

//				holdElevator()
			}
		}
	}

	/**
	 * Move side shift to position
	 */
	fun moveSideShiftToPosition(position: Int): Job {
		if (sideShiftJob != null) {
			sideShiftJob!!.cancel()
			logger.trace("Canceled side shift job")
		}

		// Coroutines again!
		val sideShiftJob = launch("Side Shift Auto Move") {
			moveSideShiftToPositionSuspendPID(position)
		}

		this.sideShiftJob = sideShiftJob
		return sideShiftJob
	}


	private suspend fun moveSideShiftToPositionSuspendPID(position: Int) {
		logger.trace("SIDE SHIFT PID CALLED")

		if (sideShiftPosition != position) {
			val destinationTicks = when (position) {
				LEFT -> LEFT_TICKS
				CENTER -> CENTER_TICKS
				RIGHT -> RIGHT_TICKS
				else -> 0
			}

			logger.trace("Moving side shift to position: {}", box(position))
			if (position == CENTER) logger.trace("Moving to center.")

			sideShiftTalon.config_kP(0, 0.0275, 10)
			sideShiftTalon.config_kI(0, 0.0, 10)
			sideShiftTalon.config_kD(0, 0.0, 10)
			sideShiftTalon.config_kF(0, 0.0, 10)

			sideShiftTalon.set(ControlMode.Position, destinationTicks.toDouble())

			while (Math.abs(destinationTicks - sideShiftTalon.sensorCollection.quadraturePosition) > 2000) {
				delay(20)
			}

			sideShiftPosition = position
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

	fun zeroSideShift() {
		logger.trace("Zero sideshift")
		sideShiftTalon.sensorCollection.setQuadraturePosition(0, 10)
	}

	fun turnOffElevator() {
		logger.trace("Turning off elevator")
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
	}

	private suspend fun holdElevator() {
		logger.trace("Holding elevator")
		delay(300)

		// Hold current elevator position
		talons.masterMotor.set(ControlMode.Position, currentPositionTicks.toDouble())
	}

	fun moveSideShiftOverLeft(): Job? {
		logger.trace("Moving sideshift left")
		return if (sideShiftPosition > -1) moveSideShiftToPosition(sideShiftPosition - 1) else null
	}

	fun moveSideShiftOverRight(): Job? {
		logger.trace("Moving sideshift right")
		return if (sideShiftPosition < 1) moveSideShiftToPosition(sideShiftPosition + 1) else null
	}

	fun moveUpManual() {
		logger.trace("Move up manual")
		talons.masterMotor.set(ControlMode.PercentOutput, -1.0)
		overrodeSide = false
	}

	private var downPower = 0.33
	fun moveDownManual() {
		overrodeSide = false

//		if (Intake.isUp && talons.masterMotor.sensorCollection.quadraturePosition > INTAKE_HEIGHT) return
//		if (currentPositionTicks > -110000 && elevatorZeroed && useGartnerRate) downPower *= 0.95
//		else downPower = 0.33

		logger.trace("downPower = {}", downPower)

		talons.masterMotor.set(ControlMode.PercentOutput, downPower)
	}

	fun moveLeftManual() {
		logger.trace("Move left manual")
		sideShiftTalon.set(ControlMode.PercentOutput, 0.75)
		overrodeSide = true
	}

	fun moveRightManual() {
		logger.trace("Move right manual")
		sideShiftTalon.set(ControlMode.PercentOutput, -0.75)
		overrodeSide = true
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
