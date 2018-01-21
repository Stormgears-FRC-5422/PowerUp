package org.stormgears.powerup.subsystems.sensors;

import org.stormgears.powerup.subsystems.sensors.vision.Vision;

public class Sensors {
	private static Sensors instance;
	public static Sensors getInstance() { return instance; }

	private Vision vision;

	private Sensors() {
		vision = new Vision();
	}

	public static void init() {
		instance = new Sensors();
	}

	public Vision getVision() {
		return vision;
	}
}
