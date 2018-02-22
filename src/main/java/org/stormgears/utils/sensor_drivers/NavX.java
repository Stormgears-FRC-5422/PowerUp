package org.stormgears.utils.sensor_drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NavX {
	private static final Logger logger = LogManager.getLogger(NavX.class);

	private AHRS ahrs;
	private float velocityX, displacementX;
	private double initialTheta;
	private boolean thetaIsSet = false;

	public NavX() {
		ahrs = new AHRS(SPI.Port.kMXP);
	}

	/**
	 * Gets the angle of the robot in radians
	 * <p>
	 * Make sure that you wrap calls to this method in "if (navX.thetaIsSet())"
	 *
	 * @return the angle of the robot in radians, null if NavX is not calibrated
	 */
	public double getTheta() {
		if (!thetaIsSet) {
			throw new IllegalStateException("Theta is not set.");
		}

		double theta = (ahrs.getAngle() - initialTheta) / 180.0 * Math.PI;

		theta = theta % (2 * Math.PI);
		if (theta < 0) theta += 2 * Math.PI;
		return theta;
	}

	/**
	 * TODO: This method is sketchy at times, we need to fix it
	 * <p>
	 * Gets the displacement in the X direction of the robot from 0,0
	 *
	 * @return the displacement in the X direction of the robot
	 */
	public float getDisplacementX() {
		velocityX += ahrs.getRawAccelX() * 9.8 * 0.02;
		displacementX += velocityX * 0.02;
		return displacementX;
	}

	public float getVelocityX() {
		return ahrs.getVelocityX();
	}

	public float getVelocityY() {
		return ahrs.getVelocityY();
	}

	public void debug() {
		if (thetaIsSet()) logger.debug("Theta: {}", getTheta());
		logger.debug("DisplacementX: {}", getDisplacementX());
	}

	public boolean isCalibrating() {
		return ahrs.isCalibrating();
	}

	public boolean thetaIsSet() {
		return thetaIsSet;
	}

	public void setInitialTheta() {
		initialTheta = ahrs.getAngle();
		thetaIsSet = true;
	}

	public boolean test() {
		debug();
		return true;
	}

}
