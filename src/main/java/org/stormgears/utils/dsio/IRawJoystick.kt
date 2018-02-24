package org.stormgears.utils.dsio

interface IRawJoystick {
	val joystickX: Double

	val joystickY: Double

	val joystickZ: Double

	fun getRawButton(button: Int): Boolean
}
