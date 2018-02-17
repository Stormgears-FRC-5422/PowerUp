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

	public Choosers choosers;

	// If you want to change the channel, change it here
	private static final byte JOYSTICK_CHANNEL = 0, BUTTON_BOARD_CHANNEL = 1;

	// Don't instantiate any other joysticks
	private final Joystick joystick = new Joystick(JOYSTICK_CHANNEL),
		buttonBoard = new Joystick(BUTTON_BOARD_CHANNEL);

	private boolean joystickEnabled = true;


	private final Button
		bigBlueButton = new Button(ButtonIds.Board.BIG_BLUE, buttonBoard),
		redButton = new Button(ButtonIds.Board.RED, buttonBoard),
		yellowButton = new Button(ButtonIds.Board.YELLOW, buttonBoard),
		greenButton = new Button(ButtonIds.Board.GREEN, buttonBoard),
		smallBlueButton = new Button(ButtonIds.Board.SMALL_BLUE, buttonBoard),
		blackButton = new Button(ButtonIds.Board.BLACK, buttonBoard),
		whiteButton = new Button(ButtonIds.Board.WHITE, buttonBoard);

	private final Switch
		greenSwitch = new Switch(ButtonIds.Board.GREEN_SWITCH, buttonBoard),
		orangeSwitch = new Switch(ButtonIds.Board.ORANGE_SWITCH, buttonBoard),
		redSwitch = new Switch(ButtonIds.Board.RED_SWITCH, buttonBoard);

	private DSIO() {
		setupButtonsAndSwitches();
		choosers = new Choosers();
	}

	/*
	 * If you want a button/switch to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtonsAndSwitches() {
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
		orangeSwitch.setOnSwitchFlippedListener(isOn -> { //For AbsControl
			if (isOn) {
				Robot.drive.onAbsoluteControl();
			} else {
				Robot.drive.offAbsoluteControl();
			}
		});

		// RED SWITCH
		redSwitch.setOnSwitchFlippedListener(isOn -> Robot.drive.useTractionControl = isOn);
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
