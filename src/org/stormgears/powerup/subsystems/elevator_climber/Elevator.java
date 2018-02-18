package org.stormgears.powerup.subsystems.elevator_climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionManager;
import org.stormgears.utils.StormTalon;

import java.util.concurrent.TimeUnit;

// 8192 encoder ticks = 1  rotation of the wheel
public class Elevator extends Subsystem {
	private static Elevator instance;
	public static Elevator getInstance() {
		return instance;
	}

	// TODO: change this later
	private static final int SIDE_SHIFT_TALON_ID = 0;
	private static final double SIDE_SHIFT_P = 0.1;
	private static final double SIDE_SHIFT_I = 0.00001;
	private static final double SIDE_SHIFT_D = 0.5;

	private static final int START = 8; // inches elevator starts off the ground
	private int currentElevatorPosition = START; // current number of inches off the ground

	private static final int TICKS_TO_REVS = 8192; // Number of encoder ticks:Number of Revolutions of the Motor
	private static final double REVS_TO_INCHES = 4.925; // Number of revs/in

	// TODO: Fine Tune Positions (ALL IN INCHES)
	private final int SWITCH_POSITION_1 = 20;
	private final int SWITCH_POSITION_2 = 30;
	private final int SWITCH_POSITION_3 = 43;
	private final int SCALE_POSITION_1 = 52;
	private final int SCALE_POSITION_2 = 64;
	private final int SCALE_POSITION_3 = 78;
	private final int SCALE_POSITION_4 = 92;
	private final int SCALE_POSITION_5 = 106;

	private static final int ELEVATOR_MAX_VELOCITY = 10000;
	private static final int ELEVATOR_MAX_ACCELERATION = 3000;

	private ElevatorSharedTalons talons;
	private MotionMagic motionLeft; // Motion magic object for moving the elevator

	private StormTalon sideShiftTalon;
	private MotionMagic sideShiftMover;
	private int sideShiftPosition = 0;
	public static final int LEFT = -1, CENTER = 0, RIGHT = 1;
	private static final int LEFT_TICKS = -75000, CENTER_TICKS = 0, RIGHT_TICKS = 75000;
	private boolean sideShiftMoving = false;

	/**
	 * Default constructor for the creation of the elevator
	 */
	private Elevator() {
		talons = Robot.elevatorSharedTalons;
		
		sideShiftTalon = new StormTalon(0);

		motionLeft = new MotionMagic(talons.getLeftMotor(), ELEVATOR_MAX_VELOCITY / 2,
			ELEVATOR_MAX_ACCELERATION / 2);
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
	public void moveToPosition(int position) {
		motionLeft.runMotionMagic(toEncoderTicks(position - currentElevatorPosition));
		currentElevatorPosition = position;
	}

	/**
	 * Bring the elevator to the lowest position
	 */
	public void resetElevator() {
		moveToPosition(START);
	}

	/**
	 * @return the current number of inches the elevator is off the ground
	 */
	public int getCurrentElevatorPosition() {
		return currentElevatorPosition;
	}

	/**
	 * Stop all elevator motion
	 */
	public void stopElevator() {
		talons.getLeftMotor().set(0);
		talons.getRightMotor().set(0);
	}

	/**
	 * Move side shift to position
	 */
	public void moveSideShiftToPosition(int position) {
		int newPosition;
		switch (position) {
			case LEFT: newPosition = LEFT_TICKS; break;
			case CENTER: newPosition = CENTER_TICKS; break;
			case RIGHT: newPosition = RIGHT_TICKS; break;
			default: newPosition = CENTER_TICKS; break;
		}

		sideShiftTalon.set(ControlMode.PercentOutput, 0.5);
		System.out.println("current on side shifter: " + sideShiftTalon.getOutputCurrent());
		while (sideShiftTalon.getOutputCurrent() <= 20.0) {
			waitMs(20);
		}
		System.out.println("Side shift current limit reached");
		sideShiftTalon.set(0);
	}

	public boolean isSideShiftMoving() {
		return sideShiftMoving;
	}

	/**
	 * WARNING! This method will hold whatever thread it is called on until the side shift
	 * finishes moving
	 */
	public void waitUntilSideShiftFinishes() {
		while (Math.abs(sideShiftTalon.getSensorCollection().getQuadratureVelocity()) > 10) {
			waitMs(20);
		}

		sideShiftMoving = false;
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
	 * @return number of encoder ticks necessary to go thatmany inches
	 */
	private int toEncoderTicks(double inches) {
		return ((int) Math.round(inches * REVS_TO_INCHES * TICKS_TO_REVS));
	}

	@Override
	protected void initDefaultCommand() {

	}
}
