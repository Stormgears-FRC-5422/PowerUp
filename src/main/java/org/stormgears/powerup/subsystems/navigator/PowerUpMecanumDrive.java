package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.sensordrivers.NavX;
import org.stormgears.utils.talons.ITalon;

public class PowerUpMecanumDrive extends MecanumDrive {

//Steamworks
//talons[0] = new WPI_TalonSRX(0);//left front
//talons[1] = new WPI_TalonSRX(2);//left back
//talons[2] = new WPI_TalonSRX(1);//right front
//talons[3] = new WPI_TalonSRX(3);//right back
	
	private static PowerUpMecanumDrive instance;
	private ITalon[] talons;

	public static PowerUpMecanumDrive getInstance() {
		return instance;
	}

	private PowerUpMecanumDrive() {
//		MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		super(Robot.driveTalons.getTalons()[0], Robot.driveTalons.getTalons()[2], Robot.driveTalons.getTalons()[1], Robot.driveTalons.getTalons()[3]);
		talons = Robot.driveTalons.getTalons();
	}

	public static void init() {
		instance = new PowerUpMecanumDrive();
	}


	public PowerUpMecanumDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
			SpeedController frontRightMotor, SpeedController rearRightMotor) {
		//m_robotDrive = new MecanumDrive(talons[0], talons[1], talons[2], talons[3]);
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		double x = Robot.dsio.getJoystickX(),
			y = Robot.dsio.getJoystickY(),
			z = Robot.dsio.getJoystickZ();
		
		this.driveCartesian(-x, y, z, 0);
	}
	
	public void moveFieldOriented() {
		double x = Robot.dsio.getJoystickX(),
			y = Robot.dsio.getJoystickY(),
			z = Robot.dsio.getJoystickZ();

		double navX_theta = Robot.sensors.getNavX().getTheta(NavX.AngleUnit.Radians, true);
		
		this.driveCartesian(-x, y, z, navX_theta);
	}
	
	
	//this function is NOT tested
	public void turnTo(double theta) {
		double navX_theta = Robot.sensors.getNavX().getTheta(NavX.AngleUnit.Radians, true);
		double x = Robot.dsio.getJoystickX(),
				y = Robot.dsio.getJoystickY(),
				z = Robot.dsio.getJoystickZ();

		this.driveCartesian(-x, y, z, navX_theta);
	}
	
	
	//To run in Velocity Mode
	public void driveCartesian(double xSpeed, double ySpeed, double zRotation, double gyroAngle) {
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);

		ySpeed = limit(ySpeed);
		ySpeed = applyDeadband(ySpeed, m_deadband);
		
		// Compensate for gyro angle.
		Vector2d input = new Vector2d(xSpeed, ySpeed);
		input.rotate(-gyroAngle);
		
		double[] wheelSpeeds = new double[4];
		wheelSpeeds[MotorType.kFrontLeft.value] = input.x + input.y + zRotation;
		wheelSpeeds[MotorType.kFrontRight.value] = input.x - input.y + zRotation;
		wheelSpeeds[MotorType.kRearLeft.value] = -input.x + input.y + zRotation;
		wheelSpeeds[MotorType.kRearRight.value] = -input.x - input.y + zRotation;
		
		normalize(wheelSpeeds);
		
		talons[0].set(ControlMode.Velocity, wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput);
		talons[1].set(ControlMode.Velocity, wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput);
		talons[2].set(ControlMode.Velocity, wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput);
		talons[3].set(ControlMode.Velocity, wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput);
		
		m_safetyHelper.feed();
	}
}
