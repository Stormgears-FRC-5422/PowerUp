package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.Button
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.LambdaCommand
import org.stormgears.utils.RunnableCommand

class POVButton(private val joystick: Joystick, private val direction: Direction) : Button /* WPI button! */(), IButton {

	enum class Direction(private val deg: Int, private val lower: Int, private val upper: Int) {
		Up(0, 315, 45),
		Right(90, 45, 135),
		Down(180, 135, 225),
		Left(270, 315, 225);

		internal fun match(d: Int): Boolean {
			return d == deg || d == lower || d == upper
		}
	}

	init {
		this.whenPressed { logger.trace("POV button {} pressed - {}", direction, joystick.name) }
	}

	override fun get(): Boolean {
		val d = this.joystick.pov
		return d != -1 && direction.match(d)
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

	companion object {
		private val logger = LogManager.getLogger(POVButton::class.java)
	}
}
