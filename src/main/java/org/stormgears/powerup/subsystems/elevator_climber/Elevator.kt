package org.stormgears.powerup.subsystems.elevator_climber

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminableSubsystem

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

    private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons
	val sideShiftTalon: StormTalon
	private var sideShiftPosition = 0

	private const val REVS_TO_TICKS = 8192 // Number of encoder ticks:Number of Revolutions of the Motor
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // 4.925; // Number of revs/in

	// PID values for elevator
	private const val RAISE_P = 0.1
	private const val RAISE_I = 0.0001
	private const val RAISE_D = 8.0
	private const val LOWER_P = 0.01
	private const val LOWER_I = 0.00001
	private const val LOWER_D = 1.0

	// TODO: Fine Tune Positions (ALL IN INCHES)
	val SWITCH_POSITIONS = intArrayOf(20, 30, 43)
	val SCALE_POSITIONS = intArrayOf(52, 64, 78, 92, 106)
	private const val START = 0 // inches elevator starts off the ground

	// Side shift stuff
	private const val SIDE_SHIFT_TALON_ID = 5
	const val LEFT = -1
	const val CENTER = 0
	const val RIGHT = 1
	private const val SIDE_SHIFT_POWER = 0.4
	private const val FULL_LEFT_TICKS = 181000
	private const val FULL_RIGHT_TICKS = -181000


	// Jobs
	private var sideShiftJob: Job? = null
	private var elevatorJob: Job? = null

	/**
	 * @return the current number of encoder ticks the elevator is above the base
	 */
	var currentElevatorPosition = START
		private set

    init {
		sideShiftTalon = StormTalon(SIDE_SHIFT_TALON_ID)
		sideShiftTalon.inverted = true
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
			val positionTicks = toEncoderTicks(position.toDouble())
			if (position < currentElevatorPosition) {     // Raising elevator
				talons.masterMotor.config_kP(0, RAISE_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
				talons.masterMotor.config_kI(0, RAISE_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
				talons.masterMotor.config_kD(0, RAISE_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			} else {    // Lowering elevator
				talons.masterMotor.config_kP(0, LOWER_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
				talons.masterMotor.config_kI(0, LOWER_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
				talons.masterMotor.config_kD(0, LOWER_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			}
			talons.masterMotor.config_kF(0, 0.0, ElevatorSharedTalons.TALON_FPID_TIMEOUT);

			logger.trace("Desired encoder position: {}", box(positionTicks))
			talons.masterMotor.set(ControlMode.Position, positionTicks)

			// wait until elevator finishes
			while (Math.abs(position.toDouble() - talons.masterMotor.sensorCollection.quadraturePosition) > 50) {
				logger.trace(talons.masterMotor.sensorCollection.quadraturePosition)
				delay(20)
			}

			logger.trace("Elevator has moved to encoder position: {}", box(positionTicks))
			currentElevatorPosition = position
		}
	}


    /**
     * Bring the elevator to the lowest position
     */
    fun resetElevator() {
        moveElevatorToPosition(START)
    }

    /**
     * Stop all motion
     */
    fun stop() {
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
        sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
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

				while (sideShiftTalon.outputCurrent <= 15.0 && !reachedCenter) {
					logger.trace(box(sideShiftTalon.outputCurrent))
					if (position == CENTER) {
						// The API is reversed, so the FWD port on the breakout board corresponds to isRevLimitSwitchClosed
						// and vice versa
						reachedCenter = sideShiftTalon.sensorCollection.isRevLimitSwitchClosed ||
							Math.abs(sideShiftTalon.sensorCollection.quadraturePosition) < 25000
						logger.trace(box(sideShiftTalon.sensorCollection.quadraturePosition))
					}

					delay(20)
				}
				sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
				logger.trace("Side shift current limit reached or reached center")

				sideShiftPosition = position
			}
		}
	}


    fun moveSideShiftOverLeft() {
        if (sideShiftPosition > -1) moveSideShiftToPosition(sideShiftPosition - 1)
    }

    fun moveSideShiftOverRight() {
        if (sideShiftPosition < 1) moveSideShiftToPosition(sideShiftPosition + 1)
    }

    fun moveUpManual() {
        talons.masterMotor.set(ControlMode.PercentOutput, -0.33)
    }

    fun moveDownManual() {
        talons.masterMotor.set(ControlMode.PercentOutput, 0.33)
    }

    fun moveLeftManual() {
        sideShiftTalon.set(ControlMode.PercentOutput, -0.33)
    }

    fun moveRightManual() {
        sideShiftTalon.set(ControlMode.PercentOutput, 0.33)
    }

    /**
     * @param inches number of inches
     * @return number of encoder ticks necessary to go that many inches
     */
	private fun toEncoderTicks(inches: Double): Double =
		inches * ELEVATOR_DISTANCE_MULTIPLIER * REVS_TO_TICKS.toDouble()


    override fun initDefaultCommand() {

    }
}
