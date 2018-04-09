package org.stormgears.powerup

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.auto.command.AutonomousCommandGroup
import org.stormgears.powerup.subsystems.dsio.Choosers
import org.stormgears.powerup.subsystems.dsio.DSIO
import org.stormgears.powerup.subsystems.elevatorclimber.Climber
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.elevatorclimber.ElevatorSharedTalons
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.field.fmsInterfaceWarmup
import org.stormgears.powerup.subsystems.field.parseFmsData
import org.stormgears.powerup.subsystems.information.RobotConfiguration
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.powerup.subsystems.navigator.Drive
import org.stormgears.powerup.subsystems.navigator.DriveTalons
import org.stormgears.powerup.subsystems.navigator.TalonDebugger
import org.stormgears.powerup.subsystems.sensors.Sensors
import org.stormgears.utils.BaseStormgearsRobot
import org.stormgears.utils.RegisteredNotifier
import org.stormgears.utils.StormScheduler
import org.stormgears.utils.concurrency.Terminator
import org.stormgears.utils.fixPermissions
import org.stormgears.utils.logging.StormyLog
import java.util.*

/*
 * The entry point of the PowerUp program. Please keep it clean.
 */
class Robot : BaseStormgearsRobot() {
	companion object {
		init {
			StormyLog.init()
			fixPermissions()
		}

		private val logger = LogManager.getLogger(Robot::class.java)

		@JvmStatic
		val notifierRegistry = ArrayList<RegisteredNotifier>()

		@JvmStatic
		var config = RobotConfiguration

		@JvmStatic
		var dsio: DSIO? = null

		@JvmStatic
		var choosers: Choosers? = null

		@JvmStatic
		var sensors: Sensors? = null

		@JvmStatic
		var driveTalons: DriveTalons? = null

		@JvmStatic
		var drive: Drive? = null

		@JvmStatic
		var intake: Intake? = null

		@JvmStatic
		var elevatorSharedTalons: ElevatorSharedTalons? = null

		@JvmStatic
		var elevator: Elevator? = null

		@JvmStatic
		var climber: Climber? = null

		var talonDebugger: TalonDebugger? = null
	}


	private var selectedAlliance: FieldPositions.Alliance? = null
	private var selectedStartSpot: FieldPositions.StartingSpots? = null
	private var selectedPlacementSpot: FieldPositions.PlacementSpot? = null

	private var selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight? = null
	private var selectedScalePlateAssignment: FieldPositions.LeftRight? = null
	private var selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight? = null

	//	**BEGIN**FOR USE WITH WPI MECANUM DRIVE API
	//	private PowerUpMecanumDrive wpiMecanumDrive;
	//	**END**FOR USE WITH WPI MECANUM DRIVE API

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	override fun robotInit() {
		logger.info("{} is running", config.robotName)

		//		MemWatch.INSTANCE.start();

		StormScheduler.init()

		if (config.enableSensors) {
			Sensors.init()
			sensors = Sensors.getInstance()
		}

		if (config.enableDrive) {
			driveTalons = DriveTalons()

			drive = Drive

			//			**BEGIN**FOR USE WITH WPI MECANUM DRIVE API
			//			PowerUpMecanumDrive.init();
			//			wpiMecanumDrive = PowerUpMecanumDrive.getInstance();
			//			**END**FOR USE WITH WPI MECANUM DRIVE API
		}

		if (config.enableIntake) {
			intake = Intake
		}

		if (config.enableElevator) {
			elevatorSharedTalons = ElevatorSharedTalons

			elevator = Elevator
		}

		if (config.enableClimber) {
			Climber.init()
			climber = Climber.getInstance()
		}

		Robot.elevator?.zeroElevatorEncoder()

		fmsInterfaceWarmup()
	}

	/**
	 * Runs when autonomous mode starts
	 */
	override fun autonomousInit() {
		logger.trace("autonomous init")

		if (choosers == null) {
			choosers = Choosers
		}

		parseFmsData()

		Terminator.disabled = false //DSIO.buttonBoard.overrideSwitch.get()

		// Get all the selected autonomous command properties for this run
		getSelectedAutonomousCommand()

		logger.trace("starting the autonomous command")

		if (drive != null && sensors != null) {
			if (!sensors!!.navX.thetaIsSet()) sensors!!.navX.setInitialTheta()

			AutonomousCommandGroup.run(selectedAlliance!!,
				selectedStartSpot!!,
				selectedPlacementSpot!!,
				selectedOwnSwitchPlateAssignment!!,
				selectedScalePlateAssignment!!,
				selectedOpponentSwitchPlateAssignmentChooser!!)
		}

		//		this.talonDebugger = new TalonDebugger(driveTalons.getTalons(), "autonomous");//.start();
	}

	/**
	 * Runs once right at the start of autonomousPeriodic
	 */
	override fun afterAutonomousInit() {

	}

	/**
	 * Runs when operator control starts
	 */
	override fun teleopInit() {
		logger.trace("teleop init")

		driveTalons?.velocityPIDMode()

		if (dsio == null) {
			dsio = DSIO
		}

		Terminator.disabled = DSIO.buttonBoard.overrideSwitch.get()

		//		this.talonDebugger = new TalonDebugger(driveTalons.getTalons(), "teleop");//.start();
	}

	/**
	 * Runs once right at the start of teleopPeriodic
	 */
	override fun afterTeleopInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	override fun autonomousPeriodic() {
		super.autonomousPeriodic()

		//		logger.info("Current timer value: {}", timer.get());

		//		if (driveTalons != null) {
		//			TalonDebuggerKt.dashboardify(driveTalons);
		//		}

		StormScheduler.getInstance().run()
	}

	/**
	 * This function is called periodically during operator control
	 */

	override fun teleopPeriodic() {
		super.teleopPeriodic()

		StormScheduler.getInstance().run()

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

		//		if (driveTalons != null) {
		//			TalonDebuggerKt.dashboardify(driveTalons);
		//		}

		elevator?.debug()

		if (drive != null) {
			if (!sensors!!.navX.isCalibrating) {
				if (!sensors!!.navX.thetaIsSet()) {
					sensors!!.navX.setInitialTheta()
				}
				drive!!.joystickMove()
			} else {
				logger.fatal("NavX is currently calibrating! Cannot drive!")
			}
		} else {
			logger.fatal("Robot.drive is null; that's a problem!")
		}
	}

	/**
	 * This function is called periodically during sendTestData mode
	 */
	override fun disabledInit() {
		logger.trace("disabled init")
		super.disabledInit()

		Terminator.disabled = true

		talonDebugger?.job?.cancel(null)

		elevator?.turnOffElevator()

		for (rn in notifierRegistry) {
			rn.stop()
		}
	}

	private fun getSelectedAutonomousCommand() {
		// TODO: Untangle this spaghetti
		selectedAlliance = choosers!!.alliance
		selectedStartSpot = choosers!!.startingSpot
		selectedPlacementSpot = choosers!!.placementSpot
		selectedScalePlateAssignment = FieldPositions.SCALE_PLATE_ASSIGNMENT
		selectedOwnSwitchPlateAssignment = FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT
		selectedOpponentSwitchPlateAssignmentChooser = FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT

		logger.info("Selected Alliance: {}", selectedAlliance!!.toString())
		logger.info("Selected Starting Spot: {}", selectedStartSpot!!.toString())
		logger.info("Selected Placement Spot: {}", selectedPlacementSpot!!.toString())
		logger.info("Selected Scale Plate Assignment: {}", selectedScalePlateAssignment!!.toString())
		logger.info("Selected Own Switch Plate Assignment: {}", selectedOwnSwitchPlateAssignment!!.toString())
		logger.info("Selected Opponent Switch Plate Assignment: {}", selectedOpponentSwitchPlateAssignmentChooser!!.toString())
	}
}

