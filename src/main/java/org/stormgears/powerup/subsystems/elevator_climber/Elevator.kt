package org.stormgears.powerup.subsystems.elevator_climber

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.TalonIds
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminableSubsystem

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

    private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons
	private val sideShiftTalon: StormTalon
	private var sideShiftPosition = 0

	private const val TICKS_PER_INCH = 13000
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // Because the encoder is reversed

	var elevatorZeroed = false
		private set

	// TODO: Change these to real values
	private const val ZERO_POWER = 0.3
	private const val ZERO_CURRENT_LIMIT = 10.0

	// PID values for elevator
	private const val RAISE_P = 0.088
	private const val RAISE_I = 0.00000076
	private const val RAISE_D = 3.2
	private const val LOWER_P = 0.088
	private const val LOWER_I = 0.00000076
	private const val LOWER_D = 3.2

	// Elevator button positions (inches)
	val SWITCH_POSITIONS = intArrayOf(22, 37, 40)
	val SCALE_POSITIONS = intArrayOf(56, 70, 81, 90, 91)

	// Side shift stuff
	private const val SIDE_SHIFT_TALON_ID = TalonIds.SIDESHIFT
	const val LEFT = -1
	const val CENTER = 0
	const val RIGHT = 1
	private const val SIDE_SHIFT_POWER = 0.6
	private const val SLOW_DOWN = -0.05
//	private const val FULL_LEFT_TICKS = 181000
//	private const val FULL_RIGHT_TICKS = -181000


	// Jobs
	private var sideShiftJob: Job? = null
	private var elevatorJob: Job? = null

	/**
	 * @return the current number of encoder ticks the elevator is above the base
	 */
	var currentElevatorPosition = 0
		private set

    init {
		sideShiftTalon = StormTalon(SIDE_SHIFT_TALON_ID)
		sideShiftTalon.inverted = true
    }

	private fun updatePosition() {
		currentElevatorPosition = talons.masterMotor.sensorCollection.quadraturePosition
	}

    /**
     * Move the elevator to a position
     *
     * @param position = encoder ticks of the position where the elevator should move
     */
    fun moveElevatorToPosition(position: Int) {
		if (elevatorJob != null) {
			elevatorJob!!.cancel()
			logger.trace("Cancelled elevator job")
		}

		elevatorJob = launch("Elevator Auto Move") {
			elevatorAutoMove(position)
		}
	}

	suspend fun elevatorAutoMove(position: Int) {
		val positionTicks = toEncoderTicks(position.toDouble())
		if (positionTicks < currentElevatorPosition) {     // Raising elevator
			talons.masterMotor.config_kP(0, RAISE_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, RAISE_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, RAISE_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
		} else {    // Lowering elevator
			talons.masterMotor.config_kP(0, LOWER_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, LOWER_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, LOWER_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
		}
		talons.masterMotor.config_kF(0, 0.0, ElevatorSharedTalons.TALON_FPID_TIMEOUT)

		logger.trace("Desired encoder position: {}", box(positionTicks))
		talons.masterMotor.set(ControlMode.Position, positionTicks.toDouble())

		// wait until elevator finishes
		while (Math.abs(positionTicks - talons.masterMotor.sensorCollection.quadraturePosition) > 50) {
			logger.trace(talons.masterMotor.sensorCollection.quadraturePosition)
			delay(20)
		}

		logger.trace("Elevator has moved to encoder position: {}", box(positionTicks))
		currentElevatorPosition = positionTicks
	}

	private var overrodeSide = false
    /**
     * Stop all motion
     */
    fun stop() {
		launch("Override slow down", parent = null) {
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)

			if (!overrodeSide) {
				talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

				delay(300)
				updatePosition()

				// Hold current elevator position
				talons.masterMotor.set(ControlMode.Position, currentElevatorPosition.toDouble())
			}
		}
    }

    /**
     * Move side shift to position
     */
	fun moveSideShiftToPosition(position: Int) {
		if (sideShiftJob != null) {
			sideShiftJob!!.cancel()
			logger.trace("Canceled side shift job")
		}

		// Coroutines again!
		sideShiftJob = launch("Side Shift Auto Move") {
			moveSideShift(position)
		}
	}

	suspend fun moveSideShift(position: Int) {
		if (sideShiftPosition != position) {
			var multiplier = 0
			if (position == LEFT) {
				multiplier = -1
			} else if (position == CENTER) {
				if (sideShiftPosition == LEFT) multiplier = 1
				else if (sideShiftPosition == RIGHT) multiplier = -1
			} else if (position == RIGHT) {
				multiplier = 1
			}

			var reachedCenter = false
			logger.trace("Moving side shift to position: {}", box(position))
			if (position == CENTER) logger.trace("Moving to center.")
			sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier)

			while (sideShiftTalon.outputCurrent <= 17.0 && !reachedCenter) {
				logger.trace(box(sideShiftTalon.outputCurrent))
				if (position == CENTER) {
					// The API is reversed, so the FWD port on the breakout board corresponds to isRevLimitSwitchClosed
					// and vice versa
					if (Math.abs(sideShiftTalon.sensorCollection.quadraturePosition) < 30000) {
						sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier + SLOW_DOWN * multiplier)
					}

					reachedCenter = sideShiftTalon.sensorCollection.isRevLimitSwitchClosed ||
						Math.abs(sideShiftTalon.sensorCollection.quadraturePosition) < 10000

					logger.trace(box(sideShiftTalon.sensorCollection.quadraturePosition))
				}

				delay(20)
			}
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
			logger.trace("Side shift current limit reached or reached center")

			sideShiftPosition = position
		}
	}

	suspend fun zeroElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, ZERO_POWER)
		println("Zero-ing Elevator. Watch out!")

		var iteration = 0
		while (talons.masterMotor.outputCurrent < ZERO_CURRENT_LIMIT || ++iteration < 40) delay(20)
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

		talons.masterMotor.sensorCollection.setQuadraturePosition(0, 10)
		elevatorZeroed = true
		println("Elevator zeroed")
	}

	fun zeroSideShift() {
		sideShiftTalon.sensorCollection.setQuadraturePosition(0, 10)
	}

	fun turnOffElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
	}

    fun moveSideShiftOverLeft() {
        if (sideShiftPosition > -1) moveSideShiftToPosition(sideShiftPosition - 1)
    }

    fun moveSideShiftOverRight() {
        if (sideShiftPosition < 1) moveSideShiftToPosition(sideShiftPosition + 1)
    }

    fun moveUpManual() {
		talons.masterMotor.set(ControlMode.PercentOutput, -0.5)
		overrodeSide = false
    }

    fun moveDownManual() {
        talons.masterMotor.set(ControlMode.PercentOutput, 0.33)
		overrodeSide = false
	}

    fun moveLeftManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, -0.5)
		overrodeSide = true
	}

    fun moveRightManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, 0.5)
		overrodeSide = true
	}

    /**
     * @param inches number of inches
     * @return number of encoder ticks necessary to go that many inches
     */
	private fun toEncoderTicks(inches: Double): Int =
		Math.round(inches * TICKS_PER_INCH * ELEVATOR_DISTANCE_MULTIPLIER).toInt()
}
