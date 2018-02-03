package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.stormgears.powerup.subsystems.dsio.event_listeners.OnButtonTouchUpListener;

public class Button extends BaseControl {
	private static final Logger logger = LogManager.getLogger(Button.class);

	private OnButtonTouchUpListener delegate;

	public Button(int number, Joystick joystick) {
		super(joystick, number);
		logger.trace("new Button number = {} joystick = {}", Unbox.box(number), joystick);
	}

	@Override
	protected void setupCommand() {
		logger.trace("setupCommand");
		wpiInstance.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.info("Button #{} pressed", number);
				if (delegate != null) delegate.onTouchUp();
			}
		});
	}

	public void setOnButtonTouchUpListener(OnButtonTouchUpListener delegate) {
		this.delegate = delegate;
	}
}
