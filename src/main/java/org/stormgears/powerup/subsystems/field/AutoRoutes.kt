package org.stormgears.powerup.subsystems.field

import java.util.*

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes {
	var path2LeftScaleFromLeftSpot = ArrayList<Segment>()
	var path2RightScaleFromLeftSpot = ArrayList<Segment>()
	var path2LeftSwitchFromLeftSpot = ArrayList<Segment>()
	var path2RightSwitchFromLeftSpot = ArrayList<Segment>()
	var path2CrossLineFromLeftSpot = ArrayList<Segment>()

	var path2LeftScaleFromRightSpot = ArrayList<Segment>()
	var path2RightScaleFromRightSpot = ArrayList<Segment>()
	var path2LeftSwitchFromRightSpot = ArrayList<Segment>()
	var path2RightSwitchFromRightSpot = ArrayList<Segment>()
	var path2CrossLineFromRightSpot = ArrayList<Segment>()

	var path2LeftScaleFromCenterSpot = ArrayList<Segment>()
	var path2RightScaleFromCenterSpot = ArrayList<Segment>()
	var path2LeftSwitchFromCenterSpot = ArrayList<Segment>()
	var path2RightSwitchFromCenterSpot = ArrayList<Segment>()
	var path2CrossLineFromCenterSpot = ArrayList<Segment>()

	fun initialize() {
		//FROM LEFT STARTING SPOT
		path2LeftScaleFromLeftSpot.add(0, Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.SPOT_TO_SCALE_LEFT_PLATE))
		path2LeftScaleFromLeftSpot.add(1, Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE))

		path2RightScaleFromLeftSpot.add(0, Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT))
		path2RightScaleFromLeftSpot.add(1, Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT))
		path2RightScaleFromLeftSpot.add(2, Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE))
		path2RightScaleFromLeftSpot.add(3, Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE))

		path2LeftSwitchFromLeftSpot.add(0, Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE))
		path2LeftSwitchFromLeftSpot.add(1, Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE))

		path2RightSwitchFromLeftSpot.add(0, Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT))
		path2RightSwitchFromLeftSpot.add(1, Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT))
		path2RightSwitchFromLeftSpot.add(2, Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE))
		path2RightSwitchFromLeftSpot.add(3, Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE))

		path2CrossLineFromLeftSpot.add(0, Segment(FieldPositions.StartingSpots.LEFT.position, FieldPositions.LEFT_AUTO_LINE_SPOT))

		//FROM RIGHT STARTING SPOT
		path2RightScaleFromRightSpot.add(0, Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE))
		path2RightScaleFromRightSpot.add(1, Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE))

		path2LeftScaleFromRightSpot.add(0, Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT))
		path2LeftScaleFromRightSpot.add(1, Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT))
		path2LeftScaleFromRightSpot.add(2, Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_LEFT_PLATE))
		path2LeftScaleFromRightSpot.add(3, Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE))

		path2RightSwitchFromRightSpot.add(0, Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE))
		path2RightSwitchFromRightSpot.add(1, Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE))

		path2LeftSwitchFromRightSpot.add(0, Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT))
		path2LeftSwitchFromRightSpot.add(1, Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT))
		path2LeftSwitchFromRightSpot.add(2, Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE))
		path2LeftSwitchFromRightSpot.add(3, Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE))

		path2CrossLineFromRightSpot.add(0, Segment(FieldPositions.StartingSpots.RIGHT.position, FieldPositions.RIGHT_AUTO_LINE_SPOT))

		//FROM CENTER STARTING SPOT
		path2CrossLineFromCenterSpot.add(0, Segment(FieldPositions.StartingSpots.CENTER.position, FieldPositions.CENTER_AUTO_LINE_SPOT))
	}

}// Private constructor to prevent instantiation
