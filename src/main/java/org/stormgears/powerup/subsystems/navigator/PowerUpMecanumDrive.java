package org.stormgears.powerup.subsystems.navigator;

import org.stormgears.powerup.Robot;
import org.stormgears.utils.StormTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class PowerUpMecanumDrive extends MecanumDrive {

//Steamworks
//talons[0] = new WPI_TalonSRX(0);//left front
//talons[1] = new WPI_TalonSRX(2);//left back
//talons[2] = new WPI_TalonSRX(1);//right front
//talons[3] = new WPI_TalonSRX(3);//right back
	
	
	private static PowerUpMecanumDrive instance;
	private StormTalon[] talons;

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

		double navX_theta = Robot.sensors.getNavX().getTheta();
		
		this.driveCartesian(-x, y, z, navX_theta);
	}
	
	
	//this function is NOT tested
	public void turnTo(double theta) {
		double navX_theta = Robot.sensors.getNavX().getTheta();
		double x = Robot.dsio.getJoystickX(),
				y = Robot.dsio.getJoystickY(),
				z = Robot.dsio.getJoystickZ();

		this.driveCartesian(-x, y, z, navX_theta);
	}
	
}
