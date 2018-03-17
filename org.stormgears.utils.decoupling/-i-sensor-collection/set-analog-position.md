[PowerUp](../../index.md) / [org.stormgears.utils.decoupling](../index.md) / [ISensorCollection](index.md) / [setAnalogPosition](./set-analog-position.md)

# setAnalogPosition

`abstract fun setAnalogPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`

Sets analog position.

### Parameters

`newPosition` - The new position.

`timeoutMs` - Timeout value in ms. If nonzero, function will wait for
config success and report an error if it times out.
If zero, no blocking or checking is performed.

**Return**
an ErrorCode.

