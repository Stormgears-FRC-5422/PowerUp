package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static org.apache.logging.log4j.util.Unbox.box;

public class TernarySwitch extends SwitchControl implements ITernarySwitch {
	private static final Logger logger = LogManager.getLogger(TernarySwitch.class);

	private JoystickButton downButton;

	/**
	 * Create a joystick button for triggering commands.
	 *
	 * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick,
	 *                 etc)
	 * @param buttonUp The button number (see {@link GenericHID#getRawButton(int) }
	 */
	public TernarySwitch(@NotNull GenericHID joystick, int buttonUp, int buttonDown) {
		super(joystick, buttonUp);

		downButton = new JoystickButton(joystick, buttonDown);

		downButton.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.trace("Switch {}/{} flipped off - {} ({})", box(buttonUp), box(buttonDown), joystick.getName(), box(joystick.getPort()));
			}
		});

		downButton.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.trace("Switch {}/{} flipped down - {} ({})", box(buttonUp), box(buttonDown), joystick.getName(), box(joystick.getPort()));
			}
		});
	}

	public void whenFlippedTernary(TernaryFlipListener listener) {
		whenFlipped(listener);
	}

	public void whenFlipped(TernaryFlipListener listener) {
		super.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(SwitchState.Neutral);
			}
		});

		super.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(SwitchState.Up);
			}
		});

		downButton.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(SwitchState.Neutral);
			}
		});

		downButton.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				listener.flipped(SwitchState.Down);
			}
		});
	}


}
