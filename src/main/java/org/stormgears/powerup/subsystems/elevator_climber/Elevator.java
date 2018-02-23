package org.stormgears.powerup.subsystems.elevator_climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.StormTalon;
import org.stormgears.utils.TerminatableSubsystem;

public class Elevator extends TerminatableSubsystem {
	private static Elevator instance;

	public static Elevator getInstance() {
		return instance;
	}

	private static final int TICKS_TO_REVS = 8192; // Number of encoder ticks:Number of Revolutions of the Motor
	private static final double REVS_TO_INCHES = -1; // 4.925; // Number of revs/in

	// PID values for elevator
	private static final double RAISE_P = 0.1;
	private static final double RAISE_I = 0.0001;
	private static final double RAISE_D = 8;
	private static final double LOWER_P = 0.01;
	private static final double LOWER_I = 0.00001;
	private static final double LOWER_D = 1;

	// TODO: Fine Tune Positions (ALL IN INCHES)
	public static final int[] SWITCH_POSITIONS = {20, 30, 43};
	public static final int[] SCALE_POSITIONS = {52, 64, 78, 92, 106};
	private static final int START = 0; // inches elevator starts off the ground

	private int currentElevatorPosition = START; // current number of inches off the ground

	private ElevatorSharedTalons talons;

	// Side shift stuff
	private static final int SIDE_SHIFT_TALON_ID = 7;
	private StormTalon sideShiftTalon;
	private int sideShiftPosition = 0;
	public static final int LEFT = -1, CENTER = 0, RIGHT = 1;
	private static final double SIDE_SHIFT_POWER = 0.7;

	/**
	 * Default constructor for the creation of the elevator
	 */
	private Elevator() {
		talons = Robot.elevatorSharedTalons;

		sideShiftTalon = new StormTalon(SIDE_SHIFT_TALON_ID);
	}

	/**
	 * Standard initialization method
	 * Creates instance of elevator
	 */
	public static void init() {
		instance = new Elevator();
	}

	/**
	 * Move the elevator to a position
	 *
	 * @param position = encoder ticks of the position where the elevator should move
	 */
	public void moveElevatorToPosition(int position) {
		position = toEncoderTicks(position);
		if (position < currentElevatorPosition) {     // Raising elevator
			talons.getMasterMotor().config_kP(0, RAISE_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
			talons.getMasterMotor().config_kI(0, RAISE_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
			talons.getMasterMotor().config_kD(0, RAISE_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
		} else {    // Lowering elevator
			talons.getMasterMotor().config_kP(0, LOWER_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
			talons.getMasterMotor().config_kI(0, LOWER_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
			talons.getMasterMotor().config_kD(0, LOWER_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT);
		}

		System.out.println("Desired position: " + position);
		talons.getMasterMotor().set(ControlMode.Position, position);

		while (isAllowed() && Math.abs(talons.getMasterMotor().getSensorCollection().getQuadratureVelocity()) > 10) {
			waitMs(20);
		}

		System.out.println("Elevator has moved to position: " + position);
		currentElevatorPosition = position;
	}

	/**
	 * Bring the elevator to the lowest position
	 */
	public void resetElevator() {
		moveElevatorToPosition(START);
	}

	/**
	 * @return the current number of inches the elevator is off the ground
	 */
	public int getCurrentElevatorPosition() {
		return currentElevatorPosition;
	}

	/**
	 * Stop all motion
	 */
	public void stop() {
		talons.getMasterMotor().set(ControlMode.PercentOutput, 0);
		sideShiftTalon.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * Move side shift to position
	 */
	public void moveSideShiftToPosition(int position) {
		if (sideShiftPosition == position) return;

		int multiplier = 0;
		if (position == LEFT) {
			multiplier = -1;
		} else if (position == CENTER) {
			if (sideShiftPosition == LEFT) multiplier = 1;
			else if (sideShiftPosition == RIGHT) multiplier = -1;
		} else if (position == RIGHT) {
			multiplier = 1;
		}

		boolean limitSwitchReachedInCenter = false;
		sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier);

		while (isAllowed() && sideShiftTalon.getOutputCurrent() <= 20.0 && !limitSwitchReachedInCenter) {
			if (position == CENTER && sideShiftTalon.getSensorCollection().isRevLimitSwitchClosed()) {
				// The API is reversed, so the FWD port on the breakout board corresponds to isRevLimitSwitchClosed
				// and vice versa
				limitSwitchReachedInCenter = true;
			}
			waitMs(20);
		}
		sideShiftTalon.set(ControlMode.PercentOutput, 0);
		System.out.println("Side shift current limit reached or reached center");

		sideShiftPosition = position;
	}

	public void moveSideShiftOverLeft() {
		if (sideShiftPosition > -1) moveSideShiftToPosition(sideShiftPosition - 1);
	}

	public void moveSideShiftOverRight() {
		if (sideShiftPosition < 1) moveSideShiftToPosition(sideShiftPosition + 1);
	}

	public void moveUpManual() {
		talons.getMasterMotor().set(ControlMode.PercentOutput, -0.33);
	}

	public void moveDownManual() {
		talons.getMasterMotor().set(ControlMode.PercentOutput, 0.33);
	}

	public void moveLeftManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, -0.33);
	}

	public void moveRightManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, 0.33);
	}

	private void waitMs(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param inches number of inches
	 * @return number of encoder ticks necessary to go that many inches
	 */
	private int toEncoderTicks(double inches) {
		return ((int) Math.round(inches * REVS_TO_INCHES * TICKS_TO_REVS));
	}

	@Override
	protected void initDefaultCommand() {

	}
}