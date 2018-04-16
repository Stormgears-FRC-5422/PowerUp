[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [JoystickAxisButton](./index.md)

# JoystickAxisButton

`class JoystickAxisButton : Button, `[`IButton`](../-i-button/index.md)

### Types

| Name | Summary |
|---|---|
| [Direction](-direction/index.md) | `enum class Direction` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `JoystickAxisButton(joystick: Joystick, axis: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, direction: `[`Direction`](-direction/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [get](get.md) | `fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [whenPressed](when-pressed.md) | `fun whenPressed(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenPressed(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `fun whenReleased(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenReleased(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](while-held.md) | `fun whileHeld(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whileHeld(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
