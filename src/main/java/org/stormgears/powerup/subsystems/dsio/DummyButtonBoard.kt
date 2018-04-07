package org.stormgears.powerup.subsystems.dsio

import org.stormgears.utils.dsio.*

class DummyButtonBoard : IButtonBoard {
	override val forceZeroElevatorButton: IButton = DummyButton()
	override val dropButton: IButton = DummyButton()
	override val zeroElevatorButton: IButton = DummyButton()
	override val switch0Button: IButton = DummyButton()
	override val switch1Button: IButton = DummyButton()
	override val scaleButtons: Array<IButton> = arrayOf(DummyButton(), DummyButton(), DummyButton(), DummyButton(), DummyButton())
	override val sideLeftButton: IButton = DummyButton()
	override val sideRightButton: IButton = DummyButton()
	override val intakeGrabButton: IButton = DummyButton()
	override val gripOpenButton: IButton = DummyButton()
	override val gripCloseButton: IButton = DummyButton()
	override val climbUpButton: IButton = DummyButton()
	override val climbDownButton: IButton = DummyButton()
	override val intakeWheelsSwitch: ISwitch = DummyTernarySwitch()
	override val intakeLiftSwitch: ISwitch = DummyTernarySwitch()
	override val overrideSwitch: ISwitch = DummySwitch()
	override val overrideUp: IButton = DummyButton()
	override val overrideDown: IButton = DummyButton()
	override val overrideLeft: IButton = DummyButton()
	override val overrideRight: IButton = DummyButton()
}
