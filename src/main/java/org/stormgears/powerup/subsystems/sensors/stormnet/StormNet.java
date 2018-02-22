package org.stormgears.powerup.subsystems.sensors.stormnet;

public class StormNet {
	private EthernetLidar m_lidar;
	private LineIR m_lineIR;

	public StormNet() {
		// TODO - get these values from configuration
		//	EthernetVoice ethernetVoice = new EthernetVoice("10.54.22.177", 5422);
		//	I2CEthernetVoice i2cEthernetVoice = new I2CEthernetVoice(ethernetVoice, 11);

		//	m_lidar = new EthernetLidar(ethernetVoice);
		//	m_lineIR = new LineIR(i2cEthernetVoice);
	}

	public boolean test() {
		System.out.println("In Stormnet Test");
		System.out.println("about to test lidar");
		m_lidar.test();
		System.out.println("about to test lineIR");
		m_lineIR.test();
		return true;
	}

	public int getLidarDistance(int sensorNumber) {
		return m_lidar.getDistance(sensorNumber);
	}

	public int getLineIRDistance(int sensorNumber) {
		return m_lineIR.getDistance(sensorNumber);
	}

	// TODO: when the serial line is connected to the arduino then the socket needs to reconnect
}
