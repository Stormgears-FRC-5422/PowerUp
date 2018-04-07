package org.stormgears.powerup.auto.command

import kotlinx.coroutines.experimental.delay
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.utils.concurrency.WithCoroutines

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes : WithCoroutines {
	private suspend fun backOffAndRetractElevator() {
		launch { Robot.drive?.moveStraightNavX(-12.0, maxAMultiplier = 0.7) }
		delay(2000)
		Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0])?.join()
		Robot.elevator?.zeroElevator()
	}

	object FromLeft : AutoRoute {
		override suspend fun leftScale() {
//			Robot.intake?.startWheelsIn(output = 0.5)
			var triggered = false
			Robot.drive?.moveStraightNavX(254.0 /* for good luck */, fun(progress) {
				if (!triggered && progress > 0.8) {
					triggered = true
					Robot.intake?.startWheelsIn()
				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Robot.drive?.turnNavX(Math.PI / 6.0)
			//Robot.drive?.strafeNavX(38.0)
//			Robot.drive?.turnNavX(PI / 2)
			delay(1000)
			Robot.drive?.moveStraightNavX(34.0, maxAMultiplier = 0.7)
			elevatorJob?.join()
//			Robot.drive?.moveStraightNavX(5.0)
			Robot.intake?.eject()
			delay(750)

			backOffAndRetractElevator()
		}


		override suspend fun rightScale() {
			Robot.drive?.moveStraightNavX(225.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(258.0) // WITH cable protector in the middle

//			Robot.drive?.moveStraightNavX(225.0)
//			Robot.drive?.turnNavX(PI / 2)
//			Robot.drive?.moveStraightNavX(194.0)
//			Robot.drive?.turnNavX(-PI / 2)

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			delay(1200)
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject()
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			var triggered = false
			Robot.drive?.moveStraightNavX(141.0, fun(progress) {
				if (!triggered && progress > 0.8) {
					triggered = true
					Robot.intake?.startWheelsIn()
				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.turnNavX(Math.PI / 2.0)
			Robot.drive?.moveStraightNavX(12.0)
			elevatorJob?.join()
			Robot.intake?.eject()

			backOffAndRetractElevator()
		}

		override suspend fun rightSwitch() {
			Robot.drive?.moveStraightNavX(60.0)
			Robot.drive?.strafeNavX(242.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.moveStraightNavX(25.0)
			elevatorJob?.join()
			Robot.intake?.eject()
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
				if (!triggered && progress > 0.8) {
					triggered = true
					Robot.intake?.startWheelsIn()
				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Robot.drive?.turnNavX(-Math.PI / 6.0)
			delay(1000)
			Robot.drive?.moveStraightNavX(34.0, maxAMultiplier = 0.7)
			elevatorJob?.join()
			Robot.intake?.eject()
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftScale() {
			Robot.drive?.moveStraightNavX(225.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(-258.0) // WITH cable protector in the middle

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			delay(1200)
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)
			eJ?.join()
			Robot.intake?.eject()
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftSwitch() {
			Robot.drive?.moveStraightNavX(60.0)
			Robot.drive?.strafeNavX(-242.0)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.moveStraightNavX(25.0)
			elevatorJob?.join()
			Robot.intake?.eject()
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun rightSwitch() {
			var triggered = false
			Robot.drive?.moveStraightNavX(141.0, fun(progress) {
				if (!triggered && progress > 0.8) {
					triggered = true
					Robot.intake?.startWheelsIn()
				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.turnNavX(-Math.PI / 2.0)
			Robot.drive?.moveStraightNavX(12.0)
			elevatorJob?.join()
			Robot.intake?.eject()

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			Robot.drive?.moveStraightNavX(150.0)
		}
	}

	object FromCenter : AutoRoute {
		override suspend fun leftScale() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun rightScale() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun leftSwitch() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun rightSwitch() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun crossBaseline() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}
	}
}
