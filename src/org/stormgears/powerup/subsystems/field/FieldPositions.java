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
		private StartingSpots(Position position) {
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

	public static final Position OWN_SWITCH = new Position(0, 0);
	public static final Position OWN_PORTAL = new Position(0, 0);
	public static final Position OWN_CUBE_STACK = new Position(0, 0);
	public static final Position OWN_PLATFORM_ZONE = new Position(0, 0);
	public static final Position AUTO_LINE = new Position(0, 0);
}
