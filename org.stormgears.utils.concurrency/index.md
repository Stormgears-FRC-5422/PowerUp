[PowerUp](../index.md) / [org.stormgears.utils.concurrency](./index.md)

## Package org.stormgears.utils.concurrency

Helpers for concurrent programming with coroutines.

### Types

| Name | Summary |
|---|---|
| [TerminableSubsystem](-terminable-subsystem/index.md) | `abstract class TerminableSubsystem : `[`WithCoroutines`](-with-coroutines/index.md) |
| [Terminator](-terminator/index.md) | `object Terminator` |
| [WithCoroutines](-with-coroutines/index.md) | `interface WithCoroutines`<br>Classes that utilize coroutines should implement this interface to make coroutines launch in a single-threaded context. |

### Properties

| Name | Summary |
|---|---|
| [globalContext](global-context.md) | `val globalContext: `[`ThreadPoolDispatcher`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.experimental/-thread-pool-dispatcher/index.html)<br>The global, single-threaded context in which coroutines are run. This is to prevent threading issues by not using more than one thread. |
