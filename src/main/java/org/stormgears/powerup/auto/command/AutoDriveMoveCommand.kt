package org.stormgears.powerup.auto.command

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.field.FieldPositions
import org.stormgears.powerup.subsystems.field.FieldPositions.LeftRight.L
import org.stormgears.powerup.subsystems.field.FieldPositions.LeftRight.R
import org.stormgears.powerup.subsystems.field.FieldPositions.PlacementSpot.*
import org.stormgears.powerup.subsystems.field.FieldPositions.StartingSpots.*
import org.stormgears.powerup.subsystems.field.Segment

// TODO: Cleanup
object AutoDriveMoveCommand {
	private val logger = LogManager.getLogger(AutonomousCommandGroup::class.java)

	suspend fun execute(selectedAlliance: FieldPositions.Alliance,
						startingSpot: FieldPositions.StartingSpots,
						placementSpot: FieldPositions.PlacementSpot,
						switchSide: FieldPositions.LeftRight,
						scaleSide: FieldPositions.LeftRight,
						opponentSwitchSide: FieldPositions.LeftRight,
						crossFieldForOppositeSwitch: Boolean) {
//		Robot.elevator?.zeroElevator()
		Robot.elevator?.moveElevatorToPosition(if (placementSpot == SWITCH) Elevator.SWITCH_POSITIONS[0] else 12)

		val autoRoute = when (startingSpot) {
			LEFT -> AutoRoutes.FromLeft
			CENTER -> AutoRoutes.FromCenter
			RIGHT -> AutoRoutes.FromRight
		}

		when (placementSpot) {
			SCALE -> when (scaleSide) {
				L -> autoRoute.leftScale()
				R -> autoRoute.rightScale()
			}
			SWITCH -> when (switchSide) {
				L -> autoRoute.leftSwitch()
				R -> autoRoute.rightSwitch()
			}
			JUST_CROSS -> autoRoute.crossBaseline()
			SCALE_SWITCH -> {
				if (scaleSide == L && startingSpot == LEFT) {
					autoRoute.leftScale()
				} else if (scaleSide == L && startingSpot == RIGHT) {
					autoRoute.rightScale()
				} else when (switchSide) {
					L -> autoRoute.leftSwitch()
					R -> autoRoute.rightSwitch()
				}
			}
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
