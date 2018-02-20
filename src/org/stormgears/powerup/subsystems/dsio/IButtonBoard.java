package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.StormButton;

public interface IButtonBoard {
	StormButton getDropButton();

	StormButton getSwitch0Button();

	StormButton getSwitch1Button();

	StormButton[] getScaleButtons();

	StormButton getSideLeftButton();

	StormButton getSideRightButton();

	StormButton getIntakeGrabButton();

	StormButton getGripOpenButton();

	StormButton getGripCloseButton();

	StormButton getClimbUpButton();

	StormButton getClimbDownButton();

}
