[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [POVButton](./index.md)

# POVButton

`class POVButton : Button, `[`IButton`](../-i-button/index.md)

### Types

| Name | Summary |
|---|---|
| [Direction](-direction/index.md) | `enum class Direction` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `POVButton(joystick: Joystick, direction: `[`Direction`](-direction/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [get](get.md) | `fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [whenPressed](when-pressed.md) | `fun whenPressed(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenPressed(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `fun whenReleased(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenReleased(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
