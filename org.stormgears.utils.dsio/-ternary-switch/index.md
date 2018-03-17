[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [TernarySwitch](./index.md)

# TernarySwitch

`class TernarySwitch : `[`SwitchControl`](../-switch-control/index.md)`, `[`ITernarySwitch`](../-i-ternary-switch/index.md)

Create a joystick button for triggering commands.

### Parameters

`joystick` - The GenericHID object that has the button (e.g. Joystick, KinectStick,
etc)

`buttonUp` - The button number (see [GenericHID.getRawButton](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TernarySwitch(joystick: GenericHID, buttonUp: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, buttonDown: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Create a joystick button for triggering commands. |

### Functions

| Name | Summary |
|---|---|
| [whenFlipped](when-flipped.md) | `fun whenFlipped(listener: `[`TernaryFlipListener`](../-i-ternary-switch/-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlippedTernary](when-flipped-ternary.md) | `fun whenFlippedTernary(listener: (state: `[`SwitchState`](../-i-ternary-switch/-switch-state/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenFlippedTernary(listener: `[`TernaryFlipListener`](../-i-ternary-switch/-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [whenFlipped](../-switch-control/when-flipped.md) | `open fun whenFlipped(listener: `[`FlipListener`](../-i-switch/-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun whenFlipped(listener: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
