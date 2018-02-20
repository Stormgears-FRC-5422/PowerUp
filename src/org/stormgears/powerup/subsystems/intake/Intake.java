package org.stormgears.powerup.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.utils.StormTalon;

public class Intake extends Subsystem {
	private static final Logger logger = LogManager.getLogger(Intake.class);
	private static Intake instance;

	public static Intake getInstance() {
		return instance;
	}

	public static final int VERTICAL = 0;
	public static final int DIAGONAL = 0;
	public static final int HORIZONTAL = 0;

	//TODO: Change these to correct values
	private static final int LEFT_TALON_ID = 0;
	private static final int RIGHT_TALON_ID = 0;
	private static final int ARTICULATOR_TALON_ID = 0;

	private static final int SPEED = 5000;

	// TODO: PID tune
	private static final double WHEEL_P = 0.1;
	private static final double WHEEL_I = 0.0;
	private static final double WHEEL_D = 0.0;
	private static final double WHEEL_F = 4.48;
	private static final int WHEEL_IZONE = 1500;
	private static final double ART_P = 0.1;
	private static final double ART_I = 0.0;
	private static final double ART_D = 0.0;

	private static final int TALON_FPID_TIMEOUT = 10;

	private StormTalon leftTalon, rightTalon, articulatorTalon;

	private Intake(int leftTalonId, int rightTalonId, int articulatorTalonId) {
//		leftTalon = new StormTalon(leftTalonId);
//		rightTalon = new StormTalon(rightTalonId);
//		articulatorTalon = new StormTalon(articulatorTalonId);
//
//		leftTalon.config_kP(0, WHEEL_P, TALON_FPID_TIMEOUT);
//		leftTalon.config_kI(0, WHEEL_I, TALON_FPID_TIMEOUT);
//		leftTalon.config_kD(0, WHEEL_D, TALON_FPID_TIMEOUT);
//		leftTalon.config_kF(0, WHEEL_F, TALON_FPID_TIMEOUT);
//		leftTalon.config_IntegralZone(0, WHEEL_IZONE, TALON_FPID_TIMEOUT);
//
//		rightTalon.config_kP(0, WHEEL_P, TALON_FPID_TIMEOUT);
//		rightTalon.config_kI(0, WHEEL_I, TALON_FPID_TIMEOUT);
//		rightTalon.config_kD(0, WHEEL_D, TALON_FPID_TIMEOUT);
//		rightTalon.config_kF(0, WHEEL_F, TALON_FPID_TIMEOUT);
//		rightTalon.config_IntegralZone(0, WHEEL_IZONE, TALON_FPID_TIMEOUT);
//
//		articulatorTalon.config_kP(0, ART_P, TALON_FPID_TIMEOUT);
//		articulatorTalon.config_kI(0, ART_I, TALON_FPID_TIMEOUT);
//		articulatorTalon.config_kD(0, ART_D, TALON_FPID_TIMEOUT);
	}

	public static void init() {
		instance = new Intake(LEFT_TALON_ID, RIGHT_TALON_ID, ARTICULATOR_TALON_ID);
	}

	public void enableIntake() {
		logger.info("Intake on");

		leftTalon.set(ControlMode.Velocity, SPEED);
		rightTalon.set(ControlMode.Velocity, -SPEED);
	}

	public void disableIntake() {
		logger.info("Intake off");

		leftTalon.set(ControlMode.Velocity, 0);
		rightTalon.set(ControlMode.Velocity, 0);
	}

	public void moveIntakeToPosition(int position) {

	}

	@Override
	protected void initDefaultCommand() {

	}
}
