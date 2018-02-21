package org.stormgears.utils.dsio;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class POVButton extends Button /* WPI button! */ implements StormButton {
	private static final Logger logger = LogManager.getLogger(POVButton.class);

	public enum Direction {
		Up(0, 315, 45),
		Right(90, 45, 135),
		Down(180, 135, 225),
		Left(270, 315, 225);

		private final int deg;
		private final int upper;
		private final int lower;

		Direction(int deg, int lower, int upper) {
			this.deg = deg;
			this.lower = lower;
			this.upper = upper;
		}

		private boolean match(int d) {
			return d == deg || d == lower || d == upper;
		}
	}

	private final Joystick joystick;
	private final Direction direction;

	public POVButton(Joystick joystick, Direction direction) {
		this.joystick = joystick;
		this.direction = direction;

		this.whenPressed(() -> logger.info("POV button {} pressed - {}", direction, joystick.getName()));
	}

	@Override
	public boolean get() {
		int d = this.joystick.getPOV();
		return d != -1 && direction.match(d);
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
