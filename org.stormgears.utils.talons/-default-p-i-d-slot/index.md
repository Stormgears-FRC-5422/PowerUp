[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [DefaultPIDSlot](./index.md)

# DefaultPIDSlot

`open class DefaultPIDSlot : `[`PIDSlot`](../-p-i-d-slot/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultPIDSlot()` |

### Properties

| Name | Summary |
|---|---|
| [allowableClosedloopError](allowable-closedloop-error.md) | `open val allowableClosedloopError: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the allowable closed-loop error in the given parameter slot. |
| [closedLoopPeakOutput](closed-loop-peak-output.md) | `open val closedLoopPeakOutput: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the peak closed-loop output. This peak output is slot-specific and is applied to the output of the associated PID loop. This setting is separate from the generic Peak Output setting. |
| [closedLoopPeriod](closed-loop-period.md) | `open val closedLoopPeriod: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the loop time (in milliseconds) of the PID closed-loop calculations. Default value is 1 ms. |
| [integralZone](integral-zone.md) | `open val integralZone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the Integral Zone constant in the given parameter slot. If the (absolute) closed-loop error is outside of this zone, integral accumulator is automatically cleared. This ensures than integral wind up events will stop after the sensor gets far enough from its target. |
| [kD](k-d.md) | `open val kD: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the 'D' constant in the given parameter slot. |
| [kF](k-f.md) | `open val kF: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the 'F' constant in the given parameter slot. |
| [kI](k-i.md) | `open val kI: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the 'I' constant in the given parameter slot. |
| [kP](k-p.md) | `open val kP: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the 'P' constant in the given parameter slot. |
| [maxIntegralAccumulator](max-integral-accumulator.md) | `open val maxIntegralAccumulator: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sets the maximum integral accumulator in the given parameter slot. |
