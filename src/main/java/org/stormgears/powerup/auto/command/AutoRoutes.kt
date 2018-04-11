package org.stormgears.powerup.auto.command

import kotlinx.coroutines.experimental.delay
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.WithCoroutines

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes : WithCoroutines {
	private suspend fun backOffAndRetractElevator() {
		launch { Robot.drive?.moveStraightNavX(-12.0, maxAMultiplier = 0.7) }
		delay(2000)
		Robot.elevator?.moveElevatorToPosition(6)?.join()
		Robot.elevator?.zeroElevator()
	}

	object FromLeft : AutoRoute {
		override suspend fun leftScale() {
//			Robot.intake?.startWheelsIn(output = 0.5)
			var triggered = false
			Robot.drive?.moveStraightNavX(254.0 /* for good luck */, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(Math.PI / 6.0)
			//Robot.drive?.strafeNavX(38.0)
//			Robot.drive?.turnNavX(PI / 2)
			delay(1000)
			Robot.drive?.moveStraightNavX(34.0, maxAMultiplier = 0.7)
			elevatorJob?.join()
//			Robot.drive?.moveStraightNavX(5.0)
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()
		}


		override suspend fun rightScale() {
			Robot.drive?.moveStraightNavX(235.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(258.0) // WITH cable protector in the middle

//			Robot.drive?.moveStraightNavX(225.0)
//			Robot.drive?.turnNavX(PI / 2)
//			Robot.drive?.moveStraightNavX(194.0)
//			Robot.drive?.turnNavX(-PI / 2)

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			var triggered = false
			Robot.drive?.moveStraightNavX(155.0, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(Math.PI / 2.0)
			Robot.drive?.moveStraightNavX(20.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun rightSwitch() {
			Robot.drive?.moveStraightNavX(50.0)
			Robot.drive?.strafeNavX(220.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightNavX(72.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			Robot.drive?.moveStraightNavX(150.0)
		}
	}

	object FromRight : AutoRoute {

		override suspend fun rightScale() {
			var triggered = false
			Robot.drive?.moveStraightNavX(254.0 /* for good luck */, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(-Math.PI / 6.0)
			delay(1000)
			Robot.drive?.moveStraightNavX(34.0, maxAMultiplier = 0.7)
			elevatorJob?.join()
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()

			/* Comment this back in for picking up a 2nd cube */
//			Robot.drive?.turnNavX(-5 * Math.PI / 6.0)
//			delay(500)
//			Robot.drive?.moveStraightNavX(30.0)
//			Robot.drive?.strafeNavX(58.0)
//			Intake.startWheelsIn()
//			Robot.drive?.moveStraightNavX(12.0)
		}

		override suspend fun leftScale() {
			Robot.drive?.moveStraightNavX(235.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(-258.0) // WITH cable protector in the middle

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject(output = 1.0)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			Robot.drive?.moveStraightNavX(50.0)
			//new stuff
//			Robot.drive?.turnNavX(-Math.PI/2)
//			Robot.drive?.moveStraightNavX(115.0)
//			Robot.drive?.turnNavX(Math.PI/2)

			Robot.drive?.strafeNavX(-210.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightNavX(115.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun rightSwitch() {
			var triggered = false
			Robot.drive?.moveStraightNavX(162.0, fun(progress) {
//				if (!triggered && progress > 0.8) {
//					triggered = true
//					Robot.intake?.startWheelsIn()
//				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.turnNavX(-Math.PI / 2.0)
			Robot.drive?.moveStraightNavX(23.0)
			elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			Robot.drive?.moveStraightNavX(150.0)
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
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.9)
// 			Robot.drive?.moveStraightNavX(50.0)
//			Robot.drive?.strafeNavX(69.0)
			Robot.drive?.moveStraightNavX(130.0)
			//	val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
//			Robot.drive?.moveStraightNavX(70.0)
			//	elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()

		}
		override suspend fun rightSwitch() {
			Robot.drive?.turnNavX(Math.PI / 12.0)
// 			Robot.drive?.moveStraightNavX(50.0)
//			Robot.drive?.strafeNavX(69.0)
			Robot.drive?.moveStraightNavX(125.0)
			//	val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
//			Robot.drive?.moveStraightNavX(70.0)
			//	elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			// TODO: Not implemented
		}
	}
}
