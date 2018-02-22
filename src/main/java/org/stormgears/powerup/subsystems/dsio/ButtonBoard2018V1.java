package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stormgears.utils.dsio.*;

public class ButtonBoard2018V1 implements IButtonBoard {
	@Nullable
	private static ButtonBoard2018V1 instance = null;

	@NotNull
	public static IButtonBoard getInstance(Joystick buttonBoard, Joystick joystick) {
		if (instance == null) {
			if (initialized) {
				throw new IllegalStateException("Only one button board can exist at once.");
			}

			return instance = new ButtonBoard2018V1(buttonBoard, joystick);
		}

		return instance;
	}

	private final Joystick msp;
	private final Joystick logitech;

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

	private ButtonBoard2018V1(Joystick msp, Joystick logitech) {
		this.msp = msp;
		this.logitech = logitech;

		gripOpenButton = new POVButton(logitech, POVButton.Direction.Down);
		gripCloseButton = new POVButton(logitech, POVButton.Direction.Up);

		dropButton = new EnhancedButton(logitech, 12);

		switch0Button = new EnhancedButton(msp, 8);
		switch1Button = new EnhancedButton(logitech, 1);

		sideLeftButton = new EnhancedButton(logitech, 3);
		sideRightButton = new EnhancedButton(logitech, 4);

		intakeGrabButton = new POVButton(logitech, POVButton.Direction.Left);

		climbUpButton = new POVButton(logitech, POVButton.Direction.Right);
		climbDownButton = new EnhancedButton(logitech, 10);

		intakeWheelsSwitch = new TernarySwitch(msp, 9, 5);
		intakeLiftSwitch = new TernarySwitch(msp, 3, 10);

		overrideSwitch = new SwitchControl(msp, 4);
		overrideUp = new EnhancedButton(msp, 15);
		overrideDown = new EnhancedButton(msp, 14);
		overrideLeft = new EnhancedButton(msp, 12);
		overrideRight = new EnhancedButton(msp, 13);

		scaleButtons = new IButton[]{
			new EnhancedButton(logitech, 8),
			new EnhancedButton(logitech, 5),
			new EnhancedButton(logitech, 7),
			new EnhancedButton(logitech, 9),
			new EnhancedButton(logitech, 6)
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

	//	public static class Ids {
//		public static final int[] SCALE = {8, 5, 7, 10, 6}; // log
//		public static final int SWITCH_0 = 10;  		// msp
//		public static final int SWITCH_1 = 1;			// log
//		public static final int DROP = 14;				// log
//		public static final int SIDE_GO_LEFT = 3;		// log
//		public static final int SIDE_GO_RIGHT = 4;		// log
//		public static final int INTAKE_GRAB = 0;		// log pov left
//		public static final int INTAKE_WHEELS_OUT = 11;	// msp
//		public static final int INTAKE_WHEELS_IN = 5;	// msp
//		public static final int LIFT_OUT = 3;			// msp
//		public static final int LIFT_IN = 12;			// msp
//		public static final int GRIP_UPPER = 14;		// log
//		public static final int GRIP_LOWER = 0;			// log pov up
//		public static final int CLIMB_UPPER = 0;		// log pov right
//		public static final int CLIMB_LOWER = 12;		// log
//		public static final int OVERRIDE_SWITCH = 4;	// msp
//		public static final int JOYSTICK_LEFT = 12;		// msp
//		public static final int JOYSTICK_RIGHT = 13;	// msp
//		public static final int JOYSTICK_UP = 15;		// msp
//		public static final int JOYSTICK_DOWN = 14;		// msp
//	}
}
