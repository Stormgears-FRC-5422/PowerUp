[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [RemoteLimitSwitchSourceConfig](./index.md)

# RemoteLimitSwitchSourceConfig

`data class RemoteLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](../-limit-switch-source-config.md)

Configures the limit switch for a remote source. For example, a CAN motor controller may need to monitor the
Limit-F pin of another Talon or CANifier.

**See Also**

[TalonSRX.configForwardLimitSwitchSource](#)

[TalonSRX.configReverseLimitSwitchSource](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RemoteLimitSwitchSourceConfig(type: RemoteLimitSwitchSource, normalOpenOrClose: LimitSwitchNormal, deviceID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Configures the limit switch for a remote source. For example, a CAN motor controller may need to monitor the Limit-F pin of another Talon or CANifier. |

### Properties

| Name | Summary |
|---|---|
| [deviceID](device-i-d.md) | `val deviceID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Device ID of remote source (Talon SRX or CANifier device ID). |
| [normalOpenOrClose](normal-open-or-close.md) | `val normalOpenOrClose: LimitSwitchNormal`<br>Setting for normally open, normally closed, or disabled. This setting matches the web-based configuration drop down. |
| [type](type.md) | `val type: RemoteLimitSwitchSource`<br>Remote limit switch source. User can choose between a remote Talon SRX, CANifier, or deactivate the feature. |
