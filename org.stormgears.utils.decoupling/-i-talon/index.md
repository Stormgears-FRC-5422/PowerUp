[PowerUp](../../index.md) / [org.stormgears.utils.decoupling](../index.md) / [ITalon](./index.md)

# ITalon

`interface ITalon : IMotorControllerEnhanced, SpeedController, MotorSafety, `[`IControlModeGettable`](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-control-mode-gettable/index.md)`, `[`IHandleGettable`](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/index.md)

### Properties

| Name | Summary |
|---|---|
| [dummy](dummy.md) | `abstract val dummy: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sensorCollection](sensor-collection.md) | `abstract val sensorCollection: `[`ISensorCollection`](../-i-sensor-collection/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [getControlMode](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-control-mode-gettable/get-control-mode.md) | `abstract fun getControlMode(): ControlMode?` |
| [getHandle](../-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/get-handle.md) | `abstract fun getHandle(): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyTalon](../-dummy-talon/index.md) | `class DummyTalon : `[`ITalon`](./index.md) |
| [MotorLogger](../../org.stormgears.utils.logging/-motor-logger/index.md) | `class MotorLogger : `[`ITalon`](./index.md)<br>Logs all of the method calls to a motor |
| [TalonSRXAdapter](../-talon-s-r-x-adapter/index.md) | `class TalonSRXAdapter : `[`ITalon`](./index.md)`, WPI_TalonSRX` |
