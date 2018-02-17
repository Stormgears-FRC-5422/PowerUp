package org.stormgears.powerup;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.stormgears.powerup.subsystems.dsio.DSIO;
import org.stormgears.powerup.subsystems.elevator_climber.Climber;
import org.stormgears.powerup.subsystems.elevator_climber.Elevator;
import org.stormgears.powerup.subsystems.elevator_climber.ElevatorSharedTalons;
import org.stormgears.powerup.subsystems.field.FmsInterface;
import org.stormgears.powerup.subsystems.gripper.Gripper;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.powerup.subsystems.intake.Intake;
import org.stormgears.powerup.subsystems.navigator.Drive;
import org.stormgears.powerup.subsystems.navigator.DriveTalons;
import org.stormgears.powerup.subsystems.navigator.GlobalMapping;
import org.stormgears.powerup.subsystems.sensors.Sensors;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.logging.Log4jConfigurationFactory;

import java.util.ArrayList;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends IterativeRobot {
	static {
		ConfigurationFactory.setConfigurationFactory(new Log4jConfigurationFactory());
	}

	private static final Logger logger = LogManager.getLogger(Robot.class);

	public static ArrayList<RegisteredNotifier> notifierRegistry = new ArrayList<>();

	/*
	 * Everybody, _please_ follow the singleton pattern!
	 * Create a private, final instance in your class, and link it here like this
	 * Then, when you want to use it somewhere else, use Robot.<subsystem>.blah
	 * Example: Robot.dsio.
	 */
	public static RobotConfiguration config = RobotConfiguration.getInstance();
	public static DSIO dsio = DSIO.getInstance();
	public static FmsInterface fmsInterface = FmsInterface.getInstance();
	public static Sensors sensors;
	public static GlobalMapping globalMapping;
	public static DriveTalons driveTalons;
	public static Drive drive;
	public static Intake intake;
	public static ElevatorSharedTalons elevatorSharedTalons;
	public static Elevator elevator;
	public static Climber climber;
	public static Gripper gripper;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		logger.info("{} is running", config.robotName);

		StormScheduler.init();

		Sensors.init();
		sensors = Sensors.getInstance();

		DriveTalons.init();
		driveTalons = DriveTalons.getInstance();

		Drive.init();
		drive = Drive.getInstance();

		Intake.init();
		intake = Intake.getInstance();

		ElevatorSharedTalons.init();
		elevatorSharedTalons = ElevatorSharedTalons.getInstance();

		Elevator.init();
		elevator = Elevator.getInstance();

		Climber.init();
		climber = Climber.getInstance();

		//GlobalMapping.init();
		//globalMapping = GlobalMapping.getInstance();

		Gripper.init();
		gripper = Gripper.getInstance();
	}

	/**
	 * Runs when autonomous mode starts
	 */
	@Override
	public void autonomousInit() {
		fmsInterface.sendTestData(dsio.choosers.getPlateAssignmentData());
		SmartDashboard.putNumber("Gripper Close Current", 0.0);

	}

	/**
	 * Runs when operator control starts
	 */
	@Override
	public void teleopInit() {
//		fmsInterface.sendTestData(dsio.choosers.getPlateAssignmentData());

//		globalMapping.run();
//		if (drive != null && !sensors.getNavX().isCalibrating()) {
//			Robot.drive.runMotionMagic(60, 0);
//		}
//		Robot.drive.enableMotionMagic(60, (2 / 3) * Math.PI);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//Robot.gripper.closeGripper();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int i = 0;

	@Override
	public void teleopPeriodic() {

		StormScheduler.getInstance().run();

		if (drive != null) {
			if (!sensors.getNavX().isCalibrating()) {
				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
				if (i == 0) {
					Robot.drive.enableMotionMagic(60, 0);
					i++;
				}
			}
		} else {
			logger.fatal("Robot.drive is null; that's a problem!");
		}

		SmartDashboard.putNumber("Talon 0", driveTalons.getTalons()[0].getSensorCollection().getQuadratureVelocity());
		SmartDashboard.putNumber("Talon 1", driveTalons.getTalons()[1].getSensorCollection().getQuadratureVelocity());
		SmartDashboard.putNumber("Talon 2", driveTalons.getTalons()[2].getSensorCollection().getQuadratureVelocity());
		SmartDashboard.putNumber("Talon 3", driveTalons.getTalons()[3].getSensorCollection().getQuadratureVelocity());
//		sensors.getNavX().debug();



	}

	/**
	 * This function is called periodically during sendTestData mode
	 */

	public void testInit(){
		SmartDashboard.putNumber("Gripper Open Current", 0.0);

	}

	@Override
	public void testPeriodic() {

		//Robot.gripper.openGripper();

	}

	/**
	 * This function is called whenever the robot is disabled.
	 */
	public void disabledInit() {
//		fmsInterface.startPollingForData();

		for (RegisteredNotifier rn : notifierRegistry) {
			rn.stop();
		}
	}
}

