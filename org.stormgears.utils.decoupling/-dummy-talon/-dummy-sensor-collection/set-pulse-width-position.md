[PowerUp](../../../index.md) / [org.stormgears.utils.decoupling](../../index.md) / [DummyTalon](../index.md) / [DummySensorCollection](index.md) / [setPulseWidthPosition](./set-pulse-width-position.md)

# setPulseWidthPosition

`fun setPulseWidthPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`

Overrides [ISensorCollection.setPulseWidthPosition](../../-i-sensor-collection/set-pulse-width-position.md)

Sets pulse width position.

### Parameters

`newPosition` - The position value to apply to the sensor.

`timeoutMs` - Timeout value in ms. If nonzero, function will wait for
config success and report an error if it times out.
If zero, no blocking or checking is performed.

**Return**
an ErrErrorCode
