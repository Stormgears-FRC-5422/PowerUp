package org.stormgears.utils;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseStormgearsRobot extends IterativeRobot {
	private static final Logger logger = LogManager.getLogger(BaseStormgearsRobot.class);

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

	@Override
	public void startCompetition() {
		// stupid WPILib!
		try {
			super.startCompetition();
		} catch (Throwable e) {
			logger.fatal("Uncaught exception:");
			logger.throwing(Level.FATAL, e);
			throw e;
		}
	}
}
