package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionManager;
import org.stormgears.powerup.subsystems.navigator.motionprofile.TrapezoidalProfile;
import org.stormgears.utils.StormTalon;
import org.stormgears.utils.TerminatableSubsystem;
import org.stormgears.utils.sensor_drivers.NavX;

public class Drive extends TerminatableSubsystem {
	private static Drive instance;

	public static Drive getInstance() {
		return instance;
	}

	private static final Logger logger = LogManager.getLogger(Drive.class);

	private static final int MAX_VELOCITY_ENCODER_TICKS = 6300;
	private static final ControlMode MODE = ControlMode.Velocity;


	private static final int MAX_VELOCITY = 25000; // Originally 25k
	private static final int MAX_ACCELERATION = 750; // Originally 750

	private StormTalon[] talons;
	private double[] vels;

	public boolean useAbsoluteControl = false;
	public boolean useTractionControl = false;

	private MotionMagic[] motions;
	private MotionManager motionManager;

	private Drive() {
		talons = Robot.driveTalons.getTalons();
		vels = new double[talons.length];

		motions = new MotionMagic[Robot.driveTalons.getTalons().length];
		motionManager = new MotionManager();
	}

	public static void init() {
		instance = new Drive();
	}

	public void move() {
		double x = Robot.dsio.getJoystickX(),
			y = Robot.dsio.getJoystickY(),
			z = Robot.dsio.getJoystickZ();

//		logger.debug("x: {} y: {} z: {}", box(x), box(y), box(z));

		double theta = Math.atan2(x, y);
		if (theta < 0) theta = 2 * Math.PI + theta;

		if (x == 0 && y == 0 && z == 0) {
			setDriveTalonsZeroVelocity();
		} else {
			mecMove(MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y + z * z),
				x, y, z,
				theta,
				useAbsoluteControl);
		}
	}

	public void setVelocityPID() {
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.config_kP(0, Robot.config.velocityP, 10);
			t.config_kI(0, Robot.config.velocityI, 10);
			t.config_kD(0, Robot.config.velocityD, 10);
			t.config_IntegralZone(0, Robot.config.velocityIzone, 10);
		}
	}

	// Run mecanum math on each raw speed and set talons accordingly
	private void mecMove(double tgtVel,
						 double x,
						 double y,
						 double changeVel,
						 double theta,
						 boolean useAbsoluteControl) {

		for (int i = 0; i < talons.length; i++) {
			talons[i].setInverted(true);
		}

		talons[3].setSensorPhase(true);
		talons[0].setSensorPhase(true);
		talons[2].setSensorPhase(true);

		if (useAbsoluteControl) {
			double navX_theta = Robot.sensors.getNavX().getTheta();
			theta = theta - navX_theta - (Math.PI / 2);
		}

		// If +/- 15 degrees of a special angle, assume that angle was the intended direction
		// TODO: constrain theta to be from -pi to pi
		if (Math.abs(theta - 0) <= Math.PI / 12.0 || Math.abs(theta - 2.0 * Math.PI) <= Math.PI / 12.0) {
			theta = 0;
		}

		if (Math.abs(theta - Math.PI / 2.0) <= Math.PI / 12.0) {
			theta = Math.PI / 2.0;
		}

		if (Math.abs(theta - Math.PI) <= Math.PI / 12.0) {
			theta = Math.PI;
		}

		if (Math.abs(theta - 3.0 * Math.PI / 2.0) <= Math.PI / 12.0) {
			theta = 3.0 * Math.PI / 2.0;
		}


		vels[0] = (Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		vels[1] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		vels[2] = (Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		vels[3] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		//TODO: Flip signs for REAL BOT

		if (Math.abs(changeVel) > 0.5) {
			for (int i = 0; i < vels.length; i++) {
				vels[i] += changeVel;
			}
		}

		while (Math.abs(vels[0]) > 1.0 ||
			Math.abs(vels[1]) > 1.0 ||
			Math.abs(vels[2]) > 1.0 ||
			Math.abs(vels[3]) > 1.0) {
			double max = Math.max(Math.max(Math.max(Math.abs(vels[0]),
				Math.abs(vels[1])),
				Math.abs(vels[2])),
				Math.abs(vels[3]));

			for (int i = 0; i < vels.length; i++) {
				vels[i] /= max;
			}
		}

		//Turning in place
		if (x == 0 && y == 0) {
			for (int i = 0; i < vels.length; i++) {
				vels[i] = changeVel;
			}
		}

		for (int i = 0; i < vels.length; i++) {
			vels[i] *= tgtVel;
		}

		// Traction control
		if (useTractionControl && driverInputEligibleForTractionControl()) {
			NavX navX = Robot.sensors.getNavX();
			float nx = navX.getVelocityX();
			float ny = navX.getVelocityY();
			double actualVelocity = convertToEncoderUnits(Math.sqrt(nx * nx + ny * ny));
			double stickVelocity = MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y);

			SmartDashboard.putNumber("stickVelocity", stickVelocity);
			SmartDashboard.putNumber("actualVelocity", actualVelocity);
			SmartDashboard.putNumber("tractiontest", ((actualVelocity - stickVelocity) / stickVelocity));

			if (stickVelocity > 700 && Math.abs((actualVelocity - stickVelocity) / stickVelocity) > 0.1) {
				logger.info("Using traction control...");

				double multiplier = 0.5; // (actualVelocity + 0.1) / (vels[0] + 0.1) * 1.1;
				for (int i = 0; i < vels.length; i++) {
					vels[i] *= multiplier;
				}
			}
		}

		for (int i = 0; i < talons.length; i++) {
			talons[i].set(MODE, vels[i]);
		}
	}

	private double convertToEncoderUnits(double speedInMetersPerSecond) {
		double inPer100ms = speedInMetersPerSecond * 100 / 2.54 / 10;
		return inPer100ms / Robot.config.wheelRadius / 2 / Math.PI * 8192;
	}

	private void setDriveTalonsZeroVelocity() {
		StormTalon[] talons = Robot.driveTalons.getTalons();
		for (StormTalon t : talons) {
			t.set(ControlMode.PercentOutput, 0);
		}
	}

	public void driveMotionProfile(double rotations, double theta) {
		double navX_theta = Robot.sensors.getNavX().getTheta();
		theta = theta - navX_theta - Math.PI / 2.0;

		double[][] profile = TrapezoidalProfile.getTrapezoidZero(rotations, 300, theta, 0);
		motionManager.pushProfile(profile, false, true);
		motionManager.startProfile();
	}

	public void debug() {
		StormTalon[] talons = Robot.driveTalons.getTalons();
		for (StormTalon t : talons) {
			logger.debug("Real Velocities: {}", t.getSensorCollection().getQuadratureVelocity());
		}
	}

	private boolean driverInputEligibleForTractionControl() {
		return Math.abs(Robot.dsio.getJoystickX()) < 0.4 &&
			Math.abs(Robot.dsio.getJoystickZ()) < 0.4;

	}

	private double average(double[] data) {
		double total = 0;
		for (double d : data) {
			total += d;
		}

		return total / data.length;
	}

	/**
	 * The inteded purpose of this method is to move the robot at a given
	 * angle (theta) for a given distance (distance). The distance should be
	 * given in inches, and the theta in radians. The method will convert the
	 * distance into encoder ticks in order to facilitate motion magic. Ensure
	 * that when calling this method.
	 *
	 * @param distance - the distance to move the robot in inches
	 * @param theta    - the angle at which to move the robot
	 */
	public void moveStraight(double distance, double theta) {

//		if (useAbsoluteControl) {
//			double navX_theta = Robot.sensors.getNavX().getTheta();
//			theta = theta - navX_theta;
//		}

		if (Math.abs(theta) == Math.PI / 2.0) {
			distance = distance / 0.833333;
			System.out.println(distance);
			System.out.println((distance * 8192) / (2 * Robot.config.wheelRadius * Math.PI));
		}

		//TODO: make wheel diameter and other constants that im just making up
		double wheelCircumference = 2.0 * Math.PI * Robot.config.wheelRadius;
		//TODO: constant for encoder ticks
		double ticks = distance / wheelCircumference * 8192.0;
		// motions[0].runMotionMagic((int) ticks);


		double[] modifiers = new double[motions.length];

		//From the mecMove method...
		//TODO: test and see if this works
		modifiers[0] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		modifiers[1] = (Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		modifiers[2] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		modifiers[3] = (Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));

		int max = 0;
		for (int i = 0; i < modifiers.length; i++) {
			if (Math.abs(modifiers[i]) > Math.abs(modifiers[max])) {
				max = i;
			}
		}

		double currentDistance;
		double t1;
		double totTime = 0;
		double vmax2;
		double a2;


		double maxDistance = ((Math.abs(modifiers[max] * distance)) * 8192) / (2 * Math.PI * Robot.config.wheelRadius);
		for (int i = 0; i < Robot.driveTalons.getTalons().length; i++) {

			currentDistance = ((Math.abs(modifiers[i] * distance)) * 8192) / (2 * Math.PI * Robot.config.wheelRadius);


			t1 = MAX_VELOCITY / MAX_ACCELERATION;
			totTime = (t1) + (maxDistance / MAX_VELOCITY) / 10.0; //TODO: FIND TOTAL TIME
			vmax2 = currentDistance / (totTime - t1) / 10.0;
			a2 = vmax2 / t1;


			if ((Math.abs(modifiers[i] * distance) != maxDistance)) {
				System.out.println("Vmax2 " + vmax2);
				System.out.println("A2 " + a2);
				motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], vmax2, a2);
			} else {
				System.out.println("MAX VEL " + MAX_VELOCITY);
				System.out.println("MAX ACCEL " + MAX_ACCELERATION);
				motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], MAX_VELOCITY, MAX_ACCELERATION);
			}
		}
		Robot.timer.start();
		for (int i = 0; i < motions.length; i++) {
			System.out.println("Talon " + i + " Commanded: " + (ticks * modifiers[i]));
			motions[i].runMotionMagic((int) (ticks * modifiers[i]));
		}

		while (isAllowed() && !Robot.timer.hasPeriodPassed(totTime/10.0)) {
			waitMs(20);
		}
		Robot.timer.stop();
		Robot.timer.reset();
	}

	/**
	 * This method makes the thread sleep for a given amount of time.
	 *
	 * @param ms - amount of milliseconds to make the thread
	 *           wait for.
	 */
	private void waitMs(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method takes in an angle, theta, and turns the robot to the
	 * corresponding direction so that its front faces the angle passed in.
	 *
	 * @param theta Angle desired for robot turn
	 */
	public void turnTo(double theta) {
		if (useAbsoluteControl) {
			double navX_theta = Robot.sensors.getNavX().getTheta();
			theta = theta - navX_theta;
		}

		double negative = -1.0;


		theta = theta % (2 * Math.PI);
		if (theta < 0) {
			theta += (2 * Math.PI);
			System.out.println("Theta 2 " + theta);
		}

		if (theta > Math.PI) {
			negative = 1.0;
			System.out.println("Theta 1 " + theta);
			theta -= Math.PI;

			System.out.println("Rohan blows massive pterodactyl cock" + theta);
		}

		double robotLength = Double.parseDouble(Robot.config.robotLength);
		double robotWidth = Double.parseDouble(Robot.config.robotWidth);

		double r = (robotLength + robotWidth) / 3.8;

		double s = r * theta;


		double encoderTicks = s / (2 * Math.PI * Robot.config.wheelRadius) * 8192.0;
		encoderTicks *= Math.sqrt(2);


		double t1 = MAX_VELOCITY / MAX_ACCELERATION;
		double totTime = (t1) + (s / MAX_VELOCITY) / 10.0; //TODO: FIND TOTAL


		for (int i = 0; i < motions.length; i++) {
			motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], MAX_VELOCITY / 2, MAX_ACCELERATION / 2);
		}

		System.out.println(encoderTicks + "");
		Robot.timer.start();
		for (int i = 0; i < motions.length; i++) {
			if (i == 0 || i == 2) {
				System.out.println("Talon " + i + " Commanded: " + (encoderTicks));
				motions[i].runMotionMagic((int) (negative * encoderTicks));
			} else {
				System.out.println("Talon " + i + " Commanded: " + (encoderTicks));
				motions[i].runMotionMagic((int) (negative * encoderTicks));

			}
		}

		while (isAllowed() && !Robot.timer.hasPeriodPassed(totTime / 10.0)) {
			waitMs(20);
		}
		Robot.timer.stop();
		Robot.timer.reset();
	}


	/**
	 * MoveToPos Method; Moves robot between two positions
	 *
	 * @param p1 first position
	 * @param p2 second position
	 */
	public void moveToPos(Position p1, Position p2) {

		double deltaX = p2.getX() - p1.getX();
		double deltaY = p2.getY() - p1.getY();

		double theta = -Math.atan(deltaY / deltaX) + Math.PI/2;

		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		System.out.println(hyp);

		moveStraight(hyp, theta);


	}

	@Override
	protected void initDefaultCommand() {

	}

}
