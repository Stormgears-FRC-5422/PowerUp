package org.stormgears.powerup.subsystems.sensors.stormnet;

public class StormNet {
	private EthernetVoice m_voice;
	private EthernetLidar m_lidar;

	public StormNet() {
		// TODO - get these values from configuration
		m_voice = new EthernetVoice("10.54.22.177", 5422);
		m_lidar = new EthernetLidar(m_voice);
	}

	public boolean test() {
		m_lidar.test();
		return true;
	}

	// TODO: when the serial line is connected to the arduino then the socket needs to reconnect
}
