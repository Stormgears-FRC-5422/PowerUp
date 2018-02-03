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

	private final Button
			bigBlueButton 	= new Button(ButtonIds.BIG_BLUE_BUTTON_ID, buttonBoard),
			redButton 		= new Button(ButtonIds.RED_BUTTON_ID, buttonBoard),
			yellowButton 	= new Button(ButtonIds.YELLOW_BUTTON_ID, buttonBoard),
			greenButton 	= new Button(ButtonIds.GREEN_BUTTON_ID, buttonBoard),
			smallBlueButton = new Button(ButtonIds.SMALL_BLUE_BUTTON_ID, buttonBoard),
			blackButton 	= new Button(ButtonIds.BLACK_BUTTON_ID, buttonBoard),
			whiteButton 	= new Button(ButtonIds.WHITE_BUTTON_ID, buttonBoard);

	private final Switch
			greenSwitch 	= new Switch(ButtonIds.GREEN_SWITCH_ID, buttonBoard),
			orangeSwitch 	= new Switch(ButtonIds.ORANGE_SWITCH_ID, buttonBoard),
			redSwitch 		= new Switch(ButtonIds.RED_SWITCH_ID, buttonBoard);

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
		redButton.setOnButtonTouchUpListener(() -> {

		});

		// YELLOW
		yellowButton.setOnButtonTouchUpListener(() -> {

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

			} else {

			}
		});

		// ORANGE SWITCH
		orangeSwitch.setOnSwitchFlippedListener(isOn -> {
			if (isOn) {

			} else {

			}
		});

		// RED SWITCH
		redSwitch.setOnSwitchFlippedListener(isOn -> {
			if (isOn) {

			} else {

			}
		});
	}

	// Joystick related methods

	private static final double X_NULLZONE = 0.2;
	private static final double Y_NULLZONE = 0.2;
	private static final double Z_NULLZONE = 0.1;

	public double getJoystickX() {
		double x = joystick.getX();

		// TODO: Determine whether we need the entire range of 0.0..1.0 joystick values
		double filtered = Math.abs(x) < X_NULLZONE ? 0 : x - Math.copySign(X_NULLZONE, x);
		int reverse = Robot.config.reverseJoystick ? -1 : 1;

		return filtered * reverse;
	}

	public double getJoystickY() {
		double y = joystick.getY();

		double filtered = Math.abs(y) < Y_NULLZONE ? 0 : y - Math.copySign(Y_NULLZONE, y);
		int reverse = Robot.config.reverseJoystick ? -1 : 1;

		return filtered * reverse;
	}

	public double getJoystickZ() {
		double z = joystick.getZ();

		double filtered = Math.abs(z) < Z_NULLZONE ? 0 : z - Math.copySign(Z_NULLZONE, z);
		int reverse = Robot.config.reverseJoystick ? -1 : 1;

		return filtered * reverse;
	}
	public Joystick getJoystick(){
		return joystick;
	}
}
