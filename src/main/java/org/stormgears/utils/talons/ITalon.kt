package org.stormgears.utils.talons

import com.ctre.phoenix.ErrorCode
import com.ctre.phoenix.motorcontrol.*

/**
 * Talon + Config management
 */
interface ITalon : IBaseTalon {
	fun setConfig(config: TalonConfig)

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configContinuousCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configForwardSoftLimitThreshold(forwardSensorLimit: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configVoltageMeasurementFilter(filterWindowSamples: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configMotionAcceleration(sensorUnitsPer100msPerSec: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun enableVoltageCompensation(enable: Boolean)

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configPeakCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configClosedLoopPeriod(slotIdx: Int, loopTimeMs: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun config_kI(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configReverseLimitSwitchSource(type: LimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configReverseLimitSwitchSource(type: RemoteLimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, deviceID: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configPeakCurrentDuration(milliseconds: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configSelectedFeedbackCoefficient(coefficient: Double, pidIdx: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configClosedLoopPeakOutput(slotIdx: Int, percentOut: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun config_IntegralZone(slotIdx: Int, izone: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configAuxPIDPolarity(invert: Boolean, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setNeutralMode(neutralMode: NeutralMode?)

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setStatusFramePeriod(frame: StatusFrameEnhanced?, periodMs: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setStatusFramePeriod(frame: StatusFrame?, periodMs: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configForwardSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configOpenloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configVelocityMeasurementWindow(windowSize: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configPeakOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configPeakOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configVoltageCompSaturation(voltage: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configVelocityMeasurementPeriod(period: VelocityMeasPeriod?, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun config_kF(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setIntegralAccumulator(iaccum: Double, pidIdx: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setControlFramePeriod(frame: ControlFrame?, periodMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configForwardLimitSwitchSource(type: LimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configForwardLimitSwitchSource(type: RemoteLimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, deviceID: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configNominalOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun enableCurrentLimit(enable: Boolean)

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun config_kP(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configMotionProfileTrajectoryPeriod(baseTrajDurationMs: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configReverseSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun setSensorPhase(PhaseSensor: Boolean)

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configMaxIntegralAccumulator(slotIdx: Int, iaccum: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configSelectedFeedbackSensor(feedbackDevice: FeedbackDevice?, pidIdx: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configSelectedFeedbackSensor(feedbackDevice: RemoteFeedbackDevice?, pidIdx: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configRemoteFeedbackFilter(deviceID: Int, remoteSensorSource: RemoteSensorSource?, remoteOrdinal: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configAllowableClosedloopError(slotIdx: Int, allowableClosedLoopError: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configNominalOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configClosedloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configNeutralDeadband(percentDeadband: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configReverseSoftLimitThreshold(reverseSensorLimit: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configMotionCruiseVelocity(sensorUnitsPer100ms: Int, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun config_kD(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode

	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
	override fun configSensorTerm(sensorTerm: SensorTerm?, feedbackDevice: FeedbackDevice?, timeoutMs: Int): ErrorCode

//	@Deprecated(message = "Use TalonConfig objects and setConfig instead.")
//	override fun enableHeadingHold(enable: Boolean)
}
