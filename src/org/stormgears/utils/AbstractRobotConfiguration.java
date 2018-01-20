package org.stormgears.utils;

import java.io.*;
import java.util.Properties;

public abstract class AbstractRobotConfiguration {
	private static final String PATH = "/home/lvuser";
	private static final String NAME = "config.properties";
	private static final String COMMENTS =
			" If a property varies from robot to robot, add it here.\n" +
			"# If it is the same across every robot, make it a static field in the appropriate class.";
	File configFile = new File(PATH, NAME);

	public String robotName, robotWidth, robotLength, robotHeight;

	protected Properties properties;

	public AbstractRobotConfiguration() {
		properties = new Properties();
		FileInputStream inputStream = null;

		try {
			inputStream = new FileInputStream(configFile);

			if (!configFile.exists()) {
				configFile.createNewFile();
			}

			properties.load(inputStream);

			loadDefaults();
			loadExtras();
		} catch (IOException e) {
			System.err.println("THIS IS A BIG PROBLEM. Error reading " + NAME);
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		applyDefaults();
	}

	private void loadDefaults() {
		robotName = properties.getProperty("robotName");
		robotWidth = properties.getProperty("robotWidth");
		robotLength = properties.getProperty("robotLength");
		robotHeight = properties.getProperty("robotHeight");

	}

	protected abstract void loadExtras();

	private void applyDefaults() {
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

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(configFile);

			if (!configFile.exists()) {
				configFile.createNewFile();
			}

			properties.store(outputStream, COMMENTS);
			outputStream.flush();

		} catch (IOException e) {
			System.err.println("Error writing to " + NAME + ". Check file permissions.");
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * These methods will help with parsing Strings stored as values in the config file
	 */

	protected int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		}
	}

	protected double parseDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			return Double.MIN_VALUE;
		}
	}
}
