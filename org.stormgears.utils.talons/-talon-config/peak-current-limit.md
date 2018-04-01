[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [peakCurrentLimit](./peak-current-limit.md)

# peakCurrentLimit

`abstract val peakCurrentLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Configure the peak allowable current (when current limit is enabled). Current limit is activated when current
exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This
ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single
threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`.

**See Also**

[TalonSRX.configPeakCurrentLimit](#)

