package org.stormgears.powerup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.auto.command.AutonomousCommandGroup;
import org.stormgears.powerup.subsystems.dsio.DSIO;
import org.stormgears.powerup.subsystems.elevator_climber.Climber;
import org.stormgears.powerup.subsystems.elevator_climber.Elevator;
import org.stormgears.powerup.subsystems.elevator_climber.ElevatorSharedTalons;
import org.stormgears.powerup.subsystems.field.FieldPositions;
import org.stormgears.powerup.subsystems.field.FmsInterface;
import org.stormgears.powerup.subsystems.gripper.Gripper;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.powerup.subsystems.intake.Intake;
import org.stormgears.powerup.subsystems.navigator.Drive;
import org.stormgears.powerup.subsystems.navigator.DriveTalons;
import org.stormgears.powerup.subsystems.navigator.GlobalMapping;
import org.stormgears.powerup.subsystems.sensors.Sensors;
import org.stormgears.utils.BaseStormgearsRobot;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.logging.StormyLog;

import java.util.ArrayList;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends BaseStormgearsRobot {
	static {
		StormyLog.init();
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
	public static Timer timer = new Timer();
	public static DSIO dsio;
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

	private Command autonomousCommand = null;

	private FieldPositions.Alliance selectedAlliance;
	private FieldPositions.StartingSpots selectedStartSpot;
	private FieldPositions.PlacementSpot selectedPlacementSpot;
	private FieldPositions.LeftRight selectedOwnSwitchPlateAssignment;
	private FieldPositions.LeftRight selectedScalePlateAssignment;
	private FieldPositions.LeftRight selectedOpponentSwitchPlateAssignmentChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		logger.info("{} is running", config.robotName);

		StormScheduler.init();

//		Sensors.init();
//		sensors = Sensors.getInstance();
//
//		GlobalMapping.init();
//		globalMapping = GlobalMapping.getInstance();
//
		DriveTalons.init();
		driveTalons = DriveTalons.getInstance();

		Drive.init();
		drive = Drive.getInstance();
//
//		Intake.init();
//		intake = Intake.getInstance();
//
//		ElevatorSharedTalons.init();
//		elevatorSharedTalons = ElevatorSharedTalons.getInstance();
//
//		Elevator.init();
//		elevator = Elevator.getInstance();
//
//		Climber.init();
//		climber = Climber.getInstance();

		Gripper.init();
		gripper = Gripper.getInstance();
	}

	/**
	 * Runs when autonomous mode starts
	 */
	@Override
	public void autonomousInit() {
		logger.trace("autonomous init");

		if (dsio == null) {
			dsio = DSIO.INSTANCE;
		}

		// Get all the selected autonomous command properties for this run
		getSelectedAutonomousCommand();

		//if any residual commands exist, cancel them
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

		logger.trace("creating autonomous command group");

		autonomousCommand = new AutonomousCommandGroup(selectedAlliance,
			selectedStartSpot,
			selectedPlacementSpot,
			selectedOwnSwitchPlateAssignment,
			selectedScalePlateAssignment,
			selectedOpponentSwitchPlateAssignmentChooser);

		//execute autonomous command
		logger.trace("starting the autonomous command");
		autonomousCommand.start();
	}

	/**
	 * Runs once right at the start of autonomousPeriodic
	 */
	@Override
	public void afterAutonomousInit() {
//		if (drive != null) {
//			if (!sensors.getNavX().isCalibrating()) {
//				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
//				drive.moveStraight(120, 0);
//			}
//		}
	}

	/**
	 * Runs when operator control starts
	 */
	@Override
	public void teleopInit() {
		logger.trace("teleop init");
//		drive.setVelocityPID();

		if (dsio == null) {
			dsio = DSIO.INSTANCE;
		}
	}

	/**
	 * Runs once right at the start of teleopPeriodic
	 */
	@Override
	public void afterTeleopInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		super.autonomousPeriodic();
		
//		logger.info("Current timer value: " + timer.get());
		
//		if (autonomousCommand != null) {
		StormScheduler.getInstance().run();
//		}

	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		super.teleopPeriodic();

		StormScheduler.getInstance().run();

//		if (drive != null) {
//			if (!sensors.getNavX().isCalibrating()) {
//				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
//				drive.move();
//			}
//		} else {
//			logger.fatal("Robot.drive is null; that's a problem!");
//		}
	}

	/**
	 * This function is called periodically during sendTestData mode
	 */
	@Override
	public void disabledInit() {
		logger.trace("disabled init");
		super.disabledInit();

//		fmsInterface.startPollingForData();

		for (RegisteredNotifier rn : notifierRegistry) {
			rn.stop();
		}
	}

	private void getSelectedAutonomousCommand() {
		fmsInterface.sendTestData(dsio.getChoosers().getPlateAssignmentData());
		selectedAlliance = dsio.getChoosers().getAlliance();
		selectedStartSpot = dsio.getChoosers().getStartingSpot();
		selectedPlacementSpot = dsio.getChoosers().getPlacementSpot();
		selectedScalePlateAssignment = dsio.getChoosers().getScalePlateAssignmentChooser();
		selectedOwnSwitchPlateAssignment = dsio.getChoosers().getOwnSwitchPlateAssignmentChooser();
		selectedOpponentSwitchPlateAssignmentChooser = dsio.getChoosers().getOpponentSwitchPlateAssignmentChooser();

		logger.info("Selected Alliance: " + selectedAlliance.toString());
		logger.info("Selected Starting Spot: " + selectedStartSpot.toString());
		logger.info("Selected Placement Spot: " + selectedPlacementSpot.toString());
		logger.info("Selected Scale Plate Assignment: " + selectedScalePlateAssignment.toString());
		logger.info("Selected Own Switch Plate Assignment: " + selectedOwnSwitchPlateAssignment.toString());
		logger.info("Selected Opponent Switch Plate Assignment: " + selectedOpponentSwitchPlateAssignmentChooser.toString());
	}
}

