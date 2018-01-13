package org.stormgears.PowerUp.subsystems.dsio;

import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.PowerUp.subsystems.dsio.controls.Button;

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
			whiteButton 	= new Button(ButtonIds.WHITE_BUTTON_ID, buttonBoard),
			greenSwitch 	= new Button(ButtonIds.GREEN_SWITCH_ID, buttonBoard),
			orangeSwitch 	= new Button(ButtonIds.ORANGE_SWITCH_ID, buttonBoard),
			redSwitch 		= new Button(ButtonIds.RED_SWITCH_ID, buttonBoard);

	private DSIO() {
		setupButtons();
	}

	/*
	 * If you want a button to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtons() {
		bigBlueButton.setOnButtonTouchUpListener(() -> {

		});
	}
}
