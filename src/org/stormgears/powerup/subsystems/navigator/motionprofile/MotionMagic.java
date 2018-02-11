package org.stormgears.powerup.subsystems.navigator.motionprofile;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.information.RobotConfiguration;
import org.stormgears.utils.StormTalon;

import java.util.concurrent.TimeUnit;

public class MotionMagic {

	private StormTalon talon;
	public static RobotConfiguration config = RobotConfiguration.getInstance();
	private static final int TALON_FPID_TIMEOUT = 10;

	public MotionMagic(StormTalon talon, double maxVel, double maxAccel) {
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
		talon.configMotionCruiseVelocity(maxVel, TALON_FPID_TIMEOUT);
		talon.configMotionAcceleration(maxAccel, TALON_FPID_TIMEOUT);
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, 0, TALON_FPID_TIMEOUT);
	}

	/** The runMotionMagic method receives an encoder position
	 * (8192 ticks / 1 revolution) and uses the MotionMagic
	 * ControlMode along with PID to get to the commanded position.
	 * This class and method applies to only one talon.
	 *
	 * @param targetPos - encoder position
	 */
	public void runMotionMagic(int targetPos) {
		//sets position using motion magic
		talon.config_kP(0, Robot.config.positionP, TALON_FPID_TIMEOUT);
		talon.config_kI(0, Robot.config.positionI, TALON_FPID_TIMEOUT);
		talon.config_kD(0, Robot.config.positionD, TALON_FPID_TIMEOUT);
		talon.config_IntegralZone(0, Robot.config.positionIzone, TALON_FPID_TIMEOUT);
		talon.set(ControlMode.MotionMagic, targetPos);

		//TODO: figure out what this try-catch actually does
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
			System.out.println("SOMEBODY FORGOT TO PRINT SOMETHING IN THIS CATCH BLOCK. BLAME WHOEVER WROTE MotionMagic.java");
			System.out.println(e.getMessage());
		}
	}
}


