[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [ManagedTalon](./index.md)

# ManagedTalon

`class ManagedTalon : `[`IBaseTalon`](../-i-base-talon/index.md)`, `[`ITalon`](../-i-talon/index.md)`, `[`GeneratedTalonManager`](../-generated-talon-manager/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ManagedTalon(base: `[`IBaseTalon`](../-i-base-talon/index.md)`, defaultConfig: `[`TalonConfig`](../-talon-config/index.md)` = FactoryTalonConfig())` |

### Properties

| Name | Summary |
|---|---|
| [base](base.md) | `val base: `[`IBaseTalon`](../-i-base-talon/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [prevConfig](../-generated-talon-manager/prev-config.md) | `var prevConfig: `[`TalonConfig`](../-talon-config/index.md)`?` |
| [talon](../-generated-talon-manager/talon.md) | `val talon: `[`IBaseTalon`](../-i-base-talon/index.md) |

### Functions

| Name | Summary |
|---|---|
| [set](set.md) | `fun set(Mode: ControlMode?, demand0: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, demand1: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun set(Mode: ControlMode?, demand0: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, demand1Type: DemandType?, demand1: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun set(Mode: ControlMode?, demand: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun set(speed: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setConfig](set-config.md) | `fun setConfig(config: `[`TalonConfig`](../-talon-config/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
