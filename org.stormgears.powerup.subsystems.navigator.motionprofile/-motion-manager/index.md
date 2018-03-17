[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator.motionprofile](../index.md) / [MotionManager](./index.md)

# MotionManager

`open class MotionManager`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MotionManager()` |

### Properties

| Name | Summary |
|---|---|
| [turnController](turn-controller.md) | `var turnController: PIDController` |

### Functions

| Name | Summary |
|---|---|
| [endProfile](end-profile.md) | `open fun endProfile(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getRobotRPM](get-robot-r-p-m.md) | `open fun getRobotRPM(): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [getTurnProfile](get-turn-profile.md) | `open fun getTurnProfile(d: ProfileDetails): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`DoubleArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double-array/index.html)`>` |
| [pushLinear](push-linear.md) | `open fun pushLinear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [pushProfile](push-profile.md) | `open fun pushProfile(pathArray: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`DoubleArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double-array/index.html)`>, immediate: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, done: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [pushTurn](push-turn.md) | `open fun pushTurn(theta: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, immediate: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, done: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun pushTurn(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [rotateToAngle](rotate-to-angle.md) | `open fun rotateToAngle(theta: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutDownProfiling](shut-down-profiling.md) | `open fun shutDownProfiling(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startProfile](start-profile.md) | `open fun startProfile(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitUntilProfileFinishes](wait-until-profile-finishes.md) | `open fun waitUntilProfileFinishes(pollMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
