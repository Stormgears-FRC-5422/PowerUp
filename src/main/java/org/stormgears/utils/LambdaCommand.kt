package org.stormgears.utils

import edu.wpi.first.wpilibj.command.Command

class LambdaCommand(val func: () -> Unit) : Command() {
	override fun isFinished(): Boolean {
		return true
	}

	override fun execute() {
		func()
	}
}
