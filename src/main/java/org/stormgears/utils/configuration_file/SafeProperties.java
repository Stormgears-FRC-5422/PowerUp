package org.stormgears.utils.configuration_file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class SafeProperties extends Properties {
	private static final Logger logger = LogManager.getLogger(SafeProperties.class);

	/**
	 * @param key the expected key in the properties file to look up
	 * @return the associated value of key
	 * @throws IllegalStateException when expected key isn't present in expected properties file
	 */
	@Override
	public String getProperty(String key) {
		String property = super.getProperty(key);
		if (property == null) {
			throw new IllegalStateException("\nWARNING!\nWARNING!\nWARNING!\n" +
				"The key " + "\"" + key + "\" was accessed in code.\n" +
				"This key is not present in the expected properties file.\n" +
				"It's probably something important, so check with somebody and add the key to the file.");
		} else {
			logger.trace("Read property key={} value={}", key, property);
			return property;
		}
	}
}
