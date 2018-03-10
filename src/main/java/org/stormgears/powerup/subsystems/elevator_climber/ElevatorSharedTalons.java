package org.stormgears.powerup.subsystems.elevator_climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.stormgears.utils.decoupling.ITalon;
import org.stormgears.utils.decoupling.StormTalon;

public class ElevatorSharedTalons {
	private static ElevatorSharedTalons instance;

	public static ElevatorSharedTalons getInstance() {
		return instance;
	}

	public static final int TALON_FPID_TIMEOUT = 10;

	// TODO: Set these to correct elevator_climber ids
	private static final int MASTER_MOTOR_TALON_ID = 11;
	private static final int SLAVE_MOTOR_TALON_ID = 0;

	// TODO: Rename these if needed
	private StormTalon masterMotor;
	private ITalon slaveMotor;

	private ElevatorSharedTalons(int masterMotorId, int slaveMotorId) {
		System.out.println("Initializing elevator talons");
		// TODO: Correctly setup talons
		masterMotor = new StormTalon(masterMotorId);
		slaveMotor = new StormTalon(slaveMotorId);
		slaveMotor.set(ControlMode.Follower, masterMotorId);
		masterMotor.setInverted(true);
		masterMotor.setSensorPhase(true);
		slaveMotor.setInverted(true);

	}

	public static void init() {
		instance = new ElevatorSharedTalons(MASTER_MOTOR_TALON_ID, SLAVE_MOTOR_TALON_ID);
	}

	public StormTalon getMasterMotor() {
		return masterMotor;
	}

	public ITalon getSlaveMotor() {
		return slaveMotor;
	}
}
