package org.stormgears.powerup.subsystems.sensors.stormnet;

public abstract class StormNetVoice {

	public abstract String getDeviceString();

	protected abstract boolean transaction_internal(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize);

	public boolean transaction(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize) {
		return this.transaction_internal(dataToSend, sendSize, dataReceived, receiveSize);
	}

}
