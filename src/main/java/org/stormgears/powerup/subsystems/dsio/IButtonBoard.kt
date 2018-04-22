package org.stormgears.powerup.subsystems.dsio

import org.stormgears.utils.dsio.IButton
import org.stormgears.utils.dsio.ISwitch

interface IButtonBoard {
	val dropButton: IButton

	val switch0Button: IButton

	val switch1Button: IButton

	val scaleButtons: Array<IButton>

	val sideLeftButton: IButton

	val sideRightButton: IButton

	val intakeGrabButton: IButton

	val gripOpenButton: IButton

	val gripCloseButton: IButton

	val climbUpButton: IButton

	val climbDownButton: IButton

	val forceZeroElevatorButton: IButton
	val zeroElevatorButton: IButton

	val intakeWheelsSwitch: ISwitch

	val intakeLiftSwitch: ISwitch

	val overrideSwitch: ISwitch

	val overrideUp: IButton

	val overrideDown: IButton

	val overrideLeft: IButton

	val overrideRight: IButton

	val speedSwitch: ISwitch

	val intakeTrigger: IButton

	companion object {
		var initialized = false
	}
}
