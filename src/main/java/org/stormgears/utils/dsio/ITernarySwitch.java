package org.stormgears.utils.dsio;

public interface ITernarySwitch extends ISwitch {
	void whenFlippedTernary(TernaryFlipListener listener);

	void whenFlipped(TernaryFlipListener listener);

	enum SwitchState {
		Up,
		Neutral,
		Down
	}

	@FunctionalInterface
	interface TernaryFlipListener {
		void flipped(SwitchState state);
	}
}
