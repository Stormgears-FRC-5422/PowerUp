[PowerUp](../../index.md) / [org.stormgears.powerup](../index.md) / [Robot](./index.md)

# Robot

`class Robot : `[`BaseStormgearsRobot`](../../org.stormgears.utils/-base-stormgears-robot/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Robot()` |

### Functions

| Name | Summary |
|---|---|
| [afterAutonomousInit](after-autonomous-init.md) | `fun afterAutonomousInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs once right at the start of autonomousPeriodic |
| [afterTeleopInit](after-teleop-init.md) | `fun afterTeleopInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs once right at the start of teleopPeriodic |
| [autonomousInit](autonomous-init.md) | `fun autonomousInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs when autonomous mode starts |
| [autonomousPeriodic](autonomous-periodic.md) | `fun autonomousPeriodic(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during autonomous |
| [disabledInit](disabled-init.md) | `fun disabledInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during sendTestData mode |
| [robotInit](robot-init.md) | `fun robotInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is run when the robot is first started up and should be used for any initialization code |
| [teleopInit](teleop-init.md) | `fun teleopInit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs when operator control starts |
| [teleopPeriodic](teleop-periodic.md) | `fun teleopPeriodic(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called periodically during operator control |

### Inherited Functions

| Name | Summary |
|---|---|
| [startCompetition](../../org.stormgears.utils/-base-stormgears-robot/start-competition.md) | `open fun startCompetition(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [choosers](choosers.md) | `var choosers: `[`Choosers`](../../org.stormgears.powerup.subsystems.dsio/-choosers/index.md)`?` |
| [climber](climber.md) | `var climber: `[`Climber`](../../org.stormgears.powerup.subsystems.elevatorclimber/-climber/index.md)`?` |
| [config](config.md) | `var config: `[`RobotConfiguration`](../../org.stormgears.powerup.subsystems.information/-robot-configuration/index.md) |
| [drive](drive.md) | `var drive: `[`Drive`](../../org.stormgears.powerup.subsystems.navigator/-drive/index.md)`?` |
| [driveTalons](drive-talons.md) | `var driveTalons: `[`DriveTalons`](../../org.stormgears.powerup.subsystems.navigator/-drive-talons/index.md)`?` |
| [dsio](dsio.md) | `var dsio: `[`DSIO`](../../org.stormgears.powerup.subsystems.dsio/-d-s-i-o/index.md)`?` |
| [elevator](elevator.md) | `var elevator: `[`Elevator`](../../org.stormgears.powerup.subsystems.elevatorclimber/-elevator/index.md)`?` |
| [elevatorSharedTalons](elevator-shared-talons.md) | `var elevatorSharedTalons: `[`ElevatorSharedTalons`](../../org.stormgears.powerup.subsystems.elevatorclimber/-elevator-shared-talons/index.md)`?` |
| [intake](intake.md) | `var intake: `[`Intake`](../../org.stormgears.powerup.subsystems.intake/-intake/index.md)`?` |
| [notifierRegistry](notifier-registry.md) | `val notifierRegistry: `[`ArrayList`](http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)`<`[`RegisteredNotifier`](../../org.stormgears.utils/-registered-notifier/index.md)`>` |
| [sensors](sensors.md) | `var sensors: `[`Sensors`](../../org.stormgears.powerup.subsystems.sensors/-sensors/index.md)`?` |
| [talonDebugger](talon-debugger.md) | `var talonDebugger: `[`TalonDebugger`](../../org.stormgears.powerup.subsystems.navigator/-talon-debugger/index.md)`?` |
