package org.stormgears.utils.dsio

interface ITernarySwitch : ISwitch {
	fun whenFlippedTernary(listener: TernaryFlipListener)

	fun whenFlipped(listener: TernaryFlipListener)

	enum class SwitchState {
		Up,
		Neutral,
		Down
	}

	@FunctionalInterface
	interface TernaryFlipListener {
		fun flipped(state: SwitchState)
	}
}
