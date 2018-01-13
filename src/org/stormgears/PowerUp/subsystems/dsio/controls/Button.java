package org.stormgears.PowerUp.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.stormgears.PowerUp.subsystems.dsio.event_listeners.OnButtonTouchUpListener;

public class Button {
	private JoystickButton wpiInstance;
	private OnButtonTouchUpListener delegate;

	public Button(int number, Joystick joystick) {
		wpiInstance = new JoystickButton(joystick, number);

		setupCommand();
	}

	private void setupCommand() {
		wpiInstance.whenReleased(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				if (delegate != null) delegate.onTouchUp();
			}
		});
	}

	public void setOnButtonTouchUpListener(OnButtonTouchUpListener delegate) {
		this.delegate = delegate;
	}
}
