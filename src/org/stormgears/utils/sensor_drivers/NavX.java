package org.stormgears.utils.sensor_drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavX {
	private AHRS ahrs;
	private float velocityX, displacementX;
	private Double initialTheta;

	public NavX() {
		ahrs = new AHRS(SPI.Port.kMXP);

		initialTheta = null;
	}

	/**
	 * Gets the angle of the robot in radians
	 *
	 * Make sure that you wrap calls to this method in "if (navX.thetaIsSet())"
	 *
	 * @return the angle of the robot in radians
	 */
	public double getTheta() {
		double theta = (ahrs.getAngle() - initialTheta) / 180.0 * Math.PI;

		theta = theta % (2 * Math.PI);
		if(theta < 0) theta += 2 * Math.PI;
		return theta;
	}

	/**
	 * TODO: This method is sketchy at times, we need to fix it
	 *
	 * Gets the displacement in the X direction of the robot from 0,0
	 *
	 * @return the displacement in the X direction of the robot
	 */
	public float getDisplacementX() {
		velocityX += ahrs.getRawAccelX() * 9.8 * 0.02;
		displacementX += velocityX * 0.02;
		return displacementX;
	}

	public void debug() {
		if (thetaIsSet()) System.out.println("Theta: " + getTheta());
		System.out.println("DisplacementX: " + getDisplacementX());
	}

	public boolean isCalibrating() {
		return ahrs.isCalibrating();
	}

	public boolean thetaIsSet() {
		return initialTheta != null;
	}

	public void setInitialTheta() {
		initialTheta = ahrs.getAngle();
	}
}
