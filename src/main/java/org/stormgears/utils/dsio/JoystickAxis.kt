package org.stormgears.utils.dsio

import edu.wpi.first.wpilibj.GenericHID

class JoystickAxis(val joystick: GenericHID, val number: Int, val mult: Double) : IJoystickAxis {
	override val value
		get(): Double = mult * joystick.getRawAxis(number)
}


