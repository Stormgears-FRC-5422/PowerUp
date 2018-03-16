package org.stormgears.utils.logging;

public class StormyLog {
	public static void init() {
		System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
//		System.setProperty("log4j2.asyncLoggerWaitStrategy", "Sleep"); // TODO: This is fast, but driverstation gets clogged up...

		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}
