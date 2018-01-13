package org.stormgears.powerup.subsystems.dsio.event_listeners;

@FunctionalInterface
public interface OnSwitchFlippedListener {
	void onSwitchFlipped(boolean isOn);
}
