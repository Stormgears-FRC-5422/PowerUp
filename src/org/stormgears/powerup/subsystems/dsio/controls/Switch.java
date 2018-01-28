package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.subsystems.dsio.event_listeners.OnSwitchFlippedListener;

public class Switch extends BaseControl {
	private Logger logger = LogManager.getLogger(Switch.class);

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
				logger.info("Switch #{} flipped off", number);
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
				logger.info("Switch #{} flipped on", number);
				if (delegate != null) delegate.onSwitchFlipped(true);
			}
		});
	}

	public void setOnSwitchFlippedListener(OnSwitchFlippedListener delegate) {
		this.delegate = delegate;
	}
}
