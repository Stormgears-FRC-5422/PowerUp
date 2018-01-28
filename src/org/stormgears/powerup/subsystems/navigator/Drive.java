package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.StormTalon;

// TODO: CLEAN THIS UP
public class Drive {
	private static Drive instance;
	public static Drive getInstance() { return instance; }

	private Logger logger = LogManager.getLogger(Drive.class);

	private static final int MAX_VELOCITY_ENCODER_TICKS = 1;
	private static final int TALON_FPID_TIMEOUT = 0;	// TODO: Adithya said 'Figure out what the hell that thing is'
	private static final ControlMode MODE = ControlMode.PercentOutput;

	private static final StormTalon[] talons = new StormTalon[4];

	private Drive() {
		talons[0] = new StormTalon(Robot.config.frontLeftTalonId);
		talons[1] = new StormTalon(Robot.config.frontRightTalonId);
		talons[2] = new StormTalon(Robot.config.rearLeftTalonId);
		talons[3] = new StormTalon(Robot.config.rearRightTalonId);

		for (StormTalon t : talons) {
			t.setInverted(true);
			t.setSensorPhase(true);
			t.config_kF(0, Robot.config.velocityF, TALON_FPID_TIMEOUT);
			t.config_kP(0, Robot.config.velocityP, TALON_FPID_TIMEOUT);
			t.config_kI(0, Robot.config.velocityI, TALON_FPID_TIMEOUT);
			t.config_kD(0, Robot.config.velocityD, TALON_FPID_TIMEOUT);
			t.config_IntegralZone(0, Robot.config.velocityIzone, TALON_FPID_TIMEOUT);
	}		}


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

		for (int i = 0; i < talons.length; i++) {
			talons[i].set(MODE, vels[i]);
		}
	}

	private void setDriveTalonsZeroVelocity() {
		for (StormTalon t : talons) {
			t.set(MODE, 0);
		}
	}

	public void debug() {
		for (StormTalon t : talons) {
			logger.debug("Real Velocities: {}", t.getSensorCollection().getQuadratureVelocity());
		}
	}
}
