[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [LocalLimitSwitchSourceConfig](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`LocalLimitSwitchSourceConfig(type: LimitSwitchSource, normalOpenOrClose: LimitSwitchNormal)`

Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID
of zero is assumed. If that's not desired, use the four parameter version of this function.

**See Also**

[TalonSRX.configForwardLimitSwitchSource](#)

