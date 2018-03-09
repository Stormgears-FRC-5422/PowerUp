package org.stormgears.powerup.subsystems.navigator.motionprofile

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.information.RobotConfiguration
import org.stormgears.utils.StormTalon

class MotionMagic(private val talon: StormTalon, maxVel: Double, maxAccel: Double) {
	companion object {
		var config = RobotConfiguration.getInstance()
		private const val TALON_FPID_TIMEOUT = 10
	}

	init {
		/* first choose the sensor */
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TALON_FPID_TIMEOUT)
		talon.setSensorPhase(true)
		talon.inverted = true


		/* Set relevant frame periods to be at least as fast as periodic rate */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT)
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT)
		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0.0, TALON_FPID_TIMEOUT)
		talon.configNominalOutputReverse(0.0, TALON_FPID_TIMEOUT)
		talon.configPeakOutputForward(1.0, TALON_FPID_TIMEOUT)
		talon.configPeakOutputReverse(-1.0, TALON_FPID_TIMEOUT)

		/* set closed loop gains in slot0 - see documentation */
		talon.selectProfileSlot(0, 0)
		/* set acceleration and vcruise velocity - see documentation */


		talon.configMotionCruiseVelocity(Math.round(maxVel).toInt(), TALON_FPID_TIMEOUT)
		talon.configMotionAcceleration(Math.round(maxAccel).toInt(), TALON_FPID_TIMEOUT)
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, 0, TALON_FPID_TIMEOUT)
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
		//redundant code
		talon.config_kP(0, Robot.config.positionP, TALON_FPID_TIMEOUT)
		talon.config_kI(0, Robot.config.positionI, TALON_FPID_TIMEOUT)
		talon.config_kD(0, Robot.config.positionD, TALON_FPID_TIMEOUT)
		talon.config_IntegralZone(0, Robot.config.positionIzone, TALON_FPID_TIMEOUT)

		talon.set(ControlMode.MotionMagic, targetPos.toDouble())
	}
}
