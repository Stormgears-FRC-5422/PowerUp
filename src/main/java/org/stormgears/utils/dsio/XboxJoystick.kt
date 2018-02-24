package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.Joystick

class XboxJoystick
/**
 * Construct an instance of a joystick. The joystick index is the USB port on the drivers
 * station.
 *
 * @param port The port on the Driver Station that the joystick is plugged into.
 */
(port: Int) : Joystick(port), IRawJoystick {

	override val joystickX: Double
		get() = getRawAxis(0)

	override val joystickY: Double
		get() = getRawAxis(5)

	override val joystickZ: Double
		get() = getRawAxis(4)
}
