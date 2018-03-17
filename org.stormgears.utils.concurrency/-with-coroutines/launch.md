[PowerUp](../../index.md) / [org.stormgears.utils.concurrency](../index.md) / [WithCoroutines](index.md) / [launch](./launch.md)

# launch

`open fun launch(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)` = globalContext, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, parent: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)`? = null, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-job/index.html)

Launches a new coroutine in the coroutine thread.

### Parameters

`context` - context of the coroutine. The default value is [globalContext](../global-context.md).

`start` - coroutine start option. The default value is [CoroutineStart.DEFAULT](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-coroutine-start/-d-e-f-a-u-l-t/index.html).

`parent` - explicitly specifies the parent job, overrides job from the [context](launch.md#org.stormgears.utils.concurrency.WithCoroutines$launch(kotlin.coroutines.experimental.CoroutineContext, kotlinx.coroutines.experimental.CoroutineStart, kotlinx.coroutines.experimental.Job, kotlin.SuspendFunction1((kotlinx.coroutines.experimental.CoroutineScope, kotlin.Unit)))/context) (if any).

`block` - the coroutine code.

**See Also**

[kotlinx.coroutines.experimental.launch](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/launch.html)

