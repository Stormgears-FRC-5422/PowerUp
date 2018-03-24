[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [IButton](./index.md)

# IButton

`interface IButton`

### Functions

| Name | Summary |
|---|---|
| [cancelWhenActive](cancel-when-active.md) | `abstract fun cancelWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelWhenPressed](cancel-when-pressed.md) | `abstract fun cancelWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](get.md) | `abstract fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggleWhenActive](toggle-when-active.md) | `abstract fun toggleWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggleWhenPressed](toggle-when-pressed.md) | `abstract fun toggleWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenActive](when-active.md) | `abstract fun whenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenInactive](when-inactive.md) | `abstract fun whenInactive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenPressed](when-pressed.md) | `abstract fun whenPressed(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenPressed(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `abstract fun whenReleased(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenReleased(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenReleased(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileActive](while-active.md) | `abstract fun whileActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](while-held.md) | `abstract fun whileHeld(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whileHeld(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whileHeld(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyButton](../-dummy-button/index.md) | `class DummyButton : `[`IButton`](./index.md) |
| [EnhancedButton](../-enhanced-button/index.md) | `class EnhancedButton : JoystickButton, `[`IButton`](./index.md)<br>Create a joystick button for triggering commands. |
| [POVButton](../-p-o-v-button/index.md) | `class POVButton : Button, `[`IButton`](./index.md) |
