package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class I2CEthernetVoice extends StormNetVoice {
	private EthernetVoice m_ethernetVoice;
	private byte m_deviceAddress;

	public I2CEthernetVoice(EthernetVoice eVoice, int deviceAddress) {
		m_ethernetVoice = eVoice;
		m_deviceAddress = (byte) deviceAddress; // Constrains us to 7 bit addresses for now
	}

	@Override
	public String getDeviceString() {
		return m_ethernetVoice.getDeviceString() + "I2C " + Integer.toString(m_deviceAddress);
	}

	@Override
	protected boolean transaction_internal(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize) {
		// The idea is to simply proxy the standard command over the ethernet with a minimal protocol
		// addition. Each command is prefixed with some metadata that is interpreted by the
		// ethernet end. The ultimate string passed along is
		// @ + id + sendSize + receiveSize + command[with arguments]
		// @, id, and len are all single bytes
		// what comes back is
		// i2c result + command results
		// i2c result is a single 0/1 byte - 1 means success

		byte eSendSize = (byte) (sendSize + 4);  // @ + id + send + receive
		byte eReceiveSize = (byte) (receiveSize + 1); // i2c result + final receive buffer

		byte[] receiveBuffer = new byte[eReceiveSize];
		ByteBuffer buffer = ByteBuffer.allocate(eSendSize);
		buffer.order(ByteOrder.LITTLE_ENDIAN);

		buffer.put("@".getBytes(StandardCharsets.US_ASCII)[0]);
		buffer.put(m_deviceAddress);
		buffer.put((byte) sendSize);
		buffer.put((byte) receiveSize);
		buffer.put(dataToSend);

		if (m_ethernetVoice.transaction(buffer.array(), eSendSize, receiveBuffer, eReceiveSize)) {
			// The ethernet command worked. Unpack
			boolean i2cResult = receiveBuffer[0] == 1; // Ethernet returns 1 in this slot if it believes i2c succeeded
			System.arraycopy(receiveBuffer, 1, dataReceived, 0, receiveSize);
			return i2cResult;
		}
		return false;
	}
}
