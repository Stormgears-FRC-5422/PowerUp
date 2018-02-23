package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.command.Command

open class DummySwitch : ISwitch {
	override fun whenFlipped(listener: (Boolean) -> Unit) {

	}

	override fun whenFlipped(listener: ISwitch.FlipListener) {

	}

	override fun whenPressed(command: Command) {

	}

	override fun whileHeld(command: Command) {

	}

	override fun whenReleased(command: Command) {

	}

	override fun toggleWhenPressed(command: Command) {

	}

	override fun cancelWhenPressed(command: Command) {

	}

	override fun whenActive(command: Command) {

	}

	override fun whileActive(command: Command) {

	}

	override fun whenInactive(command: Command) {

	}

	override fun toggleWhenActive(command: Command) {

	}

	override fun cancelWhenActive(command: Command) {

	}

	override fun get(): Boolean {
		return false
	}
}
