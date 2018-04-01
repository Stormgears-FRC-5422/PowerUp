[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [FactoryTalonConfig](index.md) / [inverted](./inverted.md)

# inverted

`open val inverted: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Overrides [TalonConfig.inverted](../-talon-config/inverted.md)

Inverts the hbridge output of the motor controller. This does not impact sensor phase and should not be used to
correct sensor polarity. This will invert the hbridge output but NOT the LEDs. This ensures....

* Green LEDs always represents positive request from robot-controller/closed-looping mode.
* Green LEDs correlates to forward limit switch.
* Green LEDs correlates to forward soft limit.

**See Also**

[TalonSRX.setInverted](#)

