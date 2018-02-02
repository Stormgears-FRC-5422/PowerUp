package org.stormgears.powerup.subsystems.navigator;

import com.sun.xml.internal.bind.v2.TODO;
import org.stormgears.powerup.Robot;


import org.stormgears.powerup.subsystems.sensors.Sensors;

//import org.stormgears.powerup.subsystems.RunnableSubsystem;
import org.stormgears.powerup.subsystems.navigator.Drive;
//import org.stormgears.powerup.subsystems.utils.NetworkConstants;
//import org.stormgears.powerup.subsystems.utils.RobotTalonConstants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GlobalMapping {

	static final double PI = Math.PI;
	static final double WHEEL_RADIUS = 4.0; //inches
	static final int ENC_RESOLUTION = 2048; //ticks per revolution


	static long enc_fl;
	static long enc_fr;
	static long enc_bl;
	static long enc_br;

	static double currentTimeStamp;

	static long prev_enc_fl;
	static long prev_enc_fr;
	static long prev_enc_bl;
	static long prev_enc_br;

	static double prevTimeStamp;

	static double x;
	static double y;
	static double vel_x;
	static double vel_y;

	static public AHRS ahrs = new AHRS(Port.kMXP);

	public GlobalMapping() {
		enc_fl = 0;
		enc_fr = 0;
		enc_bl = 0;
		enc_br = 0;

		prev_enc_fl = 0;
		prev_enc_fr = 0;
		prev_enc_bl = 0;
		prev_enc_br = 0;

		prevTimeStamp = Timer.getFPGATimestamp();

		AHRS.BoardYawAxis yawAxis = ahrs.getBoardYawAxis();
		ahrs.zeroYaw();
		SmartDashboard.putNumber("YawAxis", yawAxis.board_axis.getValue());
		SmartDashboard.putNumber("NavX Angle", ahrs.getAngle());
		SmartDashboard.putNumber("NavX Yaw", ahrs.getYaw());

	}

	public void run() {
		updatePos();
		//TODO: Make NetworkConstants class in order to get the GP information for all of the below
		SmartDashboard.putNumber("NavX Angle", ahrs.getAngle());
		SmartDashboard.putNumber("NavX Yaw", ahrs.getYaw());
		//networkPublish(NetworkConstants.GP_THETA, getTheta());
		//networkPublish(NetworkConstants.GP_X, x);
		//networkPublish(NetworkConstants.GP_Y, y);
		//networkPublish(NetworkConstants.GP_VX, vx);
		//networkPublish(NetworkConstants.GP_VY, vy);
	}

	public static void updatePos() {
		//TODO: Get access to talons in order to get the enc position for each talon
		//enc_fl = Drive.talons
	}
}
