[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [IRawJoystick](./index.md)

# IRawJoystick

`interface IRawJoystick`

### Properties

| Name | Summary |
|---|---|
| [joystickX](joystick-x.md) | `abstract val joystickX: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [joystickY](joystick-y.md) | `abstract val joystickY: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [joystickZ](joystick-z.md) | `abstract val joystickZ: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [throttleV](throttle-v.md) | `abstract val throttleV: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getRawButton](get-raw-button.md) | `abstract fun getRawButton(button: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyJoystick](../-dummy-joystick/index.md) | `class DummyJoystick : `[`IRawJoystick`](./index.md) |
| [LogitechJoystick](../-logitech-joystick/index.md) | `class LogitechJoystick : Joystick, `[`IRawJoystick`](./index.md)<br>Construct an instance of a joystick. The joystick index is the USB port on the drivers station. |
| [XboxJoystick](../-xbox-joystick/index.md) | `class XboxJoystick : Joystick, `[`IRawJoystick`](./index.md)<br>Construct an instance of a joystick. The joystick index is the USB port on the drivers station. |
