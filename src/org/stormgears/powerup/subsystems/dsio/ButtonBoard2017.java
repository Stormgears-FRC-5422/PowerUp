package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stormgears.utils.dsio.EnhancedButton;
import org.stormgears.utils.dsio.POVButton;
import org.stormgears.utils.dsio.StormButton;
import org.stormgears.utils.dsio.SwitchControl;

// I'm sorry this is so verbose. Maybe things will be better when you future people upgrade to Kotlin.

public class ButtonBoard2017 implements IButtonBoard {
	@Nullable
	private static ButtonBoard2017 instance = null;

	@NotNull
	public static ButtonBoard2017 getInstance(Joystick buttonBoard, Joystick joystick) {
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

	private final SwitchControl intakeWheelsSwitch;
	private final SwitchControl intakeLiftSwitch;

	private final SwitchControl overrideSwitch;
	private final StormButton overrideUp;
	private final StormButton overrideDown;
	private final StormButton overrideLeft;
	private final StormButton overrideRight;

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

	@Override
	public SwitchControl getIntakeWheelsSwitch() {
		return intakeWheelsSwitch;
	}

	@Override
	public SwitchControl getIntakeLiftSwitch() {
		return intakeLiftSwitch;
	}

	@Override
	public SwitchControl getOverrideSwitch() {
		return overrideSwitch;
	}

	@Override
	public StormButton getOverrideUp() {
		return overrideUp;
	}

	@Override
	public StormButton getOverrideDown() {
		return overrideDown;
	}

	@Override
	public StormButton getOverrideLeft() {
		return overrideLeft;
	}

	@Override
	public StormButton getOverrideRight() {
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
