package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.util.concurrent.TimeUnit;

public class EthernetLidar extends StormNetSensor {
	private short[] sensorValues;
	private short[] sensorPairValues;
	private byte[] addressValues;

	public EthernetLidar(StormNetVoice voice) {
		super(voice);

		// TODO magic number
		setSensorCount(4);
		sensorValues = new short[m_numSensors];
		sensorPairValues = new short[2];
		// TODO: 24 addresses are hardcoded on the Mega
		addressValues = new byte[24];

		this.m_deviceString = voice.getDeviceString();
	}

	public boolean test(int sleep) {
//		boolean superResult = super.test(sleep);
		testAddresses(sleep);

		try {
			for (int i = 0; i < sleep; i++) {
				pollDistance();
				log("Lidar test returned [ " +
					sensorValues[0] + " ] [ " +
					sensorValues[1] + " ] [ " +
					sensorValues[2] + " ] [ " +
					sensorValues[3] + " ] ");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void testAddresses(int sleep) {
		try {
			for (int i = 0; i < sleep; i++) {
				pollAddress();
				log("Lidar Address test returned [ " +
					addressValues[0] + " ] [ " +
					addressValues[1] + " ] [ " +
					addressValues[2] + " ] [ " +
					addressValues[3] + " ]");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pollDistance() {
		fetchShorts("L", "Lidar", sensorValues);
	}

	public void pollAddress() {
		fetchBytes("A", "Address", addressValues);
	}

	public void pollPairs(int pairNumber) {
		System.out.println("ENTERED POLL PAIRS");
		String command = "R";
		fetchShorts(command, "Pair", sensorPairValues);
		System.out.println("EXITED POLL PAIRS");
	}

	// Distance in millimeters
	public int getDistance(int sensorNumber) {
		pollDistance();
		return (sensorValues[sensorNumber]); // Java wants shorts to be signed.  We want unsigned value
	}

	public String printPairs(int pairNumber) {
		pollPairs(pairNumber);
		return sensorPairValues[0] + " " + sensorPairValues[1];
	}
}
