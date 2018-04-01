[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [sensorPhase](./sensor-phase.md)

# sensorPhase

`abstract val sensorPhase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate
forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in
sensor. After setting this, user can freely call SetInvert() with any value.

**See Also**

[TalonSRX.setSensorPhase](#)

