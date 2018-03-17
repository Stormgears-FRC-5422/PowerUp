[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.navigator](../index.md) / [PowerUpMecanumDrive](./index.md)

# PowerUpMecanumDrive

`open class PowerUpMecanumDrive : MecanumDrive`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PowerUpMecanumDrive(frontLeftMotor: SpeedController, rearLeftMotor: SpeedController, frontRightMotor: SpeedController, rearRightMotor: SpeedController)` |

### Functions

| Name | Summary |
|---|---|
| [driveCartesian](drive-cartesian.md) | `open fun driveCartesian(xSpeed: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, ySpeed: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, zRotation: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, gyroAngle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getInstance](get-instance.md) | `open static fun getInstance(): `[`PowerUpMecanumDrive`](./index.md) |
| [init](init.md) | `open static fun init(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [move](move.md) | `open fun move(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [moveFieldOriented](move-field-oriented.md) | `open fun moveFieldOriented(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [turnTo](turn-to.md) | `open fun turnTo(theta: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
