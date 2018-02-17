package org.stormgears.powerup.subsystems.sensors;

import org.stormgears.powerup.subsystems.sensors.stormnet.StormNet;
import org.stormgears.powerup.subsystems.sensors.vision.Vision;
import org.stormgears.utils.sensor_drivers.NavX;

public class Sensors {
	private static Sensors instance;

	public static Sensors getInstance() {
		return instance;
	}

	private Vision vision;
	private NavX navX;
	private StormNet stormNet;

	private Sensors() {
//		vision = new Vision();
		navX = new NavX();
//		stormNet = new StormNet();
	}

	public static void init() {
		instance = new Sensors();
	}

	public Vision getVision() {
		return vision;
	}

	public NavX getNavX() {
		return navX;
	}

	public StormNet getStormNet() {
		return stormNet;
	}
}
