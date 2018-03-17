[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator.motionprofile](../index.md) / [MotionControl](./index.md)

# MotionControl

`open class MotionControl`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MotionControl()` |

### Properties

| Name | Summary |
|---|---|
| [statuses](statuses.md) | `var statuses: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<MotionProfileStatus>` |

### Functions

| Name | Summary |
|---|---|
| [clearMotionProfileTrajectories](clear-motion-profile-trajectories.md) | `open fun clearMotionProfileTrajectories(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disable](disable.md) | `open fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enable](enable.md) | `open fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getEncPos](get-enc-pos.md) | `open fun getEncPos(talonIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getEncVel](get-enc-vel.md) | `open fun getEncVel(talonIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getPointsRemaining](get-points-remaining.md) | `open fun getPointsRemaining(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [holdProfile](hold-profile.md) | `open fun holdProfile(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [printStatus](print-status.md) | `open fun printStatus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [pushMotionProfileTrajectory](push-motion-profile-trajectory.md) | `open fun pushMotionProfileTrajectory(talonIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pt: TrajectoryPoint): ErrorCode` |
| [shutDownProfiling](shut-down-profiling.md) | `open fun shutDownProfiling(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startControlThread](start-control-thread.md) | `open fun startControlThread(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [stopControlThread](stop-control-thread.md) | `open fun stopControlThread(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
