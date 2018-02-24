package org.stormgears.utils

import edu.wpi.first.wpilibj.command.Command

class RunnableCommand(private val runnable: Runnable) : Command() {
	override fun isFinished(): Boolean {
		return true
	}

	override fun execute() {
		runnable.run()
	}
}
