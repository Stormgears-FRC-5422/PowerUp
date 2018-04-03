package org.stormgears.powerup.subsystems.navigator.motionprofile

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import org.stormgears.powerup.subsystems.navigator.DriveTalons
import org.stormgears.utils.talons.ITalon

class MotionMagic(private val talon: ITalon, maxVel: Double, maxAccel: Double) {
	companion object {
		const val TALON_FPID_TIMEOUT = 10
	}

	class MotionMagicDriveConfig(maxVel: Double, maxAccel: Double) : DriveTalons.DriveTalonConfig() {
		/* set acceleration and vcruise velocity - see documentation */
		override val motionCruiseVelocity: Int = Math.round(maxVel).toInt()
		override val motionAcceleration: Int = Math.round(maxAccel).toInt()
	}

	init {
		/* first choose the sensor */
//		talon.setSensorPhase(true)
//		talon.inverted = true

		talon.setConfig(MotionMagicDriveConfig(maxVel, maxAccel))

		/* Set relevant frame periods to be at least as fast as periodic rate */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT)
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT)

		/* set closed loop gains in slot0 - see documentation */
		talon.selectProfileSlot(0, 0)

		/* zero the sensor */
		talon.sensorCollection.setQuadraturePosition(0, TALON_FPID_TIMEOUT)
	}

	/**
	 * The runMotionMagic method receives an encoder position
	 * (8192 ticks / 1 revolution) and uses the MotionMagic
	 * ControlMode along with PID to get to the commanded position.
	 * This class and method applies to only one talon.
	 *
	 * @param targetPos - encoder position
	 */
	fun runMotionMagic(targetPos: Int) {
		talon.sensorCollection.setQuadraturePosition(0, TALON_FPID_TIMEOUT)

		talon.set(ControlMode.MotionMagic, targetPos.toDouble())
	}
}
