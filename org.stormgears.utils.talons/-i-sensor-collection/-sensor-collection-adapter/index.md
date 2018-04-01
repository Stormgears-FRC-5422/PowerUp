[PowerUp](../../../index.md) / [org.stormgears.utils.talons](../../index.md) / [ISensorCollection](../index.md) / [SensorCollectionAdapter](./index.md)

# SensorCollectionAdapter

`class SensorCollectionAdapter : `[`ISensorCollection`](../index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SensorCollectionAdapter(sensorCollection: SensorCollection)` |

### Properties

| Name | Summary |
|---|---|
| [analogIn](analog-in.md) | `val analogIn: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the position of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [analogInRaw](analog-in-raw.md) | `val analogInRaw: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the position of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [analogInVel](analog-in-vel.md) | `val analogInVel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the velocity of whatever is in the analog pin of the Talon, regardless of whether it is actually being used for feedback. |
| [isFwdLimitSwitchClosed](is-fwd-limit-switch-closed.md) | `val isFwdLimitSwitchClosed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Is forward limit switch closed. |
| [isRevLimitSwitchClosed](is-rev-limit-switch-closed.md) | `val isRevLimitSwitchClosed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Is reverse limit switch closed. |
| [pinStateQuadA](pin-state-quad-a.md) | `val pinStateQuadA: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad a. |
| [pinStateQuadB](pin-state-quad-b.md) | `val pinStateQuadB: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad b. |
| [pinStateQuadIdx](pin-state-quad-idx.md) | `val pinStateQuadIdx: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Gets pin state quad index. |
| [pulseWidthPosition](pulse-width-position.md) | `val pulseWidthPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width position, regardless of whether it is actually being used for feedback. |
| [pulseWidthRiseToFallUs](pulse-width-rise-to-fall-us.md) | `val pulseWidthRiseToFallUs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width rise to fall time. |
| [pulseWidthRiseToRiseUs](pulse-width-rise-to-rise-us.md) | `val pulseWidthRiseToRiseUs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width rise to rise time. |
| [pulseWidthVelocity](pulse-width-velocity.md) | `val pulseWidthVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets pulse width velocity, regardless of whether it is actually being used for feedback. |
| [quadraturePosition](quadrature-position.md) | `val quadraturePosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the quadrature position of the Talon, regardless of whether it is actually being used for feedback. |
| [quadratureVelocity](quadrature-velocity.md) | `val quadratureVelocity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the quadrature velocity, regardless of whether it is actually being used for feedback. |

### Functions

| Name | Summary |
|---|---|
| [setAnalogPosition](set-analog-position.md) | `fun setAnalogPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Sets analog position. |
| [setPulseWidthPosition](set-pulse-width-position.md) | `fun setPulseWidthPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Sets pulse width position. |
| [setQuadraturePosition](set-quadrature-position.md) | `fun setQuadraturePosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`<br>Change the quadrature reported position.  Typically this is used to "zero" the sensor. This only works with Quadrature sensor.  To set the selected sensor position regardless of what type it is, see SetSelectedSensorPosition in the motor controller class. |
