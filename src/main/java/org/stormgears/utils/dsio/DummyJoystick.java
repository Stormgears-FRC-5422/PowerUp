package org.stormgears.utils.dsio;

public class DummyJoystick implements IRawJoystick {
	@Override
	public double getJoystickX() {
		return 0;
	}

	@Override
	public double getJoystickY() {
		return 0;
	}

	@Override
	public double getJoystickZ() {
		return 0;
	}

	@Override
	public boolean getRawButton(int button) {
		return false;
	}
}
