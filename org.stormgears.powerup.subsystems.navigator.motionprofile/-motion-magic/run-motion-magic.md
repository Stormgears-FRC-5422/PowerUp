[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator.motionprofile](../index.md) / [MotionMagic](index.md) / [runMotionMagic](./run-motion-magic.md)

# runMotionMagic

`fun runMotionMagic(targetPos: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

The runMotionMagic method receives an encoder position
(8192 ticks / 1 revolution) and uses the MotionMagic
ControlMode along with PID to get to the commanded position.
This class and method applies to only one talon.

### Parameters

`targetPos` -
* encoder position
