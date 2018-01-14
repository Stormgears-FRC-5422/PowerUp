package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.stormgears.powerup.subsystems.dsio.event_listeners.OnButtonTouchUpListener;

public class Button extends BaseControl {
	private OnButtonTouchUpListener delegate;

	public Button(int number, Joystick joystick) {
		super(joystick, number);
	}

	@Override
	protected void setupCommand() {
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
