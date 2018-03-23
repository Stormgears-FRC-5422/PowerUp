[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.field](../index.md) / [AutoRoute](./index.md)

# AutoRoute

`interface AutoRoute`

### Properties

| Name | Summary |
|---|---|
| [pathToCrossLine](path-to-cross-line.md) | `abstract val pathToCrossLine: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [pathToLeftScale](path-to-left-scale.md) | `abstract val pathToLeftScale: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [pathToLeftSwitch](path-to-left-switch.md) | `abstract val pathToLeftSwitch: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [pathToRightScale](path-to-right-scale.md) | `abstract val pathToRightScale: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [pathToRightSwitch](path-to-right-switch.md) | `abstract val pathToRightSwitch: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [strafeToLeftScale](strafe-to-left-scale.md) | `abstract val strafeToLeftScale: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |
| [strafeToRightScale](strafe-to-right-scale.md) | `abstract val strafeToRightScale: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Segment`](../-segment/index.md)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [FromCenter](../-auto-routes/-from-center/index.md) | `object FromCenter : `[`AutoRoute`](./index.md) |
| [FromLeft](../-auto-routes/-from-left/index.md) | `object FromLeft : `[`AutoRoute`](./index.md) |
| [FromRight](../-auto-routes/-from-right/index.md) | `object FromRight : `[`AutoRoute`](./index.md) |
