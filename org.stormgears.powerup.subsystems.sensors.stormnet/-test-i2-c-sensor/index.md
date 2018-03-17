[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.sensors.stormnet](../index.md) / [TestI2CSensor](./index.md)

# TestI2CSensor

`open class TestI2CSensor : `[`StormNetSensor`](../-storm-net-sensor/index.md)

### Inherited Properties

| Name | Summary |
|---|---|
| [STORMNET_FAILURE](../-storm-net-sensor/-s-t-o-r-m-n-e-t_-f-a-i-l-u-r-e.md) | `static val STORMNET_FAILURE: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [STORMNET_SUCCESS](../-storm-net-sensor/-s-t-o-r-m-n-e-t_-s-u-c-c-e-s-s.md) | `static val STORMNET_SUCCESS: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| Name | Summary |
|---|---|
| [test](test.md) | `open fun test(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [blink](../-storm-net-sensor/blink.md) | `open fun blink(rate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [fast](../-storm-net-sensor/fast.md) | `open fun fast(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [fetchCounter](../-storm-net-sensor/fetch-counter.md) | `open fun fetchCounter(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDebug](../-storm-net-sensor/get-debug.md) | `open fun getDebug(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSensorCount](../-storm-net-sensor/get-sensor-count.md) | `open fun getSensorCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getSimulation](../-storm-net-sensor/get-simulation.md) | `open fun getSimulation(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hexStringToByteArray](../-storm-net-sensor/hex-string-to-byte-array.md) | `open static fun hexStringToByteArray(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)<br>Creates a byte array from a string containing the hex representation of each byte using the characters 0-9, A-F to represent each byte. <br> For example, if your input string is "414243" then the resulting byte array will be {'A', 'B', 'C'} <br> Thanks to StackOverflow for this implementation. |
| [ping](../-storm-net-sensor/ping.md) | `open fun ping(): `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html) |
| [setDebug](../-storm-net-sensor/set-debug.md) | `open fun setDebug(debug: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSensorCount](../-storm-net-sensor/set-sensor-count.md) | `open fun setSensorCount(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setSimulation](../-storm-net-sensor/set-simulation.md) | `open fun setSimulation(simulation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [slow](../-storm-net-sensor/slow.md) | `open fun slow(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [test](../-storm-net-sensor/test.md) | `open fun test(sleep: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [transaction](../-storm-net-sensor/transaction.md) | `open fun transaction(dataToSend: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, sendSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dataReceived: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, receiveSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A low-level routine that executes a transaction using the appropriate voice. <br> Adds additional logging and creates a simulation mode to allow bytes to be returned even if nothing is connected. Simulation mode always sends back bytes receiveSize bytes from 0 to (receiveSize-1). <br> All StormNetSensor command calls eventually use this transaction method. <br> Performs debug-level logging in debug mode. |
