package org.stormgears.powerup.subsystems.field;

import org.stormgears.powerup.subsystems.navigator.Position;

public class Segment {
	private Position startPos;
	private Position endPos;
	public Position getStartPos() {
		return startPos;
	}
	public Position getEndPos() {
		return endPos;
	}

	public Segment(Position startPos, Position endPos) {
		this. startPos = startPos;
		this.endPos = endPos;
	}
}
