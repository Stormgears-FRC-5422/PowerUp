package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.utils.LambdaCommand
import org.stormgears.utils.RunnableCommand

class EnhancedButton
/**
 * Create a joystick button for triggering commands.
 *
 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
 * etc)
 * @param buttonNumber The button number (see [GenericHID.getRawButton])
 */
(joystick: GenericHID, buttonNumber: Int) : JoystickButton(joystick, buttonNumber), IButton {
	init {
		this.whenPressed { logger.trace("Button {} pressed on {} ({})", box(buttonNumber), joystick.name, box(joystick.port)) }
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
		super.whileHeld(RunnableCommand(callback))
	}

	override fun whileHeld(callback: () -> Unit) {
		super.whileHeld(LambdaCommand(callback))
	}

	companion object {
		private val logger = LogManager.getLogger(EnhancedButton::class.java)
	}
}
