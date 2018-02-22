package org.stormgears.utils;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * It's like WPI_TalonSRX, but with a little more Trehan
 */
public class StormTalon extends WPI_TalonSRX {
	public StormTalon(int deviceNumber) {
		super(deviceNumber);

		System.out.println("Talon " + deviceNumber + " being initialized");
	}

	private double previousSet = Double.MAX_VALUE;

	@Override
	public void set(double speed) {
		if (speed == previousSet) {
			return;
		}

		synchronized (CANLock.lock) {
			super.set(speed);

			previousSet = speed;
		}
	}

	private double prevPidWrite = Double.MAX_VALUE;

	@Override
	public void pidWrite(double output) {
		if (output == prevPidWrite) {
			return;
		}

		synchronized (CANLock.lock) {
			super.pidWrite(output);

			prevPidWrite = output;
		}
	}

	// TODO: Other set methods
	// TODO: Add synchronized functionality
}
