[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.sensors.stormnet](../index.md) / [StormNetVoice](./index.md)

# StormNetVoice

`abstract class StormNetVoice`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StormNetVoice()` |

### Functions

| Name | Summary |
|---|---|
| [getDeviceString](get-device-string.md) | `abstract fun getDeviceString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [transaction](transaction.md) | `open fun transaction(dataToSend: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, sendSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dataReceived: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, receiveSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [EthernetVoice](../-ethernet-voice/index.md) | `open class EthernetVoice : `[`StormNetVoice`](./index.md) |
| [I2CEthernetVoice](../-i2-c-ethernet-voice/index.md) | `open class I2CEthernetVoice : `[`StormNetVoice`](./index.md) |
| [I2CVoice](../-i2-c-voice/index.md) | `open class I2CVoice : `[`StormNetVoice`](./index.md) |
