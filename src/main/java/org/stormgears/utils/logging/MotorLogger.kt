package org.stormgears.utils.logging

import com.ctre.phoenix.ErrorCode
import com.ctre.phoenix.ParamEnum
import com.ctre.phoenix.motion.MotionProfileStatus
import com.ctre.phoenix.motion.TrajectoryPoint
import com.ctre.phoenix.motorcontrol.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.utils.talons.IBaseTalon
import org.stormgears.utils.talons.ISensorCollection

/**
 * Logs all of the method calls to a motor
 */
class MotorLogger(private val motor: IBaseTalon, private val name: String) : IBaseTalon {
	override val sensorCollection: ISensorCollection
		get() = motor.sensorCollection

	override val dummy: Boolean
		get() {
			val ret = motor.dummy
			logger.trace("{} -> {}", this.name, box(ret))
			return ret
		}

	override fun configSelectedFeedbackSensor(feedbackDevice: FeedbackDevice, pidIdx: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs)
		logger.trace("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, feedbackDevice, box(pidIdx), box(timeoutMs), ret)
		return ret
	}

	override fun setStatusFramePeriod(frame: StatusFrameEnhanced, periodMs: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.setStatusFramePeriod(frame, periodMs, timeoutMs)
		logger.trace("{} frame = {}, periodMs = {}, timeoutMs = {} -> {}", this.name, frame, box(periodMs), box(timeoutMs), ret)
		return ret
	}

	override fun getStatusFramePeriod(frame: StatusFrameEnhanced, timeoutMs: Int): Int {
		val ret = motor.getStatusFramePeriod(frame, timeoutMs)
		logger.trace("{} frame = {}, timeoutMs = {} -> {}", this.name, frame, box(timeoutMs), box(ret))
		return ret
	}

	override fun configVelocityMeasurementPeriod(period: VelocityMeasPeriod, timeoutMs: Int): ErrorCode {
		val ret = motor.configVelocityMeasurementPeriod(period, timeoutMs)
		logger.trace("{} period = {}, timeoutMs = {} -> {}", this.name, period, box(timeoutMs), ret)
		return ret
	}

	override fun configVelocityMeasurementWindow(windowSize: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configVelocityMeasurementWindow(windowSize, timeoutMs)
		logger.trace("{} windowSize = {}, timeoutMs = {} -> {}", this.name, box(windowSize), box(timeoutMs), ret)
		return ret
	}

	override fun configForwardLimitSwitchSource(type: LimitSwitchSource, normalOpenOrClose: LimitSwitchNormal, timeoutMs: Int): ErrorCode {
		val ret = motor.configForwardLimitSwitchSource(type, normalOpenOrClose, timeoutMs)
		logger.trace("{} type = {}, normalOpenOrClose = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(timeoutMs), ret)
		return ret
	}

	override fun configReverseLimitSwitchSource(type: LimitSwitchSource, normalOpenOrClose: LimitSwitchNormal, timeoutMs: Int): ErrorCode {
		val ret = motor.configReverseLimitSwitchSource(type, normalOpenOrClose, timeoutMs)
		logger.trace("{} type = {}, normalOpenOrClose = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(timeoutMs), ret)
		return ret
	}

	override fun configPeakCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configPeakCurrentLimit(amps, timeoutMs)
		logger.trace("{} amps = {}, timeoutMs = {} -> {}", this.name, box(amps), box(timeoutMs), ret)
		return ret
	}

	override fun configPeakCurrentDuration(milliseconds: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configPeakCurrentDuration(milliseconds, timeoutMs)
		logger.trace("{} milliseconds = {}, timeoutMs = {} -> {}", this.name, box(milliseconds), box(timeoutMs), ret)
		return ret
	}

	override fun configContinuousCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configContinuousCurrentLimit(amps, timeoutMs)
		logger.trace("{} amps = {}, timeoutMs = {} -> {}", this.name, box(amps), box(timeoutMs), ret)
		return ret
	}

	override fun enableCurrentLimit(enable: Boolean) {
		logger.trace("{} enable = {}", this.name, box(enable))
		motor.enableCurrentLimit(enable)
	}

	override fun set(Mode: ControlMode, demand: Double) {
		logger.trace("{} Mode = {}, demand = {}", this.name, Mode, box(demand))
		motor.set(Mode, demand)
	}

	override fun set(Mode: ControlMode, demand0: Double, demand1: Double) {
		logger.trace("{} Mode = {}, demand0 = {}, demand1 = {}", this.name, Mode, box(demand0), box(demand1))
		motor.set(Mode, demand0, demand1)
	}

	override fun neutralOutput() {
		logger.trace("{}", this.name)
		motor.neutralOutput()
	}

	override fun setNeutralMode(neutralMode: NeutralMode) {
		logger.trace("{} neutralMode = {}", this.name, neutralMode)
		motor.setNeutralMode(neutralMode)
	}

	override fun setSensorPhase(PhaseSensor: Boolean) {
		logger.trace("{} PhaseSensor = {}", this.name, box(PhaseSensor))
		motor.setSensorPhase(PhaseSensor)
	}

	override fun setInverted(invert: Boolean) {
		logger.trace("{} invert = {}", this.name, box(invert))
		motor.inverted = invert
	}

	override fun getInverted(): Boolean {
		val ret = motor.inverted
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun configOpenloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs)
		logger.trace("{} secondsFromNeutralToFull = {}, timeoutMs = {} -> {}", this.name, box(secondsFromNeutralToFull), box(timeoutMs), ret)
		return ret
	}

	override fun configClosedloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs)
		logger.trace("{} secondsFromNeutralToFull = {}, timeoutMs = {} -> {}", this.name, box(secondsFromNeutralToFull), box(timeoutMs), ret)
		return ret
	}

	override fun configPeakOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configPeakOutputForward(percentOut, timeoutMs)
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret)
		return ret
	}

	override fun configPeakOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configPeakOutputReverse(percentOut, timeoutMs)
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret)
		return ret
	}

	override fun configNominalOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configNominalOutputForward(percentOut, timeoutMs)
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret)
		return ret
	}

	override fun configNominalOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configNominalOutputReverse(percentOut, timeoutMs)
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret)
		return ret
	}

	override fun configNeutralDeadband(percentDeadband: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configNeutralDeadband(percentDeadband, timeoutMs)
		logger.trace("{} percentDeadband = {}, timeoutMs = {} -> {}", this.name, box(percentDeadband), box(timeoutMs), ret)
		return ret
	}

	override fun configVoltageCompSaturation(voltage: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configVoltageCompSaturation(voltage, timeoutMs)
		logger.trace("{} voltage = {}, timeoutMs = {} -> {}", this.name, box(voltage), box(timeoutMs), ret)
		return ret
	}

	override fun configVoltageMeasurementFilter(filterWindowSamples: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configVoltageMeasurementFilter(filterWindowSamples, timeoutMs)
		logger.trace("{} filterWindowSamples = {}, timeoutMs = {} -> {}", this.name, box(filterWindowSamples), box(timeoutMs), ret)
		return ret
	}

	override fun enableVoltageCompensation(enable: Boolean) {
		logger.trace("{} enable = {}", this.name, box(enable))
		motor.enableVoltageCompensation(enable)
	}

	override fun getBusVoltage(): Double {
		val ret = motor.busVoltage
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getMotorOutputPercent(): Double {
		val ret = motor.motorOutputPercent
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getMotorOutputVoltage(): Double {
		val ret = motor.motorOutputVoltage
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getOutputCurrent(): Double {
		val ret = motor.outputCurrent
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getTemperature(): Double {
		val ret = motor.temperature
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun configSelectedFeedbackSensor(feedbackDevice: RemoteFeedbackDevice, pidIdx: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs)
		logger.trace("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, feedbackDevice, box(pidIdx), box(timeoutMs), ret)
		return ret
	}

	override fun configRemoteFeedbackFilter(deviceID: Int, remoteSensorSource: RemoteSensorSource, remoteOrdinal: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal, timeoutMs)
		logger.trace("{} deviceID = {}, remoteSensorSource = {}, remoteOrdinal = {}, timeoutMs = {} -> {}", this.name, box(deviceID), remoteSensorSource, box(remoteOrdinal), box(timeoutMs), ret)
		return ret
	}

	override fun configSensorTerm(sensorTerm: SensorTerm, feedbackDevice: FeedbackDevice, timeoutMs: Int): ErrorCode {
		val ret = motor.configSensorTerm(sensorTerm, feedbackDevice, timeoutMs)
		logger.trace("{} sensorTerm = {}, feedbackDevice = {}, timeoutMs = {} -> {}", this.name, sensorTerm, feedbackDevice, box(timeoutMs), ret)
		return ret
	}

	override fun getSelectedSensorPosition(pidIdx: Int): Int {
		val ret = motor.getSelectedSensorPosition(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret))
		return ret
	}

	override fun getSelectedSensorVelocity(pidIdx: Int): Int {
		val ret = motor.getSelectedSensorVelocity(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret))
		return ret
	}

	override fun setSelectedSensorPosition(sensorPos: Int, pidIdx: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.setSelectedSensorPosition(sensorPos, pidIdx, timeoutMs)
		logger.trace("{} sensorPos = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, box(sensorPos), box(pidIdx), box(timeoutMs), ret)
		return ret
	}

	override fun setControlFramePeriod(frame: ControlFrame, periodMs: Int): ErrorCode {
		val ret = motor.setControlFramePeriod(frame, periodMs)
		logger.trace("{} frame = {}, periodMs = {} -> {}", this.name, frame, box(periodMs), ret)
		return ret
	}

	override fun setStatusFramePeriod(frame: StatusFrame, periodMs: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.setStatusFramePeriod(frame, periodMs, timeoutMs)
		logger.trace("{} frame = {}, periodMs = {}, timeoutMs = {} -> {}", this.name, frame, box(periodMs), box(timeoutMs), ret)
		return ret
	}

	override fun getStatusFramePeriod(frame: StatusFrame, timeoutMs: Int): Int {
		val ret = motor.getStatusFramePeriod(frame, timeoutMs)
		logger.trace("{} frame = {}, timeoutMs = {} -> {}", this.name, frame, box(timeoutMs), box(ret))
		return ret
	}

	override fun configForwardLimitSwitchSource(type: RemoteLimitSwitchSource, normalOpenOrClose: LimitSwitchNormal, deviceID: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configForwardLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs)
		logger.trace("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(deviceID), box(timeoutMs), ret)
		return ret
	}

	override fun configReverseLimitSwitchSource(type: RemoteLimitSwitchSource, normalOpenOrClose: LimitSwitchNormal, deviceID: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configReverseLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs)
		logger.trace("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(deviceID), box(timeoutMs), ret)
		return ret
	}

	override fun overrideLimitSwitchesEnable(enable: Boolean) {
		logger.trace("{} enable = {}", this.name, box(enable))
		motor.overrideLimitSwitchesEnable(enable)
	}

	override fun configForwardSoftLimitThreshold(forwardSensorLimit: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configForwardSoftLimitThreshold(forwardSensorLimit, timeoutMs)
		logger.trace("{} forwardSensorLimit = {}, timeoutMs = {} -> {}", this.name, box(forwardSensorLimit), box(timeoutMs), ret)
		return ret
	}

	override fun configReverseSoftLimitThreshold(reverseSensorLimit: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configReverseSoftLimitThreshold(reverseSensorLimit, timeoutMs)
		logger.trace("{} reverseSensorLimit = {}, timeoutMs = {} -> {}", this.name, box(reverseSensorLimit), box(timeoutMs), ret)
		return ret
	}

	override fun configForwardSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode {
		val ret = motor.configForwardSoftLimitEnable(enable, timeoutMs)
		logger.trace("{} enable = {}, timeoutMs = {} -> {}", this.name, box(enable), box(timeoutMs), ret)
		return ret
	}

	override fun configReverseSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode {
		val ret = motor.configReverseSoftLimitEnable(enable, timeoutMs)
		logger.trace("{} enable = {}, timeoutMs = {} -> {}", this.name, box(enable), box(timeoutMs), ret)
		return ret
	}

	override fun overrideSoftLimitsEnable(enable: Boolean) {
		logger.trace("{} enable = {}", this.name, box(enable))
		motor.overrideSoftLimitsEnable(enable)
	}

	@Suppress("FunctionName")
	override fun config_kP(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.config_kP(slotIdx, value, timeoutMs)
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret)
		return ret
	}

	@Suppress("FunctionName")
	override fun config_kI(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.config_kI(slotIdx, value, timeoutMs)
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret)
		return ret
	}

	@Suppress("FunctionName")
	override fun config_kD(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.config_kD(slotIdx, value, timeoutMs)
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret)
		return ret
	}

	@Suppress("FunctionName")
	override fun config_kF(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.config_kF(slotIdx, value, timeoutMs)
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret)
		return ret
	}

	@Suppress("FunctionName")
	override fun config_IntegralZone(slotIdx: Int, izone: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.config_IntegralZone(slotIdx, izone, timeoutMs)
		logger.trace("{} slotIdx = {}, izone = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(izone), box(timeoutMs), ret)
		return ret
	}

	override fun configAllowableClosedloopError(slotIdx: Int, allowableCloseLoopError: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs)
		logger.trace("{} slotIdx = {}, allowableCloseLoopError = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(allowableCloseLoopError), box(timeoutMs), ret)
		return ret
	}

	override fun configMaxIntegralAccumulator(slotIdx: Int, iaccum: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configMaxIntegralAccumulator(slotIdx, iaccum, timeoutMs)
		logger.trace("{} slotIdx = {}, iaccum = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(iaccum), box(timeoutMs), ret)
		return ret
	}

	override fun setIntegralAccumulator(iaccum: Double, pidIdx: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.setIntegralAccumulator(iaccum, pidIdx, timeoutMs)
		logger.trace("{} iaccum = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, box(iaccum), box(pidIdx), box(timeoutMs), ret)
		return ret
	}

	override fun getClosedLoopError(pidIdx: Int): Int {
		val ret = motor.getClosedLoopError(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret))
		return ret
	}

	override fun getIntegralAccumulator(pidIdx: Int): Double {
		val ret = motor.getIntegralAccumulator(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret))
		return ret
	}

	override fun getErrorDerivative(pidIdx: Int): Double {
		val ret = motor.getErrorDerivative(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret))
		return ret
	}

	override fun selectProfileSlot(slotIdx: Int, pidIdx: Int) {
		logger.trace("{} slotIdx = {}, pidIdx = {}", this.name, box(slotIdx), box(pidIdx))
		motor.selectProfileSlot(slotIdx, pidIdx)
	}

	override fun getActiveTrajectoryPosition(): Int {
		val ret = motor.activeTrajectoryPosition
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getActiveTrajectoryVelocity(): Int {
		val ret = motor.activeTrajectoryVelocity
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getActiveTrajectoryHeading(): Double {
		val ret = motor.activeTrajectoryHeading
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun configMotionCruiseVelocity(sensorUnitsPer100ms: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs)
		logger.trace("{} sensorUnitsPer100ms = {}, timeoutMs = {} -> {}", this.name, box(sensorUnitsPer100ms), box(timeoutMs), ret)
		return ret
	}

	override fun configMotionAcceleration(sensorUnitsPer100msPerSec: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs)
		logger.trace("{} sensorUnitsPer100msPerSec = {}, timeoutMs = {} -> {}", this.name, box(sensorUnitsPer100msPerSec), box(timeoutMs), ret)
		return ret
	}

	override fun clearMotionProfileTrajectories(): ErrorCode {
		val ret = motor.clearMotionProfileTrajectories()
		logger.trace("{} -> {}", this.name, ret)
		return ret
	}

	override fun getMotionProfileTopLevelBufferCount(): Int {
		val ret = motor.motionProfileTopLevelBufferCount
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun pushMotionProfileTrajectory(trajPt: TrajectoryPoint): ErrorCode {
		val ret = motor.pushMotionProfileTrajectory(trajPt)
		logger.trace("{} trajPt = {} -> {}", this.name, trajPt, ret)
		return ret
	}

	override fun isMotionProfileTopLevelBufferFull(): Boolean {
		val ret = motor.isMotionProfileTopLevelBufferFull
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun processMotionProfileBuffer() {
		logger.trace("{}", this.name)
		motor.processMotionProfileBuffer()
	}

	override fun getMotionProfileStatus(statusToFill: MotionProfileStatus): ErrorCode {
		val ret = motor.getMotionProfileStatus(statusToFill)
		logger.trace("{} statusToFill = {} -> {}", this.name, statusToFill, ret)
		return ret
	}

	override fun clearMotionProfileHasUnderrun(timeoutMs: Int): ErrorCode {
		val ret = motor.clearMotionProfileHasUnderrun(timeoutMs)
		logger.trace("{} timeoutMs = {} -> {}", this.name, box(timeoutMs), ret)
		return ret
	}

	override fun changeMotionControlFramePeriod(periodMs: Int): ErrorCode {
		val ret = motor.changeMotionControlFramePeriod(periodMs)
		logger.trace("{} periodMs = {} -> {}", this.name, box(periodMs), ret)
		return ret
	}

	override fun getLastError(): ErrorCode {
		val ret = motor.lastError
		logger.trace("{} -> {}", this.name, ret)
		return ret
	}

	override fun getFaults(toFill: Faults): ErrorCode {
		val ret = motor.getFaults(toFill)
		logger.trace("{} toFill = {} -> {}", this.name, toFill, ret)
		return ret
	}

	override fun getStickyFaults(toFill: StickyFaults): ErrorCode {
		val ret = motor.getStickyFaults(toFill)
		logger.trace("{} toFill = {} -> {}", this.name, toFill, ret)
		return ret
	}

	override fun clearStickyFaults(timeoutMs: Int): ErrorCode {
		val ret = motor.clearStickyFaults(timeoutMs)
		logger.trace("{} timeoutMs = {} -> {}", this.name, box(timeoutMs), ret)
		return ret
	}

	override fun getFirmwareVersion(): Int {
		val ret = motor.firmwareVersion
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun hasResetOccurred(): Boolean {
		val ret = motor.hasResetOccurred()
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun configSetCustomParam(newValue: Int, paramIndex: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSetCustomParam(newValue, paramIndex, timeoutMs)
		logger.trace("{} newValue = {}, paramIndex = {}, timeoutMs = {} -> {}", this.name, box(newValue), box(paramIndex), box(timeoutMs), ret)
		return ret
	}

	override fun configGetCustomParam(paramIndex: Int, timoutMs: Int): Int {
		val ret = motor.configGetCustomParam(paramIndex, timoutMs)
		logger.trace("{} paramIndex = {}, timoutMs = {} -> {}", this.name, box(paramIndex), box(timoutMs), box(ret))
		return ret
	}

	override fun configSetParameter(param: ParamEnum, value: Double, subValue: Int, ordinal: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSetParameter(param, value, subValue, ordinal, timeoutMs)
		logger.trace("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, param, box(value), box(subValue), box(ordinal), box(timeoutMs), ret)
		return ret
	}

	override fun configSetParameter(param: Int, value: Double, subValue: Int, ordinal: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSetParameter(param, value, subValue, ordinal, timeoutMs)
		logger.trace("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, box(param), box(value), box(subValue), box(ordinal), box(timeoutMs), ret)
		return ret
	}

	override fun configGetParameter(paramEnum: ParamEnum, ordinal: Int, timeoutMs: Int): Double {
		val ret = motor.configGetParameter(paramEnum, ordinal, timeoutMs)
		logger.trace("{} paramEnum = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, paramEnum, box(ordinal), box(timeoutMs), box(ret))
		return ret
	}

	override fun configGetParameter(paramEnum: Int, ordinal: Int, timeoutMs: Int): Double {
		val ret = motor.configGetParameter(paramEnum, ordinal, timeoutMs)
		logger.trace("{} paramEnum = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, box(paramEnum), box(ordinal), box(timeoutMs), box(ret))
		return ret
	}

	override fun getBaseID(): Int {
		val ret = motor.baseID
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getDeviceID(): Int {
		val ret = motor.deviceID
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun follow(masterToFollow: IMotorController) {
		logger.trace("{} masterToFollow = {}", this.name, masterToFollow)
		motor.follow(masterToFollow)
	}

	override fun valueUpdated() {
		logger.trace("{}", this.name)
		motor.valueUpdated()
	}

	override fun setExpiration(timeout: Double) {
		logger.trace("{} timeout = {}", this.name, box(timeout))
		motor.expiration = timeout
	}

	override fun getExpiration(): Double {
		val ret = motor.expiration
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun isAlive(): Boolean {
		val ret = motor.isAlive
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun setSafetyEnabled(enabled: Boolean) {
		logger.trace("{} enabled = {}", this.name, box(enabled))
	}

	override fun isSafetyEnabled(): Boolean {
		val ret = motor.isSafetyEnabled
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun getDescription(): String {
		val ret = motor.description
		logger.trace("{} -> {}", this.name, ret)
		return ret
	}

	override fun set(speed: Double) {
		logger.trace("{} speed = {}", this.name, box(speed))
	}

	override fun get(): Double {
		val ret = motor.get()
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	override fun disable() {
		logger.trace("{}", this.name)
		motor.disable()
	}

	override fun stopMotor() {
		logger.trace("{}", this.name)
		motor.stopMotor()
	}

	override fun pidWrite(output: Double) {
		logger.trace("{} output = {}", this.name, box(output))
	}

	override fun getControlMode(): ControlMode? {
		val ret = motor.controlMode
		logger.trace("{} -> {}", this.name, ret)
		return ret
	}

	override fun getHandle(): Long {
		val ret = motor.handle
		logger.trace("{} -> {}", this.name, box(ret))
		return ret
	}

	// New in 5.3.1.0
	override fun set(Mode: ControlMode?, demand0: Double, demand1Type: DemandType?, demand1: Double) {
		logger.trace("{} Mode = {}, demand0 = {}, demand1Type = {}, demand1 = {}", this.name, Mode, box(demand0), demand1Type, box(demand1))
		motor.set(Mode, demand0, demand1Type, demand1)
	}

	override fun configClosedLoopPeriod(slotIdx: Int, loopTimeMs: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configClosedLoopPeriod(slotIdx, loopTimeMs, timeoutMs)
		logger.trace("{} slotIdx = {}, loopTimeMs = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(loopTimeMs), box(timeoutMs), ret)
		return ret
	}

	override fun configSelectedFeedbackCoefficient(coefficient: Double, pidIdx: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configSelectedFeedbackCoefficient(coefficient, pidIdx, timeoutMs)
		logger.trace("{} pidIdx = {}, timeoutMs = {} -> {}", this.name, box(pidIdx), box(timeoutMs), ret)
		return ret
	}

	override fun configClosedLoopPeakOutput(slotIdx: Int, percentOut: Double, timeoutMs: Int): ErrorCode {
		val ret = motor.configClosedLoopPeakOutput(slotIdx, percentOut, timeoutMs)
		logger.trace("{} slotIdx = {}, percentOut = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(percentOut), box(timeoutMs), ret)
		return ret
	}

	override fun configAuxPIDPolarity(invert: Boolean, timeoutMs: Int): ErrorCode {
		val ret = motor.configAuxPIDPolarity(invert, timeoutMs)
		logger.trace("{} invert = {}, timeoutMs = {} -> {}", this.name, box(invert), box(timeoutMs), ret)
		return ret
	}

	override fun getClosedLoopTarget(pidIdx: Int): Int {
		val ret = motor.getClosedLoopTarget(pidIdx)
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), ret)
		return ret
	}

	override fun configMotionProfileTrajectoryPeriod(baseTrajDurationMs: Int, timeoutMs: Int): ErrorCode {
		val ret = motor.configMotionProfileTrajectoryPeriod(baseTrajDurationMs, timeoutMs)
		logger.trace("{} baseTrajDurationMs = {}, timeoutMs = {} -> {}", this.name, box(baseTrajDurationMs), box(timeoutMs), ret)
		return ret
	}


	companion object {
		private val logger = LogManager.getLogger(MotorLogger::class.java)
	}
}
