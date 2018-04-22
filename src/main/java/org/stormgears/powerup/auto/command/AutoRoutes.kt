package org.stormgears.powerup.auto.command

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
	private suspend fun backOffAndRetractElevator(dist: Double = -24.0, maxAMult: Double = 0.7, delay: Int = 2800, joinElevator: Boolean = true, elevatorPos: Int = 1): Job? {
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
			Robot.intake?.eject(output = 1.0)
			delay(100)
			Robot.intake?.moveIntakeToPosition(Intake.VERTICAL)
//			delay(100)

			backOffAndRetractElevator(delay = 0)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)

			Robot.drive?.turnNavX(PI / 2 + PI / 8)
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
			Robot.intake?.eject(output = 1.0, forceHorizontal = true)
		}

		override suspend fun rightScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			Robot.drive?.moveStraightWithFeedback(235.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(258.0) // WITH cable protector in the middle

//			Robot.drive?.moveStraightWithFeedback(225.0)
//			Robot.drive?.turnNavX(PI / 2)
//			Robot.drive?.moveStraightWithFeedback(194.0)
//			Robot.drive?.turnNavX(-PI / 2)

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightWithFeedback(42.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
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
			Robot.drive?.moveStraightWithFeedback(150.0)
		}
	}

	object FromRight : AutoRoute {

		override suspend fun rightScale() {
//			Robot.drive?.moveStraightWithFeedback(254.0 /* for good luck */)
//			println("before moveintake")
//			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
//			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
//			println("before turn")
//			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.94)
//			delay(700)
//			println("before movestraight 2")
//			Robot.drive?.moveStraightWithFeedback(42.0, maxAMultiplier = 0.7)
//
//			delay(400)
//			println("before joining elevator")
//			elevatorJob?.join()
////			Robot.drive?.moveStraightWithFeedback(5.0)
//			Robot.intake?.eject(output = 0.4)
//			delay(750)
//
//			backOffAndRetractElevator()

			Robot.drive?.moveStraightWithFeedback(254.0 /* for good luck */)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[3])
			println("before turn")
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.94)
			delay(250)
			println("before movestraight 2")
			// OG 42
			Robot.drive?.moveStraightWithFeedback(36.0, maxAMultiplier = 0.7)

			delay(200)
			println("before joining elevator")
			elevatorJob?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			// OG 0.4
			Robot.intake?.eject(output = 1.0)
			delay(100)
			Robot.intake?.moveIntakeToPosition(Intake.VERTICAL)
//			delay(100)

			backOffAndRetractElevator(delay = 0)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)

			Robot.drive?.turnNavX(-(PI / 2 + 3 * PI / 24))
			Robot.intake?.grab()
			Robot.drive?.moveStraightWithFeedback(54.0)
			// Difference in angle here is due to the wheel that has the chains needs to touch!
			Robot.drive?.turnNavX(PI / 2 + PI / 4)

			val elevatorJob2 = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[2])
			delay(100)
			Robot.drive?.moveStraightWithFeedback(45.0, maxAMultiplier = 0.7)

			println("before joining elevator")
			elevatorJob2?.join()
//			Robot.drive?.moveStraightWithFeedback(5.0)
			Robot.intake?.eject(forceHorizontal = true, output = 1.0)
		}

		override suspend fun leftScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			Robot.drive?.moveStraightWithFeedback(235.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(-258.0) // WITH cable protector in the middle

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightWithFeedback(48.0, maxAMultiplier = 0.7)
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
			Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.75)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			var triggered = false
			Robot.drive?.moveStraightWithFeedback(86.0, fun(progress) {
				if (!triggered && progress > 0.85) {
					triggered = true
					Robot.intake?.eject(output = 1.0)
					launch { delay(750); Robot.intake?.stopWheels() }
				}
			})
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.75)

			backOffAndRetractElevator(-28.0, 1.6, 600, false)

			Robot.drive?.strafeNavX(49.0, 3.0)
			var grabJob = Robot.intake?.grab(300)
			Robot.drive?.joystickMove(0.0, -0.25, 0.0)
			grabJob?.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0)
			delay(100)
//			delay(750)
//			Robot.drive?.moveStraightWithFeedback(-8.0, maxAMultiplier = 2.0)
			var elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 8)
			Robot.drive?.strafeNavX(-49.0, 3.0)
			Robot.drive?.moveStraightWithFeedback(16.0, maxAMultiplier = 3.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)

			val backOffAndRetractElevator = backOffAndRetractElevator(0.0, 1.0, 600, false)

			Robot.drive?.turnNavX(Math.toRadians(66.0))
			grabJob = launch { delay(200); Robot.intake?.grab(200)?.join() }
			backOffAndRetractElevator?.join()
			Robot.drive?.joystickMove(0.0, -0.25, 0.0)
			grabJob.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0)
			delay(100)
			Robot.drive?.joystickMove(0.0, 0.0, 0.0)
			Robot.drive?.moveStraightWithFeedback(-12.0, maxAMultiplier = 2.0)
			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 12)
			Robot.drive?.turnNavX(Math.toRadians(-62.0))
			Robot.drive?.moveStraightWithFeedback(10.0, maxAMultiplier = 2.0);
			elevatorJob?.join()
			Robot.intake?.eject(1.0)

//			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0] + 6)
//			Robot.drive?.moveStraightWithFeedback(-8.0)
//			Robot.drive?.turnNavX(-PI / 2 * 0.9)
//			elevatorJob?.join()
//			Robot.intake?.eject(1.0)
		}

		override suspend fun rightSwitch() {
			Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.6)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			var triggered = false
			Robot.drive?.moveStraightWithFeedback(92.0, fun(progress) {
				if (!triggered && progress > 0.85) {
					triggered = true
					Robot.intake?.eject(output = 1.0)
					launch { delay(750); Robot.intake?.stopWheels() }
				}
			})
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.6)

			backOffAndRetractElevator(-28.0, 1.6, 600, false)

			Robot.drive?.strafeNavX(-50.0, 3.0)
			var grabJob = Robot.intake?.grab(300)
			Robot.drive?.joystickMove(0.0, -0.25, 0.0)
			grabJob?.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0)
			delay(100)
//			delay(750)
//			Robot.drive?.moveStraightWithFeedback(-8.0, maxAMultiplier = 2.0)
			var elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 8)
			Robot.drive?.strafeNavX(64.0, 3.0)
			Robot.drive?.moveStraightWithFeedback(24.0, maxAMultiplier = 3.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)

			val backOffAndRetractElevator = backOffAndRetractElevator(-36.0, 1.0, 600, false, 14)
			//elevatorJob = Robot.elevator?.moveElevatorToPosition(14)
			Robot.drive?.strafeNavX(-12.0, 3.0)
			Robot.drive?.turnNavX(Math.toRadians(-20.0))

			grabJob = launch { delay(200); Robot.intake?.grab(200)?.join() }
			backOffAndRetractElevator?.join()
			Robot.drive?.joystickMove(0.0, -0.25, 0.0)
			grabJob.join()
			Robot.drive?.joystickMove(0.0, 0.3, 0.0)
			delay(100)
			Robot.drive?.joystickMove(0.0, 0.0, 0.0)
			Robot.drive?.moveStraightWithFeedback(-12.0, maxAMultiplier = 2.0)
			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1] + 20) // Originally 12
			Robot.drive?.turnNavX(Math.toRadians(75.0))
			Robot.drive?.moveStraightWithFeedback(8.0, maxAMultiplier = 2.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)
		}

		override suspend fun crossBaseline() {
			// TODO: Not implemented
		}
	}
}
