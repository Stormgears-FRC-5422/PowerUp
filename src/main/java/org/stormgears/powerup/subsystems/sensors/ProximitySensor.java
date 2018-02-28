package org.stormgears.powerup.subsystems.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class ProximitySensor {
	private static ProximitySensor instance;
	private AnalogInput ai;
	private double threshold = 2.5;  // volts.  Sensor should jump between 1.2V and 5V

	private ProximitySensor() {
		ai = new AnalogInput(0); // TODO - use config
	}

	public static ProximitySensor getInstance() {
		return instance;
	}

	public static void init() {
		instance = new ProximitySensor();
	}

	public boolean detected() {
		return ( ai.getAverageVoltage() < threshold );
	}


}
