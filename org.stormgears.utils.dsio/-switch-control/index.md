[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [SwitchControl](./index.md)

# SwitchControl

`open class SwitchControl : JoystickButton, `[`ISwitch`](../-i-switch/index.md)

Create a joystick button for triggering commands.

### Parameters

`joystick` - The GenericHID object that has the button (e.g. Joystick, KinectStick,
etc)

`buttonNumber` - The button number (see [GenericHID.getRawButton](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SwitchControl(joystick: GenericHID, buttonNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, log: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)``SwitchControl(joystick: GenericHID, buttonNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Create a joystick button for triggering commands. |

### Functions

| Name | Summary |
|---|---|
| [whenFlipped](when-flipped.md) | `open fun whenFlipped(listener: `[`FlipListener`](../-i-switch/-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun whenFlipped(listener: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [TernarySwitch](../-ternary-switch/index.md) | `class TernarySwitch : `[`SwitchControl`](./index.md)`, `[`ITernarySwitch`](../-i-ternary-switch/index.md)<br>Create a joystick button for triggering commands. |
