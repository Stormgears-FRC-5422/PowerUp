package org.stormgears.powerup.subsystems.field;

import java.util.ArrayList;

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
public class AutoRoutes {
	public static ArrayList<Segment> path2LeftScaleFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightScaleFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2LeftSwitchFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightSwitchFromLeftSpot = new ArrayList<>();
	public static ArrayList<Segment> path2CrossLineFromLeftSpot = new ArrayList<>();

	public static ArrayList<Segment> path2LeftScaleFromRightSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightScaleFromRightSpot = new ArrayList<>();
	public static ArrayList<Segment> path2LeftSwitchFromRightSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightSwitchFromRightSpot = new ArrayList<>();
	public static ArrayList<Segment> path2CrossLineFromRightSpot = new ArrayList<>();

	public static ArrayList<Segment> path2LeftScaleFromCenterSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightScaleFromCenterSpot = new ArrayList<>();
	public static ArrayList<Segment> path2LeftSwitchFromCenterSpot = new ArrayList<>();
	public static ArrayList<Segment> path2RightSwitchFromCenterSpot = new ArrayList<>();
	public static ArrayList<Segment> path2CrossLineFromCenterSpot = new ArrayList<>();

	// Private constructor to prevent instantiation
	private AutoRoutes() {

	}

	public static void initialize() {
		//FROM LEFT STARTING SPOT
		path2LeftScaleFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.SPOT_TO_SCALE_LEFT_PLATE));
		path2LeftScaleFromLeftSpot.add(1, new Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE));

		path2RightScaleFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2RightScaleFromLeftSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2RightScaleFromLeftSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE));
		path2RightScaleFromLeftSpot.add(3, new Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE));

		path2LeftSwitchFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE));
		path2LeftSwitchFromLeftSpot.add(1, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE));

		path2RightSwitchFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2RightSwitchFromLeftSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2RightSwitchFromLeftSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE));
		path2RightSwitchFromLeftSpot.add(3, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE));

		path2CrossLineFromLeftSpot.add(0, new Segment(FieldPositions.StartingSpots.LEFT.getPosition(), FieldPositions.LEFT_AUTO_LINE_SPOT));

		//FROM RIGHT STARTING SPOT
		path2RightScaleFromRightSpot.add(0, new Segment(FieldPositions.StartingSpots.RIGHT.getPosition(), FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE));
		path2RightScaleFromRightSpot.add(1, new Segment(FieldPositions.SPOT_TO_SCALE_RIGHT_PLATE, FieldPositions.SCALE_RIGHT_PLATE));

		path2LeftScaleFromRightSpot.add(0, new Segment(FieldPositions.StartingSpots.RIGHT.getPosition(), FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2LeftScaleFromRightSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2LeftScaleFromRightSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_SCALE_LEFT_PLATE));
		path2LeftScaleFromRightSpot.add(3, new Segment(FieldPositions.SPOT_TO_SCALE_LEFT_PLATE, FieldPositions.SCALE_LEFT_PLATE));

		path2RightSwitchFromRightSpot.add(0, new Segment(FieldPositions.StartingSpots.RIGHT.getPosition(), FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE));
		path2RightSwitchFromRightSpot.add(1, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_RIGHT_PLATE, FieldPositions.OWN_SWITCH_RIGHT_PLATE));

		path2LeftSwitchFromRightSpot.add(0, new Segment(FieldPositions.StartingSpots.RIGHT.getPosition(), FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT));
		path2LeftSwitchFromRightSpot.add(1, new Segment(FieldPositions.MIDFIELD_SCALE_RIGHT_TRANSITION_SPOT, FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT));
		path2LeftSwitchFromRightSpot.add(2, new Segment(FieldPositions.MIDFIELD_SCALE_LEFT_TRANSITION_SPOT, FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE));
		path2LeftSwitchFromRightSpot.add(3, new Segment(FieldPositions.SPOT_TO_OWN_SWITCH_LEFT_PLATE, FieldPositions.OWN_SWITCH_LEFT_PLATE));

		path2CrossLineFromRightSpot.add(0, new Segment(FieldPositions.StartingSpots.RIGHT.getPosition(), FieldPositions.RIGHT_AUTO_LINE_SPOT));

		//FROM CENTER STARTING SPOT
		path2CrossLineFromCenterSpot.add(0, new Segment(FieldPositions.StartingSpots.CENTER.getPosition(), FieldPositions.CENTER_AUTO_LINE_SPOT));
	}

}
