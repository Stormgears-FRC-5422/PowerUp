package org.stormgears.powerup.subsystems.navigator;

public class Position {
	private double x, y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Position{" +
			"x=" + x +
			", y=" + y +
			'}';
	}
}
