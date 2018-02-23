package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.buttons.JoystickButton
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.utils.LambdaCommand
import org.stormgears.utils.dsio.ITernarySwitch.SwitchState

class TernarySwitch
/**
 * Create a joystick button for triggering commands.
 *
 * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick,
 * etc)
 * @param buttonUp The button number (see [GenericHID.getRawButton]
 */
(joystick: GenericHID, buttonUp: Int, buttonDown: Int) : SwitchControl(joystick, buttonUp, false), ITernarySwitch {

	private val downButton: JoystickButton = JoystickButton(joystick, buttonDown)

	init {
		whenFlippedTernary {
			logger.trace("Ternary switch {}/{} flipped {} - {} ({})",
				box(buttonUp), box(buttonDown), it.toString().toLowerCase(), joystick.name, box(joystick.port))
		}
	}

	override fun whenFlippedTernary(listener: (state: ITernarySwitch.SwitchState) -> Unit) {
		super.whenReleased(LambdaCommand { listener(SwitchState.Neutral) })
		super.whenPressed(LambdaCommand { listener(SwitchState.Up) })

		downButton.whenReleased(LambdaCommand { listener(SwitchState.Neutral) })
		downButton.whenPressed(LambdaCommand { listener(SwitchState.Down) })
	}

	override fun whenFlippedTernary(listener: ITernarySwitch.TernaryFlipListener) {
		whenFlipped(listener)
	}

	override fun whenFlipped(listener: ITernarySwitch.TernaryFlipListener) {
		super.whenReleased(LambdaCommand { listener.flipped(SwitchState.Neutral) })
		super.whenPressed(LambdaCommand { listener.flipped(SwitchState.Up) })

		downButton.whenReleased(LambdaCommand { listener.flipped(SwitchState.Neutral) })
		downButton.whenPressed(LambdaCommand { listener.flipped(SwitchState.Down) })
	}

	companion object {
		private val logger = LogManager.getLogger(TernarySwitch::class.java)
	}


}
