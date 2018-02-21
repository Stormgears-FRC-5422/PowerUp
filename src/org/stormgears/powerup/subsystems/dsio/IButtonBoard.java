package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.IButton;
import org.stormgears.utils.dsio.ISwitch;

public interface IButtonBoard {
	boolean initialized = false;

	IButton getDropButton();

	IButton getSwitch0Button();

	IButton getSwitch1Button();

	IButton[] getScaleButtons();

	IButton getSideLeftButton();

	IButton getSideRightButton();

	IButton getIntakeGrabButton();

	IButton getGripOpenButton();

	IButton getGripCloseButton();

	IButton getClimbUpButton();

	IButton getClimbDownButton();

	ISwitch getIntakeWheelsSwitch();

	ISwitch getIntakeLiftSwitch();

	ISwitch getOverrideSwitch();

	IButton getOverrideUp();

	IButton getOverrideDown();

	IButton getOverrideLeft();

	IButton getOverrideRight();

}
