[PowerUp](../../../index.md) / [org.stormgears.powerup.subsystems.elevatorclimber](../../index.md) / [ElevatorSharedTalons](../index.md) / [ElevatorTalonConfig](index.md) / [forwardLimitSwitchSource](./forward-limit-switch-source.md)

# forwardLimitSwitchSource

`val forwardLimitSwitchSource: `[`LocalLimitSwitchSourceConfig`](../../../org.stormgears.utils.talons/-local-limit-switch-source-config/index.md)

Overrides [FactoryTalonConfig.forwardLimitSwitchSource](../../../org.stormgears.utils.talons/-factory-talon-config/forward-limit-switch-source.md)

Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device
ID of zero is assumed. If that's not desired, use the four parameter version of this function.

**See Also**

[TalonSRX.configForwardLimitSwitchSource](#)

