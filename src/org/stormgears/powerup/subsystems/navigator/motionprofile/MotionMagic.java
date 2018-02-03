package org.stormgears.powerup.subsystems.navigator.motionprofile;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.utils.StormTalon;

import java.util.concurrent.TimeUnit;


public class MotionMagic {

	StormTalon talon;
	public static RobotConfiguration config = RobotConfiguration.getInstance();
	public static final int TALON_FPID_TIMEOUT = 10;

	public MotionMagic(StormTalon talon) {
		this.talon = talon;
		/* first choose the sensor */
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TALON_FPID_TIMEOUT);
		talon.setSensorPhase(true);
		talon.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, TALON_FPID_TIMEOUT, TALON_FPID_TIMEOUT);
		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0, TALON_FPID_TIMEOUT);
		talon.configNominalOutputReverse(0, TALON_FPID_TIMEOUT);
		talon.configPeakOutputForward(1, TALON_FPID_TIMEOUT);
		talon.configPeakOutputReverse(-1, TALON_FPID_TIMEOUT);

		/* set closed loop gains in slot0 - see documentation */
		talon.selectProfileSlot(0, 0);
		talon.config_kF(0, config.velocityF, TALON_FPID_TIMEOUT);
		talon.config_kP(0, config.velocityP, TALON_FPID_TIMEOUT);
		talon.config_kI(0, config.velocityI, TALON_FPID_TIMEOUT);
		talon.config_kD(0, config.velocityD, TALON_FPID_TIMEOUT);
		/* set acceleration and vcruise velocity - see documentation */
		talon.configMotionCruiseVelocity(15000, TALON_FPID_TIMEOUT);
		talon.configMotionAcceleration(6000, TALON_FPID_TIMEOUT);
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, 0, TALON_FPID_TIMEOUT);
	}

	public void runMotionMagic(Joystick _joy) {
		System.out.println(Robot.driveTalons.getTalons()[1].getSensorCollection().getQuadraturePosition());
		/* get gamepad axis - forward stick is positive */
		double leftYstick = -1.0 * _joy.getY();
		/* calculate the percent motor output */
		double motorOutput = talon.getMotorOutputPercent();


		if (_joy.getRawButton(1)) {
			/* Motion Magic - 8192 ticks/rev * 10 Rotations in either direction */
			double targetPos = 8192 * 2;
			if(talon.equals(Robot.driveTalons.getTalons()[1]) || talon.equals(Robot.driveTalons.getTalons()[3])) {
				targetPos *= -1;
			}
			talon.set(ControlMode.MotionMagic, targetPos);

		} else {
			/* Percent voltage mode */
			//talon.set(ControlMode.PercentOutput, leftYstick);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		}
	}
}


