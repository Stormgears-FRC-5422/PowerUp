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
	}
}
