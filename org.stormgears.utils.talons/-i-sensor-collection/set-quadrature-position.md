[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [ISensorCollection](index.md) / [setQuadraturePosition](./set-quadrature-position.md)

# setQuadraturePosition

`abstract fun setQuadraturePosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`

Change the quadrature reported position.  Typically this is used to "zero" the
sensor. This only works with Quadrature sensor.  To set the selected sensor position
regardless of what type it is, see SetSelectedSensorPosition in the motor controller class.

### Parameters

`newPosition` - The position value to apply to the sensor.

`timeoutMs` - Timeout value in ms. If nonzero, function will wait for
config success and report an error if it times out.
If zero, no blocking or checking is performed.

**Return**
error code.

