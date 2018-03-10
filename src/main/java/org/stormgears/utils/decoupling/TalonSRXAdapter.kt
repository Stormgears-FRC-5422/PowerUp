package org.stormgears.utils.decoupling

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX

class TalonSRXAdapter(deviceNumber: Int) : ITalon, WPI_TalonSRX(deviceNumber) {
	override val sensorCollection: ISensorCollection = ISensorCollection.SensorCollectionAdapter(super.getSensorCollection())
}