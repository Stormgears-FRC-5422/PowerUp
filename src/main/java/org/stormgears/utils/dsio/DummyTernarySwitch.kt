package org.stormgears.utils.dsio

class DummyTernarySwitch : DummySwitch(), ITernarySwitch {
	override fun whenFlippedTernary(listener: (state: ITernarySwitch.SwitchState) -> Unit) {

	}

	override fun whenFlipped(listener: (Boolean) -> Unit) {

	}

	override fun whenFlippedTernary(listener: ITernarySwitch.TernaryFlipListener) {

	}

	override fun whenFlipped(listener: ITernarySwitch.TernaryFlipListener) {

	}
}
