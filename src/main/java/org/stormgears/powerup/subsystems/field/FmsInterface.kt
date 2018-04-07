package org.stormgears.powerup.subsystems.field

import edu.wpi.first.wpilibj.DriverStation
import org.apache.logging.log4j.LogManager

private val logger = LogManager.getLogger()

fun fmsInterfaceWarmup() {}

fun parseFmsData() {
	logger.info("Polling for FMS data")

	var data: String? = null
	while (data == null) {
		logger.trace("trying...")
		data = DriverStation.getInstance().gameSpecificMessage
	}

	logger.info("FMS Data: {}", data)

	// Own switch
	when (data[0]) {
		'L' -> FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT = FieldPositions.LeftRight.L
		'R' -> FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT = FieldPositions.LeftRight.R
	}

	// Own switch
	when (data[1]) {
		'L' -> FieldPositions.SCALE_PLATE_ASSIGNMENT = FieldPositions.LeftRight.L
		'R' -> FieldPositions.SCALE_PLATE_ASSIGNMENT = FieldPositions.LeftRight.R
	}

	// Own switch
	when (data[2]) {
		'L' -> FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT = FieldPositions.LeftRight.L
		'R' -> FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT = FieldPositions.LeftRight.R
	}
}
