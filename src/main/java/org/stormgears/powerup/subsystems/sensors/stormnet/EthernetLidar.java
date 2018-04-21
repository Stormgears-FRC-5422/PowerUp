package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class EthernetLidar extends StormNetSensor {
	private short[] sensorValues;
	private short[] sensorPairValues;
	private byte[] addressValues;

	private int threshold;

	public EthernetLidar(StormNetVoice voice) {
		super(voice);

		// TODO magic number
		setSensorCount(4);
		sensorValues = new short[m_numSensors];
		sensorPairValues = new short[2];
		// TODO: 24 addresses are hardcoded on the Mega
		addressValues = new byte[24];

		this.m_deviceString = voice.getDeviceString();

		threshold = 5;
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
		fetchPairs(0, sensorPairValues);
	}

	// Distance in millimeters
	public int getDistance(int sensorNumber) {
		pollDistance();
		return (sensorValues[sensorNumber]); // Java wants shorts to be signed.  We want unsigned value
	}

	public short[] getPair(int pairNumber) {
		pollPairs(pairNumber);
		return sensorPairValues;
	}

	public void changeThreshold(int threshold) {
		this.threshold = threshold;
		fetchThreshold(threshold);
	}

	public boolean isAligned() {
		return Math.abs(sensorPairValues[0] - sensorPairValues[1]) <= threshold;
	}

	public boolean fetchThreshold(int threshold) {
		byte[] receiveBuffer = new byte[4];
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.order(ByteOrder.LITTLE_ENDIAN);

		buffer.put("T".getBytes(StandardCharsets.US_ASCII)[0]);
		buffer.putInt(threshold);
		return fetchCommand(buffer.array(), "Change Threshold", receiveBuffer);
	}

	public boolean fetchPairs(int pairNumber, short[] shortArray) {
		byte[] receiveBuffer = new byte[shortArray.length * Short.BYTES];
		ByteBuffer sendingBuffer = ByteBuffer.allocate(5);
		sendingBuffer.order(ByteOrder.LITTLE_ENDIAN);

		sendingBuffer.put("R".getBytes(StandardCharsets.US_ASCII)[0]);
		sendingBuffer.putInt(pairNumber);

		boolean result = fetchCommand(sendingBuffer.array(), "Report Pairs", receiveBuffer);

		ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < shortArray.length; i++) {
			shortArray[i] = buffer.getShort();
			debug("short value: " + shortArray[i]);
		}
		return result;
	}

}
