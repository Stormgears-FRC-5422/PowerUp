[PowerUp](../../index.md) / [org.stormgears.utils.sensordrivers](../index.md) / [LidarSensorTest](index.md) / [hexStringToByteArray](./hex-string-to-byte-array.md)

# hexStringToByteArray

`open static fun hexStringToByteArray(s: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)

Creates a byte array from a string containing the hex representation of each byte using the characters 0-9, A-F to represent each byte.

 For example, if your input string is "414243" then the resulting byte array will be {'A', 'B', 'C'}

 Thanks to StackOverflow for this implementation.

### Parameters

`s` - A hex string representation of a byte array

**Return**
The resulting byte array

