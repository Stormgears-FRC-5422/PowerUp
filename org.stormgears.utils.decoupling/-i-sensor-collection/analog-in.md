[PowerUp](../../index.md) / [org.stormgears.utils.decoupling](../index.md) / [ISensorCollection](index.md) / [analogIn](./analog-in.md)

# analogIn

`abstract val analogIn: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Get the position of whatever is in the analog pin of the Talon, regardless of
whether it is actually being used for feedback.

**Return**
the 24bit analog value.  The bottom ten bits is the ADC (0 - 1023)
on the analog pin of the Talon. The upper 14 bits tracks the overflows and underflows
(continuous sensor).

