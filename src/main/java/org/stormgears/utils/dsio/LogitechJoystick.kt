package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.Joystick

class LogitechJoystick
/**
 * Construct an instance of a joystick. The joystick index is the USB port on the drivers
 * station.
 *
 * @param port The port on the Driver Station that the joystick is plugged into.
 */
(port: Int) : Joystick(port), IRawJoystick {

	override val joystickX: Double
		get() = x

	override val joystickY: Double
		get() = y

	override val joystickZ: Double
		get() = -super.getZ() // needs to be inverted for some reason

	override val throttleV: Double
		get() = -super.getThrottle()
}
