package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic;
import org.stormgears.powerup.subsystems.navigator.motionprofile.TrapezoidalProfile;
import org.stormgears.utils.StormTalon;
import org.stormgears.utils.sensor_drivers.NavX;

public class Drive {
	private static Drive instance;

	public static Drive getInstance() {
		return instance;
	}

	private static final Logger logger = LogManager.getLogger(Drive.class);

	private static final int MAX_VELOCITY_ENCODER_TICKS = 6300;
	private static int maxVel;
	private static int maxAccel;
	private static boolean useAbsoluteControl;
	private static final ControlMode MODE = ControlMode.Velocity;

	private StormTalon[] talons;
	private double[] vels;

	public boolean useTractionControl = true;

	private static MotionMagic[] motions;

	private Drive() {
		talons = Robot.driveTalons.getTalons();
		vels = new double[talons.length];

		maxVel = 15000;
		maxAccel = 6000;
		motions = new MotionMagic[Robot.driveTalons.getTalons().length];

	}


	public static void init() {
		instance = new Drive();
	}

	public void onAbsoluteControl()  {
		useAbsoluteControl = true;
	}

	public void offAbsoluteControl() {
		useAbsoluteControl =  false;
	}

	public void move() {
		double x = Robot.dsio.getJoystickX(),
			y = Robot.dsio.getJoystickY(),
			z = Robot.dsio.getJoystickZ();

		double theta = Math.atan2(x, y);
		if (theta < 0) theta = 2 * Math.PI + theta;

		if (x == 0 && y == 0 && z == 0) {
			setDriveTalonsZeroVelocity();
		} else {
			mecMove(MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y + z * z),
				theta,
				z,
				useAbsoluteControl, x, y);
		}
	}

	// Run mecanum math on each raw speed and set talons accordingly
	private void mecMove(double tgtVel,
						 double theta,
						 double changeVel,
						 boolean useAbsoluteControl, double x, double y) {
		if (useAbsoluteControl) {
			double navX_theta = Robot.sensors.getNavX().getTheta();
			theta = theta - navX_theta;
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

		vels[0] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		vels[1] = (Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		vels[2] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		vels[3] = (Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));

		if (Math.abs(changeVel) > 0.5) {
			for (int i = 0; i < vels.length; i++) {
				vels[i] -= changeVel;
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
				vels[i] = -changeVel;
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
			SmartDashboard.putNumber("tractiontest", Math.abs((actualVelocity - stickVelocity) / stickVelocity));
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
		theta = theta + navX_theta;

		double[][] profile = TrapezoidalProfile.getTrapezoidZero(rotations, 300, theta, 0);
		m.pushProfile(profile, false, true);
		m.startProfile();
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


		if (useAbsoluteControl) {
			double navX_theta = Robot.sensors.getNavX().getTheta();theta = theta - navX_theta;
		}

		//TODO: make wheel diameter and other constants that im just making up
		double wheelCircumference = 2 * Math.PI * 4; //4 in wheel radius???
		//TODO: constant for encoder ticks
		double ticks = distance / wheelCircumference * 8192;
		// motions[0].runMotionMagic((int) ticks);

		double[] modifiers = new double[motions.length];

		//From the mecMove method...
		//TODO: test and see if this works
		modifiers[0] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		modifiers[1] = (Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		modifiers[2] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		modifiers[3] = (Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));

		int max = 0;
		for (int i = 0; i < modifiers.length; i++) {
			if (Math.abs(modifiers[i]) > Math.abs(modifiers[max])) {
				max = i;
			}
		}

		double currentDistance;
		double t1;
		double totTime;
		double vmax2;
		double a2;


		double maxDistance = ((Math.abs(modifiers[max] * distance))* 8192)/(8*Math.PI);
		for (int i = 0; i < Robot.driveTalons.getTalons().length; i++) {

			currentDistance = ((Math.abs(modifiers[i] * distance))* 8192)/(8*Math.PI);
			t1 = maxVel / maxAccel;
			totTime = (t1) + (maxDistance/maxVel) * 10; //TODO: FIND TOTAL TIME
			vmax2 = currentDistance / (totTime - t1) / 10.0;
			a2 = vmax2 / t1;


			if ((Math.abs(modifiers[i] * distance) != maxDistance)) {
				motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], vmax2, a2);
			} else {
				motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], maxVel, maxAccel);
			}
		}
		for (int i = 0; i < motions.length; i++) {
			System.out.println("Talon " + i + " Commanded: " + (ticks * modifiers[i]));
			motions[i].runMotionMagic((int) (ticks * modifiers[i]));
		}
	}

	/** This method takes in an angle, theta, and turns the robot to the
	 * corresponding direction so that its front faces the angle passed in.
	 *
	 * @param theta Angle desired for robot turn
	 */
	public void turnTo(double theta) {
		if (useAbsoluteControl) {
			double navX_theta = Robot.sensors.getNavX().getTheta();
			theta = theta - navX_theta;
		}

		double robotLength = Double.parseDouble(Robot.config.robotLength);
		double robotWidth = Double.parseDouble(Robot.config.robotWidth);

		double r = Math.sqrt(Math.pow(robotLength, 2) + Math.pow((robotWidth),2))/2.0;

		double s = r * theta;

		double encoderTicks = s/(8.0 * Math.PI) * 8192.0;
		encoderTicks *= Math.sqrt(2);

		for(int i = 0; i < motions.length; i ++) {
			motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i], maxVel/2, maxAccel/2);
		}

		System.out.println(encoderTicks + "");

		for (int i = 0; i < motions.length; i++) {
			System.out.println("Talon " + i + " Commanded: " + (encoderTicks));
			motions[i].runMotionMagic((int) encoderTicks);
		}
	}

	/**
	 * MoveToPos Method; Moves robot between two positions
	 *
	 * @param p1 first position
	 * @param p2 second position
	 *
	 */
	public void moveToPos(Position p1, Position p2){

		double deltaX = p2.getX() - p1.getX();
		double deltaY = p2.getY() - p1.getY();

		double theta = Math.atan(deltaY/deltaX);

		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		moveStraight(hyp, theta);






	}
}
