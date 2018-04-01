package org.stormgears.utils.talons

import com.ctre.phoenix.motorcontrol.*

open class DefaultPIDSlot : PIDSlot {
	override val integralZone: Int = 0
	override val kD: Double = 0.0
	override val kF: Double = 0.0
	override val kI: Double = 0.0
	override val kP: Double = 0.0
	override val allowableClosedloopError: Int = 0
	override val closedLoopPeakOutput: Double = 1.0
	override val closedLoopPeriod: Int = 1
	override val maxIntegralAccumulator: Double = 0.0
}

/**
 * Factory default Talon configuration
 */
open class FactoryTalonConfig : TalonConfig {
	override val profileSlots: Array<PIDSlot> = Array(4) { DefaultPIDSlot() }

	override val auxPIDPolarity: Boolean = false
	override val closedloopRamp: Double = 0.0
	override val forwardLimitSwitchSource: LimitSwitchSourceConfig =
		LocalLimitSwitchSourceConfig(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen)
	override val forwardSoftLimitEnable: Boolean = false
	override val forwardSoftLimitThreshold: Int = 0
	override val motionAcceleration: Int = 0
	override val motionCruiseVelocity: Int = 0
	override val motionProfileTrajectoryPeriod: Int = 0
	override val neutralDeadband: Double = 0.04
	override val nominalOutputForward: Double = 0.0
	override val nominalOutputReverse: Double = 0.0
	override val openloopRamp: Double = 0.0
	override val peakOutputForward: Double = +1.0
	override val peakOutputReverse: Double = -1.0
	override val remoteFeedbackFilter = RemoteFeedbackFilterConfig(0, RemoteSensorSource.Off, 0)
	override val reverseLimitSwitchSource: LimitSwitchSourceConfig =
		LocalLimitSwitchSourceConfig(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen)
	override val reverseSoftLimitEnable: Boolean = false
	override val reverseSoftLimitThreshold: Int = 0
	override val selectedFeedbackCoefficient: Double = 1.0
	override val selectedFeedbackSensor: FeedbackDeviceConfig = LocalFeedbackDeviceConfig(FeedbackDevice.None)
	override val sensorTerm: SensorTermConfig? = null // SensorTermConfig(SensorTerm.Sum0, FeedbackDevice.QuadEncoder)
	override val velocityMeasurementPeriod: VelocityMeasPeriod = VelocityMeasPeriod.Period_100Ms
	override val velocityMeasurementWindow: Int = 64
	override val voltageCompSaturation: Double = 0.0
	override val voltageMeasurementFilter: Int = 32
	override val enableHeadingHold: Boolean = false
	override val enableVoltageCompensation: Boolean = false // TODO
	override val inverted: Boolean = false
	override val neutralMode: NeutralMode = NeutralMode.Coast
	override val sensorPhase: Boolean = false
	override val continuousCurrentLimit: Int = 0
	override val peakCurrentDuration: Int = 0
	override val peakCurrentLimit: Int = 0
}
