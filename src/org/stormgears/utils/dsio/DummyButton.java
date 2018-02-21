package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.command.Command;

public class DummyButton implements IButton {
	@Override
	public void whenPressed(Runnable callback) {

	}

	@Override
	public void whenPressed(Command command) {

	}

	@Override
	public void whileHeld(Command command) {

	}

	@Override
	public void whenReleased(Command command) {

	}

	@Override
	public void whenReleased(Runnable callback) {

	}

	@Override
	public void toggleWhenPressed(Command command) {

	}

	@Override
	public void cancelWhenPressed(Command command) {

	}

	@Override
	public void whenActive(Command command) {

	}

	@Override
	public void whileActive(Command command) {

	}

	@Override
	public void whenInactive(Command command) {

	}

	@Override
	public void toggleWhenActive(Command command) {

	}

	@Override
	public void cancelWhenActive(Command command) {

	}
}
