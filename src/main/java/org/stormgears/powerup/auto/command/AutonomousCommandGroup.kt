package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.utils.concurrency.TerminableSubsystem

object AutonomousCommandGroup : TerminableSubsystem() {
	fun run(selectedAlliance: FieldPositions.Alliance,
			selectedStartingSpot: FieldPositions.StartingSpots,
			selectedPlacementSpot: FieldPositions.PlacementSpot,
			selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
			selectedScalePlateAssignment: FieldPositions.LeftRight,
			selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight) {
		logger.trace("initiating autonomous command group")

		launch {
			AutoDriveMoveCommand.execute(selectedAlliance, selectedStartingSpot,
				selectedPlacementSpot, selectedOwnSwitchPlateAssignment,
				selectedScalePlateAssignment, selectedOpponentSwitchPlateAssignmentChooser)
		}

		//		addSequential(autoCloseGripperCommand);
		//		addSequential(autoLiftElevatorCommand);
		//		addSequential(autoShiftSideCommand);
	}

	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)
}
