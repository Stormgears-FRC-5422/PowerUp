package org.stormgears.utils.concurrency

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.ActorScope
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlin.coroutines.experimental.CoroutineContext

/**
 * The global, single-threaded context in which coroutines are run. This is to prevent threading issues by not
 * using more than one thread.
 */
val globalContext = newSingleThreadContext("Coroutine Thread")

/**
 * Classes that utilize coroutines should implement this interface to make coroutines launch in a single-threaded
 * context.
 */
interface WithCoroutines {
	/**
	 * Launches a new coroutine in the coroutine thread.
	 *
	 * @see kotlinx.coroutines.experimental.launch
	 *
	 * @param context context of the coroutine. The default value is [globalContext].
	 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
	 * @param parent explicitly specifies the parent job, overrides job from the [context] (if any).
	 * @param block the coroutine code.
	 */
	fun launch(context: CoroutineContext = globalContext,
			   start: CoroutineStart = CoroutineStart.DEFAULT,
			   parent: Job? = null,
			   block: suspend CoroutineScope.() -> Unit
	) = kotlinx.coroutines.experimental.launch(context = context, start = start, parent = parent, block = block)

	/**
	 * Creates new coroutine and returns its future result as an implementation of [Deferred], within the coroutine
	 * thread.
	 *
	 * @see kotlinx.coroutines.experimental.async
	 *
	 * @param context context of the coroutine. The default value is [globalContext].
	 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
	 * @param parent explicitly specifies the parent job, overrides job from the [context] (if any).*
	 * @param block the coroutine code.
	 */
	fun <T> async(
		context: CoroutineContext = globalContext,
		start: CoroutineStart = CoroutineStart.DEFAULT,
		parent: Job? = null,
		block: suspend CoroutineScope.() -> T
	) = kotlinx.coroutines.experimental.async(context = context, start = start, parent = parent, block = block)

	/**
	 * Launches new coroutine to produce a stream of values by sending them to a channel
	 * and returns a reference to the coroutine as a [ReceiveChannel], within the coroutine thread.
	 *
	 * @see kotlinx.coroutines.experimental.channels.produce
	 *
	 * @param context context of the coroutine. The default value is [globalContext].
	 * @param capacity capacity of the channel's buffer (no buffer by default).
	 * @param parent explicitly specifies the parent job, overrides job from the [context] (if any).*
	 * @param block the coroutine code.
	 */
	fun <E> produce(
		context: CoroutineContext = globalContext,
		capacity: Int = 0,
		parent: Job? = null,
		block: suspend ProducerScope<E>.() -> Unit
	): ReceiveChannel<E>? = kotlinx.coroutines.experimental.channels.produce(
		context = context,
		capacity = capacity,
		parent = parent,
		block = block
	)

	/**
	 * Launches new coroutine that is receiving messages from its mailbox channel
	 * and returns a reference to its mailbox channel as a [SendChannel], within the coroutine thread.
	 *
	 * @see kotlinx.coroutines.experimental.channels.actor
	 *
	 * @param context context of the coroutine. The default value is [globalContext].
	 * @param capacity capacity of the channel's buffer (no buffer by default).
	 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
	 * @param parent explicitly specifies the parent job, overrides job from the [context] (if any).*
	 * @param block the coroutine code.
	 */
	fun <E> actor(
		context: CoroutineContext = globalContext,
		capacity: Int = 0,
		start: CoroutineStart = CoroutineStart.DEFAULT,
		parent: Job? = null,
		block: suspend ActorScope<E>.() -> Unit
	) = kotlinx.coroutines.experimental.channels.actor(
		context = context,
		capacity = capacity,
		start = start,
		parent = parent,
		block = block
	)
}
