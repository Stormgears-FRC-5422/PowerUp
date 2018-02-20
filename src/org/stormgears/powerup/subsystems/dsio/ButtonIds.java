package org.stormgears.powerup.subsystems.dsio;

class ButtonIds {
	public static class Board {
		public static class Rev2017 {
			public static final int BIG_BLUE = 10;
			public static final int RED = 15;
			public static final int YELLOW = 14;
			public static final int GREEN = 13;
			public static final int SMALL_BLUE = 12;
			public static final int BLACK = 9;
			public static final int WHITE = 8;
			public static final int GREEN_SWITCH = 5;
			public static final int ORANGE_SWITCH = 4;
			public static final int RED_SWITCH = 3;
		}

		public static class Rev2018 {
			public static final int SCALE_0 = 8;			// log
			public static final int SCALE_1 = 5;			// log
			public static final int SCALE_2 = 7;			// log
			public static final int SCALE_3 = 10;			// log
			public static final int SCALE_4 = 6;			// log
			public static final int SWITCH_0 = 10;  		// msp
			public static final int SWITCH_1 = 1;			// log
			public static final int DROP = 14;				// log
			public static final int SIDE_GO_LEFT = 3;		// log
			public static final int SIDE_GO_RIGHT = 4;		// log
			public static final int INTAKE_GRAB = 0;		// log pov left
			public static final int INTAKE_WHEELS_OUT = 11;	// msp
			public static final int INTAKE_WHEELS_IN = 5;	// msp
			public static final int LIFT_OUT = 3;			// msp
			public static final int LIFT_IN = 12;			// msp
			public static final int GRIP_UPPER = 14;		// log
			public static final int GRIP_LOWER = 0;			// log pov up
			public static final int CLIMB_UPPER = 0;		// log pov right
			public static final int CLIMB_LOWER = 12;		// log
			public static final int OVERRIDE_SWITCH = 4;	// msp
			public static final int JOYSTICK_LEFT = 12;		// msp
			public static final int JOYSTICK_RIGHT = 13;	// msp
			public static final int JOYSTICK_UP = 15;		// msp
			public static final int JOYSTICK_DOWN = 14;		// msp
		}
	}

	public static class Joystick {
		public static final int TRIGGER = 1;
		public static final int THUMB = 2;

		// Other buttons are labeled with a number, no constants needed
	}
}
