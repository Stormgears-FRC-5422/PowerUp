package org.stormgears.powerup.subsystems.navigator

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic.Companion.TALON_FPID_TIMEOUT
import org.stormgears.utils.talons.*

class DriveTalons {
	val talons = arrayOf(createTalon(Robot.config.frontLeftTalonId),
		createTalon(Robot.config.frontRightTalonId),
		createTalon(Robot.config.rearLeftTalonId),
		createTalon(Robot.config.rearRightTalonId))

	open class DriveTalonConfig : FactoryTalonConfig() {
		// Slot 0 = velocity mode
		override val profileSlot0: PIDSlot = object : DefaultPIDSlot() {
			override val integralZone: Int = Robot.config.velocityIzone
			override val kP: Double = Robot.config.velocityP
			override val kI: Double = Robot.config.velocityI
			override val kD: Double = Robot.config.velocityD
			override val kF: Double = Robot.config.velocityF
		}

		// Slot 1 = position mode
		override val profileSlot1: PIDSlot = object : DefaultPIDSlot() {
			override val integralZone: Int = Robot.config.positionIzone
			override val kP: Double = Robot.config.positionP
			override val kI: Double = Robot.config.positionI
			override val kD: Double = Robot.config.positionD
			override val kF: Double = Robot.config.velocityF
		}

		override val neutralMode: NeutralMode = NeutralMode.Brake
		override val inverted: Boolean = true
		override val sensorPhase: Boolean = true
		override val selectedFeedbackSensor: FeedbackDeviceConfig = LocalFeedbackDeviceConfig(FeedbackDevice.QuadEncoder)

		override val statusFramePeriod: Map<StatusFrameEnhanced, Int> = mapOf(
			StatusFrameEnhanced.Status_13_Base_PIDF0 to TALON_FPID_TIMEOUT,
			StatusFrameEnhanced.Status_10_MotionMagic to TALON_FPID_TIMEOUT
		)
	}

	val driveTalonConfig = DriveTalonConfig()

	init {
		for (t in talons) {
			t.setConfig(driveTalonConfig)
		}
		velocityPIDMode()
	}

	fun velocityPIDMode() {
		for (t in talons) {
			t.selectProfileSlot(0, 0)
		}
	}

	fun positionPIDMode() {
		for (t in talons) {
			t.selectProfileSlot(1, 0)
		}
	}

}
