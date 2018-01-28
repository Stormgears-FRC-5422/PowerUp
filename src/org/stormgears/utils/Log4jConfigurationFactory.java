package org.stormgears.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import java.net.URI;

@Plugin(name = "Log4jConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(50)
public class Log4jConfigurationFactory extends ConfigurationFactory {
	private static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
		builder.setConfigurationName(name);
		builder.setStatusLevel(Level.WARN);
//		builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL).
//			addAttribute("level", Level.DEBUG));
		AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").
			addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
		appenderBuilder.add(builder.newLayout("PatternLayout").
			addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %class{36}.%M:%L - %msg%xEx%n"));
		builder.add(appenderBuilder);

//		builder.add(builder.newLogger("org.stormgears.utils.TestDrive", Level.TRACE).add(builder.newAppenderRef("Stdout")));
		builder.add(builder.newRootLogger(Level.TRACE).add(builder.newAppenderRef("Stdout")));

		return builder.build();
	}

	@Override
	public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
		return getConfiguration(loggerContext, source.toString(), null);
	}

	@Override
	public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
		ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
		return createConfiguration(name, builder);
	}

	@Override
	protected String[] getSupportedTypes() {
		return new String[] {"*"};
	}
}
