package org.stormgears.powerup.subsystems.field

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes {
	object FromLeft : AutoRoute {
		override val pathToLeftScale = arrayOf(
			Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.SPOT_TO_SCALE_LEFT_PLATE)
		)

		override val strafeToLeftScale = arrayOf(
			Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE)
		)

		override val pathToRightScale = arrayOf(
			Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE)
		)

		override val strafeToRightScale = arrayOf(
			Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE)
		)

		override val pathToLeftSwitch = arrayOf(
			Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE),
			Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE)
		)

		override val pathToRightSwitch = arrayOf(
			Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE),
			Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE)
		)

		override val pathToCrossLine = arrayOf(
			Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.LEFT_AUTO_LINE_SPOT)
		)
	}

	object FromRight : AutoRoute {
		override val pathToLeftScale = arrayOf(
			Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_LEFT_PLATE)
		)

		override val strafeToLeftScale = arrayOf(
			Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE)
		)

		override val pathToRightScale = arrayOf(
			Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE),
			Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE)
		)

		override val strafeToRightScale = arrayOf(
			Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE)
		)

		override val pathToLeftSwitch = arrayOf(
			Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT),
			Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE),
			Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE)
		)

		override val pathToRightSwitch = arrayOf(
			Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE),
			Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE)
		)

		override val pathToCrossLine = arrayOf(
			Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.RIGHT_AUTO_LINE_SPOT)
		)
	}

	object FromCenter : AutoRoute {
		override val pathToLeftScale = emptyArray<Segment>()

		override val strafeToLeftScale = emptyArray<Segment>()

		override val pathToRightScale = emptyArray<Segment>()

		override val strafeToRightScale = emptyArray<Segment>()

		override val pathToLeftSwitch = emptyArray<Segment>()

		override val pathToRightSwitch = emptyArray<Segment>()

		override val pathToCrossLine = arrayOf(
			Segment(FieldPositions.StartingSpots.CENTER.position, FieldPositions.CENTER_AUTO_LINE_SPOT),
			Segment(FieldPositions.CENTER_AUTO_LINE_SPOT, FieldPositions.CENTER_AUTO_LINE_SPOT_2)
		)
	}
}
