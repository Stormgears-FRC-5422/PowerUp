package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.EnhancedButton;

public abstract class AbstractButtonBoard {
	EnhancedButton gripperButton;

	abstract EnhancedButton getGripperButton();
}
