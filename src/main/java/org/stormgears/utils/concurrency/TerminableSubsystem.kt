package org.stormgears.utils.concurrency

import edu.wpi.first.wpilibj.command.Subsystem
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.ActorScope
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import org.apache.logging.log4j.LogManager
import kotlin.coroutines.experimental.CoroutineContext

abstract class TerminableSubsystem : Subsystem(), WithCoroutines {
	companion object {
		private val logger = LogManager.getLogger(TerminableSubsystem::class.java)
	}

	private val parentJob = Job()
	private val subclassName = this.javaClass.canonicalName

	fun launch(
		jobName: String, context: CoroutineContext = globalContext,
		start: CoroutineStart = CoroutineStart.DEFAULT,
		parent: Job? = null,
		block: suspend CoroutineScope.() -> Unit
	): Job {
		logger.trace("Starting coroutine job {} in terminable subsystem {}", jobName, subclassName)
		return launch(context = context, start = start, parent = parent, block = block);
	}

	override fun launch(
		context: CoroutineContext,
		start: CoroutineStart,
		parent: Job?,
		block: suspend CoroutineScope.() -> Unit
	): Job {
		if (parent != null && parent != parentJob) {
			throw IllegalArgumentException("Cannot specify parent job in terminable subsystem")
		}

		return super.launch(context, start, parentJob, block)
	}

	override fun <T> async(context: CoroutineContext, start: CoroutineStart, parent: Job?, block: suspend CoroutineScope.() -> T): Deferred<T> {
		return super.async(context = context, start = start, parent = parentJob, block = block)
	}

	override fun <E> produce(context: CoroutineContext, capacity: Int, parent: Job?, block: suspend ProducerScope<E>.() -> Unit): ReceiveChannel<E> {
		return super.produce(context = context, capacity = capacity, parent = parentJob, block = block)
	}

	override fun <E> actor(context: CoroutineContext, capacity: Int, start: CoroutineStart, parent: Job?, block: suspend ActorScope<E>.() -> Unit): SendChannel<E> {
		return super.actor(context = context, capacity = capacity, start = start, parent = parentJob, block = block)
	}

	fun terminate() {
		parentJob.cancelChildren()
	}
}
