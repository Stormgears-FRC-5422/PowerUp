package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.utils.dsio.EnhancedButton;
import org.stormgears.utils.dsio.StormButton;

public class ButtonBoard2017 extends AbstractButtonBoard {
	private final Joystick buttonBoard;

	private final StormButton gripOpenButton;
	private final StormButton gripCloseButton;
	private final StormButton dropButton;

	public ButtonBoard2017(Joystick buttonBoard) {
		this.buttonBoard = buttonBoard;

		gripOpenButton = new EnhancedButton(this.buttonBoard, ButtonIds.Board.Rev2017.YELLOW);
		gripCloseButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.RED);
		dropButton = new EnhancedButton(this.buttonBoard, ButtonIds.Board.Rev2017.BIG_BLUE);
	}

	@Override
	public StormButton getGripOpenButton() {
		return gripOpenButton;
	}

	@Override
	public StormButton getGripCloseButton() {
		return gripCloseButton;
	}

	@Override
	public StormButton getDropButton() {
		return dropButton;
	}
}
