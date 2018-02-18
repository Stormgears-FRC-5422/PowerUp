package org.stormgears.powerup.subsystems.sensors.stormnet;

public class StormNet {
	private EthernetLidar m_lidar;
	private LineIR m_lineIR;

	public StormNet() {
		// TODO - get these values from configuration
		EthernetVoice ethernetVoice = new EthernetVoice("10.54.22.177", 5422);
		I2CEthernetVoice i2cEthernetVoice = new I2CEthernetVoice(ethernetVoice, 11);

		m_lidar = new EthernetLidar(ethernetVoice);
		m_lineIR = new LineIR(i2cEthernetVoice);
	}

	public boolean test() {
		m_lidar.test();
		m_lineIR.test();
		return true;
	}

	// TODO: when the serial line is connected to the arduino then the socket needs to reconnect
}
