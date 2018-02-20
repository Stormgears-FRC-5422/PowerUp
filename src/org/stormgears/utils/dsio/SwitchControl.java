package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static org.apache.logging.log4j.util.Unbox.box;

public class SwitchControl extends JoystickButton {
	private static final Logger logger = LogManager.getLogger(SwitchControl.class);

	/**
	 * Create a joystick button for triggering commands.
	 *
	 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
	 *                     etc)
	 * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
	 */
	public SwitchControl(@NotNull GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);

		super.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.info("Switch {} flipped off on {} ({})", box(buttonNumber), joystick.getName(), box(joystick.getPort()));
			}
		});

		super.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.info("Switch {} flipped on on {} ({})", box(buttonNumber), joystick.getName(), box(joystick.getPort()));
			}
		});
	}

	public void whenFlipped(@NotNull FlipListener listener) {
		super.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(false);
			}
		});

		super.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(true);
			}
		});
	}

	@FunctionalInterface
	public static interface FlipListener {
		void flipped(boolean on);
	}
}
