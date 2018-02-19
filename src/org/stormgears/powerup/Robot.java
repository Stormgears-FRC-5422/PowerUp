package org.stormgears.powerup;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
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
import org.stormgears.powerup.subsystems.navigator.Position;
import org.stormgears.powerup.subsystems.sensors.Sensors;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.logging.Log4jConfigurationFactory;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends IterativeRobot {
	static {
		ConfigurationFactory.setConfigurationFactory(new Log4jConfigurationFactory());
	}

	private static final Logger logger = LogManager.getLogger(Robot.class);

	public static final ArrayList<RegisteredNotifier> notifierRegistry = new ArrayList<>();

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




		boolean sensorBot = true;

		StormScheduler.init();

		Sensors.init();

		if (sensorBot) {
			sensors = Sensors.getInstance();
			sensors.getNavX().setInitialTheta();
			sensors.getNavX().test();
		//	sensors.getStormNet().test();

				try {
					System.out.println("NavX theta: " + sensors.getNavX().getTheta());
					System.out.println("Lidar distances: " + sensors.getStormNet().getLidarDistance(1) + ", "
						+ sensors.getStormNet().getLidarDistance(2));
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

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

		for (int i = 0; i < driveTalons.getTalons().length; i++)
			driveTalons.getTalons()[i].setInverted(true);
	}

	/**
	 * Runs when autonomous mode starts
	 */
	int minus = 1;
	@Override
	public void autonomousInit() {
		fmsInterface.sendTestData(dsio.choosers.getPlateAssignmentData());
	}

	/**
	 * Runs when operator control starts
	 */
	@Override
	public void teleopInit() {

//		fmsInterface.sendTestData(dsio.choosers.getPlateAssignmentData());
		driveTalons.getTalons()[3].setSensorPhase(true);
//		driveTalons.getTalons()[1].set(ControlMode.Velocity, 1000);
//		driveTalons.getTalons()[2].set(ControlMode.Velocity, 1000);
//		driveTalons.getTalons()[3].set(ControlMode.Velocity, 1000);
//		globalMapping.run();
//		if (drive != null && !sensors.getNavX().isCalibrating()) {
//			Robot.drive.runMotionMagic(60, 0);
//		}
	}

	/**
	 * This function is called periodically during autonomous
	 */

	int i = 0;

	@Override
	public void autonomousPeriodic() {

		for (int i = 0; i < driveTalons.getTalons().length; i++) {
			SmartDashboard.putNumber("Talon " + i, driveTalons.getTalons()[i].getSensorCollection().getQuadraturePosition());
		}

	}

	/**
	 * This function is called periodically during operator control
	 */

	//Naik is dumb
	@Override
	public void teleopPeriodic() {
		StormScheduler.getInstance().run();

		if (drive != null) {
			if (!sensors.getNavX().isCalibrating()) {
				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
				drive.move();

			}
		} else {
			logger.fatal("Robot.drive is null; that's a problem!");
		}

		for (int i = 0; i < driveTalons.getTalons().length; i++) {
			SmartDashboard.putNumber("Talon " + i, driveTalons.getTalons()[i].getSensorCollection().getQuadraturePosition());
			SmartDashboard.putNumber("Talon Vel " + i, driveTalons.getTalons()[i].getSensorCollection().getQuadratureVelocity());
		}
	}

	/**
	 * This function is called periodically during sendTestData mode
	 */
	@Override
	public void testPeriodic() {

	}

	/**
	 * This function is called whenever the robot is disabled.
	 */
	public void disabledInit() {
//		fmsInterface.startPollingForData();

		for (int i = 0; i < driveTalons.getTalons().length; i++) {
			System.out.println(driveTalons.getTalons()[i].getSensorCollection().getQuadraturePosition());
		}

		 i = 0;

		for (RegisteredNotifier rn : notifierRegistry) {
			rn.stop();
		}
	}
}

