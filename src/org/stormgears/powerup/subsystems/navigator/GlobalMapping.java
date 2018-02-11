package org.stormgears.powerup.subsystems.navigator;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.stormgears.powerup.Robot;

public class GlobalMapping {


	private static GlobalMapping instance;
	public static GlobalMapping getInstance() { return instance; }

	public static void init() {
		instance = new GlobalMapping();
		instance.run();
	}

	private static NetworkTableInstance robotTable = NetworkTableInstance.getDefault();
	private NetworkTable gpTable = robotTable.getTable("GP Table");


	static final double PI = Math.PI;
	static final double WHEEL_RADIUS = 4.0; //inch
	static final int ENC_RESOLUTION = 8192; //ticks per revolution
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
		SmartDashboard.putNumber("GP_X_POS", x);
		SmartDashboard.putNumber("GP_Y_POS", y);
		SmartDashboard.putNumber("GP_VEL_X", vel_x);
		SmartDashboard.putNumber("GP_VEL_Y", vel_y);

		SmartDashboard.putNumber("ENC_FR",enc_fr);
		gpTable.getEntry("ENC_FR").setNumber(enc_fr);

		SmartDashboard.putNumber("ENC_FL",enc_fl);
		gpTable.getEntry("ENC_FL").setNumber(enc_fl);

		SmartDashboard.putNumber("ENC_BR",enc_br);
		gpTable.getEntry("ENC_BR").setNumber(enc_br);

		SmartDashboard.putNumber("ENC_BL",enc_bl);
		gpTable.getEntry("ENC_BL").setNumber(enc_bl);

		SmartDashboard.putNumber("NavX Angle", ahrs.getAngle());
		SmartDashboard.putNumber("NavX Yaw", ahrs.getYaw());

		gpTable.getEntry("GP_THETA").setNumber(getTheta());
		gpTable.getEntry("GP_X_POS").setNumber(x);
		gpTable.getEntry("GP_Y_POS").setNumber(y);
		gpTable.getEntry("GP_VEL_X").setNumber(vel_x);
		gpTable.getEntry("GP_VEL_Y").setNumber(vel_y);

	}

	public void resetPosition(double X, double Y, double theta) {
		x = X;
		y = Y;
		vel_x = 0;
		vel_y = 0;

		ahrs.setAngleAdjustment((theta*180/Math.PI)-ahrs.getAngle());
	}

	public static void updatePos() {

		enc_fl = -1*Robot.driveTalons.getTalons()[0].getSensorCollection().getQuadraturePosition();
		enc_fr = Robot.driveTalons.getTalons()[1].getSensorCollection().getQuadraturePosition();
		enc_bl =-1* Robot.driveTalons.getTalons()[2].getSensorCollection().getQuadraturePosition();
		enc_br = Robot.driveTalons.getTalons()[3].getSensorCollection().getQuadraturePosition();

		int d_enc_fl = (int) (enc_fl - prev_enc_fl);
		int d_enc_fr = (int) (enc_fr - prev_enc_fr);
		int d_enc_bl = (int) (enc_bl - prev_enc_bl);
		int d_enc_br = (int) (enc_br - prev_enc_br);

		prev_enc_fl=enc_fl;
		prev_enc_fr=enc_fr;
		prev_enc_bl=enc_bl;
		prev_enc_br=enc_br;

		//Probably is how much time has passed since last run
		double time=Timer.getFPGATimestamp() - prevTimeStamp;

		//averages the encoder ticks
		double enc_1=(d_enc_fl + d_enc_br)/2;
		double enc_2=(d_enc_fr + d_enc_bl)/2;

		//double v1=enc_1/ENC_RESOLUTION*2.0*Math.PI*WHEEL_RADIUS/time;
		//double v2=enc_2/ENC_RESOLUTION*2.0*Math.PI*WHEEL_RADIUS/time;
		//double movementAngle=Math.atan(1/Math.sqrt(2)*((v2-v1)/(v1+v2)));

		double encY=(enc_1+enc_2)/2.0/Math.sqrt(2);
		double encX=(enc_1-enc_2)/2.0/Math.sqrt(2);

		double  revY=encY/ENC_RESOLUTION;
		double  revX=encX/ENC_RESOLUTION;

		double dY=revY*2.0*PI*WHEEL_RADIUS;
		double dX=revX*2.0*PI*WHEEL_RADIUS;

		double field_Angle = getTheta();

		double field_DistanceY=dY*Math.cos(field_Angle)+dX*Math.sin(field_Angle);
		double field_DistanceX=dX*Math.cos(field_Angle)-dY*Math.sin(field_Angle);

		x += field_DistanceX;
		y += field_DistanceY;

		//TODO: Figure out the smoothing factor code
		//TODO: Fix NavX CRC Error
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
