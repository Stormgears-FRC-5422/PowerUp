[PowerUp](../index.md) / [org.stormgears.utils.talons](index.md) / [LimitSwitchSourceConfig](./-limit-switch-source-config.md)

# LimitSwitchSourceConfig

`interface LimitSwitchSourceConfig`

### Inheritors

| Name | Summary |
|---|---|
| [LocalLimitSwitchSourceConfig](-local-limit-switch-source-config/index.md) | `data class LocalLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](./-limit-switch-source-config.md)<br>Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID of zero is assumed. If that's not desired, use the four parameter version of this function. |
| [RemoteLimitSwitchSourceConfig](-remote-limit-switch-source-config/index.md) | `data class RemoteLimitSwitchSourceConfig : `[`LimitSwitchSourceConfig`](./-limit-switch-source-config.md)<br>Configures the limit switch for a remote source. For example, a CAN motor controller may need to monitor the Limit-F pin of another Talon or CANifier. |
