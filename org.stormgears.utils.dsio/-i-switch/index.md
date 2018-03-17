[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [ISwitch](./index.md)

# ISwitch

`interface ISwitch`

### Types

| Name | Summary |
|---|---|
| [FlipListener](-flip-listener/index.md) | `interface FlipListener` |

### Functions

| Name | Summary |
|---|---|
| [cancelWhenActive](cancel-when-active.md) | `abstract fun cancelWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelWhenPressed](cancel-when-pressed.md) | `abstract fun cancelWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](get.md) | `abstract fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggleWhenActive](toggle-when-active.md) | `abstract fun toggleWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggleWhenPressed](toggle-when-pressed.md) | `abstract fun toggleWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenActive](when-active.md) | `abstract fun whenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlipped](when-flipped.md) | `abstract fun whenFlipped(listener: `[`FlipListener`](-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenFlipped(listener: (on: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenInactive](when-inactive.md) | `abstract fun whenInactive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenPressed](when-pressed.md) | `abstract fun whenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `abstract fun whenReleased(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileActive](while-active.md) | `abstract fun whileActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](while-held.md) | `abstract fun whileHeld(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummySwitch](../-dummy-switch/index.md) | `open class DummySwitch : `[`ISwitch`](./index.md) |
| [ITernarySwitch](../-i-ternary-switch/index.md) | `interface ITernarySwitch : `[`ISwitch`](./index.md) |
| [SwitchControl](../-switch-control/index.md) | `open class SwitchControl : JoystickButton, `[`ISwitch`](./index.md)<br>Create a joystick button for triggering commands. |
