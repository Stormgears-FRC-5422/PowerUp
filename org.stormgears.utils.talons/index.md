[PowerUp](../index.md) / [org.stormgears.utils.talons](./index.md)

## Package org.stormgears.utils.talons

Helpers regarding Talon SRX devices

### Types

| Name | Summary |
|---|---|
| [DummyTalon](-dummy-talon/index.md) | `class DummyTalon : `[`IBaseTalon`](-i-base-talon/index.md) |
| [FactoryTalonConfig](-factory-talon-config/index.md) | `open class FactoryTalonConfig : `[`TalonConfig`](-talon-config/index.md)<br>Factory default Talon configuration |
| [FeedbackDeviceConfig](-feedback-device-config.md) | `interface FeedbackDeviceConfig` |
| [GeneratedTalonManager](-generated-talon-manager/index.md) | `open class GeneratedTalonManager` |
| [IBaseTalon](-i-base-talon/index.md) | `interface IBaseTalon : IMotorControllerEnhanced, SpeedController, MotorSafety, `[`IControlModeGettable`](-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-control-mode-gettable/index.md)`, `[`IHandleGettable`](-i-talon-java-helpers/__-d-o_-n-o-t_-u-s-e_-o-r_-y-o-u_-w-i-l-l_-b-e_-f-i-r-e-d/-i-handle-gettable/index.md) |
| [ISensorCollection](-i-sensor-collection/index.md) | `interface ISensorCollection` |
| [ITalon](-i-talon/index.md) | `interface ITalon : `[`IBaseTalon`](-i-base-talon/index.md)<br>Talon + Config management |
| [ITalonJavaHelpers](-i-talon-java-helpers/index.md) | `open class ITalonJavaHelpers` |
| [LimitSwitchSourceConfig](-limit-switch-source-config.md) | `interface LimitSwitchSourceConfig` |
| [LocalFeedbackDeviceConfig](-local-feedback-device-config/index.md) | `data class LocalFeedbackDeviceConfig : `[`FeedbackDeviceConfig`](-feedback-device-config.md)<br>Select the feedback device for the motor controller. |
| [LocalLimitSwitchSourceConfig](-local-limit-switch-source-config/index.md) | `data class LocalLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](-limit-switch-source-config.md)<br>Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID of zero is assumed. If that's not desired, use the four parameter version of this function. |
| [PIDSlot](-p-i-d-slot/index.md) | `interface PIDSlot`<br>Config specific to a single PID slot |
| [RemoteFeedbackDeviceConfig](-remote-feedback-device-config/index.md) | `data class RemoteFeedbackDeviceConfig : `[`FeedbackDeviceConfig`](-feedback-device-config.md)<br>Select the remote feedback device for the motor controller. |
| [RemoteFeedbackFilterConfig](-remote-feedback-filter-config/index.md) | `data class RemoteFeedbackFilterConfig`<br>Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote device and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features. |
| [RemoteLimitSwitchSourceConfig](-remote-limit-switch-source-config/index.md) | `data class RemoteLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](-limit-switch-source-config.md)<br>Configures the limit switch for a remote source. For example, a CAN motor controller may need to monitor the Limit-F pin of another Talon or CANifier. |
| [SensorTermConfig](-sensor-term-config/index.md) | `data class SensorTermConfig`<br>Select what sensor term should be bound to switch feedback device. |
| [TalonConfig](-talon-config/index.md) | `interface TalonConfig`<br>Configuration values for a Talon |
| [TalonManager](-talon-manager/index.md) | `class TalonManager : `[`IBaseTalon`](-i-base-talon/index.md)`, `[`ITalon`](-i-talon/index.md)`, `[`GeneratedTalonManager`](-generated-talon-manager/index.md) |
| [TalonSRXAdapter](-talon-s-r-x-adapter/index.md) | `class TalonSRXAdapter : `[`IBaseTalon`](-i-base-talon/index.md)`, WPI_TalonSRX` |

### Functions

| Name | Summary |
|---|---|
| [createTalon](create-talon.md) | `fun createTalon(deviceNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ITalon`](-i-talon/index.md) |
