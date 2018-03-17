[PowerUp](../../index.md) / [org.stormgears.utils.decoupling](../index.md) / [ISensorCollection](./index.md)

# ISensorCollection

`interface ISensorCollection`

### Types

| Name | Summary |
|---|---|
| [SensorCollectionAdapter](-sensor-collection-adapter/index.md) | `class SensorCollectionAdapter : `[`ISensorCollection`](./index.md) |

### Properties

| Name | Summary |
|---|---|
| [analogIn](analog-in.md) | `abstract val analogIn: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the position of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [analogInRaw](analog-in-raw.md) | `abstract val analogInRaw: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the position of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [analogInVel](analog-in-vel.md) | `abstract val analogInVel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the velocity of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [isFwdLimitSwitchClosed](is-fwd-limit-switch-closed.md) | `abstract val isFwdLimitSwitchClosed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Is forward limit switch closed. |
| [isRevLimitSwitchClosed](is-rev-limit-switch-closed.md) | `abstract val isRevLimitSwitchClosed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Is reverse limit switch closed. |
| [pinStateQuadA](pin-state-quad-a.md) | `abstract val pinStateQuadA: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad a. |
| [pinStateQuadB](pin-state-quad-b.md) | `abstract val pinStateQuadB: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad b. |
| [pinStateQuadIdx](pin-state-quad-idx.md) | `abstract val pinStateQuadIdx: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad index. |
| [pulseWidthPosition](pulse-width-position.md) | `abstract val pulseWidthPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width position, regardless of whether it is actually being used for feedback. |
| [pulseWidthRiseToFallUs](pulse-width-rise-to-fall-us.md) | `abstract val pulseWidthRiseToFallUs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width rise to fall time. |
| [pulseWidthRiseToRiseUs](pulse-width-rise-to-rise-us.md) | `abstract val pulseWidthRiseToRiseUs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width rise to rise time. |
| [pulseWidthVelocity](pulse-width-velocity.md) | `abstract val pulseWidthVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width velocity, regardless of whether it is actually being used for feedback. |
| [quadraturePosition](quadrature-position.md) | `abstract val quadraturePosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the quadrature position of the Talon, regardless of whether it is actually being used for feedback. |
| [quadratureVelocity](quadrature-velocity.md) | `abstract val quadratureVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the quadrature velocity, regardless of whether it is actually being used for feedback. |

### Functions

| Name | Summary |
|---|---|
| [setAnalogPosition](set-analog-position.md) | `abstract fun setAnalogPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Sets analog position. |
| [setPulseWidthPosition](set-pulse-width-position.md) | `abstract fun setPulseWidthPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Sets pulse width position. |
| [setQuadraturePosition](set-quadrature-position.md) | `abstract fun setQuadraturePosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Change the quadrature reported position.  Typically this is used to "zero" the sensor. This only works with Quadrature sensor.  To set the selected sensor position regardless of what type it is, see SetSelectedSensorPosition in the motor controller class. |

### Inheritors

| Name | Summary |
|---|---|
| [DummySensorCollection](../-dummy-talon/-dummy-sensor-collection/index.md) | `class DummySensorCollection : `[`ISensorCollection`](./index.md) |
| [SensorCollectionAdapter](-sensor-collection-adapter/index.md) | `class SensorCollectionAdapter : `[`ISensorCollection`](./index.md) |
