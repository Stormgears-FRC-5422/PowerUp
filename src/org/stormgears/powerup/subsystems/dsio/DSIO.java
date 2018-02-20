package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.dsio.controls.Button;
import org.stormgears.powerup.subsystems.dsio.controls.Switch;

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

	// If you want to change the channel, change it here
	// We need 2 buttonBoards because the 2018 revision takes 2 joysticks to use, and then 1 normal joystick
	private static final byte JOYSTICK_CHANNEL = 0, BUTTON_BOARD_CHANNEL = 1, BUTTON_BOARD_2_CHANNEL = 2;

	// Don't instantiate any other joysticks
	private Joystick joystick = new Joystick(JOYSTICK_CHANNEL),
		buttonBoard = new Joystick(BUTTON_BOARD_CHANNEL),
		buttonBoard2;

	private boolean joystickEnabled = true;

	/*
	 * year <= 2017 buttons
	 */
	private final Button
		bigBlueButton = new Button(ButtonIds.Board.Rev2017.BIG_BLUE, buttonBoard),
		redButton = new Button(ButtonIds.Board.Rev2017.RED, buttonBoard),
		yellowButton = new Button(ButtonIds.Board.Rev2017.YELLOW, buttonBoard),
		greenButton = new Button(ButtonIds.Board.Rev2017.GREEN, buttonBoard),
		smallBlueButton = new Button(ButtonIds.Board.Rev2017.SMALL_BLUE, buttonBoard),
		blackButton = new Button(ButtonIds.Board.Rev2017.BLACK, buttonBoard),
		whiteButton = new Button(ButtonIds.Board.Rev2017.WHITE, buttonBoard);
	private final Switch
		greenSwitch = new Switch(ButtonIds.Board.Rev2017.GREEN_SWITCH, buttonBoard),
		orangeSwitch = new Switch(ButtonIds.Board.Rev2017.ORANGE_SWITCH, buttonBoard),
		redSwitch = new Switch(ButtonIds.Board.Rev2017.RED_SWITCH, buttonBoard);

	/*
	 * year == 2018 buttons
	 */
	private final Button[] scaleButtons = {
		new Button(ButtonIds.Board.Rev2018.SCALE_0, buttonBoard2),
		new Button(ButtonIds.Board.Rev2018.SCALE_1, buttonBoard2),
		new Button(ButtonIds.Board.Rev2018.SCALE_2, buttonBoard2),
		new Button(ButtonIds.Board.Rev2018.SCALE_3, buttonBoard2),
		new Button(ButtonIds.Board.Rev2018.SCALE_4, buttonBoard2)
	};
	private final Button[] switchButtons = {
		new Button(ButtonIds.Board.Rev2018.SCALE_0, buttonBoard2),
		new Button(ButtonIds.Board.Rev2018.SCALE_1, buttonBoard2),
	};

	private DSIO() {
		setupButtonsAndSwitches();
		choosers = new Choosers();

		if (using2018Board) {
			buttonBoard2 = new Joystick(BUTTON_BOARD_2_CHANNEL);
		}
	}

	/*
	 * If you want a button/switch to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtonsAndSwitches() {
		if (using2018Board) {

		} else {
			// BIG BLUE
			bigBlueButton.setOnButtonTouchUpListener(() -> {

			});

			// RED
			redButton.setOnButtonTouchUpListener(() -> { //closes gripper
				Robot.gripper.closeGripper();
			});

			// YELLOW
			yellowButton.setOnButtonTouchUpListener(() -> { //opens gripper
				Robot.gripper.openGripper();
			});

			// GREEN
			greenButton.setOnButtonTouchUpListener(() -> {

			});

			// SMALL BLUE
			smallBlueButton.setOnButtonTouchUpListener(() -> {

			});

			// BLACK
			blackButton.setOnButtonTouchUpListener(() -> {

			});

			// WHITE
			whiteButton.setOnButtonTouchUpListener(() -> {

			});

			// GREEN SWITCH
			greenSwitch.setOnSwitchFlippedListener(isOn -> {
				if (isOn) {
					Robot.intake.enableIntake();
				} else {
					Robot.intake.disableIntake();
				}
			});

			// ORANGE SWITCH
			orangeSwitch.setOnSwitchFlippedListener(isOn -> Robot.drive.useAbsoluteControl = isOn);

			// RED SWITCH
			redSwitch.setOnSwitchFlippedListener(isOn -> Robot.drive.useTractionControl = isOn);
		}
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

		return processJoystick(joystick.getX(), X_NULLZONE);
	}

	public double getJoystickY() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getY(), Y_NULLZONE);
	}

	public double getJoystickZ() {
		if (!joystickEnabled) return 0;

		return processJoystick(-joystick.getZ(), Z_NULLZONE);
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
