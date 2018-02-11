package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class EthernetLidar extends StormNetSensor {
	private short[] sensorValues;

	public EthernetLidar(EthernetVoice voice) {
		super(voice);
		// TODO magic number
		setSensorCount(4);
		sensorValues = new short[m_numSensors];
	}

	public boolean test(int sleep) {
		boolean superResult = super.test(sleep);

		try {
			short[] sensorValues = new short[4];  // 4 sensor values
			for (int i = 0; i < 2000; i++) {
				fetchShorts("L", "Lidar", sensorValues);
				log("Lidar test returned [ " +
					sensorValues[0] + " ] [ " +
					sensorValues[1] + " ] [ " +
					sensorValues[2] + " ] [ " +
					sensorValues[3] + " ]");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void pollDistance() {
		fetchShorts("L", "Lidar", sensorValues);
	}

	// Distance in millimeters
	public int getDistance(int sensorNumber) {
		return (0xFFFF & sensorValues[sensorNumber]); // Java wants shorts to be signed.  We want unsigned value
	}

}
