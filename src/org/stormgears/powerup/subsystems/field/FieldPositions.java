package org.stormgears.powerup.subsystems.field;

import org.jetbrains.annotations.Nullable;
import org.stormgears.powerup.subsystems.navigator.Position;

public class FieldPositions {
	/*
	 * This is the FieldPositions class
	 *
	 * Please keep the stuff in here related to positions on the field
	 * Also, use enums for stuff used in Choosers
	 */

	public enum StartingSpots {
		// TODO: Put correct coordinates here
		LEFT(new Position(0, 0)),
		CENTER(new Position(0, 0)),
		RIGHT(new Position(0, 0));

		private Position position;
		StartingSpots(Position position) {
			this.position = position;
		}

		public Position getPosition() {
			return position;
		}
	}

	public enum StartingDirections {
		// TODO: Put correct thetas
		LEFT(0),
		STRAIGHT(0),
		RIGHT(0);

		private double theta;
		private StartingDirections(double theta){
			this.theta = theta;
		}

		public double getTheta() {
			return theta;
		}
	}

	public enum PlacementSpot {
		//TODO:Change position values & Light Colors
		SCALE(new Position(0, 0)),
		SWITCH(OWN_SWITCH),
		JUST_CROSS(new Position(0, 0));

		private Position destination;

		private PlacementSpot(Position destination) {
			this.destination = destination;
		}
	}

	public enum Alliance {
		RED, BLUE
	}

	public enum LeftRight { L, R }

	@Nullable
	public static Position OWN_SWITCH_PLATE_ASSIGNMENT = null;
	@Nullable
	public static Position SCALE_PLATE_ASSIGNMENT = null;
	@Nullable
	public static Position OPPONENT_SWITCH_PLATE_ASSIGNMENT = null;

	// TODO: Update these to the right positions
	public static final Position OWN_SWITCH_LEFT_PLATE = new Position(0, 0);
	public static final Position OWN_SWITCH_RIGHT_PLATE = new Position(0, 0);
	public static final Position SCALE_LEFT_PLATE = new Position(0, 0);
	public static final Position SCALE_RIGHT_PLATE = new Position(0, 0);
	public static final Position OPPONENT_SWITCH_LEFT_PLATE = new Position(0, 0);
	public static final Position OPPONENT_SWITCH_RIGHT_PLATE = new Position(0, 0);


	// TODO: Put the right numbers in
	public static final Position OWN_CUBE_STACK = new Position(139, 99.1);
	public static final Position OWN_EXCHANGE = new Position(102, 0);
	public static final Position OWN_AUTO_LINE = new Position(0, 121.1);
	public static final Position OWN_SCALE_PLATFORM_ZONE = new Position(95.2, 211);
	public static final Position OWN_SCALE_PLATFORM_EDGE = new Position(97.4, 262.5);
	public static final Position OWN_SCALE_PLATFORM_ELEVATED = new Position(110, 275.1);
	public static final Position OWN_NULL_ZONE_LEFT = new Position(0, 289);
	public static final Position OWN_NULL_ZONE_RIGHT = new Position(228.8, 289);
	public static final Position OWN_SWITCH = new Position(95.7,141.2);
	public static final Position OWN_POWER_CUBE_1 = new Position(95.5,197.3);
	public static final Position OWN_POWER_CUBE_2 = new Position(113.6,197.3);
	public static final Position OWN_POWER_CUBE_3 = new Position(141.7,197.3);
	public static final Position OWN_POWER_CUBE_4 = new Position(169.8,197.3);
	public static final Position OWN_POWER_CUBE_5 = new Position(197.9,197.3);
	public static final Position OWN_POWER_CUBE_6 = new Position(226,197.3);


	public static final Position MIDLINE = new Position(0, 324);

	public static final Position OPPONENT_EXCHANGE = new Position(174, 613);
	public static final Position OPPONENT_AUTO_LINE = new Position(0, 527);
	public static final Position OPPONENT_SWITCH = new Position(85.2, 453.1);
	public static final Position OPPONENT_PLATFORM_ZONE = new Position(95.1, 333.6);
	public static final Position OPPONENT_PLATFORM_EDGE = new Position(95.1, 333.6);
	public static final Position OPPONENT_PLATFORM_ELEVATED = new Position(106.2, 331.3);
	public static final Position OPPONENT_PORTAL_LEFT = new Position(0, 614);
}
