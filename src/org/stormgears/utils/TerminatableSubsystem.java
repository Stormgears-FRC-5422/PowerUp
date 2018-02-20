package org.stormgears.utils;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class TerminatableSubsystem extends Subsystem {
	protected boolean shouldTerminate = false;

	public void terminateCurrentLongRunningOperation() {
		shouldTerminate = true;
	}
}
