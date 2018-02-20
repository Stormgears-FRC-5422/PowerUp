package org.stormgears.powerup.subsystems.dsio;

import org.stormgears.powerup.Robot;
import org.stormgears.utils.dsio.IRawJoystick;

public class DSIO {
	private static DSIO instance = new DSIO();

	public static DSIO getInstance() {
		return instance;
	}

	/*
	 * If using a 2017 or older button board, set this to false
	 */
	private boolean using2018Board = true;


	public Choosers choosers;

//	// If you want to change the channel, change it here
//	// We need 2 buttonBoards because the 2018 revision takes 2 joysticks to use, and then 1 normal joystick
//	private static final byte JOYSTICK_CHANNEL = 0, BUTTON_BOARD_CHANNEL = 1, BUTTON_BOARD_2_CHANNEL = 2;

//	// Don't instantiate any other joysticks
//	private Joystick joystick = new Joystick(JOYSTICK_CHANNEL),
//		buttonBoard = new Joystick(BUTTON_BOARD_CHANNEL),
//		buttonBoard2;

	private IRawJoystick joystick;

	private boolean joystickEnabled = true;

	private IButtonBoard buttonBoard;

	private DSIO() {
		setupButtonsAndSwitches();
		choosers = new Choosers();

//		if (using2018Board) {
//			buttonBoard2 = new Joystick(BUTTON_BOARD_2_CHANNEL);
//		}
	}

	/*
	 * If you want a button/switch to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtonsAndSwitches() {
		// RED
		buttonBoard.getGripCloseButton().whenPressed(() -> { //closes gripper
			Robot.gripper.closeGripper();
		});

		// YELLOW
		buttonBoard.getGripOpenButton().whenPressed(() -> { //opens gripper
			Robot.gripper.openGripper();
		});

		// GREEN SWITCH // TODO
//			greenSwitch.whenFlipped(isUp -> {
//				if (isOn) {
//					Robot.intake.enableIntake();
//				} else {
//					Robot.intake.disableIntake();
//				}
//			});

		// ORANGE SWITCH
//			orangeSwitch.whenFlipped(isOn -> Robot.drive.useAbsoluteControl = isOn);
	}


	// Joystick related methods

	private static final double X_NULLZONE = 0.2;
	private static final double Y_NULLZONE = 0.2;
	private static final double Z_NULLZONE = 0.1;

	public void enableDriveControls() {
		joystickEnabled = true;
	}

	public void disableDriveControls() {
		joystickEnabled = false;
	}

	public double getJoystickX() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickX(), X_NULLZONE);
	}

	public double getJoystickY() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickY(), Y_NULLZONE);
	}

	public double getJoystickZ() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickZ(), Z_NULLZONE);
	}

	private double processJoystick(double val, final double nullzone) {
		if (Math.abs(val) < nullzone) {
			return 0;
		} else {
			val = ((val - Math.copySign(nullzone, val)) / (1 - nullzone));
		}

		if (joystick.getRawButton(ButtonIds.Joystick.THUMB)) {
			return 0.2 * val;
		}

		val *= Math.abs(val);

		if (Robot.config.reverseJoystick) {
			return -val;
		} else {
			return val;
		}
	}
}
