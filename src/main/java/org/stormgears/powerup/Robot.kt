package org.stormgears.powerup

import edu.wpi.first.wpilibj.DriverStation
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.auto.command.AutoDriveMoveCommand
import org.stormgears.powerup.auto.command.AutoRoutes
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
import org.stormgears.utils.*
import org.stormgears.utils.concurrency.Terminator
import org.stormgears.utils.logging.StormyLog

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

		if (choosers == null) {
			choosers = Choosers
		}

		Robot.elevator?.zeroElevatorEncoder()

		// Warm up classes used by autonomous to reduce latency
		DriverStation.getInstance()
		fmsInterfaceWarmup()
		forceInit(AutonomousCommandGroup::class.java)
		forceInit(AutoDriveMoveCommand::class.java)
		forceInit(AutoRoutes::class.java)
	}

	/**
	 * Runs when autonomous mode starts
	 */
	override fun autonomousInit() {
		logger.trace("autonomous init")

		parseFmsData()

		Terminator.disabled = false //DSIO.buttonBoard.overrideSwitch.get()

		// Get all the selected autonomous command properties for this run

		logger.info("Alliance: {}", choosers!!.alliance)
		logger.info("Starting Spot: {}", choosers!!.startingSpot)
		logger.info("Placement Spot: {}", choosers!!.placementSpot)
		logger.info("Scale Plate Assignment: {}", FieldPositions.SCALE_PLATE_ASSIGNMENT)
		logger.info("Own Switch Plate Assignment: {}", FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT)
		logger.info("Opponent Switch Plate Assignment: {}", FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT)

		logger.trace("starting the autonomous command")

		if (drive != null && sensors != null) {
			if (!sensors!!.navX.thetaIsSet()) sensors!!.navX.setInitialTheta()

			AutonomousCommandGroup.run(choosers!!.startingSpot,
				choosers!!.placementSpot,
				FieldPositions.SCALE_PLATE_ASSIGNMENT!!,
				FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT!!)
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
//		println(Sensors.getInstance().stormNet.test())
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
//		for(i in 0..3) {
//			println("Sensor" + i + ": " + Sensors.getInstance().stormNet.getLidarDistance(i))
//		}
//		println("Pair 0: " + Sensors.getInstance().stormNet.printLidarPair(0));

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
		intake?.debug()

//		val coords = sensors?.vision?.findClosestCube() ?: doubleArrayOf(0.0, 0.0)
//		val alpha = sensors?.vision?.convertAngle(69.0, 104.0, coords[0], coords[1])

		dsio?.tick()

		if (drive != null) {
			if (!sensors!!.navX.thetaIsSet()) {
				sensors!!.navX.setInitialTheta()
			}
			drive!!.joystickMove()
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
}
