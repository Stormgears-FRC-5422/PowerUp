package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.command.Command;

public interface StormButton {
	void whenPressed(Runnable callback);

	void whenPressed(final Command command);

	void whileHeld(final Command command);

	void whenReleased(final Command command);

	void toggleWhenPressed(final Command command);

	void cancelWhenPressed(final Command command);

	void whenActive(final Command command);

	void whileActive(final Command command);

	void whenInactive(final Command command);

	void toggleWhenActive(final Command command);

	void cancelWhenActive(final Command command);
}
