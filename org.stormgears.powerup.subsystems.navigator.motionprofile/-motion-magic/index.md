[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator.motionprofile](../index.md) / [MotionMagic](./index.md)

# MotionMagic

`class MotionMagic`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MotionMagic(talon: `[`ITalon`](../../org.stormgears.utils.decoupling/-i-talon/index.md)`, maxVel: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, maxAccel: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [runMotionMagic](run-motion-magic.md) | `fun runMotionMagic(targetPos: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>The runMotionMagic method receives an encoder position (8192 ticks / 1 revolution) and uses the MotionMagic ControlMode along with PID to get to the commanded position. This class and method applies to only one talon. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [TALON_FPID_TIMEOUT](-t-a-l-o-n_-f-p-i-d_-t-i-m-e-o-u-t.md) | `const val TALON_FPID_TIMEOUT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
