package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.StormButton;
import org.stormgears.utils.dsio.SwitchControl;

public interface IButtonBoard {
	boolean initialized = false;

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

	SwitchControl getIntakeWheelsSwitch();

	SwitchControl getIntakeLiftSwitch();

	SwitchControl getOverrideSwitch();

	StormButton getOverrideUp();

	StormButton getOverrideDown();

	StormButton getOverrideLeft();

	StormButton getOverrideRight();

}
