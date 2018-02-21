package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class ButtonLogger extends Button {
	private final Button delegate;

	public ButtonLogger(Button delegate) {
		this.delegate = delegate;
	}

	@Override
	public void whenPressed(Command command) {
		super.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return false;
			}
		});
	}

	@Override
	public void whileHeld(Command command) {
		super.whileHeld(command);
	}

	@Override
	public void whenReleased(Command command) {
		super.whenReleased(command);
	}

	@Override
	public boolean get() {
		return delegate.get();
	}
}
