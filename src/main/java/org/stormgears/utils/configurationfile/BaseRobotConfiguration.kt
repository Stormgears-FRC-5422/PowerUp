package org.stormgears.utils.configurationfile

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.FileInputStream
import java.io.IOException

open class BaseRobotConfiguration(useBackupIfFileNotAvailable: Boolean = false) {
	private val configFile = File(PATH, NAME)
	private var properties: SafeProperties

	/**
	 *
	 */
	init {
		logger.trace("Reading configuration")

		properties = SafeProperties()

		var inputStream: FileInputStream? = null
		try {
			inputStream = FileInputStream(configFile)
			properties.load(inputStream)
		} catch (e: IOException) {
			if (useBackupIfFileNotAvailable) {
				logger.info("Using backup config file: {}", BACKUP_NAME)
				properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(BACKUP_NAME))
			} else {
				logger.fatal("Error reading {}. NO ROBOT PROPERTIES ARE AVAILABLE!", NAME)

				logger.throwing(Level.FATAL, e)
				throw e
			}
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

	protected fun getString(key: String) = properties.getProperty(key)
	protected fun getString(key: String, default: String) = properties.getProperty(key, default)

	protected fun getInt(key: String) = getString(key).toInt()
	protected fun getInt(key: String, default: Int) = getString(key, default.toString()).toInt()

	protected fun getDouble(key: String) = getString(key).toDouble()
	protected fun getDouble(key: String, default: Double) = getString(key, default.toString()).toDouble()

	protected fun getFloat(key: String) = getString(key).toFloat()
	protected fun getFloat(key: String, default: Float) = getString(key, default.toString()).toFloat()

	protected fun getBoolean(key: String) = getString(key).toBoolean()
	protected fun getBoolean(key: String, default: Boolean) = getString(key, default.toString()).toBoolean()

	companion object {
		private val logger = LogManager.getLogger(BaseRobotConfiguration::class.java)

		private const val PATH = "/home/lvuser"
		private const val NAME = "config.properties"
		private const val BACKUP_NAME = "config_backup.properties"
	}
}
