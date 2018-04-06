[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.navigator.motionprofile](../../index.md) / [MotionMagic](../index.md) / [MotionMagicDriveConfig](./index.md)

# MotionMagicDriveConfig

`class MotionMagicDriveConfig : `[`DriveTalonConfig`](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MotionMagicDriveConfig(maxVel: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, maxAccel: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [motionAcceleration](motion-acceleration.md) | `val motionAcceleration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the Motion Magic Acceleration. This is the target acceleration that the motion magic curve generator can use in ticks/100ms/sec. |
| [motionCruiseVelocity](motion-cruise-velocity.md) | `val motionCruiseVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the Motion Magic Cruise Velocity. This is the peak target velocity that the motion magic curve generator can use in ticks/100ms. |

### Inherited Properties

| Name | Summary |
|---|---|
| [inverted](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/inverted.md) | `open val inverted: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Inverts the hbridge output of the motor controller. This does not impact sensor phase and should not be used to correct sensor polarity. This will invert the hbridge output but NOT the LEDs. This ensures.... |
| [neutralMode](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/neutral-mode.md) | `open val neutralMode: NeutralMode`<br>Sets the mode of operation during neutral throttle output. |
| [profileSlot0](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/profile-slot0.md) | `open val profileSlot0: `[`PIDSlot`](../../../org.stormgears.utils.talons/-p-i-d-slot/index.md)<br>Profile slot 0 |
| [profileSlot1](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/profile-slot1.md) | `open val profileSlot1: `[`PIDSlot`](../../../org.stormgears.utils.talons/-p-i-d-slot/index.md)<br>Profile slot 1 |
| [selectedFeedbackSensor](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/selected-feedback-sensor.md) | `open val selectedFeedbackSensor: `[`FeedbackDeviceConfig`](../../../org.stormgears.utils.talons/-feedback-device-config.md)<br>Select the feedback device for the motor controller. |
| [sensorPhase](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/sensor-phase.md) | `open val sensorPhase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in sensor. After setting this, user can freely call SetInvert() with any value. |
| [statusFramePeriod](../../../org.stormgears.powerup.subsystems.navigator/-drive-talons/-drive-talon-config/status-frame-period.md) | `open val statusFramePeriod: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<StatusFrameEnhanced, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>Sets the period of each status frame in the given map. User ensure CAN Bus utilization is not high. This setting is not persistent and is lost when device is reset. If this is a concern, calling application can use [TalonSRX.hasResetOccurred](#) to determine if the status frame needs to be reconfigured. |
