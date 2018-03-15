package org.stormgears.powerup.auto.command

import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.subsystems.elevator_climber.Elevator
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.intake.Intake
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
			AutoDriveMoveCommand.execute(selectedAlliance,
				selectedStartingSpot,
				selectedPlacementSpot,
				selectedOwnSwitchPlateAssignment,
				selectedScalePlateAssignment,
				selectedOpponentSwitchPlateAssignmentChooser)

			Intake.moveIntakeToPositionSuspend(Intake.HORIZONTAL)
			delay(500)
			Elevator.zeroElevator()

			// FIXME: This code is temporary - for testing only

//			Robot.drive.moveStraight(60.0, 0.0)
//			Drive.moveStraight(60.0, 0.0)

//			Elevator.elevatorAutoMove(Elevator.SWITCH_POSITIONS[2])
//			Elevator.moveSideShift(-1)
//			Gripper.openGripperSuspend()
		}
	}
}
