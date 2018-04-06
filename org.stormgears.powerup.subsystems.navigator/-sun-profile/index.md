[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator](../index.md) / [SunProfile](./index.md)

# SunProfile

`class SunProfile`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SunProfile(vTarget: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 5000.0, vI: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 500.0, vMin: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = 100.0)` |

### Properties

| Name | Summary |
|---|---|
| [vI](v-i.md) | `val vI: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Maximum initial velocity in ticks per 100ms |
| [vMin](v-min.md) | `val vMin: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Absolute minimum velocity in ticks per 100ms |
| [vTarget](v-target.md) | `val vTarget: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Target maximum velocity in ticks/100ms |

### Functions

| Name | Summary |
|---|---|
| [mX](m-x.md) | `fun mX(y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, maxA: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates rBase target x given [y](m-x.md#org.stormgears.powerup.subsystems.navigator.SunProfile$mX(kotlin.Double, kotlin.Double, kotlin.Double)/y) and distance [d](m-x.md#org.stormgears.powerup.subsystems.navigator.SunProfile$mX(kotlin.Double, kotlin.Double, kotlin.Double)/d) |
| [mY](m-y.md) | `fun mY(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, maxA: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates rBase target y given [x](m-y.md#org.stormgears.powerup.subsystems.navigator.SunProfile$mY(kotlin.Double, kotlin.Double, kotlin.Double)/x) and distance [d](m-y.md#org.stormgears.powerup.subsystems.navigator.SunProfile$mY(kotlin.Double, kotlin.Double, kotlin.Double)/d) |
| [maxA](max-a.md) | `fun maxA(d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Maximum acceleration in ticks per 100ms per inch given distance [d](max-a.md#org.stormgears.powerup.subsystems.navigator.SunProfile$maxA(kotlin.Double)/d) in inches |
| [profile](profile.md) | `fun profile(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the PID velocity of a motor given movement progress [x](profile.md#org.stormgears.powerup.subsystems.navigator.SunProfile$profile(kotlin.Double, kotlin.Double)/x) in inches and target distance [d](profile.md#org.stormgears.powerup.subsystems.navigator.SunProfile$profile(kotlin.Double, kotlin.Double)/d) in inches |
| [rBase](r-base.md) | `fun rBase(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, xT: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, yT: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Function of the ramp up and ramp down curves |
| [vI](v-i.md) | `fun vI(y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sine curve correction factor for [rBase](r-base.md) |
