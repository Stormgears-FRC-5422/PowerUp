package org.stormgears.utils;

import edu.wpi.first.wpilibj.IterativeRobot;

public abstract class BaseStormgearsRobot extends IterativeRobot {
	private int i = 0;

	public abstract void afterAutonomousInit();

	public abstract void afterTeleopInit();

	@Override
	public void autonomousPeriodic() {
		if (i == 0) {
			afterAutonomousInit();
			i++;
		}
	}

	@Override
	public void teleopPeriodic() {
		if (i == 0) {
			afterTeleopInit();
			i++;
		}
	}

	@Override
	public void disabledInit() {
		i = 0;
	}
}
