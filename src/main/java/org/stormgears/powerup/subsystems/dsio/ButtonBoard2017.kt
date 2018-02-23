package org.stormgears.powerup.subsystems.dsio

import edu.wpi.first.wpilibj.Joystick
import org.stormgears.utils.dsio.*


class ButtonBoard2017 private constructor(buttonBoard: Joystick, joystick: Joystick) : IButtonBoard {

	override val gripOpenButton: IButton = EnhancedButton(buttonBoard, Ids.YELLOW)
	override val gripCloseButton: IButton = EnhancedButton(buttonBoard, Ids.RED)

	override val dropButton: IButton = EnhancedButton(buttonBoard, Ids.BIG_BLUE)

	override val switch0Button: IButton = EnhancedButton(buttonBoard, Ids.SMALL_BLUE)
	override val switch1Button: IButton = EnhancedButton(buttonBoard, Ids.GREEN)

	override val sideLeftButton: IButton = EnhancedButton(buttonBoard, Ids.WHITE)
	override val sideRightButton: IButton = EnhancedButton(buttonBoard, Ids.BLACK)

	override val intakeWheelsSwitch: ISwitch = SwitchControl(buttonBoard, Ids.GREEN_SWITCH)
	override val intakeLiftSwitch: ISwitch = SwitchControl(buttonBoard, Ids.ORANGE_SWITCH)

	override val overrideSwitch: ISwitch = SwitchControl(buttonBoard, Ids.RED_SWITCH)
	override val overrideUp: IButton = POVButton(joystick, POVButton.Direction.Up)
	override val overrideDown: IButton = POVButton(joystick, POVButton.Direction.Down)
	override val overrideLeft: IButton = POVButton(joystick, POVButton.Direction.Left)
	override val overrideRight: IButton = POVButton(joystick, POVButton.Direction.Right)

	// Falling back on buttons on the Logitech joystick
	override val intakeGrabButton: IButton = EnhancedButton(joystick, 12)

	override val climbUpButton: IButton = EnhancedButton(joystick, 5)
	override val climbDownButton: IButton = EnhancedButton(joystick, 6)

	override val scaleButtons: Array<IButton> = arrayOf(
		EnhancedButton(joystick, 7),
		EnhancedButton(joystick, 8),
		EnhancedButton(joystick, 9),
		EnhancedButton(joystick, 10),
		EnhancedButton(joystick, 11)
	)

	object Ids {
		val BIG_BLUE = 10
		val RED = 15
		val YELLOW = 14
		val GREEN = 13
		val SMALL_BLUE = 12
		val BLACK = 9
		val WHITE = 8
		val GREEN_SWITCH = 5
		val ORANGE_SWITCH = 4
		val RED_SWITCH = 3
	}

	companion object {
		private var instance: ButtonBoard2017? = null

		@JvmStatic
		fun getInstance(buttonBoard: Joystick, joystick: Joystick): IButtonBoard {
			if (instance == null) {
				if (IButtonBoard.initialized) {
					throw IllegalStateException("Only one button board can exist at once.")
				}

				IButtonBoard.initialized = true

				instance = ButtonBoard2017(buttonBoard, joystick)
				return instance as IButtonBoard
			}

			return instance as IButtonBoard
		}
	}
}
