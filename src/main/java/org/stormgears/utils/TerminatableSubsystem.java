package org.stormgears.utils;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class TerminatableSubsystem extends Subsystem {
	private static boolean shouldTerminate = false;

	public static void terminateCurrentLongRunningOperations() {
		shouldTerminate = true;
	}

	public static void allowLongRunningOperations() {
		shouldTerminate = false;
	}

	public boolean isAllowed() {
		if (shouldTerminate) {
			System.out.println("WARNING! Long running operations are currently not allowed.");
			System.out.println("Please turn off manual override switch.");
		}
		return !shouldTerminate;
	}
}
