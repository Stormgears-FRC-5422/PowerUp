package org.stormgears.powerup.auto.command

import com.ctre.phoenix.motorcontrol.ControlMode
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.dsio.Choosers
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.TerminableSubsystem
import kotlin.math.PI

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes : TerminableSubsystem() {
	private suspend fun backOffAndRetractElevator(
		dist: Double = -24.0,
		maxAMult: Double = 0.7,
		delay: Int = 2800,
		joinElevator: Boolean = true,
		elevatorPos: Double = 0.25
	): Job? {
		val j = launch { Robot.drive?.moveStraightWithFeedback(dist, maxAMultiplier = maxAMult) }
		delay(delay)
		val ej = Robot.elevator?.moveElevatorToPosition(elevatorPos)
		if (joinElevator) {
			ej?.join()
		}
		j.join()

		return ej;
	}

	object FromLeft : AutoRoute {
		override suspend fun leftScale() {
			println("Starting at left spot, going to left scale")
			Robot.drive?.moveStraightWithFeedback(254.0 /* for good luck */)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[3])
			println("before turn")
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.94)
			delay(250)
			println("before movestraight 2")
			// OG 42
			Robot.drive?.moveStraightWithFeedback(36.0, maxAMultiplier = 0.7)

			delay(200)
			println("before joining elevator")
			elevatorJob?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			// OG 0.4
			Robot.intake?.eject(output = 0.6)
			delay(100)
			Robot.intake?.moveIntakeToPosition(Intake.VERTICAL)
//			delay(100)

			backOffAndRetractElevator(delay = 0)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)

			Robot.drive?.turnNavX((PI / 2 + PI / 8))
			Robot.intake?.grab()
			Robot.drive?.moveStraightWithFeedback(54.0)
			Robot.drive?.turnNavX(-(PI / 2 + PI / 4))

			val elevatorJob2 = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[3])
			delay(100)
			Robot.drive?.moveStraightWithFeedback(45.0, maxAMultiplier = 0.7)

//			delay(400)
			println("before joining elevator")
			elevatorJob2?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			Robot.intake?.eject(output = 0.8, forceHorizontal = true)
		}

		override suspend fun rightScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			println("Starting at left spot, going to right scale")

			Robot.drive?.moveStraightWithFeedback(247.0)
			realignNavX(Math.toRadians(-5.0))
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(258.0) // WITH cable protector in the middle

//			Robot.drive?.moveStraightWithFeedback(225.0)
//			Robot.drive?.turnNavX(PI / 2)
//			Robot.drive?.moveStraightWithFeedback(194.0)
//			Robot.drive?.turnNavX(-PI / 2)

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightWithFeedback(50.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject(output = 0.8)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			println("Starting at left spot, going to left switch")

			var triggered = false
			Robot.drive?.moveStraightWithFeedback(155.0, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(Math.PI / 2.0)
			Robot.drive?.moveStraightWithFeedback(20.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun rightSwitch() {
			println("Starting at left spot, going to right switch")

			if (!Choosers.crossField) {
				crossBaseline()
				return
			}
			Robot.drive?.moveStraightWithFeedback(50.0)
			Robot.drive?.strafeNavX(220.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightWithFeedback(72.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()

		}

		override suspend fun crossBaseline() {
			println("Starting at left spot, crossing baseline")

			Robot.drive?.moveStraightWithFeedback(150.0)
		}
	}

	object FromRight : AutoRoute {

		override suspend fun rightScale() {
			println("Starting at right spot, going to right scale")

			Robot.drive?.moveStraightWithFeedback(254.0 /* for good luck */, maxAMultiplier = 1.0)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[3])
			println("before turn")
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.9)
			delay(250)
			println("before movestraight 2")
			// OG 42
			Robot.drive?.moveStraightWithFeedback(36.0, maxAMultiplier = 0.7)

			delay(200)
			println("before joining elevator")
			elevatorJob?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			// OG 0.4
			Robot.intake?.eject(output = 0.7)
			delay(100)
			Robot.intake?.moveIntakeToPosition(Intake.VERTICAL)
//			delay(100)

			backOffAndRetractElevator(delay = 0)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)

			Robot.drive?.turnNavX(-(PI / 2 + 3 * PI / 24))
			Robot.intake?.grab()
			Robot.drive?.moveStraightWithFeedback(58.0, maxAMultiplier = 2.0)
			// Difference in angle here is due to the wheel that has the chains needs to touch!
			Robot.drive?.turnNavX(PI / 2 + PI / 4)

			val elevatorJob2 = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[2])
			delay(100)
			Robot.drive?.moveStraightWithFeedback(45.0)

			println("before joining elevator")
			elevatorJob2?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			Robot.intake?.eject(forceHorizontal = true, output = 0.8)
		}

		override suspend fun leftScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			println("Starting at right spot, going to left scale")

			Robot.drive?.moveStraightWithFeedback(247.0)
			realignNavX(Math.toRadians(5.0))
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(-258.0) // WITH cable protector in the middle

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightWithFeedback(50.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			println("Starting at right spot, going to left switch")
			Robot.drive?.moveStraightWithFeedback(50.0)
			//new stuff
			//			Robot.drive?.turnNavX(-Math.PI/2)
			//			Robot.drive?.moveStraightWithFeedback(115.0)
			//			Robot.drive?.turnNavX(Math.PI/2)

			Robot.drive?.strafeNavX(-210.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightWithFeedback(115.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()

		}

		override suspend fun rightSwitch() {
			println("Starting at right spot, going to right switch")

			var triggered = false
			Robot.drive?.moveStraightWithFeedback(162.0, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(-Math.PI / 2.0)
			Robot.drive?.moveStraightWithFeedback(23.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			println("Starting at right spot, crossing baseline")

			Robot.drive?.moveStraightWithFeedback(150.0)
		}
	}

	object FromCenter : AutoRoute {
		override suspend fun leftScale() {
			// TODO: Not implemented
		}

		override suspend fun rightScale() {
			// TODO: Not implemented
		}

		override suspend fun leftSwitch() {
			var elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 5)
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.75)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightWithFeedback(90.0)
			realignNavX()

			elevatorJob?.join()

			launch {
				Robot.intake?.eject(output = 0.7);
				delay(750);
				Robot.intake?.stopWheels()
			}
			delay(150)

			// Turning to get second cube
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.75)

			backOffAndRetractElevator(-33.0, 1.6, 600, false)

			Robot.drive?.strafeNavX(42.0, 3.0)
			var grabJob = Robot.intake?.grab(300, forceVertical = false)
			Robot.drive?.joystickMove(0.0, -0.25, 0.0, mode = ControlMode.Velocity)
			grabJob?.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0, mode = ControlMode.Velocity)
			delay(100)
			realignNavX()
//			delay(750)
//			Robot.drive?.moveStraightWithFeedback(-8.0, maxAMultiplier = 2.0)
			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 8)
			Robot.drive?.strafeNavX(-55.0, 3.0)
			Robot.drive?.moveStraightWithFeedback(16.0, maxAMultiplier = 3.0)
//			elevatorJob?.join()
			Robot.intake?.eject(0.7)

			// THIRD CUBE
			val backOffAndRetractElevator = backOffAndRetractElevator(0.0, 1.0, 600, false)

			Robot.drive?.turnNavX(Math.toRadians(61.0))
			grabJob = launch { delay(200); Robot.intake?.grab(200, forceVertical = false)?.join() }
			backOffAndRetractElevator?.join()
			Robot.drive?.moveStraightWithFeedback(dist = 22.0, maxAMultiplier = 2.0)
			grabJob.join()
			Robot.drive?.moveStraightWithFeedback(-22.0, maxAMultiplier = 3.5)
			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 12)
			Robot.drive?.turnNavX(Math.toRadians(-80.0))
			Robot.drive?.moveStraightWithFeedback(10.0, maxAMultiplier = 2.0);
			elevatorJob?.join()
			Robot.intake?.eject(0.7)

//			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0] + 6)
//			Robot.drive?.moveStraightWithFeedback(-8.0)
//			Robot.drive?.turnNavX(-PI / 2 * 0.9)
//			elevatorJob?.join()
//			Robot.intake?.eject(1.0)
		}

		override suspend fun rightSwitch() {
			Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 5)
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.6)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightWithFeedback(93.0)

			launch {
				Robot.intake?.eject(output = 0.7)
				delay(750);
				Robot.intake?.stopWheels()
			}
			delay(150)

			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.6)

			backOffAndRetractElevator(-30.0, 1.6, 600, false)

			Robot.drive?.strafeNavX(-47.0, 3.0)
			var grabJob = Robot.intake?.grab(300, forceVertical = false)
			Robot.drive?.joystickMove(0.0, -0.25, 0.0, mode = ControlMode.Velocity)
			grabJob?.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0, mode = ControlMode.Velocity)
			delay(100)
			realignNavX()
//			delay(750)
//			Robot.drive?.moveStraightWithFeedback(-8.0, maxAMultiplier = 2.0)
			var elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 8)
			Robot.drive?.strafeNavX(64.0, 3.0)
			Robot.drive?.moveStraightWithFeedback(24.0, maxAMultiplier = 3.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)

			val backOffAndRetractElevator = backOffAndRetractElevator(-36.0, 1.0, 600, false)
			//elevatorJob = Robot.elveator?.moveElevatorToPosition(14)
			Robot.drive?.strafeNavX(-12.0, 3.0)
			Robot.drive?.turnNavX(Math.toRadians(-20.0))

			//drive forward 30 inches with intake on
			//turn +20 degrees with intake on

			backOffAndRetractElevator?.join()
			grabJob = Robot.intake?.grab(20000, forceVertical = false)
			Robot.drive?.moveStraightWithFeedback(dist = 25.0, maxAMultiplier = 2.0)
			Robot.drive?.turnNavX(Math.toRadians(55.0))
			//Robot.drive?.moveStraightWithFeedback(-12.0, maxAMultiplier = 2.0)
			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 20) // Originally 12

			Robot.drive?.moveStraightWithFeedback(8.0, maxAMultiplier = 2.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)
		}

		override suspend fun crossBaseline() {
			// TODO: Not implemented
		}
	}

	private suspend fun realignNavX(offset: Double = 0.0) {
		if (Robot.sensors?.navX != null) {
			Robot.drive?.turnNavX(Robot.sensors?.navX?.getTheta(wrap = false)!! + offset)
		}
	}
}
