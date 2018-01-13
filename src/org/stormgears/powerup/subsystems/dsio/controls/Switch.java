package org.stormgears.PowerUp.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.stormgears.PowerUp.subsystems.dsio.event_listeners.OnSwitchFlippedListener;

public class Switch extends BaseControl {
	private OnSwitchFlippedListener delegate;

	public Switch(int number, Joystick joystick) {
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
				if (delegate != null) delegate.onSwitchFlipped(false);
			}
		});

		wpiInstance.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				if (delegate != null) delegate.onSwitchFlipped(true);
			}
		});
	}

	public void setOnSwitchFlippedListener(OnSwitchFlippedListener delegate) {
		this.delegate = delegate;
	}
}
