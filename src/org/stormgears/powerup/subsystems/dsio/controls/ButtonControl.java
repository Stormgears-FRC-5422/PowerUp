package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.subsystems.dsio.event_listeners.OnButtonTouchUpListener;

import static org.apache.logging.log4j.util.Unbox.box;

public class ButtonControl extends BaseControl {
	private static final Logger logger = LogManager.getLogger(ButtonControl.class);

	private OnButtonTouchUpListener delegate;

	public ButtonControl(Button button) {
		super(button);
	}

	@Override
	protected void setupCommand() {
		button.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}

			@Override
			protected void execute() {
				logger.info("ButtonControl #{} pressed", box(number));
				if (delegate != null) delegate.onTouchUp();
			}
		});
	}

	public void setOnButtonTouchUpListener(OnButtonTouchUpListener delegate) {
		this.delegate = delegate;
	}
}
