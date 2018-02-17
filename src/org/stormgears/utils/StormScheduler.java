package org.stormgears.utils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class StormScheduler {
	private static StormScheduler instance;

	public static StormScheduler getInstance() {
		return instance;
	}

	private StormScheduler() {
		Scheduler.getInstance().removeAll();
	}

	public static void init() {
		instance = new StormScheduler();
	}

	public void queue(Command command) {
		Scheduler.getInstance().add(command);
	}

	public void queue(Runnable runnable) {
		Scheduler.getInstance().add(new Command() {
			@Override
			protected boolean isFinished() {
				return true;
			}
		});
	}

	public void run() {
		Scheduler.getInstance().run();
	}
}
