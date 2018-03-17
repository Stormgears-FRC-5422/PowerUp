[PowerUp](../index.md) / [org.stormgears.utils.concurrency](index.md) / [globalContext](./global-context.md)

# globalContext

`val globalContext: `[`ThreadPoolDispatcher`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-thread-pool-dispatcher/index.html)

The global, single-threaded context in which coroutines are run. This is to prevent threading issues by not
using more than one thread.

