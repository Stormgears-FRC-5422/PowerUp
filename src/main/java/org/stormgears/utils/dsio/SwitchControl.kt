package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.utils.LambdaCommand

open class SwitchControl
/**
 * Create a joystick button for triggering commands.
 *
 * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
 * etc)
 * @param buttonNumber The button number (see [GenericHID.getRawButton]
 */
(private val joystick: GenericHID, private val buttonNumber: Int) : JoystickButton(joystick, buttonNumber), ISwitch {

	protected constructor(joystick: GenericHID, buttonNumber: Int, log: Boolean) : this(joystick, buttonNumber) {
		if (!log) {
			this.whenFlipped { isOn -> logger.trace("Switch {} flipped {} - {} ({})", box(buttonNumber), if (isOn) "on" else "off", joystick.name, box(joystick.port)) }
		}
	}

	override fun whenFlipped(listener: ISwitch.FlipListener) {
		super.whenReleased(object : Command() {
			override fun isFinished(): Boolean {
				return true
			}

			override fun execute() {
				listener.flipped(false)
			}
		})

		super.whenPressed(object : Command() {
			override fun isFinished(): Boolean {
				return true
			}

			override fun execute() {
				listener.flipped(true)
			}
		})
	}

	override fun whenFlipped(listener: (Boolean) -> Unit) {
		super.whenReleased(LambdaCommand { listener(false) })
		super.whenPressed(LambdaCommand { listener(true) })
	}

	companion object {
		private val logger = LogManager.getLogger(SwitchControl::class.java)
	}
}
