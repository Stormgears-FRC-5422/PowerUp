package org.stormgears.utils.talons

import com.ctre.phoenix.motorcontrol.*
import com.ctre.phoenix.motorcontrol.can.TalonSRX

/**
 * Configuration values for a Talon
 */
@GenerateTalonConfigReconciler
interface TalonConfig {
	/**
	 * Profile slot 0
	 *
	 * @see TalonSRX.selectProfileSlot
	 */
	@get:PIDSlotConfig(0)
	val profileSlot0: PIDSlot

	/**
	 * Profile slot 1
	 *
	 * @see TalonSRX.selectProfileSlot
	 */
	@get:PIDSlotConfig(1)
	val profileSlot1: PIDSlot

	/**
	 * Profile slot 2
	 *
	 * @see TalonSRX.selectProfileSlot
	 */
	@get:PIDSlotConfig(2)
	val profileSlot2: PIDSlot

	/**
	 * Profile slot 3
	 *
	 * @see TalonSRX.selectProfileSlot
	 */
	@get:PIDSlotConfig(3)
	val profileSlot3: PIDSlot

	/**
	 * Configures the Polarity of the Auxiliary PID (PID1). Standard Polarity: Primary Output = PID0 + PID1 Auxiliary Output
	 * = PID0 - PID1 Inverted Polarity: Primary Output = PID0 - PID1 Auxiliary Output = PID0 + PID1
	 *
	 * @see TalonSRX.configAuxPIDPolarity
	 */
	@get:Config("config")
	val auxPIDPolarity: Boolean

	/**
	 * Configures the closed-loop ramp rate of throttle output.
	 *
	 * @see TalonSRX.configClosedloopRamp
	 */
	@get:Config("config")
	val closedloopRamp: Double

	/**
	 * Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
	 * Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device
	 * ID of zero is assumed. If that's not desired, use the four parameter version of this function.
	 *
	 * @see TalonSRX.configForwardLimitSwitchSource
	 */
	@get:Config("config", deep = true)
	val forwardLimitSwitchSource: LimitSwitchSourceConfig

	/**
	 * Configures the forward soft limit enable.
	 *
	 * @see TalonSRX.configForwardSoftLimitEnable
	 */
	@get:Config("config")
	val forwardSoftLimitEnable: Boolean

	/**
	 * Configures the forward soft limit threhold.
	 *
	 * @see TalonSRX.configForwardSoftLimitThreshold
	 */
	@get:Config("config")
	val forwardSoftLimitThreshold: Int

	/**
	 * Sets the Motion Magic Acceleration. This is the target acceleration that the motion magic curve generator can use
	 * in ticks/100ms/sec.
	 *
	 * @see TalonSRX.configMotionAcceleration
	 */
	@get:Config("config")
	val motionAcceleration: Int

	/**
	 * Sets the Motion Magic Cruise Velocity. This is the peak target velocity that the motion magic curve generator can
	 * use in ticks/100ms.
	 *
	 * @see TalonSRX.configMotionCruiseVelocity
	 */
	@get:Config("config")
	val motionCruiseVelocity: Int

	/**
	 * When trajectory points are processed in the motion profile executer, the MPE determines how long to apply the
	 * active trajectory point by summing baseTrajDurationMs with the timeDur of the trajectory point (see
	 * [com.ctre.phoenix.motion.TrajectoryPoint]). This allows general selection of the execution rate of the points
	 * with 1ms resolution, while allowing some degree of change from point to point.
	 *
	 * @see TalonSRX.configMotionProfileTrajectoryPeriod
	 */
	@get:Config("config")
	val motionProfileTrajectoryPeriod: Int

	/**
	 * Configures the output deadband percentage.
	 *
	 * @see TalonSRX.configNeutralDeadband
	 */
	@get:Config("config")
	val neutralDeadband: Double

	/**
	 * Configures the forward nominal output percentage.
	 *
	 * @see TalonSRX.configNominalOutputForward
	 */
	@get:Config("config")
	val nominalOutputForward: Double

	/**
	 * @get:Config("config")
	 * Configures the reverse nominal output percentage.
	 *
	 * @see TalonSRX.configNominalOutputReverse
	 */
	@get:Config("config")
	val nominalOutputReverse: Double

	/**
	 * Configures the open-loop ramp rate of throttle output in seconds from neutral to full.
	 *
	 * @see TalonSRX.configOpenloopRamp
	 */
	@get:Config("config")
	val openloopRamp: Double

	/**
	 * Configures the forward peak output percentage.
	 *
	 * @see TalonSRX.configPeakOutputForward
	 */
	@get:Config("config")
	val peakOutputForward: Double

	/**
	 * Configures the reverse peak output percentage.
	 *
	 * @see TalonSRX.configPeakOutputReverse
	 */
	@get:Config("config")
	val peakOutputReverse: Double

	/**
	 * Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote
	 * device and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features.
	 *
	 * @see TalonSRX.configRemoteFeedbackFilter
	 */
	@get:Config("config", deep = true)
	val remoteFeedbackFilter: RemoteFeedbackFilterConfig

	/**
	 * Configures the reverse limit switch for a remote source.
	 *
	 * @see TalonSRX.configReverseLimitSwitchSource
	 */
	@get:Config("config", deep = true)
	val reverseLimitSwitchSource: LimitSwitchSourceConfig

	/**
	 * Configures the reverse soft limit enable.
	 *
	 * @see TalonSRX.configReverseSoftLimitEnable
	 */
	@get:Config("config")
	val reverseSoftLimitEnable: Boolean

	/**
	 * Configures the reverse soft limit threshold.
	 *
	 * @see TalonSRX.configReverseSoftLimitThreshold
	 */
	@get:Config("config")
	val reverseSoftLimitThreshold: Int

	/**
	 * The Feedback Coefficient is a scalar applied to the value of the feedback sensor. Useful when you need to scale
	 * your sensor values within the closed-loop calculations. Default value is 1.
	 *
	 * Selected Feedback Sensor register in firmware is the decoded sensor value multiplied by the Feedback Coefficient.
	 *
	 * @see TalonSRX.configSelectedFeedbackCoefficient
	 */
	@get:Config("config", pidIdx = true)
	val selectedFeedbackCoefficient: Double // TODO: Add support for pidIdx

	/**
	 * Select the feedback device for the motor controller.
	 *
	 * @see TalonSRX.configSelectedFeedbackSensor
	 */
	@get:Config("config", deep = true)
	val selectedFeedbackSensor: FeedbackDeviceConfig

//	/**
//	 * Select what sensor term should be bound to switch feedback device.
//	 *
//	 * Sensor Sum = Sensor Sum Term 0 - Sensor Sum Term 1
//	 *
//	 * Sensor Difference = Sensor Diff Term 0 - Sensor Diff Term 1
//	 *
//	 * The four terms are specified with this routine. Then Sensor Sum/Difference can be selected for closed-looping.
//	 *
//	 * @see TalonSRX.configSensorTerm
//	 */
//	@get:Config("config", deep = true)
//	val sensorTerm: SensorTermConfig

	/**
	 * Sets the period over which velocity measurements are taken.
	 *
	 * @see TalonSRX.configVelocityMeasurementPeriod
	 */
	@get:Config("config")
	val velocityMeasurementPeriod: VelocityMeasPeriod

	/**
	 * Sets the number of velocity samples used in the rolling average velocity measurement.
	 *
	 * @see TalonSRX.configVelocityMeasurementWindow
	 */
	@get:Config("config")
	val velocityMeasurementWindow: Int

	/**
	 * Configures the Voltage Compensation saturation voltage.
	 *
	 * @see TalonSRX.configVoltageCompSaturation
	 */
	@get:Config("config")
	val voltageCompSaturation: Double

	/**
	 * Configures the voltage measurement filter.
	 *
	 * @see TalonSRX.configVoltageMeasurementFilter
	 */
	@get:Config("config")
	val voltageMeasurementFilter: Int

//	/**
//	 * Enables a future feature called "Heading Hold". For now this simply updates the CAN signal to the motor
//	 * controller. Future firmware updates will use this.
//	 *
//	 * @see TalonSRX.enableHeadingHold
//	 */
//	@get:Config("")
//	val enableHeadingHold: Boolean

	/**
	 * Enables voltage compensation. If enabled, voltage compensation works in all control modes.
	 *
	 * @see TalonSRX.enableVoltageCompensation
	 */
	@get:Config("", false)
	val enableVoltageCompensation: Boolean

//	/**
//	 * Sets the integral accumulator. Typically this is used to clear/zero the integral accumulator, however some use cases
//	 * may require seeding the accumulator for a faster response.
//	 *
//	 * @see TalonSRX.setIntegralAccumulator
//	 */
//	val integralAccumulator: Double // TODO: add support for pidIdx

	/**
	 * Inverts the hbridge output of the motor controller. This does not impact sensor phase and should not be used to
	 * correct sensor polarity. This will invert the hbridge output but NOT the LEDs. This ensures....
	 *
	 * - Green LEDs always represents positive request from robot-controller/closed-looping mode.
	 * - Green LEDs correlates to forward limit switch.
	 * - Green LEDs correlates to forward soft limit.
	 *
	 * @see TalonSRX.setInverted
	 */
	@get:Config("set", false)
	val inverted: Boolean

	/**
	 * Sets the mode of operation during neutral throttle output.
	 *
	 * @see TalonSRX.setNeutralMode
	 */
	@get:Config("set", false)
	val neutralMode: NeutralMode

	/**
	 * Sets the phase of the sensor. Use when controller forward/reverse output doesn't correlate to appropriate
	 * forward/reverse reading of sensor. Pick a value so that positive PercentOutput yields a positive change in
	 * sensor. After setting this, user can freely call SetInvert() with any value.
	 *
	 * @see TalonSRX.setSensorPhase
	 */
	@get:Config("set", false)
	val sensorPhase: Boolean

	// Only in TalonSRX class

	/**
	 * Configure the continuous allowable current-draw (when current limit is enabled). Current limit is activated when
	 * current exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous
	 * limit. This ensures current limiting while allowing for momentary excess current events. For simpler
	 * current-limiting (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero:
	 * `ConfigPeakCurrentLimit(0)`.
	 *
	 * @see TalonSRX.configContinuousCurrentLimit
	 */
	@get:Config("config")
	val continuousCurrentLimit: Int

	/**
	 * Configure the peak allowable duration (when current limit is enabled). Current limit is activated when current
	 * exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current events. For simpler current-limiting
	 * (single threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`.
	 *
	 * @see TalonSRX.configPeakCurrentDuration
	 */
	@get:Config("config")
	val peakCurrentDuration: Int

	/**
	 * Configure the peak allowable current (when current limit is enabled). Current limit is activated when current
	 * exceeds the peak limit for longer than the peak duration. Then software will limit to the continuous limit. This
	 * ensures current limiting while allowing for momentary excess current events. For simpler current-limiting (single
	 * threshold) use `ConfigContinuousCurrentLimit()` and set the peak to zero: `ConfigPeakCurrentLimit(0)`.
	 *
	 * @see TalonSRX.configPeakCurrentLimit
	 */
	@get:Config("config")
	val peakCurrentLimit: Int
}

/**
 * Config specific to a single PID slot
 */
interface PIDSlot {
	/**
	 * Sets the Integral Zone constant in the given parameter slot. If the (absolute) closed-loop error is outside of this
	 * zone, integral accumulator is automatically cleared. This ensures than integral wind up events will stop after the
	 * sensor gets far enough from its target.
	 *
	 * @see TalonSRX.config_kI
	 */
	@get:Config("config_")
	val integralZone: Int

	/**
	 * Sets the 'D' constant in the given parameter slot.
	 *
	 * @see TalonSRX.config_kD
	 */
	@get:Config("config_", camel = false)
	val kD: Double

	/**
	 * Sets the 'F' constant in the given parameter slot.
	 *
	 * @see TalonSRX.config_kF
	 */
	@get:Config("config_", camel = false)
	val kF: Double

	/**
	 * Sets the 'I' constant in the given parameter slot.
	 *
	 * @see TalonSRX.config_kI
	 */
	@get:Config("config_", camel = false)
	val kI: Double

	/**
	 * Sets the 'P' constant in the given parameter slot.
	 *
	 * @see TalonSRX.config_kP
	 */
	@get:Config("config_", camel = false)
	val kP: Double

	/**
	 * Sets the allowable closed-loop error in the given parameter slot.
	 *
	 * @see TalonSRX.configAllowableClosedloopError
	 */
	@get:Config("config")
	val allowableClosedloopError: Int

	/**
	 * Sets the peak closed-loop output. This peak output is slot-specific and is applied to the output of the associated
	 * PID loop. This setting is separate from the generic Peak Output setting.
	 *
	 * @see TalonSRX.configClosedLoopPeakOutput
	 */
	@get:Config("config")
	val closedLoopPeakOutput: Double

	/**
	 * Sets the loop time (in milliseconds) of the PID closed-loop calculations. Default value is 1 ms.
	 *
	 * @see TalonSRX.configClosedLoopPeriod
	 */
	@get:Config("config")
	val closedLoopPeriod: Int

	/**
	 * Sets the maximum integral accumulator in the given parameter slot.
	 *
	 * @see TalonSRX.configMaxIntegralAccumulator
	 */
	@get:Config("config")
	val maxIntegralAccumulator: Double
}


interface LimitSwitchSourceConfig

/**
 * Configures a limit switch for a local/remote source. For example, a CAN motor controller may need to monitor the
 * Limit-R pin of another Talon, CANifier, or local Gadgeteer feedback connector. If the sensor is remote, a device ID
 * of zero is assumed. If that's not desired, use the four parameter version of this function.
 *
 * @see TalonSRX.configForwardLimitSwitchSource
 */
data class LocalLimitSwitchSourceConfig(
	/**
	 * Limit switch source. See [LimitSwitchSource]. User can choose between the feedback connector, remote Talon SRX,
	 * CANifier, or deactivate the feature.
	 */
	@ConfigParam
	val type: LimitSwitchSource,

	/**
	 * Setting for normally open, normally closed, or disabled. This setting matches the web-based configuration drop down.
	 */
	@ConfigParam
	val normalOpenOrClose: LimitSwitchNormal
) : LimitSwitchSourceConfig

/**
 * Configures the limit switch for a remote source. For example, a CAN motor controller may need to monitor the
 * Limit-F pin of another Talon or CANifier.
 *
 * @see TalonSRX.configForwardLimitSwitchSource
 * @see TalonSRX.configReverseLimitSwitchSource
 */
data class RemoteLimitSwitchSourceConfig(
	/**
	 * Remote limit switch source. User can choose between a remote Talon SRX, CANifier, or deactivate the feature.
	 */
	@ConfigParam
	val type: RemoteLimitSwitchSource,

	/**
	 * Setting for normally open, normally closed, or disabled. This setting matches the web-based configuration drop down.
	 */
	@ConfigParam
	val normalOpenOrClose: LimitSwitchNormal,

	/**
	 * Device ID of remote source (Talon SRX or CANifier device ID).
	 */
	@ConfigParam
	val deviceID: Int
) : LimitSwitchSourceConfig

/**
 * Select what remote device and signal to assign to Remote Sensor 0 or Remote Sensor 1. After binding a remote device
 * and signal to Remote Sensor X, you may select Remote Sensor X as a PID source for closed-loop features.
 *
 * @see TalonSRX.configRemoteFeedbackFilter
 */
data class RemoteFeedbackFilterConfig(
	/**
	 * The CAN ID of the remote sensor device.
	 */
	@ConfigParam
	val deviceID: Int,

	/**
	 * The remote sensor device and signal type to bind.
	 */
	@ConfigParam
	val remoteSensorSource: RemoteSensorSource,

	/**
	 * 0 for configuring Remote Sensor 0
	 * 1 for configuring Remote Sensor 1
	 */
	@ConfigParam
	val remoteOrdinal: Int
)

interface FeedbackDeviceConfig

/**
 * Select the feedback device for the motor controller.
 *
 * @see TalonSRX.configSelectedFeedbackSensor
 */
data class LocalFeedbackDeviceConfig(@ConfigParam val feedbackDevice: FeedbackDevice) : FeedbackDeviceConfig

/**
 * Select the remote feedback device for the motor controller.
 *
 * @see TalonSRX.configSelectedFeedbackSensor
 */
data class RemoteFeedbackDeviceConfig(@ConfigParam val remoteFeedbackDevice: RemoteFeedbackDevice) : FeedbackDeviceConfig

/**
 * Select what sensor term should be bound to switch feedback device.
 *
 * @see TalonSRX.configSensorTerm
 */
data class SensorTermConfig(
	/**
	 * Which sensor term to bind to a feedback source.
	 */
	@ConfigParam
	val sensorTerm: SensorTerm,

	/**
	 * The sensor signal to attach to sensorTerm.
	 */
	@ConfigParam
	val feedbackDevice: FeedbackDevice
)

