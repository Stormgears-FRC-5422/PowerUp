package org.stormgears.utils.concurrency

import edu.wpi.first.wpilibj.command.Subsystem
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

abstract class TerminatableSubsystem : Subsystem(), WithCoroutines {
	companion object {
		private var jobList = ArrayList<Job>()

		fun terminateCurrentLongRunningOperations() {
			jobList.forEach {
				it.cancel()
			}

			jobList.removeAll { it.isCancelled }
		}
	}

	fun launchRegistered(jobName: String, context: CoroutineContext = globalContext,
						 start: CoroutineStart = CoroutineStart.DEFAULT,
						 parent: Job? = null,
						 block: suspend CoroutineScope.() -> Unit): Job {

		println("Starting coroutine job: $jobName")
		val job = launch(context, start, parent, block)

		jobList.add(job)

		return job
	}
}
