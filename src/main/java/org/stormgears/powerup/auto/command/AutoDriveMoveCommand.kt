package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.field.AutoRoutes
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.field.Segment

object AutoDriveMoveCommand {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	suspend fun execute(selectedAlliance: FieldPositions.Alliance,
						selectedStartingSpot: FieldPositions.StartingSpots,
						selectedPlacementSpot: FieldPositions.PlacementSpot,
						selectedOwnSwitchPlateAssignment: FieldPositions.LeftRight,
						selectedScalePlateAssignment: FieldPositions.LeftRight,
						selectedOpponentSwitchPlateAssignmentChooser: FieldPositions.LeftRight) {

		if (selectedStartingSpot == FieldPositions.StartingSpots.LEFT) {
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				if (selectedScalePlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftScaleFromLeftSpot)
				} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightScaleFromLeftSpot)
				}
			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftSwitchFromLeftSpot)
				} else { //if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightSwitchFromLeftSpot)
				}
			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromLeftSpot)
			}
		} else if (selectedStartingSpot == FieldPositions.StartingSpots.RIGHT) {
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				if (selectedScalePlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftScaleFromRightSpot)
				} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightScaleFromRightSpot)
				}
			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftSwitchFromRightSpot)
				} else { //if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightSwitchFromRightSpot)
				}
			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromRightSpot)
			}
		} else { //if (selectedStartSpot == FieldPositions.StartingSpots.CENTER)
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				logger.info("Moving from {} to {} on the {}",
					selectedStartingSpot, selectedPlacementSpot, selectedScalePlateAssignment)

			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				logger.info("Moving from {} to {} on the {}",
					selectedStartingSpot, selectedPlacementSpot, selectedOwnSwitchPlateAssignment)

			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromCenterSpot)
			}
		}
	}

	suspend fun move(path: ArrayList<Segment>) {
		val segmentIterator = path.iterator()
		while (segmentIterator.hasNext()) {
			val segment = segmentIterator.next()

			logger.info("Moving from ({}, {}) to ({}, {})",
				box(segment.startPos.x), box(segment.startPos.y),
				box(segment.endPos.x), box(segment.endPos.y))

			Robot.drive.moveToPos(segment.startPos, segment.endPos)

//			delay(3)
		}
	}

}
