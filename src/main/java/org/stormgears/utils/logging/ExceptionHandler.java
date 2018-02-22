package org.stormgears.utils.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
	private static final Logger logger = LogManager.getLogger(ExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.fatal("Uncaught exception on thread {}", t.getName());
		logger.throwing(Level.FATAL, e);
	}
}
