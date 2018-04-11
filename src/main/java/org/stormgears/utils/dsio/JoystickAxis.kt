package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.GenericHID

class JoystickAxis(val joystick: GenericHID, val number: Int) : IJoystickAxis {
	override val value
		get(): Double = joystick.getRawAxis(number)
}


