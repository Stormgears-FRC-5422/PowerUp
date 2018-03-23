[PowerUp](../../index.md) / [org.stormgears.utils.sensordrivers](../index.md) / [NavX](./index.md)

# NavX

`open class NavX`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NavX()` |

### Functions

| Name | Summary |
|---|---|
| [debug](debug.md) | `open fun debug(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAhrs](get-ahrs.md) | `open fun getAhrs(): AHRS` |
| [getDisplacementX](get-displacement-x.md) | `open fun getDisplacementX(): `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>TODO: This method is sketchy at times, we need to fix it <br> Gets the displacement in the X direction of the robot from 0,0 |
| [getTheta](get-theta.md) | `open fun getTheta(): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the angle of the robot in radians <br> Make sure that you wrap calls to this method in "if (navX.thetaIsSet())" |
| [getVelocityX](get-velocity-x.md) | `open fun getVelocityX(): `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [getVelocityY](get-velocity-y.md) | `open fun getVelocityY(): `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [isCalibrating](is-calibrating.md) | `open fun isCalibrating(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setInitialTheta](set-initial-theta.md) | `open fun setInitialTheta(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [test](test.md) | `open fun test(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [thetaIsSet](theta-is-set.md) | `open fun thetaIsSet(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
