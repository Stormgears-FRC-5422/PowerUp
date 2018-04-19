package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.Button
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.LambdaCommand
import org.stormgears.utils.RunnableCommand

class JoystickAxisButton(private val joystick: Joystick, private val axis: Int, private val direction: Direction) : Button(), IButton {
	enum class Direction(private val start: Double, private val end: Double) {
		Right(0.9, 1.0),
		Left(-1.0, -0.9);

		internal fun match(d: Double): Boolean {
			return d in start..end
		}
	}

	init {
		this.whenPressed { logger.trace("Joystick axis button {} pressed - {}", direction, joystick.name) }
	}

	override fun get(): Boolean {
		val d = this.joystick.getRawAxis(axis)
		logger.trace("Joystick axis {} value: {} match: {}", axis, d, direction.match(d))
		return direction.match(d)
	}

	override fun whenPressed(callback: Runnable) {
		super.whenPressed(RunnableCommand(callback))
	}

	override fun whenPressed(callback: () -> Unit) {
		super.whenPressed(LambdaCommand(callback))
	}

	override fun whenReleased(callback: Runnable) {
		super.whenReleased(RunnableCommand(callback))
	}

	override fun whenReleased(callback: () -> Unit) {
		super.whenReleased(LambdaCommand(callback))
	}

	override fun whileHeld(callback: Runnable) {
		super.whenReleased(RunnableCommand(callback))
	}

	override fun whileHeld(callback: () -> Unit) {
		super.whenReleased(LambdaCommand(callback))
	}

	companion object {
		private val logger = LogManager.getLogger(JoystickAxisButton::class.java)
	}
}
