package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.command.Command;
import org.jetbrains.annotations.NotNull;

public class DummySwitch implements ISwitch {
	@Override
	public void whenFlipped(@NotNull SwitchControl.FlipListener listener) {

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

	@Override
	public boolean get() {
		return false;
	}
}
