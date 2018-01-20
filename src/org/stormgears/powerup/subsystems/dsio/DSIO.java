package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;

import org.stormgears.powerup.subsystems.dsio.controls.Button;
import org.stormgears.powerup.subsystems.dsio.controls.Switch;

public class DSIO {
	private static DSIO instance = new DSIO();
	public static DSIO getInstance() {
		return instance;
	}

	// If you want to change the channel, change it here
	private static final byte JOYSTICK_CHANNEL = 1, BUTTON_BOARD_CHANNEL = 0;

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
	}

	/*
	 * If you want a button/switch to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtonsAndSwitches() {
		bigBlueButton.setOnButtonTouchUpListener(() -> {

		});

		greenSwitch.setOnSwitchFlippedListener(isOn -> {
			if (isOn) {

			} else {

			}
		});
	}

	// Joystick related methods

	private static final double X_NULLZONE = 0.1;
	private static final double Y_NULLZONE = 0.1;
	private static final double Z_NULLZONE = 0.2;

	public double getJoystickX() {
		double x = joystick.getX();

		return Math.abs(x) < X_NULLZONE ? 0 : x;
	}

	public double getJoystickY() {
		double y = joystick.getY();

		return Math.abs(y) < Y_NULLZONE ? 0 : y;
	}

	public double getJoystickZ() {
		double z = joystick.getZ();

		return Math.abs(z) < Z_NULLZONE ? 0 : z;
	}
}
