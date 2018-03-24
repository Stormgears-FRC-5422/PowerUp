package org.stormgears.powerup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.stormgears.powerup.auto.command.AutonomousCommandGroup;
import org.stormgears.powerup.subsystems.dsio.DSIO;
import org.stormgears.powerup.subsystems.elevatorclimber.Climber;
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator;
import org.stormgears.powerup.subsystems.elevatorclimber.ElevatorSharedTalons;
import org.stormgears.powerup.subsystems.field.FieldPositions;
import org.stormgears.powerup.subsystems.field.FmsInterface;
import org.stormgears.powerup.subsystems.gripper.Gripper;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.powerup.subsystems.intake.Intake;
import org.stormgears.powerup.subsystems.navigator.Drive;
import org.stormgears.powerup.subsystems.navigator.DriveTalons;
import org.stormgears.powerup.subsystems.navigator.TalonDebugger;
import org.stormgears.powerup.subsystems.navigator.TalonDebuggerKt;
import org.stormgears.powerup.subsystems.sensors.Sensors;
import org.stormgears.utils.*;
import org.stormgears.utils.concurrency.Terminator;
import org.stormgears.utils.logging.StormyLog;

import java.util.ArrayList;

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
public class Robot extends BaseStormgearsRobot {
	static {
		StormyLog.init();
		FixPermissionsKt.fixPermissions();
	}

	private static final Logger logger = LogManager.getLogger(Robot.class);

	public static final ArrayList<RegisteredNotifier> notifierRegistry = new ArrayList<>();

	/*
	 * Everybody, _please_ follow the singleton pattern!
	 * Create a private, final instance in your class, and link it here like this
	 * Then, when you want to use it somewhere else, use Robot.<subsystem>.blah
	 * Example: Robot.dsio.
	 */
	public static RobotConfiguration config = RobotConfiguration.INSTANCE;
	public static DSIO dsio;
	public static FmsInterface fmsInterface = FmsInterface.getInstance();

	@Nullable
	public static Sensors sensors;

	// @Nullable
	// public static GlobalMapping globalMapping;

	@Nullable
	public static DriveTalons driveTalons;

	@Nullable
	public static Drive drive;

	@Nullable
	public static Intake intake;

	@Nullable
	public static ElevatorSharedTalons elevatorSharedTalons;

	@Nullable
	public static Elevator elevator;

	@Nullable
	public static Climber climber;

	@Nullable
	public static Gripper gripper;

	private FieldPositions.Alliance selectedAlliance;
	private FieldPositions.StartingSpots selectedStartSpot;
	private FieldPositions.PlacementSpot selectedPlacementSpot;
	private FieldPositions.LeftRight selectedOwnSwitchPlateAssignment;
	private FieldPositions.LeftRight selectedScalePlateAssignment;
	private FieldPositions.LeftRight selectedOpponentSwitchPlateAssignmentChooser;

	@Nullable
	public static TalonDebugger talonDebugger;

//	**BEGIN**FOR USE WITH WPI MECANUM DRIVE API
//	private PowerUpMecanumDrive wpiMecanumDrive;
//	**END**FOR USE WITH WPI MECANUM DRIVE API

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		logger.info("{} is running", config.getRobotName());

		MemWatch.INSTANCE.start();

		StormScheduler.init();

		if (config.getEnableSensors()) {
			Sensors.init();
			sensors = Sensors.getInstance();
		}

//		GlobalMapping.init();
//		globalMapping = GlobalMapping.getInstance();

		if (config.getEnableDrive()) {
			DriveTalons.init();
			driveTalons = DriveTalons.getInstance();

			drive = Drive.INSTANCE;

			//		**BEGIN**FOR USE WITH WPI MECANUM DRIVE API
//		PowerUpMecanumDrive.init();
//		wpiMecanumDrive = PowerUpMecanumDrive.getInstance();
//		**END**FOR USE WITH WPI MECANUM DRIVE API
		}

		if (config.getEnableIntake()) {
			intake = Intake.INSTANCE;
		}

		if (config.getEnableElevator()) {
			ElevatorSharedTalons.init();
			elevatorSharedTalons = ElevatorSharedTalons.getInstance();

			elevator = Elevator.INSTANCE;
		}

		if (config.getEnableClimber()) {
//		Climber.init();
//		climber = Climber.getInstance();
		}

		if (config.getEnableGripper()) {
			gripper = Gripper.INSTANCE;
		}
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

		Terminator.INSTANCE.setDisabled(DSIO.INSTANCE.getButtonBoard().getOverrideSwitch().get());

		// Get all the selected autonomous command properties for this run
		getSelectedAutonomousCommand();

		logger.trace("starting the autonomous command");

		if (drive != null && sensors != null && elevator != null) {
			AutonomousCommandGroup.INSTANCE.run(selectedAlliance,
				selectedStartSpot,
				selectedPlacementSpot,
				selectedOwnSwitchPlateAssignment,
				selectedScalePlateAssignment,
				selectedOpponentSwitchPlateAssignmentChooser);
		}

//		this.talonDebugger = new TalonDebugger(driveTalons.getTalons(), "autonomous");//.start();
	}

	/**
	 * Runs once right at the start of autonomousPeriodic
	 */
	@Override
	public void afterAutonomousInit() {

	}

	/**
	 * Runs when operator control starts
	 */
	@Override
	public void teleopInit() {
		logger.trace("teleop init");

		if (drive != null) {
			drive.setVelocityPID();
		}

		if (dsio == null) {
			dsio = DSIO.INSTANCE;
		}

		Terminator.INSTANCE.setDisabled(DSIO.INSTANCE.getButtonBoard().getOverrideSwitch().get());

//		this.talonDebugger = new TalonDebugger(driveTalons.getTalons(), "teleop");//.start();
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

//		logger.info("Current timer value: {}", timer.get());

		if (driveTalons != null) {
			TalonDebuggerKt.dashboardify(driveTalons);
		}

		StormScheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		super.teleopPeriodic();

		StormScheduler.getInstance().run();

//		**BEGIN**FOR USE WITH WPI MECANUM DRIVE API
//		if (wpiMecanumDrive != null) {
//			if (!sensors.getNavX().isCalibrating()) {
//				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
//				wpiMecanumDrive.move();
//			} else {
//				logger.fatal("NavX is currently calibrating! Cannot drive!");
//			}
//		} else {
//			logger.fatal("Robot.drive is null; that's a problem!");
//		}
//		**END**FOR USE WITH WPI MECANUM DRIVE API

		TalonDebuggerKt.dashboardify(driveTalons);
		elevator.debug();

		if (drive != null) {
//			if (!sensors.getNavX().isCalibrating()) {
//				if (!sensors.getNavX().thetaIsSet()) sensors.getNavX().setInitialTheta();
				drive.move();
//			} else {
//				logger.fatal("NavX is currently calibrating! Cannot drive!");
//			}
		} else {
			logger.fatal("Robot.drive is null; that's a problem!");
		}
	}

	/**
	 * This function is called periodically during sendTestData mode
	 */
	@Override
	public void disabledInit() {
		logger.trace("disabled init");
		super.disabledInit();

		fmsInterface.startPollingForData();

		Terminator.INSTANCE.setDisabled(true);

		if (talonDebugger != null && talonDebugger.getJob() != null) {
			talonDebugger.getJob().cancel(null);
		}

		if (elevatorSharedTalons != null) {
			elevatorSharedTalons.getMasterMotor().getSensorCollection().setQuadraturePosition(0, 10);
			elevator.setElevatorZeroed(true);
		}

		if (elevator != null) {
			elevator.turnOffElevator();
			elevator.zeroSideShift();
		}

		for (RegisteredNotifier rn : notifierRegistry) {
			rn.stop();
		}
	}

	private void getSelectedAutonomousCommand() {
		selectedAlliance = dsio.getChoosers().getAlliance();
		selectedStartSpot = dsio.getChoosers().getStartingSpot();
		selectedPlacementSpot = dsio.getChoosers().getPlacementSpot();
		selectedScalePlateAssignment = dsio.getChoosers().getScalePlateAssignmentChooser();
		selectedOwnSwitchPlateAssignment = dsio.getChoosers().getOwnSwitchPlateAssignmentChooser();
		selectedOpponentSwitchPlateAssignmentChooser = dsio.getChoosers().getOpponentSwitchPlateAssignmentChooser();

		logger.info("Selected Alliance: {}", selectedAlliance.toString());
		logger.info("Selected Starting Spot: {}", selectedStartSpot.toString());
		logger.info("Selected Placement Spot: {}", selectedPlacementSpot.toString());
		logger.info("Selected Scale Plate Assignment: {}", selectedScalePlateAssignment.toString());
		logger.info("Selected Own Switch Plate Assignment: {}", selectedOwnSwitchPlateAssignment.toString());
		logger.info("Selected Opponent Switch Plate Assignment: {}", selectedOpponentSwitchPlateAssignmentChooser.toString());
	}
}

