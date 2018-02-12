package org.stormgears.powerup.subsystems.sensors.stormnet;

import edu.wpi.first.wpilibj.I2C;

public class I2CVoice extends StormNetVoice {
	private I2C m_I2C;
	private int m_deviceAddress;

	// I don't understand why these are backwards, but there you have it.
	// I guess this follows the unix model of 0 exit code being OK
	// I think the original intent was to return "transaction aborted" as true
	private static final boolean I2CSUCCESS = false;
	private static final boolean I2CFAILURE = true;

	public I2CVoice(I2C.Port port, int deviceAddress) {
		m_I2C = new I2C(port, deviceAddress);
		m_deviceAddress = deviceAddress;
	}

	@Override
	public String getDeviceString() {
		return "I2C " + Integer.toString(m_deviceAddress);
	}

	/**
	* This function inverts the original sense of the return code (true no longer means "Transaction aborted").
	* Note that this return code for the wpilib result has been suspect and shouldn't necessarily be relied upon
	* for interpreting successful connections. Use one of the built-in commands for that (e.g. ping()).
	*/
	protected boolean transaction_internal(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize) {
		boolean result;
		result = m_I2C.transaction(dataToSend, sendSize, dataReceived, receiveSize);

		return (result == I2CSUCCESS);
	}

}
