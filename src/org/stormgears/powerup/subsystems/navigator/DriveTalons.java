package org.stormgears.powerup.subsystems.navigator;

import org.stormgears.powerup.Robot;
import org.stormgears.utils.StormTalon;

public class DriveTalons {
	private static DriveTalons instance;
	public static DriveTalons getInstance() {
		return instance;
	}

	private static final int TALON_FPID_TIMEOUT = 0;

	private final StormTalon[] talons = new StormTalon[4];

	private DriveTalons() {
		talons[0] = new StormTalon(Robot.config.frontLeftTalonId);
		talons[1] = new StormTalon(Robot.config.frontRightTalonId);
		talons[2] = new StormTalon(Robot.config.rearLeftTalonId);
		talons[3] = new StormTalon(Robot.config.rearRightTalonId);

		for (StormTalon t : talons) {
			t.config_kF(0, Robot.config.velocityF, TALON_FPID_TIMEOUT);
			t.config_kP(0, Robot.config.velocityP, TALON_FPID_TIMEOUT);
			t.config_kI(0, Robot.config.velocityI, TALON_FPID_TIMEOUT);
			t.config_kD(0, Robot.config.velocityD, TALON_FPID_TIMEOUT);
			t.config_IntegralZone(0, Robot.config.velocityIzone, TALON_FPID_TIMEOUT);
		}
	}

	public static void init() {
		instance = new DriveTalons();
	}

	public StormTalon[] getTalons() {
		return talons;
	}
}
