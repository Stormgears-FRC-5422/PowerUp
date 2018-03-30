package org.stormgears.utils.concurrency

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.ActorScope
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.concurrency.Terminator.parentJob
import kotlin.coroutines.experimental.CoroutineContext

abstract class TerminableSubsystem : WithCoroutines {
	companion object {
		private val logger = LogManager.getLogger(TerminableSubsystem::class.java)
	}

	private val subclassName = this.javaClass.canonicalName
	private var localDisable = false
	var disabled: Boolean
		get() = Terminator.disabled || localDisable
		set(value) {
			localDisable = value
		}

	fun launch(
		jobName: String, context: CoroutineContext = globalContext,
		start: CoroutineStart = CoroutineStart.DEFAULT,
		parent: Job? = null,
		block: suspend CoroutineScope.() -> Unit
	): Job {
		logger.info("Starting coroutine job {} in terminable subsystem {}", jobName, subclassName)
		return launch(context = context, start = start, parent = parent, block = block)
	}

	/**
	 * Launches a coroutine, overriding the override switch
	 */
	fun launchOverride(jobName: String, context: CoroutineContext = globalContext, block: suspend CoroutineScope.() -> Unit): Job {
		logger.info("Starting OVERRIDING coroutine job {} in terminable subsystem {}", jobName, subclassName)
		return super.launch(context, CoroutineStart.DEFAULT, null, block)
	}

	override fun launch(
		context: CoroutineContext,
		start: CoroutineStart,
		parent: Job?,
		block: suspend CoroutineScope.() -> Unit
	): Job {
		if (disabled) {
			val job = Job()
			job.cancel()
			logger.warn("Cannot launch in {}; subsystem is disabled", subclassName)
			return job
		}

		if (parent != null && parent != parentJob) {
			throw IllegalArgumentException("Cannot specify parent job in terminable subsystem")
		}

		return super.launch(context, start, parentJob, block)
	}

	override fun <T> async(context: CoroutineContext, start: CoroutineStart, parent: Job?, block: suspend CoroutineScope.() -> T): Deferred<T> {
		if (disabled) {
			logger.error("Cannot run async in {}; subsystem is disabled", subclassName)
			throw IllegalStateException()
		}

		return super.async(context = context, start = start, parent = parentJob, block = block)
	}

	override fun <E> produce(context: CoroutineContext, capacity: Int, parent: Job?, block: suspend ProducerScope<E>.() -> Unit): ReceiveChannel<E> {
		if (disabled) {
			logger.error("Cannot run produce in {}; subsystem is disabled", subclassName)
			throw IllegalStateException()
		}

		return super.produce(context = context, capacity = capacity, parent = parentJob, block = block)
	}

	override fun <E> actor(context: CoroutineContext, capacity: Int, start: CoroutineStart, parent: Job?, block: suspend ActorScope<E>.() -> Unit): SendChannel<E> {
		if (disabled) {
			logger.error("Cannot run actor in {}; subsystem is disabled", subclassName)
			throw IllegalStateException()
		}

		return super.actor(context = context, capacity = capacity, start = start, parent = parentJob, block = block)
	}
}
