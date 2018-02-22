package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.util.concurrent.TimeUnit;

public class LineIR extends StormNetSensor {
	private short[] sensorValues;

	public LineIR(StormNetVoice voice) {
		super(voice);

		// TODO magic number
		setSensorCount(2);
		sensorValues = new short[m_numSensors];
		this.m_deviceString = voice.getDeviceString();
	}

	public boolean test(int sleep) {
		boolean superResult = super.test(sleep);

		try {
			for (int i = 0; i < sleep; i++) {
				pollDistance();
				log("IR test returned [ " +
					sensorValues[0] + " ] [ " +
					sensorValues[1] + " ]");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void pollDistance() {
		fetchShorts("C", "Color", sensorValues);
	}

	// Distance in ???
	public int getDistance(int sensorNumber) {
		pollDistance();
		return (sensorValues[sensorNumber]); // Java wants shorts to be signed.  We want unsigned value
	}

}
