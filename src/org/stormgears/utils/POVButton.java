package org.stormgears.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButton extends Button /* WPI button! */ {
	enum Direction {
		Up(0, 315, 45),
		Right(90, 45, 135),
		Down(180, 135, 225),
		Left(270, 315, 225);

		final int deg;
		final int upper;
		final int lower;

		Direction(int deg, int lower, int upper) {
			this.deg = deg;
			this.lower = lower;
			this.upper = upper;
		}

		boolean match(int d) {
			return d >= lower && d <= upper;
		}
	}

	private final Joystick joystick;
	private final Direction direction;

	public POVButton(Joystick joystick, Direction direction) {
		this.joystick = joystick;
		this.direction = direction;
	}

	@Override
	public boolean get() {
		int d = this.joystick.getPOV();
		return d != -1 && direction.match(d);
	}
}
