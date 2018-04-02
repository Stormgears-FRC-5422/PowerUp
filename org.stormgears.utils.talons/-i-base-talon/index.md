[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [IBaseTalon](./index.md)

# IBaseTalon

`interface IBaseTalon : IMotorControllerEnhanced, SpeedController, MotorSafety, `[`IControlModeGettable`](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-control-mode-gettable/index.md)`, `[`IHandleGettable`](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/index.md)

### Properties

| Name | Summary |
|---|---|
| [dummy](dummy.md) | `abstract val dummy: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sensorCollection](sensor-collection.md) | `abstract val sensorCollection: `[`ISensorCollection`](../-i-sensor-collection/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [getHandle](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/get-handle.md) | `abstract fun getHandle(): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyTalon](../-dummy-talon/index.md) | `class DummyTalon : `[`IBaseTalon`](./index.md) |
| [ITalon](../-i-talon/index.md) | `interface ITalon : `[`IBaseTalon`](./index.md)<br>Talon + Config management |
| [MotorLogger](../../org.stormgears.utils.logging/-motor-logger/index.md) | `class MotorLogger : `[`IBaseTalon`](./index.md)<br>Logs all of the method calls to a motor |
| [TalonManager](../-talon-manager/index.md) | `class TalonManager : `[`IBaseTalon`](./index.md)`, `[`ITalon`](../-i-talon/index.md)`, `[`GeneratedTalonManager`](../-generated-talon-manager/index.md) |
| [TalonSRXAdapter](../-talon-s-r-x-adapter/index.md) | `class TalonSRXAdapter : `[`IBaseTalon`](./index.md)`, WPI_TalonSRX` |
