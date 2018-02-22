package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.utils.dsio.*;

public class DummyButtonBoard implements IButtonBoard {
	@Override
	public IButton getDropButton() {
		return new DummyButton();
	}

	@Override
	public IButton getSwitch0Button() {
		return new DummyButton();
	}

	@Override
	public IButton getSwitch1Button() {
		return new DummyButton();
	}

	@Override
	public IButton[] getScaleButtons() {
		return new IButton[]{
			new DummyButton(),
			new DummyButton(),
			new DummyButton(),
			new DummyButton(),
			new DummyButton()
		};
	}

	@Override
	public IButton getSideLeftButton() {
		return new DummyButton();
	}

	@Override
	public IButton getSideRightButton() {
		return new DummyButton();
	}

	@Override
	public IButton getIntakeGrabButton() {
		return new DummyButton();
	}

	@Override
	public IButton getGripOpenButton() {
		return new DummyButton();
	}

	@Override
	public IButton getGripCloseButton() {
		return new DummyButton();
	}

	@Override
	public IButton getClimbUpButton() {
		return new DummyButton();
	}

	@Override
	public IButton getClimbDownButton() {
		return new DummyButton();
	}

	@Override
	public ISwitch getIntakeWheelsSwitch() {
		return new DummyTernarySwitch();
	}

	@Override
	public ISwitch getIntakeLiftSwitch() {
		return new DummyTernarySwitch();
	}

	@Override
	public ISwitch getOverrideSwitch() {
		return new DummySwitch();
	}

	@Override
	public IButton getOverrideUp() {
		return new DummyButton();
	}

	@Override
	public IButton getOverrideDown() {
		return new DummyButton();
	}

	@Override
	public IButton getOverrideLeft() {
		return new DummyButton();
	}

	@Override
	public IButton getOverrideRight() {
		return new DummyButton();
	}
}
