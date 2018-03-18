package org.stormgears.utils

import org.apache.logging.log4j.LogManager

private val logger = LogManager.getLogger(object : Any() {}.javaClass.enclosingClass)

/**
 * Invokes a shell script provided by the build system with the [setuid](https://en.wikipedia.org/wiki/Setuid) flag
 * set that fixes permissions of the config.properties file, in case it was uploaded as the admin user.
 */
fun fixPermissions() {
	try {
		val pb = ProcessBuilder("/bin/sh", "/home/lvuser/fix-permissions.sh")
		pb.start().waitFor()
	} catch (e: Throwable) {
		logger.catching(e)
	}
}
