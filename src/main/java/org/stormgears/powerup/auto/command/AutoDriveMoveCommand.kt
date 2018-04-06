package org.stormgears.powerup.auto.command

import kotlinx.coroutines.experimental.Job
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.field.AutoRoutes
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.field.Segment
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.intake.Intake

// TODO: Cleanup
object AutoDriveMoveCommand {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	suspend fun execute(selectedAlliance: FieldPositions.Alliance,
						selectedStartingSpot: FieldPositions.StartingSpots,
						selectedPlacementSpot: FieldPositions.PlacementSpot,
						selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
						selectedScalePlateAssignment: FieldPositions.LeftRight,
						selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight,
						finesseJob: Job) {
		val autoRoute = when (selectedStartingSpot) {
			FieldPositions.StartingSpots.LEFT -> AutoRoutes.FromLeft
			FieldPositions.StartingSpots.CENTER -> AutoRoutes.FromCenter
			FieldPositions.StartingSpots.RIGHT -> AutoRoutes.FromRight
		}

		if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
			if (selectedScalePlateAssignment == FieldPositions.LeftRight.L && selectedStartingSpot == FieldPositions.StartingSpots.LEFT) {
				move(autoRoute.pathToLeftScale)
			} else if (selectedScalePlateAssignment == FieldPositions.LeftRight.R && selectedStartingSpot == FieldPositions.StartingSpots.RIGHT) { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
				move(autoRoute.pathToRightScale)
			} else {
				move(autoRoute.pathToCrossLine)
				return
			}

//			finesseJob.join()

			finesseJob.cancel()

			//USED TO SAY 40 instead of elevator.scale_positions[1]
			Elevator.moveElevatorToPosition(Elevator.SCALE_POSITIONS[1]).join()

			if (selectedScalePlateAssignment == FieldPositions.LeftRight.L) {
				move(autoRoute.strafeToLeftScale)
			} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
				move(autoRoute.strafeToRightScale)
			}

			Elevator.moveSideShiftToPosition(when (selectedScalePlateAssignment) {
				FieldPositions.LeftRight.L -> Elevator.RIGHT
				FieldPositions.LeftRight.R -> Elevator.LEFT
			}).join()

			Gripper.openGripper().join()
			Elevator.moveSideShiftToPosition(Elevator.CENTER).join()
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Elevator.zeroElevator()

		} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
			if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
				move(autoRoute.pathToLeftSwitch)
			} else { //if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.R)
				move(autoRoute.pathToRightSwitch)
			}

			finesseJob.join()

			Elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[2]).join()

			if (selectedStartingSpot == FieldPositions.StartingSpots.LEFT) {
				if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
					if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
						Elevator.moveSideShiftToPosition(Elevator.RIGHT).join()
					} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
						Elevator.moveSideShiftToPosition(Elevator.LEFT).join()
					}
				}
			}

			Gripper.openGripper().join()
		} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
			move(autoRoute.pathToCrossLine)
		}
	}

	suspend fun move(path: Array<Segment>) {
		logger.trace(path)
		for (segment in path) {
			logger.info("Moving from ({}, {}) to ({}, {})",
				box(segment.startPos.x), box(segment.startPos.y),
				box(segment.endPos.x), box(segment.endPos.y))

			Robot.drive!!.moveToPos(segment.startPos, segment.endPos)
		}
	}
}
