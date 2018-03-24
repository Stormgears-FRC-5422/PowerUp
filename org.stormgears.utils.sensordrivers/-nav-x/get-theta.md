[PowerUp](../../index.md) / [org.stormgears.utils.sensordrivers](../index.md) / [NavX](index.md) / [getTheta](./get-theta.md)

# getTheta

`fun getTheta(unit: `[`AngleUnit`](-angle-unit/index.md)` = AngleUnit.Radians, wrap: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)

Gets the angle of the robot in radians

Make sure that you wrap calls to this method in "if (navX.thetaIsSet())"

**Return**
the angle of the robot in radians, throws IllegalStateException if theta is not set

