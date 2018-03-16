package org.stormgears.utils

inline fun retry(tries: Int, operation: () -> Unit, exceptionHandler: (e: Throwable) -> Unit = {}) {
	for (i in 1..tries) {
		try {
			operation()
			return
		} catch (e: Throwable) {
			exceptionHandler(e)
			if (i == tries) {
				throw e
			}
		}
	}
}
