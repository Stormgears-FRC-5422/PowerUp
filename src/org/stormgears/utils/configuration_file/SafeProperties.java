package org.stormgears.utils.configuration_file;

import java.util.Properties;

public class SafeProperties extends Properties {
	/**
	 * @param key the expected key in the properties file to look up
	 * @return the associated value of key
	 * @throws IllegalStateException when expected key isn't present in expected properties file
	 */
	@Override
	public String getProperty(String key) {
		if (super.getProperty(key) == null) {
			throw new IllegalStateException("\nWARNING!\nWARNING!\nWARNING!\n" +
				"The key " + "\"" + key + "\" was accessed in code.\n" +
				"This key is not present in the expected properties file.\n" +
				"It's probably something important, so check with somebody and add the key to the file.");
		} else {
			return super.getProperty(key);
		}
	}
}
