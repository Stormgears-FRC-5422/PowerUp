package org.stormgears.powerup.subsystems.sensors;

import org.stormgears.powerup.subsystems.sensors.stormnet.StormNet;
import org.stormgears.powerup.subsystems.sensors.vision.Vision;
import org.stormgears.utils.sensordrivers.NavX;

public class Sensors {
	private static Sensors instance;

	public static Sensors getInstance() {
		return instance;
	}

	private Vision vision;
	private NavX navX;
	private StormNet stormNet;
	private ProximitySensor proximitySensor;

	private Sensors() {
//		vision = new Vision();
		navX = new NavX();

		StormNet.init();
		stormNet = StormNet.getInstance();

		ProximitySensor.init();
		proximitySensor = ProximitySensor.getInstance();
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

	public ProximitySensor getProximitySensor() {
		return proximitySensor;
	}
}
