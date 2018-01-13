package org.stormgears.utils;

/**
 * Synchronize against this object to prevent overloading the CAN bus
 */
public class CANLock {
	public static final Object lock = new Object();
}
