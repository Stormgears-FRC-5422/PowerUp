[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [ITernarySwitch](./index.md)

# ITernarySwitch

`interface ITernarySwitch : `[`ISwitch`](../-i-switch/index.md)

### Types

| Name | Summary |
|---|---|
| [SwitchState](-switch-state/index.md) | `enum class SwitchState` |
| [TernaryFlipListener](-ternary-flip-listener/index.md) | `interface TernaryFlipListener` |

### Functions

| Name | Summary |
|---|---|
| [whenFlipped](when-flipped.md) | `abstract fun whenFlipped(listener: `[`TernaryFlipListener`](-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlippedTernary](when-flipped-ternary.md) | `abstract fun whenFlippedTernary(listener: `[`TernaryFlipListener`](-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenFlippedTernary(listener: (state: `[`SwitchState`](-switch-state/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [cancelWhenActive](../-i-switch/cancel-when-active.md) | `abstract fun cancelWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelWhenPressed](../-i-switch/cancel-when-pressed.md) | `abstract fun cancelWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](../-i-switch/get.md) | `abstract fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggleWhenActive](../-i-switch/toggle-when-active.md) | `abstract fun toggleWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggleWhenPressed](../-i-switch/toggle-when-pressed.md) | `abstract fun toggleWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenActive](../-i-switch/when-active.md) | `abstract fun whenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlipped](../-i-switch/when-flipped.md) | `abstract fun whenFlipped(listener: `[`FlipListener`](../-i-switch/-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun whenFlipped(listener: (on: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenInactive](../-i-switch/when-inactive.md) | `abstract fun whenInactive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenPressed](../-i-switch/when-pressed.md) | `abstract fun whenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](../-i-switch/when-released.md) | `abstract fun whenReleased(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileActive](../-i-switch/while-active.md) | `abstract fun whileActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](../-i-switch/while-held.md) | `abstract fun whileHeld(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyTernarySwitch](../-dummy-ternary-switch/index.md) | `class DummyTernarySwitch : `[`DummySwitch`](../-dummy-switch/index.md)`, `[`ITernarySwitch`](./index.md) |
| [TernarySwitch](../-ternary-switch/index.md) | `class TernarySwitch : `[`SwitchControl`](../-switch-control/index.md)`, `[`ITernarySwitch`](./index.md)<br>Create a joystick button for triggering commands. |
