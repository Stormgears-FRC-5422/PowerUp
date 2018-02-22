package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stormgears.utils.dsio.*;

// I'm sorry this is so verbose. Maybe things will be better when you future people upgrade to Kotlin.

public class ButtonBoard2017 implements IButtonBoard {
	@Nullable
	private static ButtonBoard2017 instance = null;

	@NotNull
	public static IButtonBoard getInstance(Joystick buttonBoard, Joystick joystick) {
		if (instance == null) {
			if (initialized) {
				throw new IllegalStateException("Only one button board can exist at once.");
			}

			return instance = new ButtonBoard2017(buttonBoard, joystick);
		}

		return instance;
	}

	private final Joystick buttonBoard;
	private final Joystick joystick;

	private final IButton gripOpenButton;
	private final IButton gripCloseButton;

	private final IButton dropButton;

	private final IButton switch0Button;
	private final IButton switch1Button;

	private final IButton sideLeftButton;
	private final IButton sideRightButton;

	private final IButton intakeGrabButton;

	private final IButton climbUpButton;
	private final IButton climbDownButton;

	private final IButton[] scaleButtons;

	private final ISwitch intakeWheelsSwitch;
	private final ISwitch intakeLiftSwitch;

	private final ISwitch overrideSwitch;
	private final IButton overrideUp;
	private final IButton overrideDown;
	private final IButton overrideLeft;
	private final IButton overrideRight;

	private ButtonBoard2017(Joystick buttonBoard, Joystick joystick) {
		this.buttonBoard = buttonBoard;
		this.joystick = joystick;

		gripOpenButton = new EnhancedButton(buttonBoard, Ids.YELLOW);
		gripCloseButton = new EnhancedButton(buttonBoard, Ids.RED);

		dropButton = new EnhancedButton(buttonBoard, Ids.BIG_BLUE);

		switch0Button = new EnhancedButton(buttonBoard, Ids.SMALL_BLUE);
		switch1Button = new EnhancedButton(buttonBoard, Ids.GREEN);

		sideLeftButton = new EnhancedButton(buttonBoard, Ids.WHITE);
		sideRightButton = new EnhancedButton(buttonBoard, Ids.BLACK);

		intakeWheelsSwitch = new SwitchControl(buttonBoard, Ids.GREEN_SWITCH);
		intakeLiftSwitch = new SwitchControl(buttonBoard, Ids.ORANGE_SWITCH);

		overrideSwitch = new SwitchControl(buttonBoard, Ids.RED_SWITCH);
		overrideUp = new POVButton(joystick, POVButton.Direction.Up);
		overrideDown = new POVButton(joystick, POVButton.Direction.Down);
		overrideLeft = new POVButton(joystick, POVButton.Direction.Left);
		overrideRight = new POVButton(joystick, POVButton.Direction.Right);

		// Falling back on buttons on the Logitech joystick

		intakeGrabButton = new EnhancedButton(joystick, 12);

		climbUpButton = new EnhancedButton(joystick, 5);
		climbDownButton = new EnhancedButton(joystick, 6);

		scaleButtons = new IButton[]{
			new EnhancedButton(joystick, 7),
			new EnhancedButton(joystick, 8),
			new EnhancedButton(joystick, 9),
			new EnhancedButton(joystick, 10),
			new EnhancedButton(joystick, 11)
		};
	}

	@Override
	public IButton getGripOpenButton() {
		return gripOpenButton;
	}

	@Override
	public IButton getGripCloseButton() {
		return gripCloseButton;
	}

	@Override
	public IButton getDropButton() {
		return dropButton;
	}

	@Override
	public IButton[] getScaleButtons() {
		return scaleButtons;
	}

	@Override
	public IButton getSwitch0Button() {
		return switch0Button;
	}

	@Override
	public IButton getSwitch1Button() {
		return switch1Button;
	}

	@Override
	public IButton getSideLeftButton() {
		return sideLeftButton;
	}

	@Override
	public IButton getSideRightButton() {
		return sideRightButton;
	}

	@Override
	public IButton getIntakeGrabButton() {
		return intakeGrabButton;
	}

	@Override
	public IButton getClimbUpButton() {
		return climbUpButton;
	}

	@Override
	public IButton getClimbDownButton() {
		return climbDownButton;
	}

	@Override
	public ISwitch getIntakeWheelsSwitch() {
		return intakeWheelsSwitch;
	}

	@Override
	public ISwitch getIntakeLiftSwitch() {
		return intakeLiftSwitch;
	}

	@Override
	public ISwitch getOverrideSwitch() {
		return overrideSwitch;
	}

	@Override
	public IButton getOverrideUp() {
		return overrideUp;
	}

	@Override
	public IButton getOverrideDown() {
		return overrideDown;
	}

	@Override
	public IButton getOverrideLeft() {
		return overrideLeft;
	}

	@Override
	public IButton getOverrideRight() {
		return overrideRight;
	}

	public static class Ids {
		public static final int BIG_BLUE = 10;
		public static final int RED = 15;
		public static final int YELLOW = 14;
		public static final int GREEN = 13;
		public static final int SMALL_BLUE = 12;
		public static final int BLACK = 9;
		public static final int WHITE = 8;
		public static final int GREEN_SWITCH = 5;
		public static final int ORANGE_SWITCH = 4;
		public static final int RED_SWITCH = 3;
	}
}
