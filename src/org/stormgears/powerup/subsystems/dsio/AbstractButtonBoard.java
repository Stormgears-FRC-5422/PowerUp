package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.StormButton;

public abstract class AbstractButtonBoard {
	public abstract StormButton getGripOpenButton();

	public abstract StormButton getGripCloseButton();

	public abstract StormButton getDropButton();
}
