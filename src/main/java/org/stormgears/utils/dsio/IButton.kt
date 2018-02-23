package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.command.Command

interface IButton {
	fun whenPressed(callback: Runnable)
	fun whenPressed(callback: () -> Unit)
	fun whenPressed(command: Command)

	fun whenReleased(callback: Runnable)
	fun whenReleased(callback: () -> Unit)
	fun whenReleased(command: Command)

	fun whileHeld(command: Command)

	fun toggleWhenPressed(command: Command)

	fun cancelWhenPressed(command: Command)

	fun whenActive(command: Command)

	fun whileActive(command: Command)

	fun whenInactive(command: Command)

	fun toggleWhenActive(command: Command)

	fun cancelWhenActive(command: Command)

	fun get(): Boolean
}
