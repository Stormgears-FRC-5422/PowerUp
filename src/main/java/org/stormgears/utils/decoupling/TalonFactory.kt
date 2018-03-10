package org.stormgears.utils.decoupling

fun TalonFactory(deviceNumber: Int): ITalon {
	if (deviceNumber != -1) {
		return StormTalon(deviceNumber)
	} else {
		TODO()
	}
}
