[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../../index.md) / [ElevatorSharedTalons](../index.md) / [ElevatorTalonConfig](index.md) / [statusFramePeriod](./status-frame-period.md)

# statusFramePeriod

`val statusFramePeriod: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<StatusFrameEnhanced, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`

Overrides [FactoryTalonConfig.statusFramePeriod](../../../org.stormgears.utils.talons/-factory-talon-config/status-frame-period.md)

Sets the period of each status frame in the given map. User ensure CAN Bus utilization is not high. This setting
is not persistent and is lost when device is reset. If this is a concern, calling application can use
[TalonSRX.hasResetOccurred](#) to determine if the status frame needs to be reconfigured.

**See Also**

[TalonSRX.setStatusFramePeriod](#)

