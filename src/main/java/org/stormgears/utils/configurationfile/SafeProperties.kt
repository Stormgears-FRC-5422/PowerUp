package org.stormgears.utils.configurationfile

import org.apache.logging.log4j.LogManager
import java.util.*

class SafeProperties : Properties() {

	/**
	 * @param key the expected key in the properties file to look up
	 * @return the associated value of key
	 * @throws IllegalStateException when expected key isn't present in expected properties file
	 */
	override fun getProperty(key: String): String {
		val property = super.getProperty(key)
		if (property == null) {
			throw IllegalStateException("\nWARNING!\nWARNING!\nWARNING!\n" +
				"The key " + "\"" + key + "\" was accessed in code.\n" +
				"This key is not present in the expected properties file.\n" +
				"It's probably something important, so check with somebody and add the key to the file.")
		} else {
			logger.trace("Read property key={} value={}", key, property)
			return property
		}
	}

	/**
	 * @param key the expected key in the properties file to look up
	 * @return the associated value of key
	 * @throws IllegalStateException when expected key isn't present in expected properties file
	 */
	override fun getProperty(key: String, default: String): String {
		try {
			return getProperty(key)
		} catch (e: IllegalStateException) {

		}
//		logger.trace("Read property key={} value={} default={}", key, property, default)
		return default;
	}

	companion object {
		private val logger = LogManager.getLogger(SafeProperties::class.java)
	}
}
