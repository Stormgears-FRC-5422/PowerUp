[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [forwardLimitSwitchSource](./forward-limit-switch-source.md)

# forwardLimitSwitchSource

`abstract val forwardLimitSwitchSource: `[`LimitSwitchSourceConfig`](../-limit-switch-source-config.md)

Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device
ID of zero is assumed. If that's not desired, use the four parameter version of this function.

**See Also**

[TalonSRX.configForwardLimitSwitchSource](#)

