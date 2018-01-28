package org.stormgears.powerup.subsystems.field;

public class FieldElements {
	/*
	 * This is the FieldElements class
	 *
	 * The _only_ things in here should be inner classes describing major components of the PowerUp field
	 * Inside of each class, please add only public static final fields describing measurements
	 *
	 * Each of the following Inner Classes contains the dimensions of critical field landmarks and zones
	 */
//width = x, length = y
	//possibly wrong order means that length and width might be switched

	public static class Field {
		public static final double LENGTH = 648;
		public static final double WIDTH = 324;
		public static final double HEIGHT = 1;
	}

	public static class Switch {
		public static final double LENGTH = 56;
		public static final double WIDTH = 153.5;
		public static final double HEIGHT = 18.75;
	}

	public static class Scale {
		public static final double LENGTH = 48;
		public static final double WIDTH = 180;
		public static final double HEIGHT = 84;
	}

	public static class Exchange {
		public static final double LENGTH = 48;
		public static final double WIDTH = 36;//possibly wrong order
		public static final double HEIGHT = 0;

	}

	public static class Portal {
		public static final double LENGTH = 48;
		public static final double WIDTH = 155;//possibly wrong order
		public static final double HEIGHT = 0;
	}

	public static class Platform {
		public static final double LENGTH = 41;//our half
		public static final double WIDTH = 104;
		public static final double HEIGHT = 3.5;
	}

	public static class	PlatformZone {
		public static final double LENGTH = 133.5;
		public static final double WIDTH = 119.75;//possibly wrong order
		public static final double HEIGHT = 0;
	}

	public static class NullZone {
		public static final double LENGTH = 95.25;
		public static final double WIDTH = 72;//possibly wrong order
		public static final double HEIGHT = 0;
	}

	public static class PowerCubeZone {
		public static final double LENGTH = 45;
		public static final double WIDTH = 42;//possibly wrong order
		public static final double HEIGHT = 0;
	}

	public static class Rungs {
		public static final double LENGTH = 8.25;
		public static final double WIDTH = 13;
		public static final double HEIGHT = 84;
	}
}
