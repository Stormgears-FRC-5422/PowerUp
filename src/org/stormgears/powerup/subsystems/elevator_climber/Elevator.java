package org.stormgears.powerup.subsystems.elevator_climber;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic;

// 8192 encoder ticks = 1  rotation of the wheel
public class Elevator extends Subsystem {
	private static Elevator instance;
	public static Elevator getInstance() {
		return instance;
	}
	private int start = 8; // inches elevator starts off the ground
	private int currentPosition = start; // current number of inches off the ground
	private final int TICKS_TO_REVS = 8192; // Number of encoder ticks:Number of Revolutions of the Motor
	private final double REVS_TO_INCHES = 4.925; // # Revolutions/Inch
	// TODO: Fine Tune Positions (ALL IN INCHES)
	private final int SWITCH_POSITION_1 = 20;
	private final int SWITCH_POSITION_2 = 30;
	private final int SWITCH_POSITION_3 = 43;
	private final int SCALE_POSITION_1 = 52;
	private final int SCALE_POSITION_2 = 64;
	private final int SCALE_POSITION_3 = 78;
	private final int SCALE_POSITION_4 = 92;
	private final int SCALE_POSITION_5 = 106;
	private int maxVel;
	private int maxAccel;
	private ElevatorSharedTalons talons; // Talon object for both talons
	private MotionMagic motionLeft; // Motion magic object for moving the elevator

	/**
	 * Default constructor for the creation of the elevator
	 */
	private Elevator() {
		maxAccel = 3000;
		maxVel = 10000;

		talons = Robot.elevatorSharedTalons;
		motionLeft = new MotionMagic(talons.getLeftMotor(), maxVel/2, maxAccel/2);
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
	 * @param position = encoder ticks of the position where the elevator should move
	 */
	public void moveToPosition(int position) {
		motionLeft.runMotionMagic(toEncoderTicks(position - currentPosition));
		currentPosition = position;
	}

	/**
	 * Bring the elevator to the lowest position
	 */
	public void reset() {
		moveToPosition(start);
	}

	/**
	 * @param inches = number of inches inputted
	 * @return number of encoder ticks necessary to go thatmany inches
	 */
	public int toEncoderTicks (double inches) {
		return  ((int)Math.round(inches * REVS_TO_INCHES * TICKS_TO_REVS));
	}


	/**
	 *
	 * @return the current number of inches the elevator is off the ground
	 */
	public int getCurrentPosition() {return currentPosition; }

	/**
	 * Stop all elevator motion
	 */
	public void stop() {
		talons.getLeftMotor().set(0);
		talons.getRightMotor().set(0);
	}

	@Override
	protected void initDefaultCommand() {

	}
}
