package org.stormgears.utils;

public class TestDrive extends AbstractDrive {
	/**
	 * Turns the robot `theta` degrees (relative)
	 *
	 * @param theta the number of degrees to turn
	 */
	@Override
	void turn(double theta) {
		System.out.println("TestDrive: theta = " + theta);
	}

	/**
	 * Sets the velocity of the robot
	 *
	 * @param vX the x component of the velocity
	 * @param vY the y component of the velocity
	 */
	@Override
	void move(double vX, double vY) {
		System.out.println("TestDrive: vX = " + vX + ", vY = " + vY);
	}
}
