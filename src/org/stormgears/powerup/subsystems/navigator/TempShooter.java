package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.stormgears.utils.StormTalon;

public class TempShooter {

	StormTalon shooter;

	public TempShooter() {
		shooter = new StormTalon(6);
	}

	public void shoot() {
		shooter.set(ControlMode.PercentOutput, 0.75);
	}

	public void stop() {
		shooter.set(ControlMode.PercentOutput, 0);
	}
}
