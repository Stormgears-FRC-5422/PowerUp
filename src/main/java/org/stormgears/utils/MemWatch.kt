package org.stormgears.utils

import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.utils.concurrency.WithCoroutines

object MemWatch : WithCoroutines {
	private val logger = LogManager.getLogger(MemWatch::class.java)

	fun start() {
		launch {
			while (true) {
				val runtime = Runtime.getRuntime()
				logger.debug(
					"Memory stats: Free = {}; Total = {}; Max = {}",
					box(runtime.freeMemory()),
					box(runtime.totalMemory()),
					box(runtime.maxMemory())
				)

				delay(1000)
			}
		}
	}
}
