package org.stormgears.utils.dsio

interface IRawJoystick {
	val joystickX: Double

	val joystickY: Double

	val joystickZ: Double

	val throttleV: Double

	fun getRawButton(button: Int): Boolean
}
