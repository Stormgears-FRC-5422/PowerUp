package org.stormgears.utils.talons

import org.apache.logging.log4j.LogManager
import org.stormgears.utils.logging.MotorLogger

private val logger = LogManager.getLogger()

fun createTalon(deviceNumber: Int): ITalon {
	if (deviceNumber != -1) {
		val talon = TalonManager(TalonSRXAdapter(deviceNumber))

		if (talon.isAlive) {
			return talon
		} else {
			logger.warn("Talon {} is dead! Returning a DummyTalon...", deviceNumber)
		}
	}

	return TalonManager(MotorLogger(DummyTalon(deviceNumber), "DummyTalon $deviceNumber"))
}

