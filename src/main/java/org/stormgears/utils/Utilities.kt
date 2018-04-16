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

fun <T> forceInit(klass: Class<T>): Class<T> {
	// https://stackoverflow.com/a/9391517/1591742

	try {
		Class.forName(klass.name, true, klass.classLoader)
	} catch (e: ClassNotFoundException) {
		throw AssertionError(e)  // Can't happen
	}

	return klass
}
