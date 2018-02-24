package org.stormgears.utils;

import edu.wpi.first.wpilibj.Notifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;

public class RegisteredNotifier extends Notifier {
	private static final Logger logger = LogManager.getLogger(RegisteredNotifier.class);
	private String name;

	public RegisteredNotifier(Runnable run) {
		this(run, "Unnamed");
	}

	public RegisteredNotifier(Runnable run, String name) {
		super(run);
		System.out.println("1");
		synchronized (Robot.notifierRegistry) {
			Robot.notifierRegistry.add(this);
		}

		this.name = name;

		if (run == null) {
			logger.warn("{} runnable IS NULL", name);
		} else {
			System.out.println("2");
			logger.warn("{} runnable IS NOT NULL", name);
		}
		System.out.println("3");
	}

	public String getName() {
		return name;
	}

}
