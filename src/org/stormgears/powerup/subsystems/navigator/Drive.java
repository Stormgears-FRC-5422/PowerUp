package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionMagic;
import org.stormgears.utils.StormTalon;

// TODO: CLEAN THIS UP
public class Drive {
	private static Drive instance;
	public static Drive getInstance() { return instance; }

	private static final Logger logger = LogManager.getLogger(Drive.class);

	private static final int MAX_VELOCITY_ENCODER_TICKS = 6300;
	private static final int TALON_FPID_TIMEOUT = 0;	// TODO: Adithya said 'Figure out what the hell that thing is'
	private static final ControlMode MODE = ControlMode.Velocity;

	private static MotionMagic[] motions;

	private Drive() {
		motions = new MotionMagic[Robot.driveTalons.getTalons().length];
		for(int i = 0; i < Robot.driveTalons.getTalons().length; i ++) {
			motions[i] = new MotionMagic(Robot.driveTalons.getTalons()[i]);
		}
	}


	public static void init() {
		instance = new Drive();
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
			mecMove(MAX_VELOCITY_ENCODER_TICKS * Math.sqrt(x * x + y * y + z * z), theta, z);
		}
	}

	// Run mecanum math on each raw speed and set talons accordingly
	// TODO: This code makes the robot drive fairly poorly. It does not drive straight
	private void mecMove(double tgtVel, double theta, double changeVel) {
		StormTalon[] talons = Robot.driveTalons.getTalons();

		double navX_theta = Robot.sensors.getNavX().getTheta();
		theta = theta + navX_theta;

		double[] vels = new double[talons.length];

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

		for (int i = 0; i < vels.length; i++) {
			vels[i] *= tgtVel;
		}

//		System.out.println("Target: " + vels[2]);

		for (int i = 0; i < talons.length; i++) {
			talons[i].set(MODE, vels[i]);
		}

//		System.out.println("Actual: " + talons[2].getSensorCollection().getQuadratureVelocity());
	}

	private void setDriveTalonsZeroVelocity() {
		StormTalon[] talons = Robot.driveTalons.getTalons();
		for (StormTalon t : talons) {
			t.set(MODE, 0);
		}
	}

	public void debug() {
		StormTalon[] talons = Robot.driveTalons.getTalons();
		for (StormTalon t : talons) {
			logger.debug("Real Velocities: {}", t.getSensorCollection().getQuadratureVelocity());
		}
	}

	/** THe inteded purpose of this method is to move the robot at a given
	 * angle (theta) for a given distance (distance). The distance should be
	 * given in inches, and the theta in radians. The method will convert the
	 * distance into encoder ticks in order to facilitate motion magic. Ensure
	 * that when calling this method, you take into account the angle of the
	 * robot using the navX before inputting a theta.
	 *
	 * @param distance - the distance to move the robot in inches
	 * @param theta - the angle at which to move the robot
	 */
	public void runMotionMagic(double distance, double theta){
		//TODO: make wheel diameter and other constants that im just making up
		double wheelCircumference = 2 * Math.PI * 3; //3 in wheel radius???
		//TODO: constant for encoder ticks
		double ticks = distance / wheelCircumference * 8192;
		motions[0].runMotionMagic((int)ticks);

		double[] modifiers = new double[motions.length];
		//From the mecMove method...
		//TODO: test and see if this works
		modifiers[0] = -(Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));
		modifiers[1] = (Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		modifiers[2] = -(Math.sin(theta + Math.PI / 2.0) - Math.cos(theta + Math.PI / 2.0));
		modifiers[3] = (Math.sin(theta + Math.PI / 2.0) + Math.cos(theta + Math.PI / 2.0));

		for(int i = 0; i < motions.length; i ++) {
			motions[i].runMotionMagic((int)(ticks * modifiers[i]));
		}
	}
}
