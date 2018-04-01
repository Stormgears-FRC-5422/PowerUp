[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [motionProfileTrajectoryPeriod](./motion-profile-trajectory-period.md)

# motionProfileTrajectoryPeriod

`abstract val motionProfileTrajectoryPeriod: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

When trajectory points are processed in the motion profile executer, the MPE determines how long to apply the
active trajectory point by summing baseTrajDurationMs with the timeDur of the trajectory point (see
[com.ctre.phoenix.motion.TrajectoryPoint](#)). This allows general selection of the execution rate of the points
with 1ms resolution, while allowing some degree of change from point to point.

**See Also**

[TalonSRX.configMotionProfileTrajectoryPeriod](#)

