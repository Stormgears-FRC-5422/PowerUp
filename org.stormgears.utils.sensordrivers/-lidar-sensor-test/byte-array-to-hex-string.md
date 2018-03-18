[PowerUp](../../index.md) / [org.stormgears.utils.sensordrivers](../index.md) / [LidarSensorTest](index.md) / [byteArrayToHexString](./byte-array-to-hex-string.md)

# byteArrayToHexString

`open static fun byteArrayToHexString(in: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Turn a byte array into something more readable for display, using the characters 0-9, A-F to construct the hex value of each byte.

 For example, if your byte array is {'A', 'B', 'C'} then this function will return "414243".

 Thanks to StackOverflow for this implementation. It was originally called byteToHexString()

### Parameters

`in` - a byte array

**Return**
the hex representation of the byte array as a String

