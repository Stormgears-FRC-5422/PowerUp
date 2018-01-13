package org.stormgears.PowerUp.subsystems.dsio.event_listeners;

@FunctionalInterface
public interface OnSwitchFlippedListener {
	void onSwitchFlipped(boolean isOn);
}
