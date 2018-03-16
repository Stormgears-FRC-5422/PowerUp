package org.stormgears.utils.decoupling

import org.stormgears.utils.logging.MotorLogger

fun createTalon(deviceNumber: Int): ITalon {
	return if (deviceNumber != -1) {
		StormTalon(deviceNumber)
	} else {
		MotorLogger(DummyTalon(deviceNumber), "DummyTalon $deviceNumber")
	}
}

/**
 * It's like WPI_TalonSRX, but with a little more Trehan
 */
private class StormTalon private constructor(deviceNumber: Int, private val base: ITalon) : ITalon by base {
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

