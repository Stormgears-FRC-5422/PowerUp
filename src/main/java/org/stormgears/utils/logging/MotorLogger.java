package org.stormgears.utils.logging;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.util.Unbox.box;

/**
 * Logs all of the method calls to a motor
 */
public class MotorLogger implements IMotorControllerEnhanced {
	private static final Logger logger = LogManager.getLogger(MotorLogger.class);

	private IMotorControllerEnhanced motor;
	private String name;

	public MotorLogger(IMotorControllerEnhanced motor, String name) {
		this.motor = motor;
		this.name = name;
	}

	@Override
	public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) {
		ErrorCode ret = motor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs);
		logger.trace("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, feedbackDevice, box(pidIdx), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrameEnhanced frame, int periodMs, int timeoutMs) {
		ErrorCode ret = motor.setStatusFramePeriod(frame, periodMs, timeoutMs);
		logger.trace("{} frame = {}, periodMs = {}, timeoutMs = {} -> {}", this.name, frame, box(periodMs), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int getStatusFramePeriod(StatusFrameEnhanced frame, int timeoutMs) {
		int ret = motor.getStatusFramePeriod(frame, timeoutMs);
		logger.trace("{} frame = {}, timeoutMs = {} -> {}", this.name, frame, box(timeoutMs), box(ret));
		return ret;
	}

	@Override
	public ErrorCode configVelocityMeasurementPeriod(VelocityMeasPeriod period, int timeoutMs) {
		ErrorCode ret = motor.configVelocityMeasurementPeriod(period, timeoutMs);
		logger.trace("{} period = {}, timeoutMs = {} -> {}", this.name, period, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configVelocityMeasurementWindow(int windowSize, int timeoutMs) {
		ErrorCode ret = motor.configVelocityMeasurementWindow(windowSize, timeoutMs);
		logger.trace("{} windowSize = {}, timeoutMs = {} -> {}", this.name, box(windowSize), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configForwardLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) {
		ErrorCode ret = motor.configForwardLimitSwitchSource(type, normalOpenOrClose, timeoutMs);
		logger.trace("{} type = {}, normalOpenOrClose = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configReverseLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) {
		ErrorCode ret = motor.configReverseLimitSwitchSource(type, normalOpenOrClose, timeoutMs);
		logger.trace("{} type = {}, normalOpenOrClose = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configPeakCurrentLimit(int amps, int timeoutMs) {
		ErrorCode ret = motor.configPeakCurrentLimit(amps, timeoutMs);
		logger.trace("{} amps = {}, timeoutMs = {} -> {}", this.name, box(amps), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configPeakCurrentDuration(int milliseconds, int timeoutMs) {
		ErrorCode ret = motor.configPeakCurrentDuration(milliseconds, timeoutMs);
		logger.trace("{} milliseconds = {}, timeoutMs = {} -> {}", this.name, box(milliseconds), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configContinuousCurrentLimit(int amps, int timeoutMs) {
		ErrorCode ret = motor.configContinuousCurrentLimit(amps, timeoutMs);
		logger.trace("{} amps = {}, timeoutMs = {} -> {}", this.name, box(amps), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public void enableCurrentLimit(boolean enable) {
		logger.trace("{} enable = {}", this.name, box(enable));
		motor.enableCurrentLimit(enable);
	}

	@Override
	public void set(ControlMode Mode, double demand) {
		logger.trace("{} Mode = {}, demand = {}", this.name, Mode, box(demand));
		motor.set(Mode, demand);
	}

	@Override
	public void set(ControlMode Mode, double demand0, double demand1) {
		logger.trace("{} Mode = {}, demand0 = {}, demand1 = {}", this.name, Mode, box(demand0), box(demand1));
		motor.set(Mode, demand0, demand1);
	}

	@Override
	public void neutralOutput() {
		logger.trace("{}", this.name);
		motor.neutralOutput();
	}

	@Override
	public void setNeutralMode(NeutralMode neutralMode) {
		logger.trace("{} neutralMode = {}", this.name, neutralMode);
		motor.setNeutralMode(neutralMode);
	}

	@Override
	public void setSensorPhase(boolean PhaseSensor) {
		logger.trace("{} PhaseSensor = {}", this.name, box(PhaseSensor));
		motor.setSensorPhase(PhaseSensor);
	}

	@Override
	public void setInverted(boolean invert) {
		logger.trace("{} invert = {}", this.name, box(invert));
		motor.setInverted(invert);
	}

	@Override
	public boolean getInverted() {
		boolean ret = motor.getInverted();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public ErrorCode configOpenloopRamp(double secondsFromNeutralToFull, int timeoutMs) {
		ErrorCode ret = motor.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		logger.trace("{} secondsFromNeutralToFull = {}, timeoutMs = {} -> {}", this.name, box(secondsFromNeutralToFull), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configClosedloopRamp(double secondsFromNeutralToFull, int timeoutMs) {
		ErrorCode ret = motor.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs);
		logger.trace("{} secondsFromNeutralToFull = {}, timeoutMs = {} -> {}", this.name, box(secondsFromNeutralToFull), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configPeakOutputForward(double percentOut, int timeoutMs) {
		ErrorCode ret = motor.configPeakOutputForward(percentOut, timeoutMs);
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configPeakOutputReverse(double percentOut, int timeoutMs) {
		ErrorCode ret = motor.configPeakOutputReverse(percentOut, timeoutMs);
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configNominalOutputForward(double percentOut, int timeoutMs) {
		ErrorCode ret = motor.configNominalOutputForward(percentOut, timeoutMs);
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configNominalOutputReverse(double percentOut, int timeoutMs) {
		ErrorCode ret = motor.configNominalOutputReverse(percentOut, timeoutMs);
		logger.trace("{} percentOut = {}, timeoutMs = {} -> {}", this.name, box(percentOut), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configNeutralDeadband(double percentDeadband, int timeoutMs) {
		ErrorCode ret = motor.configNeutralDeadband(percentDeadband, timeoutMs);
		logger.trace("{} percentDeadband = {}, timeoutMs = {} -> {}", this.name, box(percentDeadband), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configVoltageCompSaturation(double voltage, int timeoutMs) {
		ErrorCode ret = motor.configVoltageCompSaturation(voltage, timeoutMs);
		logger.trace("{} voltage = {}, timeoutMs = {} -> {}", this.name, box(voltage), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configVoltageMeasurementFilter(int filterWindowSamples, int timeoutMs) {
		ErrorCode ret = motor.configVoltageMeasurementFilter(filterWindowSamples, timeoutMs);
		logger.trace("{} filterWindowSamples = {}, timeoutMs = {} -> {}", this.name, box(filterWindowSamples), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public void enableVoltageCompensation(boolean enable) {
		logger.trace("{} enable = {}", this.name, box(enable));
		motor.enableVoltageCompensation(enable);
	}

	@Override
	public double getBusVoltage() {
		double ret = motor.getBusVoltage();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public double getMotorOutputPercent() {
		double ret = motor.getMotorOutputPercent();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public double getMotorOutputVoltage() {
		double ret = motor.getMotorOutputVoltage();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public double getOutputCurrent() {
		double ret = motor.getOutputCurrent();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public double getTemperature() {
		double ret = motor.getTemperature();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public ErrorCode configSelectedFeedbackSensor(RemoteFeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) {
		ErrorCode ret = motor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs);
		logger.trace("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, feedbackDevice, box(pidIdx), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configRemoteFeedbackFilter(int deviceID, RemoteSensorSource remoteSensorSource, int remoteOrdinal, int timeoutMs) {
		ErrorCode ret = motor.configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal, timeoutMs);
		logger.trace("{} deviceID = {}, remoteSensorSource = {}, remoteOrdinal = {}, timeoutMs = {} -> {}", this.name, box(deviceID), remoteSensorSource, box(remoteOrdinal), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configSensorTerm(SensorTerm sensorTerm, FeedbackDevice feedbackDevice, int timeoutMs) {
		ErrorCode ret = motor.configSensorTerm(sensorTerm, feedbackDevice, timeoutMs);
		logger.trace("{} sensorTerm = {}, feedbackDevice = {}, timeoutMs = {} -> {}", this.name, sensorTerm, feedbackDevice, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int getSelectedSensorPosition(int pidIdx) {
		int ret = motor.getSelectedSensorPosition(pidIdx);
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret));
		return ret;
	}

	@Override
	public int getSelectedSensorVelocity(int pidIdx) {
		int ret = motor.getSelectedSensorVelocity(pidIdx);
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret));
		return ret;
	}

	@Override
	public ErrorCode setSelectedSensorPosition(int sensorPos, int pidIdx, int timeoutMs) {
		ErrorCode ret = motor.setSelectedSensorPosition(sensorPos, pidIdx, timeoutMs);
		logger.trace("{} sensorPos = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, box(sensorPos), box(pidIdx), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode setControlFramePeriod(ControlFrame frame, int periodMs) {
		ErrorCode ret = motor.setControlFramePeriod(frame, periodMs);
		logger.trace("{} frame = {}, periodMs = {} -> {}", this.name, frame, box(periodMs), ret);
		return ret;
	}

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrame frame, int periodMs, int timeoutMs) {
		ErrorCode ret = motor.setStatusFramePeriod(frame, periodMs, timeoutMs);
		logger.trace("{} frame = {}, periodMs = {}, timeoutMs = {} -> {}", this.name, frame, box(periodMs), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int getStatusFramePeriod(StatusFrame frame, int timeoutMs) {
		int ret = motor.getStatusFramePeriod(frame, timeoutMs);
		logger.trace("{} frame = {}, timeoutMs = {} -> {}", this.name, frame, box(timeoutMs), box(ret));
		return ret;
	}

	@Override
	public ErrorCode configForwardLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) {
		ErrorCode ret = motor.configForwardLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs);
		logger.trace("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(deviceID), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configReverseLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) {
		ErrorCode ret = motor.configReverseLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs);
		logger.trace("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {} -> {}", this.name, type, normalOpenOrClose, box(deviceID), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public void overrideLimitSwitchesEnable(boolean enable) {
		logger.trace("{} enable = {}", this.name, box(enable));
		motor.overrideLimitSwitchesEnable(enable);
	}

	@Override
	public ErrorCode configForwardSoftLimitThreshold(int forwardSensorLimit, int timeoutMs) {
		ErrorCode ret = motor.configForwardSoftLimitThreshold(forwardSensorLimit, timeoutMs);
		logger.trace("{} forwardSensorLimit = {}, timeoutMs = {} -> {}", this.name, box(forwardSensorLimit), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configReverseSoftLimitThreshold(int reverseSensorLimit, int timeoutMs) {
		ErrorCode ret = motor.configReverseSoftLimitThreshold(reverseSensorLimit, timeoutMs);
		logger.trace("{} reverseSensorLimit = {}, timeoutMs = {} -> {}", this.name, box(reverseSensorLimit), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configForwardSoftLimitEnable(boolean enable, int timeoutMs) {
		ErrorCode ret = motor.configForwardSoftLimitEnable(enable, timeoutMs);
		logger.trace("{} enable = {}, timeoutMs = {} -> {}", this.name, box(enable), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configReverseSoftLimitEnable(boolean enable, int timeoutMs) {
		ErrorCode ret = motor.configReverseSoftLimitEnable(enable, timeoutMs);
		logger.trace("{} enable = {}, timeoutMs = {} -> {}", this.name, box(enable), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public void overrideSoftLimitsEnable(boolean enable) {
		logger.trace("{} enable = {}", this.name, box(enable));
		motor.overrideSoftLimitsEnable(enable);
	}

	@Override
	public ErrorCode config_kP(int slotIdx, double value, int timeoutMs) {
		ErrorCode ret = motor.config_kP(slotIdx, value, timeoutMs);
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode config_kI(int slotIdx, double value, int timeoutMs) {
		ErrorCode ret = motor.config_kI(slotIdx, value, timeoutMs);
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode config_kD(int slotIdx, double value, int timeoutMs) {
		ErrorCode ret = motor.config_kD(slotIdx, value, timeoutMs);
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode config_kF(int slotIdx, double value, int timeoutMs) {
		ErrorCode ret = motor.config_kF(slotIdx, value, timeoutMs);
		logger.trace("{} slotIdx = {}, value = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(value), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode config_IntegralZone(int slotIdx, int izone, int timeoutMs) {
		ErrorCode ret = motor.config_IntegralZone(slotIdx, izone, timeoutMs);
		logger.trace("{} slotIdx = {}, izone = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(izone), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configAllowableClosedloopError(int slotIdx, int allowableCloseLoopError, int timeoutMs) {
		ErrorCode ret = motor.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs);
		logger.trace("{} slotIdx = {}, allowableCloseLoopError = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(allowableCloseLoopError), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configMaxIntegralAccumulator(int slotIdx, double iaccum, int timeoutMs) {
		ErrorCode ret = motor.configMaxIntegralAccumulator(slotIdx, iaccum, timeoutMs);
		logger.trace("{} slotIdx = {}, iaccum = {}, timeoutMs = {} -> {}", this.name, box(slotIdx), box(iaccum), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode setIntegralAccumulator(double iaccum, int pidIdx, int timeoutMs) {
		ErrorCode ret = motor.setIntegralAccumulator(iaccum, pidIdx, timeoutMs);
		logger.trace("{} iaccum = {}, pidIdx = {}, timeoutMs = {} -> {}", this.name, box(iaccum), box(pidIdx), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int getClosedLoopError(int pidIdx) {
		int ret = motor.getClosedLoopError(pidIdx);
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret));
		return ret;
	}

	@Override
	public double getIntegralAccumulator(int pidIdx) {
		double ret = motor.getIntegralAccumulator(pidIdx);
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret));
		return ret;
	}

	@Override
	public double getErrorDerivative(int pidIdx) {
		double ret = motor.getErrorDerivative(pidIdx);
		logger.trace("{} pidIdx = {} -> {}", this.name, box(pidIdx), box(ret));
		return ret;
	}

	@Override
	public void selectProfileSlot(int slotIdx, int pidIdx) {
		logger.trace("{} slotIdx = {}, pidIdx = {}", this.name, box(slotIdx), box(pidIdx));
		motor.selectProfileSlot(slotIdx, pidIdx);
	}

	@Override
	public int getActiveTrajectoryPosition() {
		int ret = motor.getActiveTrajectoryPosition();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public int getActiveTrajectoryVelocity() {
		int ret = motor.getActiveTrajectoryVelocity();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public double getActiveTrajectoryHeading() {
		double ret = motor.getActiveTrajectoryHeading();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public ErrorCode configMotionCruiseVelocity(int sensorUnitsPer100ms, int timeoutMs) {
		ErrorCode ret = motor.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs);
		logger.trace("{} sensorUnitsPer100ms = {}, timeoutMs = {} -> {}", this.name, box(sensorUnitsPer100ms), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configMotionAcceleration(int sensorUnitsPer100msPerSec, int timeoutMs) {
		ErrorCode ret = motor.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs);
		logger.trace("{} sensorUnitsPer100msPerSec = {}, timeoutMs = {} -> {}", this.name, box(sensorUnitsPer100msPerSec), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode clearMotionProfileTrajectories() {
		ErrorCode ret = motor.clearMotionProfileTrajectories();
		logger.trace("{} -> {}", this.name, ret);
		return ret;
	}

	@Override
	public int getMotionProfileTopLevelBufferCount() {
		int ret = motor.getMotionProfileTopLevelBufferCount();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public ErrorCode pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
		ErrorCode ret = motor.pushMotionProfileTrajectory(trajPt);
		logger.trace("{} trajPt = {} -> {}", this.name, trajPt, ret);
		return ret;
	}

	@Override
	public boolean isMotionProfileTopLevelBufferFull() {
		boolean ret = motor.isMotionProfileTopLevelBufferFull();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public void processMotionProfileBuffer() {
		logger.trace("{}", this.name);
		motor.processMotionProfileBuffer();
	}

	@Override
	public ErrorCode getMotionProfileStatus(MotionProfileStatus statusToFill) {
		ErrorCode ret = motor.getMotionProfileStatus(statusToFill);
		logger.trace("{} statusToFill = {} -> {}", this.name, statusToFill, ret);
		return ret;
	}

	@Override
	public ErrorCode clearMotionProfileHasUnderrun(int timeoutMs) {
		ErrorCode ret = motor.clearMotionProfileHasUnderrun(timeoutMs);
		logger.trace("{} timeoutMs = {} -> {}", this.name, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode changeMotionControlFramePeriod(int periodMs) {
		ErrorCode ret = motor.changeMotionControlFramePeriod(periodMs);
		logger.trace("{} periodMs = {} -> {}", this.name, box(periodMs), ret);
		return ret;
	}

	@Override
	public ErrorCode getLastError() {
		ErrorCode ret = motor.getLastError();
		logger.trace("{} -> {}", this.name, ret);
		return ret;
	}

	@Override
	public ErrorCode getFaults(Faults toFill) {
		ErrorCode ret = motor.getFaults(toFill);
		logger.trace("{} toFill = {} -> {}", this.name, toFill, ret);
		return ret;
	}

	@Override
	public ErrorCode getStickyFaults(StickyFaults toFill) {
		ErrorCode ret = motor.getStickyFaults(toFill);
		logger.trace("{} toFill = {} -> {}", this.name, toFill, ret);
		return ret;
	}

	@Override
	public ErrorCode clearStickyFaults(int timeoutMs) {
		ErrorCode ret = motor.clearStickyFaults(timeoutMs);
		logger.trace("{} timeoutMs = {} -> {}", this.name, box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int getFirmwareVersion() {
		int ret = motor.getFirmwareVersion();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public boolean hasResetOccurred() {
		boolean ret = motor.hasResetOccurred();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public ErrorCode configSetCustomParam(int newValue, int paramIndex, int timeoutMs) {
		ErrorCode ret = motor.configSetCustomParam(newValue, paramIndex, timeoutMs);
		logger.trace("{} newValue = {}, paramIndex = {}, timeoutMs = {} -> {}", this.name, box(newValue), box(paramIndex), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public int configGetCustomParam(int paramIndex, int timoutMs) {
		int ret = motor.configGetCustomParam(paramIndex, timoutMs);
		logger.trace("{} paramIndex = {}, timoutMs = {} -> {}", this.name, box(paramIndex), box(timoutMs), box(ret));
		return ret;
	}

	@Override
	public ErrorCode configSetParameter(ParamEnum param, double value, int subValue, int ordinal, int timeoutMs) {
		ErrorCode ret = motor.configSetParameter(param, value, subValue, ordinal, timeoutMs);
		logger.trace("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, param, box(value), box(subValue), box(ordinal), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public ErrorCode configSetParameter(int param, double value, int subValue, int ordinal, int timeoutMs) {
		ErrorCode ret = motor.configSetParameter(param, value, subValue, ordinal, timeoutMs);
		logger.trace("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, box(param), box(value), box(subValue), box(ordinal), box(timeoutMs), ret);
		return ret;
	}

	@Override
	public double configGetParameter(ParamEnum paramEnum, int ordinal, int timeoutMs) {
		double ret = motor.configGetParameter(paramEnum, ordinal, timeoutMs);
		logger.trace("{} paramEnum = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, paramEnum, box(ordinal), box(timeoutMs), box(ret));
		return ret;
	}

	@Override
	public double configGetParameter(int paramEnum, int ordinal, int timeoutMs) {
		double ret = motor.configGetParameter(paramEnum, ordinal, timeoutMs);
		logger.trace("{} paramEnum = {}, ordinal = {}, timeoutMs = {} -> {}", this.name, box(paramEnum), box(ordinal), box(timeoutMs), box(ret));
		return ret;
	}

	@Override
	public int getBaseID() {
		int ret = motor.getBaseID();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public int getDeviceID() {
		int ret = motor.getDeviceID();
		logger.trace("{} -> {}", this.name, box(ret));
		return ret;
	}

	@Override
	public void follow(IMotorController masterToFollow) {
		logger.trace("{} masterToFollow = {}", this.name, masterToFollow);
		motor.follow(masterToFollow);
	}

	@Override
	public void valueUpdated() {
		logger.trace("{}", this.name);
		motor.valueUpdated();
	}
}
