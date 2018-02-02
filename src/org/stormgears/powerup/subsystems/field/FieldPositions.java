package org.stormgears.powerup.subsystems.field;

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
		RED, BLUE;
	}

	public static final Position OWN_EXCHANGE = new Position(0, 0);
	public static final Position OWN_CUBE_STACK = new Position(0, 0);
	public static final Position OWN_AUTO_LINE = new Position(0, 0);
	public static final Position OWN_SWITCH = new Position(0, 0);
	public static final Position OWN_PLATFORM_ZONE = new Position(0, 0);
	public static final Position OWN_PLATFORM_EDGE = new Position(0, 0);
	public static final Position OWN_PLATFORM_ELEVATED = new Position(0, 0);
	public static final Position OWN_NULL_ZONE_LEFT = new Position(0, 0);
	public static final Position OWN_NULL_ZONE_RIGHT = new Position(0, 0);

	public static final Position MIDLINE = new Position(0, 0);

	public static final Position OPP_EXCHANGE = new Position(0, 0);
	public static final Position OPP_AUTO_LINE = new Position(0, 0);
	public static final Position OPP_SWITCH = new Position(0, 0);
	public static final Position OPP_PLATFORM_ZONE = new Position(0, 0);
	public static final Position OPP_PLATFORM_EDGE = new Position(0, 0);
	public static final Position OPP_PLATFORM_ELEVATED = new Position(0, 0);
	public static final Position OPP_PORTAL_LEFT = new Position(0, 0);
}