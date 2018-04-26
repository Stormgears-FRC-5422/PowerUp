package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.utils.concurrency.TerminableSubsystem

object AutonomousCommandGroup : TerminableSubsystem() {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	fun run(startingSpot: FieldPositions.StartingSpots,
			placement: FieldPositions.PlacementSpot,
			switchPlate: FieldPositions.LeftRight,
			scalePlate: FieldPositions.LeftRight) {
		logger.trace("initiating autonomous command group")

		launch {
			AutoDriveMoveCommand.execute(startingSpot,
				placement,
				switchPlate,
				scaleSide = scalePlate
			)
		}
	}
}
