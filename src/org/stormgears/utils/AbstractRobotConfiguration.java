package org.stormgears.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractRobotConfiguration {
	private static final String PATH = "/home/lvuser";
	private static final String NAME = "config.txt";
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

			properties.store(outputStream, "");
			outputStream.flush();

		} catch (IOException e) {
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
}
