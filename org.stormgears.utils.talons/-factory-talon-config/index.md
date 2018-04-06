[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [FactoryTalonConfig](./index.md)

# FactoryTalonConfig

`open class FactoryTalonConfig : `[`TalonConfig`](../-talon-config/index.md)

Factory default Talon configuration

### Types

| Name | Summary |
|---|---|
| [DefaultPIDSlot](-default-p-i-d-slot/index.md) | `open class DefaultPIDSlot : `[`PIDSlot`](../-p-i-d-slot/index.md) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FactoryTalonConfig()`<br>Factory default Talon configuration |

### Properties

| Name | Summary |
|---|---|
| [auxPIDPolarity](aux-p-i-d-polarity.md) | `open val auxPIDPolarity: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Configures the Polarity of the Auxiliary PID (PID1). Standard Polarity: Primary Output = PID0 + PID1 Auxiliary Output = PID0 - PID1 Inverted Polarity: Primary Output = PID0 - PID1 Auxiliary Output = PID0 + PID1 |
| [closedloopRamp](closedloop-ramp.md) | `open val closedloopRamp: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the closed-loop ramp rate of throttle output. |
| [continuousCurrentLimit](continuous-current-limit.md) | `open val continuousCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configure the continuous allowable current-draw (when current limit is enabled). Current limit is activated when current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`. |
| [enableCurrentLimit](enable-current-limit.md) | `open val enableCurrentLimit: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Enable or disable Current Limit. |
| [enableVoltageCompensation](enable-voltage-compensation.md) | `open val enableVoltageCompensation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Enables voltage compensation. If enabled, voltage compensation works in all control modes. |
| [forwardLimitSwitchSource](forward-limit-switch-source.md) | `open val forwardLimitSwitchSource: `[`LimitSwitchSourceConfig`](../-limit-switch-source-config.md)<br>Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID of zero is assumed. If that's not desired, use the four parameter version of this function. |
| [forwardSoftLimitEnable](forward-soft-limit-enable.md) | `open val forwardSoftLimitEnable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Configures the forward soft limit enable. |
| [forwardSoftLimitThreshold](forward-soft-limit-threshold.md) | `open val forwardSoftLimitThreshold: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configures the forward soft limit threhold. |
| [inverted](inverted.md) | `open val inverted: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Inverts the hbridge output of the motor controller. This does not impact sensor phase and should not be used to correct sensor polarity. This will invert the hbridge output but NOT the LEDs. This ensures.... |
| [motionAcceleration](motion-acceleration.md) | `open val motionAcceleration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the Motion Magic Acceleration. This is the target acceleration that the motion magic curve generator can use in ticks/100ms/sec. |
| [motionCruiseVelocity](motion-cruise-velocity.md) | `open val motionCruiseVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the Motion Magic Cruise Velocity. This is the peak target velocity that the motion magic curve generator can use in ticks/100ms. |
| [motionProfileTrajectoryPeriod](motion-profile-trajectory-period.md) | `open val motionProfileTrajectoryPeriod: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>When trajectory points are processed in the motion profile executer, the MPE determines how long to apply the active trajectory point by summing baseTrajDurationMs with the timeDur of the trajectory point (see [com.ctre.phoenix.motion.TrajectoryPoint](#)). This allows general selection of the execution rate of the points with 1ms resolution, while allowing some degree of change from point to point. |
| [neutralDeadband](neutral-deadband.md) | `open val neutralDeadband: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the output deadband percentage. |
| [neutralMode](neutral-mode.md) | `open val neutralMode: NeutralMode`<br>Sets the mode of operation during neutral throttle output. |
| [nominalOutputForward](nominal-output-forward.md) | `open val nominalOutputForward: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the forward nominal output percentage. |
| [nominalOutputReverse](nominal-output-reverse.md) | `open val nominalOutputReverse: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [openloopRamp](openloop-ramp.md) | `open val openloopRamp: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the open-loop ramp rate of throttle output in seconds from neutral to full. |
| [peakCurrentDuration](peak-current-duration.md) | `open val peakCurrentDuration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configure the peak allowable duration (when current limit is enabled). Current limit is activated when current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`. |
| [peakCurrentLimit](peak-current-limit.md) | `open val peakCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configure the peak allowable current (when current limit is enabled). Current limit is activated when current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`. |
| [peakOutputForward](peak-output-forward.md) | `open val peakOutputForward: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the forward peak output percentage. |
| [peakOutputReverse](peak-output-reverse.md) | `open val peakOutputReverse: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the reverse peak output percentage. |
| [profileSlot0](profile-slot0.md) | `open val profileSlot0: `[`PIDSlot`](../-p-i-d-slot/index.md)<br>Profile slot 0 |
| [profileSlot1](profile-slot1.md) | `open val profileSlot1: `[`PIDSlot`](../-p-i-d-slot/index.md)<br>Profile slot 1 |
| [profileSlot2](profile-slot2.md) | `open val profileSlot2: `[`PIDSlot`](../-p-i-d-slot/index.md)<br>Profile slot 2 |
| [profileSlot3](profile-slot3.md) | `open val profileSlot3: `[`PIDSlot`](../-p-i-d-slot/index.md)<br>Profile slot 3 |
| [remoteFeedbackFilter](remote-feedback-filter.md) | `open val remoteFeedbackFilter: `[`RemoteFeedbackFilterConfig`](../-remote-feedback-filter-config/index.md)<br>Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote device and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features. |
| [reverseLimitSwitchSource](reverse-limit-switch-source.md) | `open val reverseLimitSwitchSource: `[`LimitSwitchSourceConfig`](../-limit-switch-source-config.md)<br>Configures the reverse limit switch for a remote source. |
| [reverseSoftLimitEnable](reverse-soft-limit-enable.md) | `open val reverseSoftLimitEnable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Configures the reverse soft limit enable. |
| [reverseSoftLimitThreshold](reverse-soft-limit-threshold.md) | `open val reverseSoftLimitThreshold: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configures the reverse soft limit threshold. |
| [selectedFeedbackCoefficient](selected-feedback-coefficient.md) | `open val selectedFeedbackCoefficient: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>The Feedback Coefficient is a scalar applied to the value of the feedback sensor. Useful when you need to scale your sensor values within the closed-loop calculations. Default value is 1. |
| [selectedFeedbackSensor](selected-feedback-sensor.md) | `open val selectedFeedbackSensor: `[`FeedbackDeviceConfig`](../-feedback-device-config.md)<br>Select the feedback device for the motor controller. |
| [sensorPhase](sensor-phase.md) | `open val sensorPhase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in sensor. After setting this, user can freely call SetInvert() with any value. |
| [statusFramePeriod](status-frame-period.md) | `open val statusFramePeriod: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<StatusFrameEnhanced, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>Sets the period of each status frame in the given map. User ensure CAN Bus utilization is not high. This setting is not persistent and is lost when device is reset. If this is a concern, calling application can use [TalonSRX.hasResetOccurred](#) to determine if the status frame needs to be reconfigured. |
| [velocityMeasurementPeriod](velocity-measurement-period.md) | `open val velocityMeasurementPeriod: VelocityMeasPeriod`<br>Sets the period over which velocity measurements are taken. |
| [velocityMeasurementWindow](velocity-measurement-window.md) | `open val velocityMeasurementWindow: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the number of velocity samples used in the rolling average velocity measurement. |
| [voltageCompSaturation](voltage-comp-saturation.md) | `open val voltageCompSaturation: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Configures the Voltage Compensation saturation voltage. |
| [voltageMeasurementFilter](voltage-measurement-filter.md) | `open val voltageMeasurementFilter: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Configures the voltage measurement filter. |

### Inheritors

| Name | Summary |
|---|---|
| [DriveTalonConfig](../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/index.md) | `class DriveTalonConfig : `[`FactoryTalonConfig`](./index.md) |
| [ElevatorTalonConfig](../../org.stormgears.powerup.subsystems.elevatorclimber/-elevator-shared-talons/-elevator-talon-config/index.md) | `class ElevatorTalonConfig : `[`FactoryTalonConfig`](./index.md) |
