package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.command.Command;
import org.jetbrains.annotations.NotNull;

public interface ISwitch {
	void whenFlipped(@NotNull SwitchControl.FlipListener listener);

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
