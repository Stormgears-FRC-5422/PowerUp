package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public abstract class BaseControl {
	protected JoystickButton wpiInstance;
	protected int number;

	public BaseControl(Joystick joystick, int number) {
		this.number = number;

		wpiInstance = new JoystickButton(joystick, number);

		setupCommand();
	}

	protected abstract void setupCommand();
}
