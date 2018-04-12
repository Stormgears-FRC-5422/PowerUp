package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.utils.concurrency.TerminableSubsystem

object AutonomousCommandGroup : TerminableSubsystem() {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	fun run(selectedAlliance: FieldPositions.Alliance,
			selectedStartingSpot: FieldPositions.StartingSpots,
			selectedPlacementSpot: FieldPositions.PlacementSpot,
			selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
			selectedScalePlateAssignment: FieldPositions.LeftRight,
			selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight,
			crossFieldForOppositeSwitch: Boolean) {
		logger.trace("initiating autonomous command group")

		launch {
			AutoDriveMoveCommand.execute(selectedAlliance,
				selectedStartingSpot,
				selectedPlacementSpot,
				selectedOwnSwitchPlateAssignment,
				selectedScalePlateAssignment,
				selectedOpponentSwitchPlateAssignmentChooser,
				crossFieldForOppositeSwitch
			)

			// Should move elevator upwards
//			Intake.moveIntakeToPositionSuspend(Intake.HORIZONTAL)
//			delay(500)
//			Elevator.zeroElevator()

			// FIXME: This code is temporary - for testing only

			// Square test
//			for (i in 0..5) {
//				Drive.moveStraight(60.0, 0.0)
//				Drive.moveStraight(60.0, Math.PI / 2)
//				Drive.moveStraight(60.0, Math.PI)
//				Drive.moveStraight(60.0, -Math.PI / 2)
//			}

//			Elevator.elevatorAutoMove(Elevator.SWITCH_POSITIONS[2])
//			Elevator.moveSideShiftToPositionSuspend(-1)
//			Gripper.openGripperSuspend()
		}
	}
}
