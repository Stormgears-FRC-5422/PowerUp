package org.stormgears.utils.concurrency

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.ActorScope
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlin.coroutines.experimental.CoroutineContext

val globalContext = newSingleThreadContext("Coroutine Thread")

interface WithCoroutines {
	fun launch(context: CoroutineContext = globalContext,
			   start: CoroutineStart = CoroutineStart.DEFAULT,
			   parent: Job? = null,
			   block: suspend CoroutineScope.() -> Unit
	) = kotlinx.coroutines.experimental.launch(context = context, start = start, parent = parent, block = block)

	fun <T> async(
		context: CoroutineContext = globalContext,
		start: CoroutineStart = CoroutineStart.DEFAULT,
		parent: Job? = null,
		block: suspend CoroutineScope.() -> T
	) = kotlinx.coroutines.experimental.async(context = context, start = start, parent = parent, block = block)

	fun <E> produce(
		context: CoroutineContext = globalContext,
		capacity: Int = 0,
		parent: Job? = null,
		block: suspend ProducerScope<E>.() -> Unit
	) = kotlinx.coroutines.experimental.channels.produce(
		context = context,
		capacity = capacity,
		parent = parent,
		block = block
	)

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
