package org.stormgears.powerup.subsystems.sensors;

import org.stormgears.powerup.subsystems.sensors.vision.Vision;
import org.stormgears.utils.sensor_drivers.NavX;

public class Sensors {
	private static Sensors instance;
	public static Sensors getInstance() { return instance; }

	private Vision vision;
	private NavX navX;

	private Sensors() {
		vision = new Vision();
		navX = new NavX();
	}

	public static void init() {
		instance = new Sensors();
	}

	public Vision getVision() {
		return vision;
	}

	public NavX getNavX() { return navX; }
}
