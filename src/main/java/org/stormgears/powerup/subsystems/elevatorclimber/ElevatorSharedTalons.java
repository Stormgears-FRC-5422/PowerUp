package org.stormgears.powerup.subsystems.elevatorclimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.stormgears.powerup.TalonIds;
import org.stormgears.utils.decoupling.ITalon;
import org.stormgears.utils.decoupling.TalonFactoryKt;

public class ElevatorSharedTalons {
	private static ElevatorSharedTalons instance;

	public static ElevatorSharedTalons getInstance() {
		return instance;
	}

	public static final int TALON_FPID_TIMEOUT = 10;

	private static final int MASTER_MOTOR_TALON_ID = TalonIds.ELEVATOR_MASTER;
	private static final int SLAVE_MOTOR_TALON_ID = TalonIds.ELEVATOR_SLAVE;

	private ITalon masterMotor;
	private ITalon slaveMotor;

	private ElevatorSharedTalons(int masterMotorId, int slaveMotorId) {
		System.out.println("Initializing elevator talons");

		masterMotor = TalonFactoryKt.createTalon(masterMotorId);
		slaveMotor = TalonFactoryKt.createTalon(slaveMotorId);
		slaveMotor.set(ControlMode.Follower, masterMotorId);

		masterMotor.setInverted(true);
		masterMotor.setSensorPhase(true);
		slaveMotor.setInverted(true);

//		masterMotor.configPeakCurrentLimit(40, TALON_FPID_TIMEOUT);
//		masterMotor.enableCurrentLimit(true);
//		slaveMotor.configPeakCurrentLimit(40, TALON_FPID_TIMEOUT);
//		slaveMotor.enableCurrentLimit(true);

	}

	public static void init() {
		instance = new ElevatorSharedTalons(MASTER_MOTOR_TALON_ID, SLAVE_MOTOR_TALON_ID);
	}

	public ITalon getMasterMotor() {
		return masterMotor;
	}

	public ITalon getSlaveMotor() {
		return slaveMotor;
	}
}
