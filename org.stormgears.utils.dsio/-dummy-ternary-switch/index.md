[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [DummyTernarySwitch](./index.md)

# DummyTernarySwitch

`class DummyTernarySwitch : `[`DummySwitch`](../-dummy-switch/index.md)`, `[`ITernarySwitch`](../-i-ternary-switch/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DummyTernarySwitch()` |

### Functions

| Name | Summary |
|---|---|
| [whenFlipped](when-flipped.md) | `fun whenFlipped(listener: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenFlipped(listener: `[`TernaryFlipListener`](../-i-ternary-switch/-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlippedTernary](when-flipped-ternary.md) | `fun whenFlippedTernary(listener: (state: `[`SwitchState`](../-i-ternary-switch/-switch-state/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun whenFlippedTernary(listener: `[`TernaryFlipListener`](../-i-ternary-switch/-ternary-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [cancelWhenActive](../-dummy-switch/cancel-when-active.md) | `open fun cancelWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelWhenPressed](../-dummy-switch/cancel-when-pressed.md) | `open fun cancelWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](../-dummy-switch/get.md) | `open fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggleWhenActive](../-dummy-switch/toggle-when-active.md) | `open fun toggleWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggleWhenPressed](../-dummy-switch/toggle-when-pressed.md) | `open fun toggleWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenActive](../-dummy-switch/when-active.md) | `open fun whenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlipped](../-dummy-switch/when-flipped.md) | `open fun whenFlipped(listener: `[`FlipListener`](../-i-switch/-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenInactive](../-dummy-switch/when-inactive.md) | `open fun whenInactive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenPressed](../-dummy-switch/when-pressed.md) | `open fun whenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](../-dummy-switch/when-released.md) | `open fun whenReleased(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileActive](../-dummy-switch/while-active.md) | `open fun whileActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](../-dummy-switch/while-held.md) | `open fun whileHeld(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
