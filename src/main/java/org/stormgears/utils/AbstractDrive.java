package org.stormgears.utils;

public abstract class AbstractDrive {
	/**
	 * Turns the robot `theta` degrees (relative)
	 *
	 * @param theta the number of degrees to turn
	 */
	abstract void turn(double theta);

	/**
	 * Sets the velocity of the robot
	 *
	 * @param vX the x component of the velocity
	 * @param vY the y component of the velocity
	 */
	abstract void move(double vX, double vY);
}
