package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.command.Command

interface ISwitch {
	fun whenFlipped(listener: FlipListener)
	fun whenFlipped(listener: (Boolean) -> Unit)

	fun whenPressed(command: Command)

	fun whileHeld(command: Command)

	fun whenReleased(command: Command)

	fun toggleWhenPressed(command: Command)

	fun cancelWhenPressed(command: Command)

	fun whenActive(command: Command)

	fun whileActive(command: Command)

	fun whenInactive(command: Command)

	fun toggleWhenActive(command: Command)

	fun cancelWhenActive(command: Command)

	fun get(): Boolean

	@FunctionalInterface
	interface FlipListener {
		fun flipped(on: Boolean)
	}
}
