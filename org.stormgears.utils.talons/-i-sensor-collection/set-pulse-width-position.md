[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [ISensorCollection](index.md) / [setPulseWidthPosition](./set-pulse-width-position.md)

# setPulseWidthPosition

`abstract fun setPulseWidthPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`

Sets pulse width position.

### Parameters

`newPosition` - The position value to apply to the sensor.

`timeoutMs` - Timeout value in ms. If nonzero, function will wait for
config success and report an error if it times out.
If zero, no blocking or checking is performed.

**Return**
an ErrErrorCode

