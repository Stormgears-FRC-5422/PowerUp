[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator](../index.md) / [TalonDebugger](./index.md)

# TalonDebugger

`class TalonDebugger : `[`WithCoroutines`](../../org.stormgears.utils.concurrency/-with-coroutines/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TalonDebugger(talons: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`ITalon`](../../org.stormgears.utils.decoupling/-i-talon/index.md)`>, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "")` |

### Properties

| Name | Summary |
|---|---|
| [filename](filename.md) | `val filename: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [job](job.md) | `var job: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`?` |
| [talons](talons.md) | `val talons: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`ITalon`](../../org.stormgears.utils.decoupling/-i-talon/index.md)`>` |
| [writer](writer.md) | `val writer: `[`PrintWriter`](http://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html) |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(str: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [dump](dump.md) | `fun dump(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.md) | `fun start(): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [actor](../../org.stormgears.utils.concurrency/-with-coroutines/actor.md) | `open fun <E> actor(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`ActorScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-actor-scope/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-with-coroutines/actor.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`SendChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-with-coroutines/actor.md#E)`>`<br>Launches new coroutine that is receiving messages from its mailbox channel and returns a reference to its mailbox channel as a [SendChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html), within the coroutine thread. |
| [async](../../org.stormgears.utils.concurrency/-with-coroutines/async.md) | `open fun <T> async(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`T`](../../org.stormgears.utils.concurrency/-with-coroutines/async.md#T)`): `[`Deferred`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-deferred/index.html)`<`[`T`](../../org.stormgears.utils.concurrency/-with-coroutines/async.md#T)`>`<br>Creates new coroutine and returns its future result as an implementation of [Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-deferred/index.html), within the coroutine thread. |
| [launch](../../org.stormgears.utils.concurrency/-with-coroutines/launch.md) | `open fun launch(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)<br>Launches a new coroutine in the coroutine thread. |
| [produce](../../org.stormgears.utils.concurrency/-with-coroutines/produce.md) | `open fun <E> produce(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`ProducerScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-producer-scope/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-with-coroutines/produce.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ReceiveChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html)`<`[`E`](../../org.stormgears.utils.concurrency/-with-coroutines/produce.md#E)`>`<br>Launches new coroutine to produce a stream of values by sending them to a channel and returns a reference to the coroutine as a [ReceiveChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html), within the coroutine thread. |
