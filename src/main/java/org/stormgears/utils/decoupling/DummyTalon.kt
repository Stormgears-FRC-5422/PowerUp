package org.stormgears.utils.decoupling

import com.ctre.phoenix.ErrorCode
import com.ctre.phoenix.ParamEnum
import com.ctre.phoenix.motion.MotionProfileStatus
import com.ctre.phoenix.motion.TrajectoryPoint
import com.ctre.phoenix.motorcontrol.*
import org.apache.logging.log4j.LogManager

// TODO: MotorLogger wrapper thingy, also return convincing values from these methods
class DummyTalon(private val deviceNumber: Int) : ITalon {
	companion object {
		private val logger = LogManager.getLogger(DummyTalon::class.java)
	}

	class DummySensorCollection : ISensorCollection {
		companion object {
			private val logger = LogManager.getLogger(DummySensorCollection::class.java)
		}

		override val analogIn: Int
			get() = 0
		override val analogInRaw: Int
			get() = 0
		override val analogInVel: Int
			get() = 0
		override val quadraturePosition: Int
			get() = 0
		override val quadratureVelocity: Int
			get() = 0
		override val pulseWidthPosition: Int
			get() = 0
		override val pulseWidthVelocity: Int
			get() = 0
		override val pulseWidthRiseToFallUs: Int
			get() = 0
		override val pulseWidthRiseToRiseUs: Int
			get() = 0
		override val pinStateQuadA: Boolean
			get() = false
		override val pinStateQuadB: Boolean
			get() = false
		override val pinStateQuadIdx: Boolean
			get() = false
		override val isFwdLimitSwitchClosed: Boolean
			get() = false
		override val isRevLimitSwitchClosed: Boolean
			get() = false

		override fun setAnalogPosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return ErrorCode.OK
		}

		override fun setQuadraturePosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return ErrorCode.OK
		}

		override fun setPulseWidthPosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return ErrorCode.OK
		}

	}

	override val sensorCollection: ISensorCollection = DummySensorCollection()

	private var _controlMode: ControlMode? = ControlMode.PercentOutput
	private var value = 0.0

	override fun configSetParameter(param: ParamEnum?, value: Double, subValue: Int, ordinal: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configSetParameter(param: Int, value: Double, subValue: Int, ordinal: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configSetCustomParam(newValue: Int, paramIndex: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getFirmwareVersion(): Int {
		return 0;
	}

	override fun configContinuousCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configForwardSoftLimitThreshold(forwardSensorLimit: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configVoltageMeasurementFilter(filterWindowSamples: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configMotionAcceleration(sensorUnitsPer100msPerSec: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun enableVoltageCompensation(enable: Boolean) {

	}

	override fun configPeakCurrentLimit(amps: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun overrideLimitSwitchesEnable(enable: Boolean) {

	}

	override fun config_kI(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getMotorOutputVoltage(): Double {
		return 0.0
	}

	override fun getDeviceID(): Int {
		return deviceNumber;
	}

	override fun configReverseLimitSwitchSource(type: LimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configReverseLimitSwitchSource(type: RemoteLimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, deviceID: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getMotionProfileStatus(statusToFill: MotionProfileStatus?): ErrorCode {
		return ErrorCode.OK
	}

	override fun configPeakCurrentDuration(milliseconds: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getIntegralAccumulator(pidIdx: Int): Double {
		return 0.0
	}

	override fun getMotorOutputPercent(): Double {
		return 0.0
	}

	override fun config_IntegralZone(slotIdx: Int, izone: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun setNeutralMode(neutralMode: NeutralMode?) {

	}

	override fun follow(masterToFollow: IMotorController?) {

	}

	override fun getStickyFaults(toFill: StickyFaults?): ErrorCode {
		return ErrorCode.OK
	}

	override fun setStatusFramePeriod(frame: StatusFrameEnhanced?, periodMs: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun setStatusFramePeriod(frame: StatusFrame?, periodMs: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configForwardSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getActiveTrajectoryPosition(): Int {
		return 0;
	}

	override fun configOpenloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configVelocityMeasurementWindow(windowSize: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configPeakOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getSelectedSensorPosition(pidIdx: Int): Int {
		return 0;
	}

	override fun getErrorDerivative(pidIdx: Int): Double {
		return 0.0
	}

	override fun configPeakOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configVoltageCompSaturation(voltage: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configVelocityMeasurementPeriod(period: VelocityMeasPeriod?, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun config_kF(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun setIntegralAccumulator(iaccum: Double, pidIdx: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun setControlFramePeriod(frame: ControlFrame?, periodMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configForwardLimitSwitchSource(type: LimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configForwardLimitSwitchSource(type: RemoteLimitSwitchSource?, normalOpenOrClose: LimitSwitchNormal?, deviceID: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getMotionProfileTopLevelBufferCount(): Int {
		return 0;
	}

	override fun getInverted(): Boolean {
		return false
	}

	override fun configGetCustomParam(paramIndex: Int, timoutMs: Int): Int {
		return 0;
	}

	override fun clearStickyFaults(timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun valueUpdated() {

	}

	override fun configNominalOutputReverse(percentOut: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun enableCurrentLimit(enable: Boolean) {

	}

	override fun getBusVoltage(): Double {
		return 0.0
	}

	override fun config_kP(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun processMotionProfileBuffer() {

	}

	override fun getSelectedSensorVelocity(pidIdx: Int): Int {
		return 0;
	}

	override fun getOutputCurrent(): Double {
		return 0.0
	}

	override fun changeMotionControlFramePeriod(periodMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getTemperature(): Double {
		return 0.0
	}

	override fun getActiveTrajectoryHeading(): Double {
		return 0.0
	}

	override fun setInverted(invert: Boolean) {

	}

	override fun configReverseSoftLimitEnable(enable: Boolean, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun setSensorPhase(PhaseSensor: Boolean) {

	}

	override fun configMaxIntegralAccumulator(slotIdx: Int, iaccum: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun neutralOutput() {

	}

	override fun configSelectedFeedbackSensor(feedbackDevice: FeedbackDevice?, pidIdx: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configSelectedFeedbackSensor(feedbackDevice: RemoteFeedbackDevice?, pidIdx: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configRemoteFeedbackFilter(deviceID: Int, remoteSensorSource: RemoteSensorSource?, remoteOrdinal: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configAllowableClosedloopError(slotIdx: Int, allowableCloseLoopError: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configNominalOutputForward(percentOut: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun isMotionProfileTopLevelBufferFull(): Boolean {
		return false
	}

	override fun hasResetOccurred(): Boolean {
		return false
	}

	override fun getBaseID(): Int {
		return 0;
	}

	override fun setSelectedSensorPosition(sensorPos: Int, pidIdx: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun selectProfileSlot(slotIdx: Int, pidIdx: Int) {

	}

	override fun getActiveTrajectoryVelocity(): Int {
		return 0;
	}

	override fun clearMotionProfileTrajectories(): ErrorCode {
		return ErrorCode.OK
	}

	override fun getClosedLoopError(pidIdx: Int): Int {
		return 0;
	}

	override fun set(Mode: ControlMode?, demand: Double) {
		_controlMode = Mode
	}

	override fun set(Mode: ControlMode?, demand0: Double, demand1: Double) {
		_controlMode = Mode
	}

	override fun set(speed: Double) {

	}

	override fun getFaults(toFill: Faults?): ErrorCode {
		return ErrorCode.OK
	}

	override fun configClosedloopRamp(secondsFromNeutralToFull: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun pushMotionProfileTrajectory(trajPt: TrajectoryPoint?): ErrorCode {
		return ErrorCode.OK
	}

	override fun clearMotionProfileHasUnderrun(timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configNeutralDeadband(percentDeadband: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configReverseSoftLimitThreshold(reverseSensorLimit: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configGetParameter(paramEnum: ParamEnum?, ordinal: Int, timeoutMs: Int): Double {
		return 0.0
	}

	override fun configGetParameter(paramEnum: Int, ordinal: Int, timeoutMs: Int): Double {
		return 0.0
	}

	override fun configMotionCruiseVelocity(sensorUnitsPer100ms: Int, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun getLastError(): ErrorCode {
		return ErrorCode.OK
	}

	override fun overrideSoftLimitsEnable(enable: Boolean) {

	}

	override fun getStatusFramePeriod(frame: StatusFrameEnhanced?, timeoutMs: Int): Int {
		return 0;
	}

	override fun getStatusFramePeriod(frame: StatusFrame?, timeoutMs: Int): Int {
		return 0;
	}

	override fun config_kD(slotIdx: Int, value: Double, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun configSensorTerm(sensorTerm: SensorTerm?, feedbackDevice: FeedbackDevice?, timeoutMs: Int): ErrorCode {
		return ErrorCode.OK
	}

	override fun pidWrite(output: Double) {

	}

	override fun stopMotor() {

	}

	override fun get(): Double {
		return 0.0
	}

	override fun disable() {

	}

	override fun setExpiration(timeout: Double) {

	}

	override fun setSafetyEnabled(enabled: Boolean) {

	}

	override fun getExpiration(): Double {
		return 0.0
	}

	override fun getDescription(): String {
		return "DummyTalon $deviceNumber"
	}

	override fun isAlive(): Boolean {
		return false
	}

	override fun isSafetyEnabled(): Boolean {
		return false
	}

	override fun getControlMode(): ControlMode? {
		return _controlMode
	}

	override fun getHandle(): Long {
		return 0
	}


	override val dummy = true;
}
