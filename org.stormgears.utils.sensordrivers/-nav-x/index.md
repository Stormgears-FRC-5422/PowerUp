[PowerUp](../../index.md) / [org.stormgears.utils.sensordrivers](../index.md) / [NavX](./index.md)

# NavX

`class NavX`

### Types

| Name | Summary |
|---|---|
| [AngleUnit](-angle-unit/index.md) | `enum class AngleUnit` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NavX()` |

### Properties

| Name | Summary |
|---|---|
| [ahrs](ahrs.md) | `val ahrs: AHRS` |
| [compassHeading](compass-heading.md) | `val compassHeading: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [isCalibrating](is-calibrating.md) | `val isCalibrating: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isMagneticDisturbance](is-magnetic-disturbance.md) | `val isMagneticDisturbance: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [velocityY](velocity-y.md) | `val velocityY: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |

### Functions

| Name | Summary |
|---|---|
| [debug](debug.md) | `fun debug(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getDisplacementX](get-displacement-x.md) | `fun getDisplacementX(): `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>TODO: This method is sketchy at times, we need to fix it |
| [getTheta](get-theta.md) | `fun getTheta(unit: `[`AngleUnit`](-angle-unit/index.md)` = AngleUnit.Radians, wrap: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the angle of the robot in radians |
| [getVelocityX](get-velocity-x.md) | `fun getVelocityX(): `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [setInitialTheta](set-initial-theta.md) | `fun setInitialTheta(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [test](test.md) | `fun test(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [thetaIsSet](theta-is-set.md) | `fun thetaIsSet(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
