package org.stormgears.powerup.subsystems.navigator;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.decoupling.ITalon;
import org.stormgears.utils.decoupling.TalonFactoryKt;

public class DriveTalons {
	private static DriveTalons instance;

	public static DriveTalons getInstance() {
		return instance;
	}

	private static final int TALON_FPID_TIMEOUT = 0;

	private final ITalon[] talons = new ITalon[4];

	private DriveTalons() {
		talons[0] = TalonFactoryKt.TalonFactory(Robot.config.frontLeftTalonId);
		talons[1] = TalonFactoryKt.TalonFactory(Robot.config.frontRightTalonId);
		talons[2] = TalonFactoryKt.TalonFactory(Robot.config.rearLeftTalonId);
		talons[3] = TalonFactoryKt.TalonFactory(Robot.config.rearRightTalonId);

		for (ITalon t : talons) {
			t.config_kF(0, Robot.config.velocityF, TALON_FPID_TIMEOUT);
			t.config_kP(0, Robot.config.velocityP, TALON_FPID_TIMEOUT);
			t.config_kI(0, Robot.config.velocityI, TALON_FPID_TIMEOUT);
			t.config_kD(0, Robot.config.velocityD, TALON_FPID_TIMEOUT);
			t.config_IntegralZone(0, Robot.config.velocityIzone, TALON_FPID_TIMEOUT);
			t.setNeutralMode(NeutralMode.Brake);

			t.setInverted(true);
//			t.setSensorPhase(true);
		}

		talons[0].setSensorPhase(true);
		talons[3].setSensorPhase(true);
		talons[2].setSensorPhase(true);
		talons[1].setSensorPhase(true);
	}

	public static void init() {
		instance = new DriveTalons();
	}

	public ITalon[] getTalons() {
		return talons;
	}
}
