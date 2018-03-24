[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [EnhancedButton](./index.md)

# EnhancedButton

`class EnhancedButton : JoystickButton, `[`IButton`](../-i-button/index.md)

Create a joystick button for triggering commands.

### Parameters

`joystick` - The GenericHID object that has the button (e.g. Joystick, KinectStick,
etc)

`buttonNumber` - The button number (see [GenericHID.getRawButton](#))

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EnhancedButton(joystick: GenericHID, buttonNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Create a joystick button for triggering commands. |

### Functions

| Name | Summary |
|---|---|
| [whenPressed](when-pressed.md) | `fun whenPressed(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenPressed(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `fun whenReleased(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenReleased(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](while-held.md) | `fun whileHeld(callback: `[`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whileHeld(callback: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
