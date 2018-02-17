package org.stormgears.powerup.subsystems.elevator_climber;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.powerup.Robot;

public class Elevator extends Subsystem {
	private static Elevator instance;

	public static Elevator getInstance() {
		return instance;
	}

	private int currentPosition;
	private ElevatorSharedTalons talons;

	private Elevator() {
		currentPosition = 0;
		talons = Robot.elevatorSharedTalons;
	}

	public static void init() {
		instance = new Elevator();
	}

	public void moveToPosition(int position) {
		if (position > currentPosition) {
			// go up
		} else if (position < currentPosition) {
			// go down
		} else {
			// stay
		}
	}

	public void reset() {
		// elevator_climber goes to position 0
	}


	public void stop() {
		talons.getLeftMotor().set(0);
		talons.getRightMotor().set(0);
	}

	@Override
	protected void initDefaultCommand() {

	}
}
