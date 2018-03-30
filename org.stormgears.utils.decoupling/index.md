[PowerUp](../index.md) / [org.stormgears.utils.decoupling](./index.md)

## Package org.stormgears.utils.decoupling

Shims and interfaces for decoupling the robot code from physical motors.

### Types

| Name | Summary |
|---|---|
| [DummyTalon](-dummy-talon/index.md) | `class DummyTalon : `[`ITalon`](-i-talon/index.md) |
| [IDriverStation](-i-driver-station.md) | `interface IDriverStation` |
| [ISensorCollection](-i-sensor-collection/index.md) | `interface ISensorCollection` |
| [ITalon](-i-talon/index.md) | `interface ITalon : IMotorControllerEnhanced, SpeedController, MotorSafety, `[`IControlModeGettable`](-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-control-mode-gettable/index.md)`, `[`IHandleGettable`](-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/index.md) |
| [ITalonJavaHelpers](-i-talon-java-helpers/index.md) | `open class ITalonJavaHelpers` |
| [TalonSRXAdapter](-talon-s-r-x-adapter/index.md) | `class TalonSRXAdapter : `[`ITalon`](-i-talon/index.md)`, WPI_TalonSRX` |

### Functions

| Name | Summary |
|---|---|
| [createTalon](create-talon.md) | `fun createTalon(deviceNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ITalon`](-i-talon/index.md) |
