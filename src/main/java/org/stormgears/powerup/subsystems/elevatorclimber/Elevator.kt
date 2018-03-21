package org.stormgears.powerup.subsystems.elevatorclimber

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot
import org.stormgears.powerup.TalonIds
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.TerminableSubsystem
import org.stormgears.utils.decoupling.ITalon
import org.stormgears.utils.decoupling.createTalon

/**
 * Default constructor for the creation of the elevator
 */
object Elevator : TerminableSubsystem() {
	private val logger = LogManager.getLogger(this::class.java)

	private val talons: ElevatorSharedTalons = Robot.elevatorSharedTalons
	private val sideShiftTalon: ITalon
	private var sideShiftPosition = 0

	private const val TICKS_PER_INCH = 13000
	private const val ELEVATOR_DISTANCE_MULTIPLIER = -1.0 // Because the encoder is reversed
	private const val INTAKE_HEIGHT = -200000 // Ticks

	var elevatorZeroed = false

	val currentPositionTicks: Int
		get() = talons.masterMotor.sensorCollection.quadraturePosition

	private const val ZERO_POWER = 0.2
	private const val ZERO_CURRENT_LIMIT = 8.0 // 8.7

	// PID values for elevator
	private const val RAISE_P = 0.088
	private const val RAISE_I = 0.00000076
	private const val RAISE_D = 3.2
	private const val LOWER_P = 0.088
	private const val LOWER_I = 0.00000076
	private const val LOWER_D = 3.2

	// Elevator button positions (inches)
	val SWITCH_POSITIONS = intArrayOf(22, 37, 40)
	val SCALE_POSITIONS = intArrayOf(56, 70, 81, 90, 91)

	// Side shift stuff
	private const val SIDE_SHIFT_TALON_ID = TalonIds.SIDESHIFT
	const val LEFT = -1
	const val CENTER = 0
	const val RIGHT = 1
	private const val SIDE_SHIFT_POWER = 1.0
	private const val SLOW_DOWN = -0.05
//	private const val FULL_LEFT_TICKS = 181000
//	private const val FULL_RIGHT_TICKS = -181000

	// Jobs
	private var sideShiftJob: Job? = null
	private var elevatorJob: Job? = null

	init {
		sideShiftTalon = createTalon(SIDE_SHIFT_TALON_ID)
		sideShiftTalon.inverted = true

		if (sideShiftTalon.dummy) {
			logger.warn("Requires physical talon, disabling Elevator!")
			this.disabled = true
		}
	}

	/**
	 * Move the elevator to a position
	 *
	 * @param position = inches from the bottom of the elevator
	 */
	fun moveElevatorToPosition(position: Int, slowly: Boolean = false) {
		if (elevatorJob != null) {
			elevatorJob!!.cancel()
			logger.trace("Cancelled elevator job")
		}

		elevatorJob = launch("Elevator Auto Move") {
			elevatorAutoMove(position)
		}
	}

	suspend fun elevatorAutoMove(position: Int, slowly: Boolean = false) {
		val lowering: Boolean
		val positionTicks = toEncoderTicks(position.toDouble())

		if (slowly) {
			talons.masterMotor.configPeakOutputForward(0.5, 10)
			talons.masterMotor.configPeakOutputReverse(0.5, 10)
		}

		lowering = if (positionTicks < currentPositionTicks) {     // Raising elevator
			talons.masterMotor.config_kP(0, RAISE_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, RAISE_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, RAISE_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			false
		} else {    // Lowering elevator
			talons.masterMotor.config_kP(0, LOWER_P, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kI(0, LOWER_I, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			talons.masterMotor.config_kD(0, LOWER_D, ElevatorSharedTalons.TALON_FPID_TIMEOUT)
			true
		}
		talons.masterMotor.config_kF(0, 0.0, ElevatorSharedTalons.TALON_FPID_TIMEOUT)

		logger.trace("Desired encoder position: {}", box(positionTicks))
		talons.masterMotor.set(ControlMode.Position, positionTicks.toDouble())

		var openedIntake = false

		// Wait until elevator finishes
		while (Math.abs(positionTicks - talons.masterMotor.sensorCollection.quadraturePosition) > 50) {
			logger.trace(talons.masterMotor.sensorCollection.quadraturePosition)

			if (lowering && Intake.isUp && currentPositionTicks > INTAKE_HEIGHT) {
				holdElevator()
				break
			} else if (lowering && !Intake.isUp && !openedIntake) {
				Gripper.openGripper()
				openedIntake = true
			}
			delay(20)
		}

		talons.masterMotor.configPeakOutputForward(1.0, 10)
		talons.masterMotor.configPeakOutputReverse(1.0, 10)

		logger.trace("Elevator has moved to encoder position: {}", currentPositionTicks)
	}

	private var overrodeSide = false
	/**
	 * Stop all motion
	 */
	fun stop() {
		launch("Override slow down", parent = null) {
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)

			if (!overrodeSide) {
				holdElevator()
			}
		}
	}

	/**
	 * Move side shift to position
	 */
	fun moveSideShiftToPosition(position: Int) {
		if (sideShiftJob != null) {
			sideShiftJob!!.cancel()
			logger.trace("Canceled side shift job")
		}

		// Coroutines again!
		sideShiftJob = launch("Side Shift Auto Move") {
			moveSideShiftToPositionSuspend(position)
		}
	}

	suspend fun moveSideShiftToPositionSuspend(position: Int) {
		if (sideShiftPosition != position && elevatorZeroed) {
			var multiplier = 0
			if (position == LEFT) {
				multiplier = -1
			} else if (position == CENTER) {
				if (sideShiftPosition == LEFT) multiplier = 1
				else if (sideShiftPosition == RIGHT) multiplier = -1
			} else if (position == RIGHT) {
				multiplier = 1
			}

			var reachedCenter = false
			logger.trace("Moving side shift to position: {}", box(position))
			if (position == CENTER) logger.trace("Moving to center.")
			sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier)

			while (sideShiftTalon.outputCurrent <= 17.0 && !reachedCenter) {
				logger.trace(box(sideShiftTalon.outputCurrent))
				if (position == CENTER) {
					// The API is reversed, so the FWD port on the breakout board corresponds to isRevLimitSwitchClosed
					// and vice versa
					if (Math.abs(sideShiftTalon.sensorCollection.quadraturePosition) < 30000) {
						sideShiftTalon.set(ControlMode.PercentOutput, SIDE_SHIFT_POWER * multiplier + SLOW_DOWN * multiplier)
					}

					reachedCenter = sideShiftTalon.sensorCollection.isRevLimitSwitchClosed ||
						Math.abs(sideShiftTalon.sensorCollection.quadraturePosition) < 10000

					logger.trace(box(sideShiftTalon.sensorCollection.quadraturePosition))
				}

				delay(20)
			}
			sideShiftTalon.set(ControlMode.PercentOutput, 0.0)
			logger.trace("Side shift current limit reached or reached center")

			sideShiftPosition = position
		}
	}

	suspend fun zeroElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, ZERO_POWER)
		println("Zero-ing Elevator. Watch out!")

		var iteration = 0
		while (iteration < 40 || talons.masterMotor.outputCurrent < ZERO_CURRENT_LIMIT) {
			delay(20)
			println("Current limit: ${talons.masterMotor.outputCurrent}")

			iteration++
		}
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

		talons.masterMotor.sensorCollection.setQuadraturePosition(0, 10)
		elevatorZeroed = true
		println("Elevator zeroed")
	}

	fun zeroSideShift() {
		sideShiftTalon.sensorCollection.setQuadraturePosition(0, 10)
	}

	fun turnOffElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)
	}

	private suspend fun holdElevator() {
		talons.masterMotor.set(ControlMode.PercentOutput, 0.0)

		delay(300)

		// Hold current elevator position
		talons.masterMotor.set(ControlMode.Position, currentPositionTicks.toDouble())
	}

	fun moveSideShiftOverLeft() {
		if (sideShiftPosition > -1) moveSideShiftToPosition(sideShiftPosition - 1)
	}

	fun moveSideShiftOverRight() {
		if (sideShiftPosition < 1) moveSideShiftToPosition(sideShiftPosition + 1)
	}

	fun moveUpManual() {
		logger.trace("Manual override up")
		talons.masterMotor.set(ControlMode.PercentOutput, -0.5)
		overrodeSide = false
	}

	fun moveDownManual() {
		overrodeSide = false

		if (Intake.isUp && talons.masterMotor.sensorCollection.quadraturePosition > INTAKE_HEIGHT) return

		talons.masterMotor.set(ControlMode.PercentOutput, 0.33)
	}

	fun moveLeftManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, -0.5)
		overrodeSide = true
	}

	fun moveRightManual() {
		sideShiftTalon.set(ControlMode.PercentOutput, 0.5)
		overrodeSide = true
	}

    fun debug() {
		SmartDashboard.putNumber("Elevator encoder position", talons.masterMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Elevator encoder velocity", talons.masterMotor.sensorCollection.quadratureVelocity.toDouble())
		SmartDashboard.putNumber("Elevator encoder position_", talons.masterMotor.sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Elevator encoder velocity_", talons.masterMotor.sensorCollection.quadratureVelocity.toDouble())
	}

	/**
     * @param inches number of inches
     * @return number of encoder ticks necessary to go that many inches
     */
	private fun toEncoderTicks(inches: Double): Int =
		Math.round(inches * TICKS_PER_INCH * ELEVATOR_DISTANCE_MULTIPLIER).toInt()
}
