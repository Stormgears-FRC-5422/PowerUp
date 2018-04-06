[PowerUp](../index.md) / [org.stormgears.powerup.subsystems.navigator](./index.md)

## Package org.stormgears.powerup.subsystems.navigator

Subsystem that moves the robot around.

### Types

| Name | Summary |
|---|---|
| [Drive](-drive/index.md) | `object Drive : `[`TerminableSubsystem`](../org.stormgears.utils.concurrency/-terminable-subsystem/index.md) |
| [DriveTalons](-drive-talons/index.md) | `class DriveTalons` |
| [GlobalMapping](-global-mapping/index.md) | `open class GlobalMapping` |
| [Position](-position/index.md) | `open class Position` |
| [PowerUpMecanumDrive](-power-up-mecanum-drive/index.md) | `open class PowerUpMecanumDrive : MecanumDrive` |
| [TalonDebugger](-talon-debugger/index.md) | `class TalonDebugger : `[`WithCoroutines`](../org.stormgears.utils.concurrency/-with-coroutines/index.md) |

### Properties

| Name | Summary |
|---|---|
| [vI](v-i.md) | `val vI: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Maximum initial velocity in ticks per 100ms |
| [vMin](v-min.md) | `val vMin: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Absolute minimum velocity in ticks per 100ms |
| [vTarget](v-target.md) | `val vTarget: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Target maximum velocity in ticks/100ms |

### Functions

| Name | Summary |
|---|---|
| [dashboardify](dashboardify.md) | `fun dashboardify(talons: `[`DriveTalons`](-drive-talons/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [mX](m-x.md) | `fun mX(y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates rBase target x given [y](m-x.md#org.stormgears.powerup.subsystems.navigator$mX(kotlin.Double, kotlin.Double)/y) and distance [d](m-x.md#org.stormgears.powerup.subsystems.navigator$mX(kotlin.Double, kotlin.Double)/d) |
| [mY](m-y.md) | `fun mY(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates rBase target y given [x](m-y.md#org.stormgears.powerup.subsystems.navigator$mY(kotlin.Double, kotlin.Double)/x) and distance [d](m-y.md#org.stormgears.powerup.subsystems.navigator$mY(kotlin.Double, kotlin.Double)/d) |
| [maxA](max-a.md) | `fun maxA(d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Maximum acceleration in ticks per 100ms per inch given distance [d](max-a.md#org.stormgears.powerup.subsystems.navigator$maxA(kotlin.Double)/d) in inches |
| [rBase](r-base.md) | `fun rBase(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, xT: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, yT: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Function of the ramp up and ramp down curves |
| [sunProfile](sun-profile.md) | `fun sunProfile(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, d: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the PID velocity of a motor given movement progress [x](sun-profile.md#org.stormgears.powerup.subsystems.navigator$sunProfile(kotlin.Double, kotlin.Double)/x) in inches and target distance [d](sun-profile.md#org.stormgears.powerup.subsystems.navigator$sunProfile(kotlin.Double, kotlin.Double)/d) in inches |
| [vI](v-i.md) | `fun vI(y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Sine curve correction factor for [rBase](r-base.md) |
