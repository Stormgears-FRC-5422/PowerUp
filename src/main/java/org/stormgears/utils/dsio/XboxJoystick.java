package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.Joystick;

public class XboxJoystick extends Joystick implements IRawJoystick {
	/**
	 * Construct an instance of a joystick. The joystick index is the USB port on the drivers
	 * station.
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public XboxJoystick(int port) {
		super(port);
	}

	@Override
	public final double getJoystickX() {
		return getRawAxis(0);
	}

	@Override
	public double getJoystickY() {
		return getRawAxis(5);
	}

	@Override
	public double getJoystickZ() {
		return getRawAxis(4);
	}
}
