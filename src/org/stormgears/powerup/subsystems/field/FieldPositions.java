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

	public enum PlateAssignment {
		A_RIGHTSCALE_RIGHTSWITCH, B_LEFTSCALE_LEFTSWITCH, C_LEFTSCALE_RIGHTSWITCH, D_RIGHTSCALE_LEFTSWITCH;
	}

	public static final Position OWN_CUBE_STACK = new Position(157, 218.1);
	public static final Position OWN_EXCHANGE = new Position(120, 119);
	public static final Position OWN_AUTO_LINE = new Position(0, 240.1);
	public static final Position OWN_SCALE_PLATFORM_ZONE = new Position(113.2, 329);
	public static final Position OWN_SCALE_PLATFORM_EDGE = new Position(115.4, 381.5);
	public static final Position OWN_SCALE_PLATFORM_ELEVATED = new Position(128, 394.1);
	public static final Position OWN_NULL_ZONE_LEFT = new Position(18, 408);
	public static final Position OWN_NULL_ZONE_RIGHT = new Position(246.8, 408);
	public static final Position OWN_SWITCH = new Position(103.7,260.2);
	public static final Position OWN_POWER_CUBE_1 = new Position(103.5,316.3);
	public static final Position OWN_POWER_CUBE_2 = new Position(131.6,316.3);
	public static final Position OWN_POWER_CUBE_3 = new Position(159.7,316.3);
	public static final Position OWN_POWER_CUBE_4 = new Position(187.8,316.3);
	public static final Position OWN_POWER_CUBE_5 = new Position(215.9,316.3);
	public static final Position OWN_POWER_CUBE_6 = new Position(244,316.3);


	public static final Position MIDLINE = new Position(0, 443);

	public static final Position OPP_EXCHANGE = new Position(192, 732);
	public static final Position OPP_AUTO_LINE = new Position(0, 646);
	public static final Position OPP_SWITCH = new Position(103.2, 572.1);
	public static final Position OPP_PLATFORM_ZONE = new Position(113.1, 452.6);
	public static final Position OPP_PLATFORM_EDGE = new Position(113.1, 452.6);
	public static final Position OPP_PLATFORM_ELEVATED = new Position(124.2, 450.3);
	public static final Position OPP_PORTAL_LEFT = new Position(0, 733);
}
