[PowerUp](../index.md) / [org.stormgears.utils](./index.md)

## Package org.stormgears.utils

### Types

| Name | Summary |
|---|---|
| [BaseStormgearsRobot](-base-stormgears-robot/index.md) | `abstract class BaseStormgearsRobot : IterativeRobot` |
| [LambdaCommand](-lambda-command/index.md) | `class LambdaCommand : Command` |
| [RegisteredNotifier](-registered-notifier/index.md) | `open class RegisteredNotifier : Notifier` |
| [RunnableCommand](-runnable-command/index.md) | `class RunnableCommand : Command` |
| [StormScheduler](-storm-scheduler/index.md) | `open class StormScheduler` |

### Functions

| Name | Summary |
|---|---|
| [fixPermissions](fix-permissions.md) | `fun fixPermissions(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Invokes a shell script provided by the build system with the [setuid](https://en.wikipedia.org/wiki/Setuid) flag set that fixes permissions of the config.properties file, in case it was uploaded as the admin user. |
| [retry](retry.md) | `fun retry(tries: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, operation: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, exceptionHandler: (e: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
