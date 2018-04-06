package org.stormgears.powerup.auto.command

interface AutoRoute {
	suspend fun leftScale()
	suspend fun rightScale()

	suspend fun leftSwitch()
	suspend fun rightSwitch()

	suspend fun crossBaseline()
}

