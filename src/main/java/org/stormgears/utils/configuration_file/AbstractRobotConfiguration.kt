package org.stormgears.utils.configuration_file

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.FileInputStream
import java.io.IOException

abstract class AbstractRobotConfiguration {
	private val configFile = File(PATH, NAME)
	private var properties: SafeProperties;

	init {
		logger.trace("Reading configuration")

		properties = SafeProperties()

		var inputStream: FileInputStream? = null
		try {
			inputStream = FileInputStream(configFile);
			properties.load(inputStream);
		} catch (e: IOException) {
			logger.fatal("Error reading/writing {}. NO ROBOT PROPERTIES ARE AVAILABLE! Check file permissions.", NAME)
			logger.throwing(Level.FATAL, e)

			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close()
				} catch (e: IOException) {
					logger.catching(Level.ERROR, e)
				}
			}
		}
	}

	val robotName = getString("robotName")

	protected fun getString(key: String): String = properties.getProperty(key)

	protected fun getInt(key: String): Int = getString(key).toInt()

	protected fun getDouble(key: String): Double = getString(key).toDouble()

	protected fun getFloat(key: String): Float = getString(key).toFloat()

	protected fun getBoolean(key: String): Boolean = getString(key).toBoolean()

	companion object {
		private val logger = LogManager.getLogger(AbstractRobotConfiguration::class.java)

		private const val PATH = "/home/lvuser"
		private const val NAME = "config.properties"
		private const val COMMENTS = """ If a property varies from robot to robot, add it here.
""" + "# If it is the same across every robot, make it a static field in the appropriate class."
	}
}
