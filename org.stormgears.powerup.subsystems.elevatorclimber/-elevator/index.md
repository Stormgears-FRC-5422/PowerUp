[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../index.md) / [Elevator](./index.md)

# Elevator

`object Elevator : `[`TerminableSubsystem`](../../org.stormgears.utils.concurrency/-terminable-subsystem/index.md)

Default constructor for the creation of the elevator

### Properties

| Name | Summary |
|---|---|
| [CENTER](-c-e-n-t-e-r.md) | `const val CENTER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [LEFT](-l-e-f-t.md) | `const val LEFT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [RIGHT](-r-i-g-h-t.md) | `const val RIGHT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [SCALE_POSITIONS](-s-c-a-l-e_-p-o-s-i-t-i-o-n-s.md) | `val SCALE_POSITIONS: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| [SWITCH_POSITIONS](-s-w-i-t-c-h_-p-o-s-i-t-i-o-n-s.md) | `val SWITCH_POSITIONS: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| [elevatorZeroed](elevator-zeroed.md) | `var elevatorZeroed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sideShiftTalon](side-shift-talon.md) | `val sideShiftTalon: `[`ITalon`](../../org.stormgears.utils.decoupling/-i-talon/index.md) |
| [useGartnerRate](use-gartner-rate.md) | `var useGartnerRate: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inherited Properties

| Name | Summary |
|---|---|
| [disabled](../../org.stormgears.utils.concurrency/-terminable-subsystem/disabled.md) | `var disabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| Name | Summary |
|---|---|
| [debug](debug.md) | `fun debug(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [moveDownManual](move-down-manual.md) | `fun moveDownManual(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [moveElevatorToPosition](move-elevator-to-position.md) | `fun moveElevatorToPosition(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)<br>Move the elevator to a position |
| [moveLeftManual](move-left-manual.md) | `fun moveLeftManual(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [moveRightManual](move-right-manual.md) | `fun moveRightManual(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [moveSideShiftOverLeft](move-side-shift-over-left.md) | `fun moveSideShiftOverLeft(): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?` |
| [moveSideShiftOverRight](move-side-shift-over-right.md) | `fun moveSideShiftOverRight(): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?` |
| [moveSideShiftToPosition](move-side-shift-to-position.md) | `fun moveSideShiftToPosition(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)<br>Move side shift to position |
| [moveUpManual](move-up-manual.md) | `fun moveUpManual(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [stop](stop.md) | `fun stop(): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)<br>Stop all motion |
| [turnOffElevator](turn-off-elevator.md) | `fun turnOffElevator(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [zeroElevator](zero-elevator.md) | `suspend fun zeroElevator(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [zeroElevatorEncoder](zero-elevator-encoder.md) | `fun zeroElevatorEncoder(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [zeroSideShift](zero-side-shift.md) | `fun zeroSideShift(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [actor](../../org.stormgears.utils.concurrency/-terminable-subsystem/actor.md) | `open fun <E> actor(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)`, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)`, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?, block: suspend `[`ActorScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-actor-scope/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-terminable-subsystem/actor.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`SendChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-terminable-subsystem/actor.md#E)`>`<br>Launches new coroutine that is receiving messages from its mailbox channel and returns a reference to its mailbox channel as a [SendChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html), within the coroutine thread. |
| [async](../../org.stormgears.utils.concurrency/-terminable-subsystem/async.md) | `open fun <T> async(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)`, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)`, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`T`](../../org.stormgears.utils.concurrency/-terminable-subsystem/async.md#T)`): `[`Deferred`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-deferred/index.html)`<`[`T`](../../org.stormgears.utils.concurrency/-terminable-subsystem/async.md#T)`>`<br>Creates new coroutine and returns its future result as an implementation of [Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-deferred/index.html), within the coroutine thread. |
| [launch](../../org.stormgears.utils.concurrency/-terminable-subsystem/launch.md) | `fun launch(jobName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`open fun launch(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)`, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)`, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)<br>Launches a new coroutine in the coroutine thread. |
| [produce](../../org.stormgears.utils.concurrency/-terminable-subsystem/produce.md) | `open fun <E> produce(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)`, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?, block: suspend `[`ProducerScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-producer-scope/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-terminable-subsystem/produce.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ReceiveChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-terminable-subsystem/produce.md#E)`>`<br>Launches new coroutine to produce a stream of values by sending them to a channel and returns a reference to the coroutine as a [ReceiveChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html), within the coroutine thread. |
