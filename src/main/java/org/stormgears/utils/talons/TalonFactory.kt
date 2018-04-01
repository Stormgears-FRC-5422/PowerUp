package org.stormgears.utils.talons

import org.apache.logging.log4j.LogManager
import org.stormgears.utils.logging.MotorLogger

private val logger = LogManager.getLogger()

fun createTalon(deviceNumber: Int): IBaseTalon {
	if (deviceNumber != -1) {
		val talon = StormTalon(deviceNumber)

		if (talon.isAlive) {
			return talon
		} else {
			logger.warn("Talon {} is dead! Returning a DummyTalon...", deviceNumber)
		}
	}

	return MotorLogger(DummyTalon(deviceNumber), "DummyTalon $deviceNumber")
}

/**
 * It's like WPI_TalonSRX, but with a little more Trehan
 */
private class StormTalon private constructor(deviceNumber: Int, private val base: IBaseTalon) : IBaseTalon by base {
	internal constructor(deviceNumber: Int) : this(deviceNumber, TalonSRXAdapter(deviceNumber))

	private var previousSet = java.lang.Double.MAX_VALUE
	private var prevPidWrite = java.lang.Double.MAX_VALUE

	override fun set(speed: Double) {
		if (speed == previousSet) {
			return
		}

		base.set(speed)

		previousSet = speed
	}

	override fun pidWrite(output: Double) {
		if (output == prevPidWrite) {
			return
		}
		base.pidWrite(output)

		prevPidWrite = output
	}
}

