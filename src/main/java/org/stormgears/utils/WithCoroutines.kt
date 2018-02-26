package org.stormgears.utils

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlin.coroutines.experimental.CoroutineContext
import kotlinx.coroutines.experimental.launch as klaunch

val globalContext = newSingleThreadContext("CoroutineThread");

interface WithCoroutines

fun launch(context: CoroutineContext = globalContext,
		   start: CoroutineStart = CoroutineStart.DEFAULT,
		   parent: Job? = null,
		   block: suspend CoroutineScope.() -> Unit) =
	kotlinx.coroutines.experimental.launch(context = globalContext, start = start, parent = parent, block = block);
