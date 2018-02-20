package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.utils.dsio.EnhancedButton;
import org.stormgears.utils.dsio.StormButton;

// I'm sorry this is so verbose. Maybe things will be better when you future people upgrade to Kotlin.

public class ButtonBoard2017 implements IButtonBoard {
	private final Joystick buttonBoard;

	private final StormButton gripOpenButton;
	private final StormButton gripCloseButton;

	private final StormButton dropButton;

	private final StormButton switch0Button;
	private final StormButton switch1Button;

	private final StormButton sideLeftButton;
	private final StormButton sideRightButton;

	private final StormButton intakeGrabButton;

	private final StormButton climbUpButton;
	private final StormButton climbDownButton;

	private final StormButton[] scaleButtons;

	public ButtonBoard2017(Joystick buttonBoard, Joystick joystick) {
		this.buttonBoard = buttonBoard;

		gripOpenButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.YELLOW);
		gripCloseButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.RED);

		dropButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.BIG_BLUE);

		switch0Button = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.SMALL_BLUE);
		switch1Button = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.GREEN);

		sideLeftButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.WHITE);
		sideRightButton = new EnhancedButton(buttonBoard, ButtonIds.Board.Rev2017.BLACK);

		// Falling back on buttons on the Logitech joystick

		intakeGrabButton = new EnhancedButton(joystick, 12);

		climbUpButton = new EnhancedButton(joystick, 5);
		climbDownButton = new EnhancedButton(joystick, 6);

		scaleButtons = new StormButton[]{
			new EnhancedButton(joystick, 7),
			new EnhancedButton(joystick, 8),
			new EnhancedButton(joystick, 9),
			new EnhancedButton(joystick, 10),
			new EnhancedButton(joystick, 11)
		};
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

	@Override
	public StormButton[] getScaleButtons() {
		return scaleButtons;
	}

	@Override
	public StormButton getSwitch0Button() {
		return switch0Button;
	}

	@Override
	public StormButton getSwitch1Button() {
		return switch1Button;
	}

	@Override
	public StormButton getSideLeftButton() {
		return sideLeftButton;
	}

	@Override
	public StormButton getSideRightButton() {
		return sideRightButton;
	}

	@Override
	public StormButton getIntakeGrabButton() {
		return intakeGrabButton;
	}

	@Override
	public StormButton getClimbUpButton() {
		return climbUpButton;
	}

	@Override
	public StormButton getClimbDownButton() {
		return climbDownButton;
	}
}
