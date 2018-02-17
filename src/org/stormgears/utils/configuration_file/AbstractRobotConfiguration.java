package org.stormgears.utils.configuration_file;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractRobotConfiguration {
	private static final Logger logger = LogManager.getLogger(AbstractRobotConfiguration.class);

	private static final String PATH = "/home/lvuser";
	private static final String NAME = "config.properties";
	private static final String COMMENTS =
		" If a property varies from robot to robot, add it here.\n" +
			"# If it is the same across every robot, make it a static field in the appropriate class.";

	// Default properties variables
	public String robotName, robotWidth, robotLength, robotHeight;

	private File configFile = new File(PATH, NAME);
	protected SafeProperties properties;

	public AbstractRobotConfiguration() {
		properties = new SafeProperties();

		FileInputStream inputStream = null;
		try {
			if (!configFile.createNewFile()) {    // This block will only run if the file exists already
				inputStream = new FileInputStream(configFile);
				properties.load(inputStream);
			}

			applyDefaultsIfNotPresent();

			loadDefaults();
			loadExtras();
		} catch (IOException e) {
			logger.fatal("Error reading/writing {}. NO ROBOT PROPERTIES ARE AVAILABLE! Check file permissions.", NAME);
			logger.catching(Level.ERROR, e);

			throw new IllegalStateException(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.catching(Level.ERROR, e);
				}
			}
		}
	}

	private void loadDefaults() {
		robotName = properties.getProperty("robotName");
		robotWidth = properties.getProperty("robotWidth");
		robotLength = properties.getProperty("robotLength");
		robotHeight = properties.getProperty("robotHeight");

	}

	protected abstract void loadExtras();

	private void applyDefaultsIfNotPresent() throws IOException {
		/*
		 * Please add default properties here (applicable to EVERY season)
		 */
		properties.putIfAbsent("robotName", "tej");
		properties.putIfAbsent("robotWidth", "24");
		properties.putIfAbsent("robotLength", "24");
		properties.putIfAbsent("robotHeight", "24");
		/*
		 * End default properties
		 */

		FileOutputStream outputStream = new FileOutputStream(configFile);

		properties.store(outputStream, COMMENTS);
		outputStream.flush();
		outputStream.close();
	}

	/*
	 * These methods will help with parsing Strings stored as values in the config file
	 */

	/**
	 * @param s The String to parse
	 * @return The int value of s, or Integer.MIN_VALUE if s cannot be successfully parsed
	 */
	protected int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			logger.error("{} was just read from {}. This value was expected to be an integer, but it wasn't.", s, NAME);
			logger.throwing(e);
			throw e;
		}
	}

	/**
	 * @param s The String to parse
	 * @return The double value of s, or Double.MIN_VALUE if s cannot be successfully parsed
	 */
	protected double parseDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			logger.error("{} was just read from {}. This value was expected to be a double, but it wasn't.", s, NAME);
			logger.throwing(e);
			throw e;
		}
	}
}
