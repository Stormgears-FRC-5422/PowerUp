
package org.stormgears.powerup.subsystems.sensors.stormnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EthernetVoice extends StormNetVoice {
	private static final Logger logger = LogManager.getLogger(StormNetVoice.class);

	private DataInputStream m_backFromServer;
	private Socket m_clientSocket;
	private DataOutputStream m_outToServer;

	// TODO - do we really want to open the connection at construction?
	public EthernetVoice(String address, int port) {

		try {
			m_clientSocket = new Socket(address, port);
			m_outToServer = new DataOutputStream(m_clientSocket.getOutputStream());
			m_backFromServer = new DataInputStream(m_clientSocket.getInputStream());
		} catch (Exception e) {
			logger.error("SENSOR CLIENT SOCKET NOT CONNECTED!!!");
			logger.catching(e);
		}
	}

	public String getDeviceString() {
		return "ETH " + m_clientSocket.getInetAddress().toString() + ":" + Integer.toString(m_clientSocket.getPort());
	}

	protected boolean transaction_internal(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize) {
		boolean result;

		try {
			m_outToServer.write(dataToSend, 0, sendSize);
			m_backFromServer.readFully(dataReceived, 0, receiveSize);
			result = StormNetSensor.STORMNET_SUCCESS;
		} catch (IOException e) {
			logger.error("SENSOR TRANSACTION FAILED!!!");
			logger.catching(e);
			result = StormNetSensor.STORMNET_FAILURE;
		}

		return result;
	}

}
