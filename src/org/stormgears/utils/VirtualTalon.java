package org.stormgears.utils;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.util.Unbox.box;

// TODO: Correctly return dummy values when necessary
public class VirtualTalon implements IMotorControllerEnhanced {
	private static final Logger logger = LogManager.getLogger(VirtualTalon.class);

	public String name;

	public VirtualTalon(String name) {
		this.name = name;
	}

	@Override
	public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) {
		logger.debug("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {}", name, feedbackDevice, box(pidIdx), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrameEnhanced frame, int periodMs, int timeoutMs) {
		logger.debug("{} frame = {}, periodMs = {}, timeoutMs = {}", name, frame, box(periodMs), box(timeoutMs));
		return null;
	}

	@Override
	public int getStatusFramePeriod(StatusFrameEnhanced frame, int timeoutMs) {
		logger.debug("{} frame = {}, timeoutMs = {}", name, frame, box(timeoutMs));
		return 0;
	}

	@Override
	public ErrorCode configVelocityMeasurementPeriod(VelocityMeasPeriod period, int timeoutMs) {
		logger.debug("{} period = {}, timeoutMs = {}", name, period, box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configVelocityMeasurementWindow(int windowSize, int timeoutMs) {
		logger.debug("{} windowSize = {}, timeoutMs = {}", name, box(windowSize), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configForwardLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) {
		logger.debug("{} type = {}, normalOpenOrClose = {}, timeoutMs = {}", name, type, normalOpenOrClose, box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configReverseLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) {
		logger.debug("{} type = {}, normalOpenOrClose = {}, timeoutMs = {}", name, type, normalOpenOrClose, box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configPeakCurrentLimit(int amps, int timeoutMs) {
		logger.debug("{} amps = {}, timeoutMs = {}", name, box(amps), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configPeakCurrentDuration(int milliseconds, int timeoutMs) {
		logger.debug("{} milliseconds = {}, timeoutMs = {}", name, box(milliseconds), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configContinuousCurrentLimit(int amps, int timeoutMs) {
		logger.debug("{} amps = {}, timeoutMs = {}", name, box(amps), box(timeoutMs));
		return null;
	}

	@Override
	public void enableCurrentLimit(boolean enable) {
		logger.debug("{} enable = {}", name, box(enable));

	}

	@Override
	public void set(ControlMode Mode, double demand) {
		logger.debug("{} Mode = {}, demand = {}", name, Mode, box(demand));

	}

	@Override
	public void set(ControlMode Mode, double demand0, double demand1) {
		logger.debug("{} Mode = {}, demand0 = {}, demand1 = {}", name, Mode, box(demand0), box(demand1));

	}

	@Override
	public void neutralOutput() {
		logger.debug("{} ", name);

	}

	@Override
	public void setNeutralMode(NeutralMode neutralMode) {
		logger.debug("{} neutralMode = {}", name, neutralMode);

	}

	@Override
	public void setSensorPhase(boolean PhaseSensor) {
		logger.debug("{} PhaseSensor = {}", name, box(PhaseSensor));

	}

	@Override
	public void setInverted(boolean invert) {
		logger.debug("{} invert = {}", name, box(invert));

	}

	@Override
	public boolean getInverted() {
		logger.trace("{} ", name);
		return false;
	}

	@Override
	public ErrorCode configOpenloopRamp(double secondsFromNeutralToFull, int timeoutMs) {
		logger.debug("{} secondsFromNeutralToFull = {}, timeoutMs = {}", name, box(secondsFromNeutralToFull), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configClosedloopRamp(double secondsFromNeutralToFull, int timeoutMs) {
		logger.debug("{} secondsFromNeutralToFull = {}, timeoutMs = {}", name, box(secondsFromNeutralToFull), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configPeakOutputForward(double percentOut, int timeoutMs) {
		logger.debug("{} percentOut = {}, timeoutMs = {}", name, box(percentOut), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configPeakOutputReverse(double percentOut, int timeoutMs) {
		logger.debug("{} percentOut = {}, timeoutMs = {}", name, box(percentOut), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configNominalOutputForward(double percentOut, int timeoutMs) {
		logger.debug("{} percentOut = {}, timeoutMs = {}", name, box(percentOut), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configNominalOutputReverse(double percentOut, int timeoutMs) {
		logger.debug("{} percentOut = {}, timeoutMs = {}", name, box(percentOut), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configNeutralDeadband(double percentDeadband, int timeoutMs) {
		logger.debug("{} percentDeadband = {}, timeoutMs = {}", name, box(percentDeadband), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configVoltageCompSaturation(double voltage, int timeoutMs) {
		logger.debug("{} voltage = {}, timeoutMs = {}", name, box(voltage), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configVoltageMeasurementFilter(int filterWindowSamples, int timeoutMs) {
		logger.debug("{} filterWindowSamples = {}, timeoutMs = {}", name, box(filterWindowSamples), box(timeoutMs));
		return null;
	}

	@Override
	public void enableVoltageCompensation(boolean enable) {
		logger.debug("{} enable = {}", name, box(enable));

	}

	@Override
	public double getBusVoltage() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public double getMotorOutputPercent() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public double getMotorOutputVoltage() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public double getOutputCurrent() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public double getTemperature() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public ErrorCode configSelectedFeedbackSensor(RemoteFeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) {
		logger.debug("{} feedbackDevice = {}, pidIdx = {}, timeoutMs = {}", name, feedbackDevice, box(pidIdx), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configRemoteFeedbackFilter(int deviceID, RemoteSensorSource remoteSensorSource, int remoteOrdinal, int timeoutMs) {
		logger.debug("{} deviceID = {}, remoteSensorSource = {}, remoteOrdinal = {}, timeoutMs = {}", name, box(deviceID), remoteSensorSource, box(remoteOrdinal), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configSensorTerm(SensorTerm sensorTerm, FeedbackDevice feedbackDevice, int timeoutMs) {
		logger.debug("{} sensorTerm = {}, feedbackDevice = {}, timeoutMs = {}", name, sensorTerm, feedbackDevice, box(timeoutMs));
		return null;
	}

	@Override
	public int getSelectedSensorPosition(int pidIdx) {
		logger.debug("{} pidIdx = {}", name, box(pidIdx));
		return 0;
	}

	@Override
	public int getSelectedSensorVelocity(int pidIdx) {
		logger.debug("{} pidIdx = {}", name, box(pidIdx));
		return 0;
	}

	@Override
	public ErrorCode setSelectedSensorPosition(int sensorPos, int pidIdx, int timeoutMs) {
		logger.debug("{} sensorPos = {}, pidIdx = {}, timeoutMs = {}", name, box(sensorPos), box(pidIdx), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode setControlFramePeriod(ControlFrame frame, int periodMs) {
		logger.debug("{} frame = {}, periodMs = {}", name, frame, box(periodMs));
		return null;
	}

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrame frame, int periodMs, int timeoutMs) {
		logger.debug("{} frame = {}, periodMs = {}, timeoutMs = {}", name, frame, box(periodMs), box(timeoutMs));
		return null;
	}

	@Override
	public int getStatusFramePeriod(StatusFrame frame, int timeoutMs) {
		logger.debug("{} frame = {}, timeoutMs = {}", name, frame, box(timeoutMs));
		return 0;
	}

	@Override
	public ErrorCode configForwardLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) {
		logger.debug("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {}", name, type, normalOpenOrClose, box(deviceID), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configReverseLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) {
		logger.debug("{} type = {}, normalOpenOrClose = {}, deviceID = {}, timeoutMs = {}", name, type, normalOpenOrClose, box(deviceID), box(timeoutMs));
		return null;
	}

	@Override
	public void overrideLimitSwitchesEnable(boolean enable) {
		logger.debug("{} enable = {}", name, box(enable));

	}

	@Override
	public ErrorCode configForwardSoftLimitThreshold(int forwardSensorLimit, int timeoutMs) {
		logger.debug("{} forwardSensorLimit = {}, timeoutMs = {}", name, box(forwardSensorLimit), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configReverseSoftLimitThreshold(int reverseSensorLimit, int timeoutMs) {
		logger.debug("{} reverseSensorLimit = {}, timeoutMs = {}", name, box(reverseSensorLimit), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configForwardSoftLimitEnable(boolean enable, int timeoutMs) {
		logger.debug("{} enable = {}, timeoutMs = {}", name, box(enable), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configReverseSoftLimitEnable(boolean enable, int timeoutMs) {
		logger.debug("{} enable = {}, timeoutMs = {}", name, box(enable), box(timeoutMs));
		return null;
	}

	@Override
	public void overrideSoftLimitsEnable(boolean enable) {
		logger.debug("{} enable = {}", name, box(enable));

	}

	@Override
	public ErrorCode config_kP(int slotIdx, double value, int timeoutMs) {
		logger.debug("{} slotIdx = {}, value = {}, timeoutMs = {}", name, box(slotIdx), box(value), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode config_kI(int slotIdx, double value, int timeoutMs) {
		logger.debug("{} slotIdx = {}, value = {}, timeoutMs = {}", name, box(slotIdx), box(value), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode config_kD(int slotIdx, double value, int timeoutMs) {
		logger.debug("{} slotIdx = {}, value = {}, timeoutMs = {}", name, box(slotIdx), box(value), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode config_kF(int slotIdx, double value, int timeoutMs) {
		logger.debug("{} slotIdx = {}, value = {}, timeoutMs = {}", name, box(slotIdx), box(value), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode config_IntegralZone(int slotIdx, int izone, int timeoutMs) {
		logger.debug("{} slotIdx = {}, izone = {}, timeoutMs = {}", name, box(slotIdx), box(izone), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configAllowableClosedloopError(int slotIdx, int allowableCloseLoopError, int timeoutMs) {
		logger.debug("{} slotIdx = {}, allowableCloseLoopError = {}, timeoutMs = {}", name, box(slotIdx), box(allowableCloseLoopError), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configMaxIntegralAccumulator(int slotIdx, double iaccum, int timeoutMs) {
		logger.debug("{} slotIdx = {}, iaccum = {}, timeoutMs = {}", name, box(slotIdx), box(iaccum), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode setIntegralAccumulator(double iaccum, int pidIdx, int timeoutMs) {
		logger.debug("{} iaccum = {}, pidIdx = {}, timeoutMs = {}", name, box(iaccum), box(pidIdx), box(timeoutMs));
		return null;
	}

	@Override
	public int getClosedLoopError(int pidIdx) {
		logger.debug("{} pidIdx = {}", name, box(pidIdx));
		return 0;
	}

	@Override
	public double getIntegralAccumulator(int pidIdx) {
		logger.debug("{} pidIdx = {}", name, box(pidIdx));
		return 0;
	}

	@Override
	public double getErrorDerivative(int pidIdx) {
		logger.debug("{} pidIdx = {}", name, box(pidIdx));
		return 0;
	}

	@Override
	public void selectProfileSlot(int slotIdx, int pidIdx) {
		logger.debug("{} slotIdx = {}, pidIdx = {}", name, box(slotIdx), box(pidIdx));

	}

	@Override
	public int getActiveTrajectoryPosition() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public int getActiveTrajectoryVelocity() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public double getActiveTrajectoryHeading() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public ErrorCode configMotionCruiseVelocity(int sensorUnitsPer100ms, int timeoutMs) {
		logger.debug("{} sensorUnitsPer100ms = {}, timeoutMs = {}", name, box(sensorUnitsPer100ms), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configMotionAcceleration(int sensorUnitsPer100msPerSec, int timeoutMs) {
		logger.debug("{} sensorUnitsPer100msPerSec = {}, timeoutMs = {}", name, box(sensorUnitsPer100msPerSec), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode clearMotionProfileTrajectories() {
		logger.debug("{} ", name);
		return null;
	}

	@Override
	public int getMotionProfileTopLevelBufferCount() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public ErrorCode pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
		logger.debug("{} trajPt = {}", name, trajPt);
		return null;
	}

	@Override
	public boolean isMotionProfileTopLevelBufferFull() {
		logger.trace("{} ", name);
		return false;
	}

	@Override
	public void processMotionProfileBuffer() {
		logger.debug("{} ", name);

	}

	@Override
	public ErrorCode getMotionProfileStatus(MotionProfileStatus statusToFill) {
		logger.debug("{} statusToFill = {}", name, statusToFill);
		return null;
	}

	@Override
	public ErrorCode clearMotionProfileHasUnderrun(int timeoutMs) {
		logger.debug("{} timeoutMs = {}", name, box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode changeMotionControlFramePeriod(int periodMs) {
		logger.debug("{} periodMs = {}", name, box(periodMs));
		return null;
	}

	@Override
	public ErrorCode getLastError() {
		logger.trace("{} ", name);
		return null;
	}

	@Override
	public ErrorCode getFaults(Faults toFill) {
		logger.debug("{} toFill = {}", name, toFill);
		return null;
	}

	@Override
	public ErrorCode getStickyFaults(StickyFaults toFill) {
		logger.debug("{} toFill = {}", name, toFill);
		return null;
	}

	@Override
	public ErrorCode clearStickyFaults(int timeoutMs) {
		logger.debug("{} timeoutMs = {}", name, box(timeoutMs));
		return null;
	}

	@Override
	public int getFirmwareVersion() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public boolean hasResetOccurred() {
		logger.trace("{} ", name);
		return false;
	}

	@Override
	public ErrorCode configSetCustomParam(int newValue, int paramIndex, int timeoutMs) {
		logger.debug("{} newValue = {}, paramIndex = {}, timeoutMs = {}", name, box(newValue), box(paramIndex), box(timeoutMs));
		return null;
	}

	@Override
	public int configGetCustomParam(int paramIndex, int timoutMs) {
		logger.debug("{} paramIndex = {}, timoutMs = {}", name, box(paramIndex), box(timoutMs));
		return 0;
	}

	@Override
	public ErrorCode configSetParameter(ParamEnum param, double value, int subValue, int ordinal, int timeoutMs) {
		logger.debug("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {}", name, param, box(value), box(subValue), box(ordinal), box(timeoutMs));
		return null;
	}

	@Override
	public ErrorCode configSetParameter(int param, double value, int subValue, int ordinal, int timeoutMs) {
		logger.debug("{} param = {}, value = {}, subValue = {}, ordinal = {}, timeoutMs = {}", name, box(param), box(value), box(subValue), box(ordinal), box(timeoutMs));
		return null;
	}

	@Override
	public double configGetParameter(ParamEnum paramEnum, int ordinal, int timeoutMs) {
		logger.debug("{} paramEnum = {}, ordinal = {}, timeoutMs = {}", name, paramEnum, box(ordinal), box(timeoutMs));
		return 0;
	}

	@Override
	public double configGetParameter(int paramEnum, int ordinal, int timeoutMs) {
		logger.debug("{} paramEnum = {}, ordinal = {}, timeoutMs = {}", name, box(paramEnum), box(ordinal), box(timeoutMs));
		return 0;
	}

	@Override
	public int getBaseID() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public int getDeviceID() {
		logger.trace("{} ", name);
		return 0;
	}

	@Override
	public void follow(IMotorController masterToFollow) {
		logger.debug("{} masterToFollow = {}", name, masterToFollow);

	}

	@Override
	public void valueUpdated() {
		logger.trace("{} ", name);

	}
}
