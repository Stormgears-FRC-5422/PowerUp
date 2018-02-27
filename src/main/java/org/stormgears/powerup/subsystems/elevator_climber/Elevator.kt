package org.stormgears.powerup.subsystems.elevator_climber

import com.ctre.phoenix.motorcontrol.ControlMode
import org.stormgears.powerup.Robot
import org.stormgears.utils.StormTalon
import org.stormgears.utils.concurrency.TerminatableSubsystem
import org.stormgears.utils.concurrency.launch

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminatableSubsystem() {
    private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons
	private val sideShiftTalon: StormTalon
    private var sideShiftPosition = 0

	private val TICKS_TO_REVS = 8192 // Number of encoder ticks:Number of Revolutions of the Motor
	private val REVS_TO_INCHES = -1.0 // 4.925; // Number of revs/in

	// PID values for elevator
	private val RAISE_P = 0.1
	private val RAISE_I = 0.0001
	private val RAISE_D = 8.0
	private val LOWER_P = 0.01
	private val LOWER_I = 0.00001
	private val LOWER_D = 1.0

	// TODO: Fine Tune Positions (ALL IN INCHES)
	val SWITCH_POSITIONS = intArrayOf(20, 30, 43)
	val SCALE_POSITIONS = intArrayOf(52, 64, 78, 92, 106)
	private val START = 0 // inches elevator starts off the ground

	// Side shift stuff
	private val SIDE_SHIFT_TALON_ID = 10
	val LEFT = -1
	val CENTER = 0
	val RIGHT = 1
	private val SIDE_SHIFT_POWER = 0.7

	/**
	 * @return the current number of encoder ticks the elevator is above the base
	 */
	var currentElevatorPosition = START
		private set

    init {

		sideShiftTalon = StormTalon(SIDE_SHIFT_TALON_ID)
    }

    /**
     * Move the elevator to a position
     *
     * @param position = encoder ticks of the position where the elevator should move
     */
    fun moveElevatorToPosition(position: Int) {
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

        println("Desired encoder position: " + positionTicks)
        talons.masterMotor.set(ControlMode.Position, position.toDouble())

		// Launch a coroutine that waits til the elevator finishes
		launchRegistered("Elevator Auto Move") {
			while (Math.abs(talons.masterMotor.sensorCollection.quadratureVelocity) > 10) {

			}

			println("Elevator has moved to encoder position: " + positionTicks)
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
        //		talons.getMasterMotor().set(ControlMode.PercentOutput, 0);
        sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
    }

    /**
     * Move side shift to position
     */
    private fun moveSideShiftToPosition(position: Int) {
        if (sideShiftPosition == position) return

        var multiplier = 0
        if (position == LEFT) {
            multiplier = -1
        } else if (position == CENTER) {
            if (sideShiftPosition == LEFT)
                multiplier = 1
            else if (sideShiftPosition == RIGHT) multiplier = -1
        } else if (position == RIGHT) {
            multiplier = 1
        }

        var limitSwitchReachedInCenter = false
        sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier)

		// Coroutines again!
		launchRegistered("Side Shift Auto Move") {
			while (sideShiftTalon.outputCurrent <= 20.0 && !limitSwitchReachedInCenter) {
				if (position == CENTER && sideShiftTalon.sensorCollection.isRevLimitSwitchClosed) {
					// The API is reversed, so the FWD port on the breakout board corresponds to isRevLimitSwitchClosed
					// and vice versa
					limitSwitchReachedInCenter = true
				}
			}
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
			println("Side shift current limit reached or reached center")

			sideShiftPosition = position
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

    private fun waitMs(ms: Int) {
        try {
            Thread.sleep(ms.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * @param inches number of inches
     * @return number of encoder ticks necessary to go that many inches
     */
    private fun toEncoderTicks(inches: Double): Int =
		Math.round(inches * REVS_TO_INCHES * TICKS_TO_REVS.toDouble()).toInt()


    override fun initDefaultCommand() {

    }
}
