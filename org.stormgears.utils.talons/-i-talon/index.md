[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [ITalon](./index.md)

# ITalon

`interface ITalon : `[`IBaseTalon`](../-i-base-talon/index.md)

Talon + Config management

### Inherited Properties

| Name | Summary |
|---|---|
| [dummy](../-i-base-talon/dummy.md) | `abstract val dummy: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sensorCollection](../-i-base-talon/sensor-collection.md) | `abstract val sensorCollection: `[`ISensorCollection`](../-i-sensor-collection/index.md) |

### Functions

| Name | Summary |
|---|---|
| [setConfig](set-config.md) | `abstract fun setConfig(config: `[`TalonConfig`](../-talon-config/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [TalonManager](../-talon-manager/index.md) | `class TalonManager : `[`IBaseTalon`](../-i-base-talon/index.md)`, `[`ITalon`](./index.md)`, `[`GeneratedTalonManager`](../-generated-talon-manager/index.md) |
