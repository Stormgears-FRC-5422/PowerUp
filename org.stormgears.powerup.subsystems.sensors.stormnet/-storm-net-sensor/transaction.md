[PowerUp](../../index.md) / [org.stormgears.powerup.subsystems.sensors.stormnet](../index.md) / [StormNetSensor](index.md) / [transaction](./transaction.md)

# transaction

`open fun transaction(dataToSend: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, sendSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dataReceived: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`, receiveSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A low-level routine that executes a transaction using the appropriate voice.

 Adds additional logging and creates a simulation mode to allow bytes to be returned even if nothing is connected. Simulation mode always sends back bytes receiveSize bytes from 0 to (receiveSize-1).

 All StormNetSensor command calls eventually use this transaction method.

 Performs debug-level logging in debug mode.

### Parameters

`dataToSend` - Buffer of data to send from master to slave to initiate the transaction.

`sendSize` - Number of bytes to send.

`dataReceived` - Pre-allocated buffer to receive data sent from slave to end the transaction.

`receiveSize` - Number of bytes to read from I2C bus.

**See Also**
edu.wpi.first.wpilibj.I2C

