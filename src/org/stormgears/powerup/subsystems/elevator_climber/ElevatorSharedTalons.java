package org.stormgears.powerup.subsystems.elevator_climber;

import org.stormgears.utils.StormTalon;

public class ElevatorSharedTalons {
	private static ElevatorSharedTalons instance;
	public static ElevatorSharedTalons getInstance() {
		return instance;
	}

	// TODO: Set these to correct elevator_climber ids
	private static final int LEFT_MOTOR_TALON_ID = 0;
	private static final int RIGHT_MOTOR_TALON_ID = 0;

	// TODO: Rename these if needed
	private StormTalon leftMotor;
	private StormTalon rightMotor;

	private ElevatorSharedTalons(int leftMotorId, int rightMotorId) {
		// TODO: Correctly setup talons
//		leftMotor = new StormTalon(leftMotorId);
//		rightMotor = new StormTalon(rightMotorId);
	}

	public static void init() {
		instance = new ElevatorSharedTalons(LEFT_MOTOR_TALON_ID, RIGHT_MOTOR_TALON_ID);
	}

	StormTalon getLeftMotor() {
		return leftMotor;
	}

	StormTalon getRightMotor() {
		return rightMotor;
	}
}
