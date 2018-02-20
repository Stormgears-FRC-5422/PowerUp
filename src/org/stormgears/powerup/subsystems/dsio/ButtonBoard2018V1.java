package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stormgears.utils.dsio.*;

public class ButtonBoard2018V1 implements IButtonBoard {
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

	private final Joystick msp;
	private final Joystick logitech;

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

	public ButtonBoard2018V1(Joystick msp, Joystick logitech) {
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

		scaleButtons = new StormButton[]{
			new EnhancedButton(logitech, 8),
			new EnhancedButton(logitech, 5),
			new EnhancedButton(logitech, 7),
			new EnhancedButton(logitech, 10),
			new EnhancedButton(logitech, 6)
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
