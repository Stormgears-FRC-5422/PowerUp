package org.stormgears.PowerUp.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public abstract class BaseControl {
	protected JoystickButton wpiInstance;

	public BaseControl(Joystick joystick, int number) {
		wpiInstance = new JoystickButton(joystick, number);

		setupCommand();
	}

	protected abstract void setupCommand();
}
