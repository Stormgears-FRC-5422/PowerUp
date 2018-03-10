package org.stormgears.utils.decoupling

/**
 * It's like WPI_TalonSRX, but with a little more Trehan
 */
internal class StormTalon private constructor(deviceNumber: Int, private val base: ITalon) : ITalon by base {
	constructor(deviceNumber: Int) : this(deviceNumber, TalonSRXAdapter(deviceNumber))

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
