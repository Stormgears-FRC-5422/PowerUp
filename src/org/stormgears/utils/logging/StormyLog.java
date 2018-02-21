package org.stormgears.utils.logging;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StormyLog {
	public static void init() {
		System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
//		System.setProperty("log4j2.asyncLoggerWaitStrategy", "Sleep"); // TODO: This is fast, but driverstation gets clogged up...

		try {
			// TODO: Figure out how to load this stupid thing from a file
			ConfigurationSource source = new ConfigurationSource(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<Configuration status=\"WARN\">\n" +
				"\t<Appenders>\n" +
				"\t\t<Console name=\"Console\" target=\"SYSTEM_OUT\" direct=\"true\">\n" +
				"\t\t\t<PatternLayout pattern=\"%d{HH:mm:ss.SSS} [%t] %-5level %c{1.}.%M:%L - %msg%xEx%n\"/>\n" +
				"\t\t</Console>\n" +
				"\t\t<RollingFile name=\"File\" bufferedIO=\"true\" fileName=\"/home/lvuser/FRC_Trace.log\"\n" +
				"\t\t\t\t\t filePattern=\"/home/lvuser/FRC_Trace_%i.log.gz\">\n" +
				"\t\t\t<PatternLayout pattern=\"%d{HH:mm:ss.SSS} [%t] %-5level %class.%M:%L - %msg%xEx%n\"/>\n" +
				"\t\t\t<Policies>\n" +
				"\t\t\t\t<OnStartupTriggeringPolicy/>\n" +
				"\t\t\t</Policies>\n" +
				"\t\t\t<DefaultRolloverStrategy compressionLevel=\"1\"/>\n" +
				"\t\t</RollingFile>\n" +
				"\t</Appenders>\n" +
				"\t<Loggers>\n" +
				"\t\t<!--<Logger name=\"org.stormgears.utils.TestDrive\" level=\"trace\" />-->\n" +
				"\t\t<asyncRoot level=\"trace\" includeLocation=\"true\">\n" +
				"\t\t\t<AppenderRef ref=\"Console\" level=\"info\"/>\n" +
				"\t\t\t<AppenderRef ref=\"File\"/>\n" +
				"\t\t</asyncRoot>\n" +
				"\t</Loggers>\n" +
				"</Configuration>\n").getBytes()));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			e.printStackTrace();
		}


//		ConfigurationFactory.setConfigurationFactory(new Log4jConfigurationFactory());
	}
}
