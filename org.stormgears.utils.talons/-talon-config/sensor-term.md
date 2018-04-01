[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [sensorTerm](./sensor-term.md)

# sensorTerm

`abstract val sensorTerm: `[`SensorTermConfig`](../-sensor-term-config/index.md)`?`

Select what sensor term should be bound to switch feedback device.

Sensor Sum = Sensor Sum Term 0 - Sensor Sum Term 1

Sensor Difference = Sensor Diff Term 0 - Sensor Diff Term 1

The four terms are specified with this routine. Then Sensor Sum/Difference can be selected for closed-looping.

**See Also**

[TalonSRX.configSensorTerm](#)

