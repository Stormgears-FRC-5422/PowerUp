package org.stormgears.powerup.subsystems.navigator;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.stormgears.powerup.Robot;

public class GlobalMapping {

	NetworkTable table = NetworkTable.getTable("GP Table");

	static final double PI = Math.PI;
	static final double WHEEL_RADIUS = 4.0; //inches
	static final int ENC_RESOLUTION = 2048; //ticks per revolution
	static final double RADIANS_PER_TICK = 2*Math.PI/(float) ENC_RESOLUTION;

	public static long enc_fl;
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
		SmartDashboard.putNumber("NavX Angle", ahrs.getAngle());
		SmartDashboard.putNumber("NavX Yaw", ahrs.getYaw());
		table.putNumber("GP_THETA", getTheta());
		table.putNumber("GP_X_POS", x);
		table.putNumber("GP_Y_POS", y);
		table.putNumber("GP_VEL_X", vel_x);
		table.putNumber("GP_VEL_Y", vel_y);
	}

	public void resetPosition(double X, double Y, double theta) {
		x = X;
		y = Y;
		vel_x = 0;
		vel_y = 0;

		ahrs.setAngleAdjustment((theta*180/Math.PI)-ahrs.getAngle());
	}

	public static void updatePos() {
		enc_fl = Robot.driveTalons.getTalons()[Robot.config.frontLeftTalonId].getSensorCollection().getQuadraturePosition();
		enc_fr = Robot.driveTalons.getTalons()[Robot.config.frontRightTalonId].getSensorCollection().getQuadraturePosition();
		enc_bl = Robot.driveTalons.getTalons()[Robot.config.rearLeftTalonId].getSensorCollection().getQuadraturePosition();
		enc_br = Robot.driveTalons.getTalons()[Robot.config.rearRightTalonId].getSensorCollection().getQuadraturePosition();

		int d_enc_fl = (int) (enc_fl - prev_enc_fl);
		int d_enc_fr = (int) (enc_fr - prev_enc_fr);
		int d_enc_bl = (int) (enc_bl - prev_enc_bl);
		int d_enc_br = (int) (enc_br - prev_enc_br);

		double dRobotX = (d_enc_fr + d_enc_bl - (d_enc_fl + d_enc_br))*RADIANS_PER_TICK*WHEEL_RADIUS/4.f;
		double dRobotY = (d_enc_fl + d_enc_bl + (d_enc_fl + d_enc_br))*RADIANS_PER_TICK*WHEEL_RADIUS/4.f;

		double angle = getTheta();

		double dFieldX = dRobotX * Math.cos(angle);
		double dFieldY = dRobotY * Math.cos(angle);

		x += dFieldX;
		y += dRobotY;

		double dt = Timer.getFPGATimestamp() - prevTimeStamp;
		double temp_vel_x = dFieldX/dt;
		double temp_vel_y = dFieldY/dt;

		//TODO: Figure out the smoothing factor code
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static double getTheta() {
		return ahrs.getAngle()*Math.PI/180.0;
	}

}
