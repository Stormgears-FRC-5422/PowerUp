package org.stormgears.powerup.subsystems.navigator

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

fun dashboardify(talons: DriveTalons) {
	talons.talons.forEachIndexed { index, stormTalon ->
		val sensorCollection = stormTalon.sensorCollection
		SmartDashboard.putNumber("Talon $index (ID ${stormTalon.deviceID}) position", sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Talon $index (ID ${stormTalon.deviceID}) velocity", sensorCollection.quadratureVelocity.toDouble())
	}
}
