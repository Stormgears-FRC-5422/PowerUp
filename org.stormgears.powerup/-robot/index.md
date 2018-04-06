[PowerUp](../../index.md) / [org.stormgears.powerup](../index.md) / [Robot](./index.md)

# Robot

`open class Robot : `[`BaseStormgearsRobot`](../../org.stormgears.utils/-base-stormgears-robot/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Robot()` |

### Properties

| Name | Summary |
|---|---|
| [climber](climber.md) | `static var climber: `[`Climber`](../../org.stormgears.powerup.subsystems.elevatorclimber/-climber/index.md) |
| [config](config.md) | `static var config: `[`RobotConfiguration`](../../org.stormgears.powerup.subsystems.information/-robot-configuration/index.md) |
| [drive](drive.md) | `static var drive: `[`Drive`](../../org.stormgears.powerup.subsystems.navigator/-drive/index.md) |
| [driveTalons](drive-talons.md) | `static var driveTalons: `[`DriveTalons`](../../org.stormgears.powerup.subsystems.navigator/-drive-talons/index.md) |
| [dsio](dsio.md) | `static var dsio: `[`DSIO`](../../org.stormgears.powerup.subsystems.dsio/-d-s-i-o/index.md) |
| [elevator](elevator.md) | `static var elevator: `[`Elevator`](../../org.stormgears.powerup.subsystems.elevatorclimber/-elevator/index.md) |
| [elevatorSharedTalons](elevator-shared-talons.md) | `static var elevatorSharedTalons: `[`ElevatorSharedTalons`](../../org.stormgears.powerup.subsystems.elevatorclimber/-elevator-shared-talons/index.md) |
| [intake](intake.md) | `static var intake: `[`Intake`](../../org.stormgears.powerup.subsystems.intake/-intake/index.md) |
| [notifierRegistry](notifier-registry.md) | `static val notifierRegistry: `[`ArrayList`](http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)`<`[`RegisteredNotifier`](../../org.stormgears.utils/-registered-notifier/index.md)`>` |
| [sensors](sensors.md) | `static var sensors: `[`Sensors`](../../org.stormgears.powerup.subsystems.sensors/-sensors/index.md) |
| [talonDebugger](talon-debugger.md) | `static var talonDebugger: `[`TalonDebugger`](../../org.stormgears.powerup.subsystems.navigator/-talon-debugger/index.md) |

### Functions

| Name | Summary |
|---|---|
| [afterAutonomousInit](after-autonomous-init.md) | `open fun afterAutonomousInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs once right at the start of autonomousPeriodic |
| [afterTeleopInit](after-teleop-init.md) | `open fun afterTeleopInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs once right at the start of teleopPeriodic |
| [autonomousInit](autonomous-init.md) | `open fun autonomousInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs when autonomous mode starts |
| [autonomousPeriodic](autonomous-periodic.md) | `open fun autonomousPeriodic(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during autonomous |
| [disabledInit](disabled-init.md) | `open fun disabledInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during sendTestData mode |
| [robotInit](robot-init.md) | `open fun robotInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is run when the robot is first started up and should be used for any initialization code |
| [teleopInit](teleop-init.md) | `open fun teleopInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs when operator control starts |
| [teleopPeriodic](teleop-periodic.md) | `open fun teleopPeriodic(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during operator control |

### Inherited Functions

| Name | Summary |
|---|---|
| [startCompetition](../../org.stormgears.utils/-base-stormgears-robot/start-competition.md) | `open fun startCompetition(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
