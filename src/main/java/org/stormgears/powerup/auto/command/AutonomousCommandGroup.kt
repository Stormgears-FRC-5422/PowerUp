package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevator_climber.Elevator
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.navigator.Position
import org.stormgears.utils.concurrency.TerminableSubsystem

object AutonomousCommandGroup : TerminableSubsystem() {
	fun run(selectedAlliance: FieldPositions.Alliance,
			selectedStartingSpot: FieldPositions.StartingSpots,
			selectedPlacementSpot: FieldPositions.PlacementSpot,
			selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
			selectedScalePlateAssignment: FieldPositions.LeftRight,
			selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight) {
		logger.trace("initiating autonomous command group")

		// FIXME: This code is temporary - for testing only

		launch {
			Gripper.closeGripperSuspend()

			AutoDriveMoveCommand.execute(selectedAlliance, selectedStartingSpot,
				selectedPlacementSpot, selectedOwnSwitchPlateAssignment,
				selectedScalePlateAssignment, selectedOpponentSwitchPlateAssignmentChooser)

			Robot.drive.moveToPos(Position(0.0, 0.0), Position(-4.0, 0.0))

			Elevator.elevatorAutoMove(Elevator.SWITCH_POSITIONS[2])
			Elevator.moveSideShift(-1)
			Gripper.openGripperSuspend()
		}

		//		addSequential(autoCloseGripperCommand);
		//		addSequential(autoLiftElevatorCommand);
		//		addSequential(autoShiftSideCommand);
	}

	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)
}
