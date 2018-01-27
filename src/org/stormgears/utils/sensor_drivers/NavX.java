package org.stormgears.utils.sensor_drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavX {

	private AHRS ahrs;
	private Double initialTheta;

	public NavX() {
		ahrs = new AHRS(SPI.Port.kMXP);
		initialTheta = null;
	}

	/** Gets the angle of the robot in radians
	 *
	 * @return the angle of the robot in radians
	 */
	public double getTheta() {

		double theta = (ahrs.getAngle() - initialTheta) / 180.0 * Math.PI;

		theta = theta % (2 * Math.PI);
		if(theta < 0) theta += 2 * Math.PI;
		return theta;
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
