[PowerUp](../../index.md) / [org.stormgears.utils.dsio](../index.md) / [DummySwitch](./index.md)

# DummySwitch

`open class DummySwitch : `[`ISwitch`](../-i-switch/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DummySwitch()` |

### Functions

| Name | Summary |
|---|---|
| [cancelWhenActive](cancel-when-active.md) | `open fun cancelWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelWhenPressed](cancel-when-pressed.md) | `open fun cancelWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](get.md) | `open fun get(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggleWhenActive](toggle-when-active.md) | `open fun toggleWhenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toggleWhenPressed](toggle-when-pressed.md) | `open fun toggleWhenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenActive](when-active.md) | `open fun whenActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenFlipped](when-flipped.md) | `open fun whenFlipped(listener: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun whenFlipped(listener: `[`FlipListener`](../-i-switch/-flip-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenInactive](when-inactive.md) | `open fun whenInactive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenPressed](when-pressed.md) | `open fun whenPressed(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whenReleased](when-released.md) | `open fun whenReleased(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileActive](while-active.md) | `open fun whileActive(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [whileHeld](while-held.md) | `open fun whileHeld(command: Command): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DummyTernarySwitch](../-dummy-ternary-switch/index.md) | `class DummyTernarySwitch : `[`DummySwitch`](./index.md)`, `[`ITernarySwitch`](../-i-ternary-switch/index.md) |
