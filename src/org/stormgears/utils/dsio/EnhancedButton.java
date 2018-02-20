package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.util.Unbox.box;

public class EnhancedButton extends JoystickButton implements StormButton {
	private static final Logger logger = LogManager.getLogger(EnhancedButton.class);

	/**
	 * Create a joystick button for triggering commands.
	 *
	 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
	 *                     etc)
	 * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
	 */
	public EnhancedButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);

//		logger.trace("joystick = {}, buttonNumber = {}", joystick.getName(), buttonNumber);

		this.whenPressed(() -> logger.info("Button {} pressed on {} ({})", box(buttonNumber), joystick.getName(), box(joystick.getPort())));
	}

	@Override
	public void whenPressed(Runnable callback) {
		super.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				callback.run();
			}
		});
	}
}
