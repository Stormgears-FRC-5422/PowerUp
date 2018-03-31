package org.stormgears.utils.decoupling

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX

class TalonSRXAdapter(deviceNumber: Int) : IBaseTalon, WPI_TalonSRX(deviceNumber) {
	override val sensorCollection: ISensorCollection = ISensorCollection.SensorCollectionAdapter(super.getSensorCollection())

	override val dummy = false
}
