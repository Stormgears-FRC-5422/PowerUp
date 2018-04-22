package org.stormgears.powerup.subsystems.dsio

import edu.wpi.first.wpilibj.Joystick
import org.stormgears.utils.dsio.*

class ButtonBoard2018V1 private constructor(msp: Joystick, logitech: Joystick, realJoystick: LogitechJoystick?) : IButtonBoard {

	override val gripOpenButton: IButton = POVButton(logitech, POVButton.Direction.Down)
	override val gripCloseButton: IButton = POVButton(logitech, POVButton.Direction.Up)

	override val dropButton: IButton = EnhancedButton(logitech, 12)

	override val switch0Button: IButton = EnhancedButton(msp, 8)
	override val switch1Button: IButton = EnhancedButton(logitech, 1)

	override val sideLeftButton: IButton = EnhancedButton(logitech, 3)
	override val sideRightButton: IButton = EnhancedButton(logitech, 4)

	override val intakeGrabButton: IButton = POVButton(logitech, POVButton.Direction.Left)

	override val climbUpButton: IButton = POVButton(logitech, POVButton.Direction.Right)
	override val climbDownButton: IButton = EnhancedButton(logitech, 10)

	override val scaleButtons: Array<IButton> = arrayOf(
		EnhancedButton(logitech, 8),
		EnhancedButton(logitech, 5),
		EnhancedButton(logitech, 7),
		EnhancedButton(logitech, 9),
		EnhancedButton(logitech, 6)
	)

	override val intakeWheelsSwitch: ISwitch = TernarySwitch(msp, 9, 5)
	override val intakeLiftSwitch: ISwitch = TernarySwitch(msp, 3, 10)

	override val forceZeroElevatorButton: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 7) else DummyButton()
	override val zeroElevatorButton: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 8) else DummyButton()

	override val overrideSwitch: ISwitch = SwitchControl(msp, 4)
	override val overrideUp: IButton = EnhancedButton(msp, 15)
	override val overrideDown: IButton = EnhancedButton(msp, 14)
	override val overrideLeft: IButton = EnhancedButton(msp, 12)
	override val overrideRight: IButton = EnhancedButton(msp, 13)

	override val speedSwitch: ISwitch = DummySwitch()

	override val intakeTrigger: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 1) else DummyButton()

	companion object {
		private var instance: ButtonBoard2018V1? = null

		@JvmStatic // TODO: Replace with more idiomatic Kotlin (object???)
		fun getInstance(buttonBoard: Joystick, joystick: Joystick, realJoystick: LogitechJoystick?): IButtonBoard {
			if (instance == null) {
				if (IButtonBoard.initialized) {
					throw IllegalStateException("Only one button board can exist at once.")
				}

				IButtonBoard.initialized = true

				instance = ButtonBoard2018V1(buttonBoard, joystick, realJoystick)
				return instance as IButtonBoard
			}

			return instance as IButtonBoard
		}
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
