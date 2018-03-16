package org.stormgears.powerup.subsystems.elevatorclimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.powerup.Robot;

public class Climber extends Subsystem {
	private static Climber instance;

	public static Climber getInstance() {
		return instance;
	}

	private ElevatorSharedTalons talons;

	private Climber() {
		talons = Robot.elevatorSharedTalons;
	}

	public static void init() {
		instance = new Climber();
	}

	/**
	 * Moves the motor however many encoder ticks inputted
	 */
	public void climb() {
		// TODO: Implement this better
		int throttleValue = 0;
		talons.getMasterMotor().set(ControlMode.Velocity, throttleValue);
		talons.getSlaveMotor().set(ControlMode.Velocity, throttleValue);
	}

	/**
	 * Stops motor
	 */
	public void stop() {
		talons.getMasterMotor().set(0);
		talons.getSlaveMotor().set(0);
	}

	@Override
	protected void initDefaultCommand() {

	}
}
