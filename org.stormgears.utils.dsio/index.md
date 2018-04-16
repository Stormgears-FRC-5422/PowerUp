[PowerUp](../index.md) / [org.stormgears.utils.dsio](./index.md)

## Package org.stormgears.utils.dsio

Utilities for driver station I/O

### Types

| Name | Summary |
|---|---|
| [DummyButton](-dummy-button/index.md) | `class DummyButton : `[`IButton`](-i-button/index.md) |
| [DummyJoystick](-dummy-joystick/index.md) | `class DummyJoystick : `[`IRawJoystick`](-i-raw-joystick/index.md) |
| [DummyJoystickAxis](-dummy-joystick-axis/index.md) | `class DummyJoystickAxis : `[`IJoystickAxis`](-i-joystick-axis/index.md) |
| [DummySwitch](-dummy-switch/index.md) | `open class DummySwitch : `[`ISwitch`](-i-switch/index.md) |
| [DummyTernarySwitch](-dummy-ternary-switch/index.md) | `class DummyTernarySwitch : `[`DummySwitch`](-dummy-switch/index.md)`, `[`ITernarySwitch`](-i-ternary-switch/index.md) |
| [EnhancedButton](-enhanced-button/index.md) | `class EnhancedButton : JoystickButton, `[`IButton`](-i-button/index.md)<br>Create a joystick button for triggering commands. |
| [IButton](-i-button/index.md) | `interface IButton` |
| [IJoystickAxis](-i-joystick-axis/index.md) | `interface IJoystickAxis` |
| [IRawJoystick](-i-raw-joystick/index.md) | `interface IRawJoystick` |
| [ISwitch](-i-switch/index.md) | `interface ISwitch` |
| [ITernarySwitch](-i-ternary-switch/index.md) | `interface ITernarySwitch : `[`ISwitch`](-i-switch/index.md) |
| [JoystickAxis](-joystick-axis/index.md) | `class JoystickAxis : `[`IJoystickAxis`](-i-joystick-axis/index.md) |
| [JoystickAxisButton](-joystick-axis-button/index.md) | `class JoystickAxisButton : Button, `[`IButton`](-i-button/index.md) |
| [LogitechJoystick](-logitech-joystick/index.md) | `class LogitechJoystick : Joystick, `[`IRawJoystick`](-i-raw-joystick/index.md)<br>Construct an instance of a joystick. The joystick index is the USB port on the drivers station. |
| [POVButton](-p-o-v-button/index.md) | `class POVButton : Button, `[`IButton`](-i-button/index.md) |
| [SwitchControl](-switch-control/index.md) | `open class SwitchControl : JoystickButton, `[`ISwitch`](-i-switch/index.md)<br>Create a joystick button for triggering commands. |
| [TernarySwitch](-ternary-switch/index.md) | `class TernarySwitch : `[`SwitchControl`](-switch-control/index.md)`, `[`ITernarySwitch`](-i-ternary-switch/index.md)<br>Create a joystick button for triggering commands. |
| [XboxJoystick](-xbox-joystick/index.md) | `class XboxJoystick : Joystick, `[`IRawJoystick`](-i-raw-joystick/index.md)<br>Construct an instance of a joystick. The joystick index is the USB port on the drivers station. |
