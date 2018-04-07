package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.*
import org.stormgears.powerup.Robot
import org.stormgears.utils.talons.FactoryTalonConfig
import org.stormgears.utils.talons.ITalon
import org.stormgears.utils.talons.LocalLimitSwitchSourceConfig
import org.stormgears.utils.talons.createTalon

object ElevatorSharedTalons {
	private val MASTER_MOTOR_TALON_ID = Robot.config.elevatorMasterTalonId
	private val SLAVE_MOTOR_TALON_ID = Robot.config.elevatorSlaveTalonId

	val masterMotor: ITalon
	val slaveMotor: ITalon

	open class ElevatorTalonConfig : FactoryTalonConfig() {
		// Raise
		override val profileSlot0 = object : DefaultPIDSlot() {
			override val kP = Robot.config.elevatorRaiseP
			override val kI = Robot.config.elevatorRaiseI
			override val kD = Robot.config.elevatorRaiseD
			override val kF = 0.0
		}

		// Lower
		override val profileSlot1 = object : DefaultPIDSlot() {
			override val kP = Robot.config.elevatorLowerP
			override val kI = Robot.config.elevatorLowerI
			override val kD = Robot.config.elevatorLowerD
			override val kF = 0.0
		}

		override val neutralMode = NeutralMode.Brake
		override val inverted = true
		override val sensorPhase = true

		override val peakCurrentLimit = 30
		override val continuousCurrentLimit = 60
		override val enableCurrentLimit = true

		override val forwardLimitSwitchSource = LocalLimitSwitchSourceConfig(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen)
		override val reverseLimitSwitchSource = LocalLimitSwitchSourceConfig(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen)

		override val statusFramePeriod = mapOf(
			StatusFrameEnhanced.Status_13_Base_PIDF0 to 10,
			StatusFrameEnhanced.Status_3_Quadrature to 10
		)
	}

	class MasterElevatorTalonConfig : ElevatorTalonConfig() {
//		override val forwardSoftLimitThreshold = -1145000
//		override val forwardSoftLimitEnable = true
	}

	val elevatorTalonConfig = ElevatorTalonConfig()
	val masterElevatorTalonConfig = MasterElevatorTalonConfig()

	init {
//		println("Initializing elevator talons")

		masterMotor = createTalon(MASTER_MOTOR_TALON_ID)
		slaveMotor = createTalon(SLAVE_MOTOR_TALON_ID)

//		TalonDebugger(arrayOf(masterMotor, slaveMotor)).start()


		masterMotor.setConfig(masterElevatorTalonConfig)
		slaveMotor.setConfig(elevatorTalonConfig)

		slaveMotor.set(ControlMode.Follower, MASTER_MOTOR_TALON_ID.toDouble())
	}
}
