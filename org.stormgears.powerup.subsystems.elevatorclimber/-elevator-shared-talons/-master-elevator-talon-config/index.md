[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../../index.md) / [ElevatorSharedTalons](../index.md) / [MasterElevatorTalonConfig](./index.md)

# MasterElevatorTalonConfig

`class MasterElevatorTalonConfig : `[`ElevatorTalonConfig`](../-elevator-talon-config/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MasterElevatorTalonConfig()` |

### Inherited Properties

| Name | Summary |
|---|---|
| [continuousCurrentLimit](../-elevator-talon-config/continuous-current-limit.md) | `open val continuousCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configure the continuous allowable current-draw (when current limit is enabled). Current limit is activated when current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`. |
| [enableCurrentLimit](../-elevator-talon-config/enable-current-limit.md) | `open val enableCurrentLimit: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Enable or disable Current Limit. |
| [forwardLimitSwitchSource](../-elevator-talon-config/forward-limit-switch-source.md) | `open val forwardLimitSwitchSource: `[`LocalLimitSwitchSourceConfig`](../../../org.stormgears.utils.talons/-local-limit-switch-source-config/index.md)<br>Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID of zero is assumed. If that's not desired, use the four parameter version of this function. |
| [inverted](../-elevator-talon-config/inverted.md) | `open val inverted: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Inverts the hbridge output of the motor controller. This does not impact sensor phase and should not be used to correct sensor polarity. This will invert the hbridge output but NOT the LEDs. This ensures.... |
| [neutralMode](../-elevator-talon-config/neutral-mode.md) | `open val neutralMode: NeutralMode`<br>Sets the mode of operation during neutral throttle output. |
| [peakCurrentLimit](../-elevator-talon-config/peak-current-limit.md) | `open val peakCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configure the peak allowable current (when current limit is enabled). Current limit is activated when current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`. |
| [profileSlot0](../-elevator-talon-config/profile-slot0.md) | `open val profileSlot0: `[`DefaultPIDSlot`](../../../org.stormgears.utils.talons/-factory-talon-config/-default-p-i-d-slot/index.md)<br>Profile slot 0 |
| [profileSlot1](../-elevator-talon-config/profile-slot1.md) | `open val profileSlot1: `[`DefaultPIDSlot`](../../../org.stormgears.utils.talons/-factory-talon-config/-default-p-i-d-slot/index.md)<br>Profile slot 1 |
| [reverseLimitSwitchSource](../-elevator-talon-config/reverse-limit-switch-source.md) | `open val reverseLimitSwitchSource: `[`LocalLimitSwitchSourceConfig`](../../../org.stormgears.utils.talons/-local-limit-switch-source-config/index.md)<br>Configures the reverse limit switch for a remote source. |
| [sensorPhase](../-elevator-talon-config/sensor-phase.md) | `open val sensorPhase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in sensor. After setting this, user can freely call SetInvert() with any value. |
| [statusFramePeriod](../-elevator-talon-config/status-frame-period.md) | `open val statusFramePeriod: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<StatusFrameEnhanced, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>Sets the period of each status frame in the given map. User ensure CAN Bus utilization is not high. This setting is not persistent and is lost when device is reset. If this is a concern, calling application can use [TalonSRX.hasResetOccurred](#) to determine if the status frame needs to be reconfigured. |
