package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import org.stormgears.powerup.Robot
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon

object ElevatorSharedTalons {
	const val TALON_FPID_TIMEOUT = 10

	private val MASTER_MOTOR_TALON_ID = Robot.config.elevatorMasterTalonId
	private val SLAVE_MOTOR_TALON_ID = Robot.config.elevatorSlaveTalonId

	val masterMotor: ITalon
	val slaveMotor: ITalon

	init {
		println("Initializing elevator talons")

		masterMotor = createTalon(MASTER_MOTOR_TALON_ID)
		slaveMotor = createTalon(SLAVE_MOTOR_TALON_ID)

		masterMotor.setNeutralMode(NeutralMode.Brake)
		slaveMotor.setNeutralMode(NeutralMode.Brake)
		slaveMotor.set(ControlMode.Follower, MASTER_MOTOR_TALON_ID.toDouble())

		invert(true)
		masterMotor.setSensorPhase(true)

		masterMotor.configPeakCurrentLimit(30, TALON_FPID_TIMEOUT)
		masterMotor.configContinuousCurrentLimit(60, TALON_FPID_TIMEOUT)
		masterMotor.enableCurrentLimit(true)
		slaveMotor.configPeakCurrentLimit(30, TALON_FPID_TIMEOUT)
		slaveMotor.configContinuousCurrentLimit(60, TALON_FPID_TIMEOUT)
		slaveMotor.enableCurrentLimit(true)
	}

	internal fun invert(inverted: Boolean) {
		masterMotor.inverted = inverted
		slaveMotor.inverted = inverted
	}
}
