[PowerUp](../../index.md) / [org.stormgears.utils.concurrency](../index.md) / [WithCoroutines](index.md) / [produce](./produce.md)

# produce

`open fun <E> produce(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`ProducerScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-producer-scope/index.html)`<`[`E`](produce.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ReceiveChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html)`<`[`E`](produce.md#E)`>`

Launches new coroutine to produce a stream of values by sending them to a channel
and returns a reference to the coroutine as a [ReceiveChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-receive-channel/index.html), within the coroutine thread.

### Parameters

`context` - context of the coroutine. The default value is [globalContext](../global-context.md).

`capacity` - capacity of the channel's buffer (no buffer by default).

`parent` - explicitly specifies the parent job, overrides job from the [context](produce.md#org.stormgears.utils.concurrency.WithCoroutines$produce(kotlin.coroutines.experimental.CoroutineContext, kotlin.Int, kotlinx.coroutines.experimental.Job, kotlin.SuspendFunction1((kotlinx.coroutines.experimental.channels.ProducerScope((org.stormgears.utils.concurrency.WithCoroutines.produce.E)), kotlin.Unit)))/context) (if any).*

`block` - the coroutine code.

**See Also**

[kotlinx.coroutines.experimental.channels.produce](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/produce.html)

