[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../../index.md) / [ElevatorSharedTalons](../index.md) / [ElevatorTalonConfig](index.md) / [continuousCurrentLimit](./continuous-current-limit.md)

# continuousCurrentLimit

`open val continuousCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Overrides [FactoryTalonConfig.continuousCurrentLimit](../../../org.stormgears.utils.talons/-factory-talon-config/continuous-current-limit.md)

Configure the continuous allowable current-draw (when current limit is enabled). Current limit is activated when
current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous
limit. This ensures current limiting while allowing for momentary excess current events. For simpler
current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero:
`ConfigPeakCurrentLimit(0)`.

**See Also**

[TalonSRX.configContinuousCurrentLimit](#)

