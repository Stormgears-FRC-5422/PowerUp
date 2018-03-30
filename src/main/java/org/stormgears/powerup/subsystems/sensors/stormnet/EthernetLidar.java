package org.stormgears.powerup.subsystems.sensors.stormnet;

import org.stormgears.powerup.Robot;

import java.util.concurrent.TimeUnit;

public class EthernetLidar extends StormNetSensor {
	private short[] sensorValues;
	private short[] rightSensorValues;
	private short[]	leftSensorValues;
	private short[] backSensorValues;

	private byte[] addressValues;

	public EthernetLidar(StormNetVoice voice) {
		super(voice);
		this.setDebug(false);

		// TODO magic number
		setSensorCount(6);
		sensorValues = new short[m_numSensors];

		rightSensorValues = new short[2];
		leftSensorValues = new short[2];
		backSensorValues = new short[2];

		// TODO: 16 addresses are hardcoded on the Mega
		addressValues = new byte[16];

		this.m_deviceString = voice.getDeviceString();
	}

	public boolean test(int sleep) {
		boolean superResult = super.test(sleep);
		testAddresses(sleep);

		try {
			for (int i = 0; i < sleep; i++) {
				pollDistance();
				log("Lidar test returned [ " +
					sensorValues[0] + " ] [ " +
					sensorValues[1] + " ] [ " +
					sensorValues[2] + " ] [ " +
					sensorValues[3] + " ] [ " +
					sensorValues[4] + " ] [ " +
					sensorValues[5] + " ]");
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
					addressValues[3] + " ] [ " +
					addressValues[4] + " ] [ " +
					addressValues[5] + " ]");
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

	public void setRightThreshold(int threshold) {fetchThreshold(threshold);}
	public void setLeftThreshold(int threshold) {fetchThreshold(threshold);}
	public void setBackThreshold(int threshold) {fetchThreshold(threshold);}

	public void getRightSide() {fetchPair(Robot.config.getRightLIDAR_Pair(), rightSensorValues);} // Pair # = 0
	public void getLeftSide() {fetchPair(Robot.config.getLeftLIDAR_Pair(), leftSensorValues);} // Pair # = 1
	public void getBackSide() {fetchPair(Robot.config.getBackLIDAR_Pair(), backSensorValues);} // Pair # = 2

	// Distance in millimeters
	public int getDistance(int sensorNumber) {
		return (sensorValues[sensorNumber]); // Java wants shorts to be signed.  We want unsigned value
	}



}
