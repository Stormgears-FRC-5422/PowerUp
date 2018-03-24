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
		talons[0] = TalonFactoryKt.createTalon(Robot.config.getFrontLeftTalonId());
		talons[1] = TalonFactoryKt.createTalon(Robot.config.getFrontRightTalonId());
		talons[2] = TalonFactoryKt.createTalon(Robot.config.getRearLeftTalonId());
		talons[3] = TalonFactoryKt.createTalon(Robot.config.getRearRightTalonId());

		velocityPIDMode();
		for (ITalon t : talons) {
			t.setNeutralMode(NeutralMode.Brake);

			t.setInverted(true);
//			t.setSensorPhase(true);
		}

		talons[0].setSensorPhase(true);
		talons[3].setSensorPhase(true);
		talons[2].setSensorPhase(true);
		talons[1].setSensorPhase(true);
	}

	public void velocityPIDMode() {
		for (ITalon t : talons) {
			t.config_kF(0, Robot.config.getVelocityF(), TALON_FPID_TIMEOUT);
			t.config_kP(0, Robot.config.getVelocityP(), TALON_FPID_TIMEOUT);
			t.config_kI(0, Robot.config.getVelocityI(), TALON_FPID_TIMEOUT);
			t.config_kD(0, Robot.config.getVelocityD(), TALON_FPID_TIMEOUT);
			t.config_IntegralZone(0, Robot.config.getVelocityIzone(), TALON_FPID_TIMEOUT);
		}
	}

	public void positionPIDMode() {
		for (ITalon t : talons) {
			t.config_kF(0, Robot.config.getVelocityF(), TALON_FPID_TIMEOUT);
			t.config_kP(0, Robot.config.getPositionP(), TALON_FPID_TIMEOUT);
			t.config_kI(0, Robot.config.getPositionI(), TALON_FPID_TIMEOUT);
			t.config_kD(0, Robot.config.getPositionD(), TALON_FPID_TIMEOUT);
			t.config_IntegralZone(0, Robot.config.getPositionIzone(), TALON_FPID_TIMEOUT);
		}
	}

	public static void init() {
		instance = new DriveTalons();
	}

	public ITalon[] getTalons() {
		return talons;
	}
}
