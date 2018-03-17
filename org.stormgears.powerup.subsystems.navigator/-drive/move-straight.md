[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator](../index.md) / [Drive](index.md) / [moveStraight](./move-straight.md)

# moveStraight

`suspend fun moveStraight(distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, theta: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

The intended purpose of this method is to move the robot at a given
angle (theta) for a given distance (distance). The distance should be
given in inches, and the theta in radians. The method will convert the
distance into encoder ticks in order to facilitate motion magic. Ensure
that when calling this method.

### Parameters

`distance` -
* the distance to move the robot in inches

`theta` -
* the angle at which to move the robot
