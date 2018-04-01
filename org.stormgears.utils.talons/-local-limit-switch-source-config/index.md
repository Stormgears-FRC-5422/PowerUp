[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [LocalLimitSwitchSourceConfig](./index.md)

# LocalLimitSwitchSourceConfig

`data class LocalLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](../-limit-switch-source-config.md)

Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID
of zero is assumed. If that's not desired, use the four parameter version of this function.

**See Also**

[TalonSRX.configForwardLimitSwitchSource](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocalLimitSwitchSourceConfig(type: LimitSwitchSource, normalOpenOrClose: LimitSwitchNormal)`<br>Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID of zero is assumed. If that's not desired, use the four parameter version of this function. |

### Properties

| Name | Summary |
|---|---|
| [normalOpenOrClose](normal-open-or-close.md) | `val normalOpenOrClose: LimitSwitchNormal`<br>Setting for normally open, normally closed, or disabled. This setting matches the web-based configuration drop down. |
| [type](type.md) | `val type: LimitSwitchSource`<br>Limit switch source. See [LimitSwitchSource](#). User can choose between the feedback connector, remote Talon SRX, CANifier, or deactivate the feature. |
