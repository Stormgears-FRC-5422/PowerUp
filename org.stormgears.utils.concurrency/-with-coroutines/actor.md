[PowerUp](../../index.md) / [org.stormgears.utils.concurrency](../index.md) / [WithCoroutines](index.md) / [actor](./actor.md)

# actor

`open fun <E> actor(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, capacity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`ActorScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-actor-scope/index.html)`<`[`E`](actor.md#E)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`SendChannel`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html)`<`[`E`](actor.md#E)`>`

Launches new coroutine that is receiving messages from its mailbox channel
and returns a reference to its mailbox channel as a [SendChannel](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/-send-channel/index.html), within the coroutine thread.

### Parameters

`context` - context of the coroutine. The default value is [globalContext](../global-context.md).

`capacity` - capacity of the channel's buffer (no buffer by default).

`start` - coroutine start option. The default value is [CoroutineStart.DEFAULT](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/-d-e-f-a-u-l-t/index.html).

`parent` - explicitly specifies the parent job, overrides job from the [context](actor.md#org.stormgears.utils.concurrency.WithCoroutines$actor(kotlin.coroutines.experimental.CoroutineContext, kotlin.Int, kotlinx.coroutines.experimental.CoroutineStart, kotlinx.coroutines.experimental.Job, kotlin.SuspendFunction1((kotlinx.coroutines.experimental.channels.ActorScope((org.stormgears.utils.concurrency.WithCoroutines.actor.E)), kotlin.Unit)))/context) (if any).*

`block` - the coroutine code.

**See Also**

[kotlinx.coroutines.experimental.channels.actor](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental.channels/actor.html)

