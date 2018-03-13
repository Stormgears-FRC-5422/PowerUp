package org.stormgears.utils

import org.apache.logging.log4j.LogManager

private val logger = LogManager.getLogger(object : Any() {}.javaClass.enclosingClass)

fun fixPermissions() {
	try {
		// fix-permissions.sh is a shell script owned by admin and has the setuid flag
		val pb = ProcessBuilder("/bin/sh", "/home/lvuser/fix-permissions.sh")
		pb.start().waitFor()
	} catch (e: Throwable) {
		logger.catching(e)
	}
}
