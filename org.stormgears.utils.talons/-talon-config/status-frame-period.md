[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [statusFramePeriod](./status-frame-period.md)

# statusFramePeriod

`abstract val statusFramePeriod: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<StatusFrameEnhanced, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`

Sets the period of each status frame in the given map. User ensure CAN Bus utilization is not high. This setting
is not persistent and is lost when device is reset. If this is a concern, calling application can use
[TalonSRX.hasResetOccurred](#) to determine if the status frame needs to be reconfigured.

**See Also**

[TalonSRX.setStatusFramePeriod](#)

