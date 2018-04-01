[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [RemoteFeedbackFilterConfig](./index.md)

# RemoteFeedbackFilterConfig

`data class RemoteFeedbackFilterConfig`

Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote device
and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features.

**See Also**

[TalonSRX.configRemoteFeedbackFilter](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RemoteFeedbackFilterConfig(deviceID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, remoteSensorSource: RemoteSensorSource, remoteOrdinal: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote device and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features. |

### Properties

| Name | Summary |
|---|---|
| [deviceID](device-i-d.md) | `val deviceID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The CAN ID of the remote sensor device. |
| [remoteOrdinal](remote-ordinal.md) | `val remoteOrdinal: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>0 for configuring Remote Sensor 0 1 for configuring Remote Sensor 1 |
| [remoteSensorSource](remote-sensor-source.md) | `val remoteSensorSource: RemoteSensorSource`<br>The remote sensor device and signal type to bind. |
