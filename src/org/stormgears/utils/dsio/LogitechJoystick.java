package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechJoystick extends Joystick implements IRawJoystick {
	/**
	 * Construct an instance of a joystick. The joystick index is the USB port on the drivers
	 * station.
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public LogitechJoystick(int port) {
		super(port);
	}

	@Override
	public double getJoystickX() {
		return getX();
	}

	@Override
	public double getJoystickY() {
		return getY();
	}

	@Override
	public double getJoystickZ() {
		return -super.getZ(); // needs to be inverted for some reason
	}
}
