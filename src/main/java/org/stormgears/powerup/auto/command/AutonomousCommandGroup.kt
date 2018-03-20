package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.navigator.Drive
import org.stormgears.utils.concurrency.TerminableSubsystem

object AutonomousCommandGroup : TerminableSubsystem() {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	fun run(selectedAlliance: FieldPositions.Alliance,
			selectedStartingSpot: FieldPositions.StartingSpots,
			selectedPlacementSpot: FieldPositions.PlacementSpot,
			selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
			selectedScalePlateAssignment: FieldPositions.LeftRight,
			selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight) {
		logger.trace("initiating autonomous command group")

		launch {
			async {
				Gripper.openGripper()
			}

//			AutoDriveMoveCommand.execute(selectedAlliance,
//				selectedStartingSpot,
//				selectedPlacementSpot,
//				selectedOwnSwitchPlateAssignment,
//				selectedScalePlateAssignment,
//				selectedOpponentSwitchPlateAssignmentChooser)

			// Should move elevator upwards
//			Elevator.elevatorAutoMove(5)
//			Intake.moveIntakeToPositionSuspend(Intake.HORIZONTAL)
//			delay(500)
//			Elevator.zeroElevator()

			// FIXME: This code is temporary - for testing only

			Drive.moveStraight(215.0, 0.0)

//			Elevator.elevatorAutoMove(Elevator.SWITCH_POSITIONS[2])
//			Elevator.moveSideShiftToPositionSuspend(-1)
//			Gripper.openGripperSuspend()
		}
	}
}
