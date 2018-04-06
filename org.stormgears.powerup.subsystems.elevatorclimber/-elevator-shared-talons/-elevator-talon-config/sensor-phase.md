[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../../index.md) / [ElevatorSharedTalons](../index.md) / [ElevatorTalonConfig](index.md) / [sensorPhase](./sensor-phase.md)

# sensorPhase

`val sensorPhase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Overrides [FactoryTalonConfig.sensorPhase](../../../org.stormgears.utils.talons/-factory-talon-config/sensor-phase.md)

Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate
forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in
sensor. After setting this, user can freely call SetInvert() with any value.

**See Also**

[TalonSRX.setSensorPhase](#)

