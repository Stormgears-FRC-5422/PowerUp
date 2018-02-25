package org.stormgears.powerup.subsystems.field;

import java.util.ArrayList;

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
public class AutoRoutes {
	public static ArrayList<Segment> path2LeftScaleFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RighttScaleFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2LeftSwitchFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightSwitchFromLeftSpot = new ArrayList<>();

	// Private constructor to prevent instantiation
	private AutoRoutes() {

	}

	public static void initialize() {
		path2LeftScaleFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.SPOT_TO_SCALE_LEFT_PLATE));
		path2LeftScaleFromLeftSpot.add(1, new Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE));

		path2RighttScaleFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2RighttScaleFromLeftSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2RighttScaleFromLeftSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE));
		path2RighttScaleFromLeftSpot.add(3, new Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE));

		path2LeftSwitchFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE));
		path2LeftSwitchFromLeftSpot.add(1, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE));

		path2RightSwitchFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2RightSwitchFromLeftSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2RightSwitchFromLeftSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE));
		path2RightSwitchFromLeftSpot.add(3, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE));
	}

}
