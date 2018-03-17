[PowerUp](../../index.md) / [org.stormgears.utils.sensor_drivers](../index.md) / [LidarSensorTest](./index.md)

# LidarSensorTest

`open class LidarSensorTest`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LidarSensorTest()` |

### Properties

| Name | Summary |
|---|---|
| [I2CFAILURE](-i2-c-f-a-i-l-u-r-e.md) | `static val I2CFAILURE: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [I2CSUCCESS](-i2-c-s-u-c-c-e-s-s.md) | `static val I2CSUCCESS: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| Name | Summary |
|---|---|
| [blink](blink.md) | `open fun blink(rate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [byteArrayToHexString](byte-array-to-hex-string.md) | `open static fun byteArrayToHexString(in: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Turn a byte array into something more readable for display, using the characters 0-9, A-F to construct the hex value of each byte. <br> For example, if your byte array is {'A', 'B', 'C'} then this function will return "414243". <br> Thanks to StackOverflow for this implementation. It was originally called byteToHexString() |
| [debug](debug.md) | `open fun debug(message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Optionally logs a message depending on whether the class level debug member is set to true. If the debug member is set to false this function does nothing. |
| [doIt](do-it.md) | `open fun doIt(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [fast](fast.md) | `open fun fast(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [fetchCounter](fetch-counter.md) | `open fun fetchCounter(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDebug](get-debug.md) | `open fun getDebug(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDeviceAddress](get-device-address.md) | `open fun getDeviceAddress(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getSensorCount](get-sensor-count.md) | `open fun getSensorCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getSimulation](get-simulation.md) | `open fun getSimulation(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hexStringToByteArray](hex-string-to-byte-array.md) | `open static fun hexStringToByteArray(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)<br>Creates a byte array from a string containing the hex representation of each byte using the characters 0-9, A-F to represent each byte. <br> For example, if your input string is "414243" then the resulting byte array will be {'A', 'B', 'C'} <br> Thanks to StackOverflow for this implementation. |
| [log](log.md) | `open fun log(message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Unconditionally logs a message. |
| [ping](ping.md) | `open fun ping(): `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html) |
| [setDebug](set-debug.md) | `open fun setDebug(debug: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setDeviceAddress](set-device-address.md) | `open fun setDeviceAddress(deviceAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSensorCount](set-sensor-count.md) | `open fun setSensorCount(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSimulation](set-simulation.md) | `open fun setSimulation(simulation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [slow](slow.md) | `open fun slow(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [transaction](transaction.md) | `open fun transaction(dataToSend: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, sendSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dataReceived: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, receiveSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
