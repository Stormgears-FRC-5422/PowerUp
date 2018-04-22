package org.stormgears.powerup.subsystems.dsio

import edu.wpi.first.wpilibj.Joystick
import org.stormgears.utils.dsio.*

class ButtonBoard2018V2 private constructor(jumperGamepad: Joystick, normalGamepad: Joystick, realJoystick: LogitechJoystick?) : IButtonBoard {

	override val gripOpenButton: IButton = DummyButton()
	override val gripCloseButton: IButton = EnhancedButton(normalGamepad, 8)

	override val dropButton: IButton = EnhancedButton(normalGamepad, 1)

	override val switch0Button: IButton = EnhancedButton(jumperGamepad, 5)
	override val switch1Button: IButton = EnhancedButton(jumperGamepad, 9)

	override val sideLeftButton: IButton = EnhancedButton(jumperGamepad, 6)
	override val sideRightButton: IButton = EnhancedButton(jumperGamepad, 10)

	override val intakeGrabButton: IButton = EnhancedButton(normalGamepad, 5)

	override val climbUpButton: IButton = EnhancedButton(normalGamepad, 11)
	override val climbDownButton: IButton = EnhancedButton(normalGamepad, 10)

	override val scaleButtons: Array<IButton> = arrayOf(
		EnhancedButton(jumperGamepad, 7),
		EnhancedButton(jumperGamepad, 8),
		EnhancedButton(normalGamepad, 2),
		EnhancedButton(normalGamepad, 3),
		EnhancedButton(normalGamepad, 4)
	)

	override val intakeWheelsSwitch: ISwitch = TernarySwitch(jumperGamepad, 11, 12)
	override val intakeLiftSwitch: ISwitch = TernarySwitch(normalGamepad, 7, 6)

	override val forceZeroElevatorButton: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 7) else DummyButton()
	override val zeroElevatorButton: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 8) else DummyButton()

	override val overrideSwitch: ISwitch = SwitchControl(normalGamepad, 12)
	override val overrideUp: IButton = EnhancedButton(jumperGamepad, 3)
	override val overrideDown: IButton = EnhancedButton(jumperGamepad, 2)
	override val overrideLeft: IButton = DummyButton()
	override val overrideRight: IButton = DummyButton()

	override val speedSwitch: ISwitch = SwitchControl(jumperGamepad, 1)

	override val intakeTrigger: IButton = if (realJoystick != null) EnhancedButton(realJoystick, 1) else DummyButton()

	companion object {
		private var instance: ButtonBoard2018V2? = null

		@JvmStatic // TODO: Replace with more idiomatic Kotlin (object???)
		fun getInstance(jumperGamepad: Joystick, normalGamepad: Joystick, realJoystick: LogitechJoystick?): IButtonBoard {
			if (instance == null) {
				if (IButtonBoard.initialized) {
					throw IllegalStateException("Only one button board can exist at once.")
				}

				IButtonBoard.initialized = true

				instance = ButtonBoard2018V2(jumperGamepad, normalGamepad, realJoystick)
				return instance as IButtonBoard
			}

			return instance as IButtonBoard
		}
	}
}
