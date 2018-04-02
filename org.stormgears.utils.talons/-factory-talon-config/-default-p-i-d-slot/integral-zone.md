[PowerUp](../../../index.md) / [org.stormgears.utils.talons](../../index.md) / [FactoryTalonConfig](../index.md) / [DefaultPIDSlot](index.md) / [integralZone](./integral-zone.md)

# integralZone

`open val integralZone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Overrides [PIDSlot.integralZone](../../-p-i-d-slot/integral-zone.md)

Sets the Integral Zone constant in the given parameter slot. If the (absolute) closed-loop error is outside of this
zone, integral accumulator is automatically cleared. This ensures than integral wind up events will stop after the
sensor gets far enough from its target.

**See Also**

[TalonSRX.config_kI](#)

