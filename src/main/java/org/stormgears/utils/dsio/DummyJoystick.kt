package org.stormgears.utils.dsio

class DummyJoystick : IRawJoystick {
	override val joystickX: Double = 0.0

	override val joystickY: Double = 0.0

	override val joystickZ: Double = 0.0

	override val throttleV: Double = 0.0

	override fun getRawButton(button: Int): Boolean {
		return false
	}
}
