package org.stormgears.utils.concurrency

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.cancelChildren

object Terminator {
	val parentJob = Job()

	private var _disabled = false
	var disabled: Boolean
		get() = _disabled
		set(value) {
			if (value) {
				parentJob.cancelChildren()
			}

			_disabled = value
		}
}
