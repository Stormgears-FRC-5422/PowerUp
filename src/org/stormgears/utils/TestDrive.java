package org.stormgears.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDrive extends AbstractDrive {
	private Logger logger = LogManager.getLogger(TestDrive.class);

	/**
	 * Turns the robot `theta` degrees (relative)
	 *
	 * @param theta the number of degrees to turn
	 */
	@Override
	void turn(double theta) {
		logger.debug("theta = {}", theta);
	}

	/**
	 * Sets the velocity of the robot
	 *
	 * @param vX the x component of the velocity
	 * @param vY the y component of the velocity
	 */
	@Override
	void move(double vX, double vY) {
		logger.debug("vX = {}, vY = {}", vX, vY);
	}

	public static void main(String[] args) {
		TestDrive drive = new TestDrive();

		drive.move(1337, 337);
	}
}
