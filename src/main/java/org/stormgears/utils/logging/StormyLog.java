package org.stormgears.utils.logging;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StormyLog {
	public static void init() {
		System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
//		System.setProperty("log4j2.asyncLoggerWaitStrategy", "Sleep"); // TODO: This is fast, but driverstation gets clogged up...

		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}
