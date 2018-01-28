package org.stormgears.powerup;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.stormgears.powerup.subsystems.dsio.DSIO;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.powerup.subsystems.navigator.Drive;
import org.stormgears.powerup.subsystems.sensors.Sensors;
import org.stormgears.powerup.subsystems.sensors.vision.Vision;
import org.stormgears.utils.Log4jConfigurationFactory;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends IterativeRobot {
	static {
		ConfigurationFactory.setConfigurationFactory(new Log4jConfigurationFactory());
	}

	/*
	 * Everybody, _please_ follow the singleton pattern!
	 * Create a private, final instance in your class, and link it here like this
	 * Then, when you want to use it somewhere else, use Robot.<subsystem>.blah
	 * Example: Robot.dsio.
	 */
	public static RobotConfiguration config = RobotConfiguration.getInstance();
	public static Sensors sensors;
	public static DSIO dsio = DSIO.getInstance();
	public static Drive drive;
	public Vision v = new Vision();
	private Logger logger = LogManager.getLogger(Robot.class);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		logger.info("{} is running", config.robotName);

		Sensors.init();
		sensors = Sensors.getInstance();

		Drive.init();
		drive = Drive.getInstance();
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
		//REQUIRED TO TEST VISION: v.getVisionCoordinatesFromNetworkTable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (drive != null) {
			if(!sensors.getNavX().isCalibrating()) {
				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
				drive.move();
			}
		} else {
			logger.fatal("Robot.drive is null; that's a problem!");
		}

		sensors.getNavX().debug();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}

