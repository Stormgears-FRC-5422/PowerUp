package org.stormgears.powerup.subsystems.dsio.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;

import java.util.Timer;

public abstract class BaseControl {
	private static final Logger logger = LogManager.getLogger(BaseControl.class);

	protected JoystickButton wpiInstance;
	protected int number;

	public BaseControl(Joystick joystick, int number) {
		logger.debug("Button {} init", Unbox.box(number));
		this.number = number;

		wpiInstance = new JoystickButton(joystick, number);

//		wpiInstance.whenPressed(new Command() {
//			@Override
//			protected boolean isFinished() {
//				return true;
//			}
//
//			@Override
//			protected void initialize() {
//				logger.trace("initialize {}", number);
//			}
//
//			@Override
//			protected void execute() {
//				logger.trace("execute {}", number);
//			}
//
//			@Override
//			protected void end() {
//				logger.trace("end {}", number);
//			}
//		});

		setupCommand();
	}

	protected abstract void setupCommand();
}
