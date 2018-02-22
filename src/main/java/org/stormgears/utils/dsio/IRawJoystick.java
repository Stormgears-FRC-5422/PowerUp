package org.stormgears.utils.dsio;

public interface IRawJoystick {
	double getJoystickX();

	double getJoystickY();

	double getJoystickZ();

	boolean getRawButton(int button);
}
