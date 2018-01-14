package org.stormgears.powerup.subsystems.dsio;

public enum JoystickFilter {
	NULLZONE;

	private double nullzoneBound = 0.1;

	public static JoystickFilter nullzone(double nullzoneBound) {
		NULLZONE.nullzoneBound = nullzoneBound;

		return NULLZONE;
	}

	public double getNewValue(double oldValue) {
		switch (this) {
			case NULLZONE:
				if (Math.abs(oldValue) < nullzoneBound) return 0;
				else return oldValue;
			default:
				return oldValue;
		}
	}
}
