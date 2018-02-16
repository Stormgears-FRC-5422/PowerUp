package org.stormgears.powerup.subsystems.sensors.stormnet;

// The build teams sample i2c sensor
public class TestI2CSensor extends StormNetSensor {

	TestI2CSensor(StormNetVoice voice) {
		super(voice);
	}

	public boolean test() {
		return false;
//		log("Test returned " + (ping() ? "true" : "false") );
	}
}


