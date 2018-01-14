package org.stormgears.powerup.subsystems.navigator;

import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.dsio.JoystickFilter;

public class Drive {
	private static Drive instance = new Drive();

	public static Drive getInstance() {
		return instance;
	}

	// TODO: rename this
	private static final int RANDOM_CONSTANT_THAT_NEEDS_TO_BE_RENAMED = 6300;

	// Configure joystick filters here
	private static final JoystickFilter[] DRIVE_FILTERS = { JoystickFilter.NULLZONE };
	private static final JoystickFilter Z_FILTER = JoystickFilter.nullzone(0.2);

	private Drive() {

	}

	public void move() {
		double x = Robot.dsio.getJoystickX(DRIVE_FILTERS),
				y = Robot.dsio.getJoystickY(DRIVE_FILTERS),
				z = Robot.dsio.getJoystickZ(Z_FILTER);

		double theta = Math.atan2(x, y);
		if (theta < 0) theta = 2 * Math.PI + theta;

		if (x == 0 && y == 0 && z == 0) {
			// TODO: Implement
//			setDriveTalonsZeroVelocity();
		} else {
			// TODO: Implement
//			mecMove(RANDOM_CONSTANT_THAT_NEEDS_TO_BE_RENAMED * Math.sqrt(x * x + y * y + z * z), theta, z);
		}
	}

}
