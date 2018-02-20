package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.buttons.Button;

public abstract class BaseControl {
	protected Button button;
	protected int number;

	public BaseControl(Button button) {
		this.number = number;

		this.button = button;

		setupCommand();
	}

	protected abstract void setupCommand();
}
