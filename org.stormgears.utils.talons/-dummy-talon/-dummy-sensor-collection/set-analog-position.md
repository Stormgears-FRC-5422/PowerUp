[PowerUp](../../../index.md) / [org.stormgears.utils.talons](../../index.md) / [DummyTalon](../index.md) / [DummySensorCollection](index.md) / [setAnalogPosition](./set-analog-position.md)

# setAnalogPosition

`fun setAnalogPosition(newPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeoutMs: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ErrorCode`

Overrides [ISensorCollection.setAnalogPosition](../../-i-sensor-collection/set-analog-position.md)

Sets analog position.

### Parameters

`newPosition` - The new position.

`timeoutMs` - Timeout value in ms. If nonzero, function will wait for
config success and report an error if it times out.
If zero, no blocking or checking is performed.

**Return**
an ErrorCode.

