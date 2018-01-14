package org.stormgears.powerup;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.stormgears.powerup.subsystems.dsio.DSIO;
import org.stormgears.powerup.subsystems.navigator.Drive;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends IterativeRobot {
	/*
	 * Everybody, _please_ follow the singleton pattern!
	 * Create a private, final instance in your class, and link it here like this
	 * Then, when you want to use it somewhere else, use Robot.<subsystem>.blah
	 * Example: Robot.dsio.
	 */
	public static DSIO dsio = DSIO.getInstance();
	public static Drive drive = Drive.getInstance();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {

	}

	/**
	 * Runs when autonomous mode starts
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * Runs when operator control starts
	 */
	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}

