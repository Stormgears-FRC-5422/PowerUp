package org.stormgears.utils.sensor_drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavX {
	private AHRS ahrs;

	public NavX() {
		ahrs = new AHRS(SPI.Port.kMXP);
		ahrs.zeroYaw();
	}

	/**
	 * Gets the angle of the robot in radians
	 *
	 * @return the angle of the robot in radians
	 */
	public double getTheta() {
		double theta = ahrs.getAngle() / 180.0 * Math.PI;

		theta = theta % (2 * Math.PI);
		if(theta < 0) theta += 2 * Math.PI;
		return theta;
	}

}
