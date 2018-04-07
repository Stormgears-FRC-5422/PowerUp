package org.stormgears.powerup.auto.command

import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator

/**
 * Routes for robot in AUTONOMOUS ONLY!!!
 */
object AutoRoutes {
	object FromLeft : AutoRoute {
		override suspend fun leftScale() {
//			Robot.intake?.startWheelsIn(output = 0.5)
			var triggered = false
			Robot.drive?.moveStraightNavX(253.0, fun(progress) {
				if (!triggered && progress > 0.8) {
					triggered = true
					Robot.intake?.startWheelsIn()
				}
			})
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Robot.drive?.turnNavX(Math.PI / 6.0)
			//Robot.drive?.strafeNavX(38.0)
//			Robot.drive?.turnNavX(PI / 2)
			Robot.drive?.moveStraightNavX(23.5)
			elevatorJob?.join()
//			Robot.drive?.moveStraightNavX(5.0)
			Robot.intake?.eject()
		}


		override suspend fun rightScale() {
			Robot.drive?.moveStraightNavX(215.0)
			Robot.drive?.strafeNavX(230.5)
			Robot.drive?.moveStraightNavX(200.0)
			Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])?.join()
			Robot.intake?.startWheelsOut()
		}

		override suspend fun leftSwitch() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun rightSwitch() {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		override suspend fun crossBaseline() {
			Robot.drive?.moveStraightNavX(150.0)
		}
	}

	object FromRight : AutoRoute {
		override suspend fun rightScale() {

			Robot.drive?.moveStraightNavX(253.0)
			Robot.drive?.strafeNavX(-38.0)
//			Robot.drive?.turnNavX(PI / 2)
			Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])?.join()
			Robot.drive?.moveStraightNavX(24.0)
			Robot.intake?.startWheelsOut()
		}

		override suspend fun leftScale() {
			Robot.drive?.moveStraightNavX(215.0)
			Robot.drive?.strafeNavX(-230.5)
			Robot.drive?.moveStraightNavX(200.0)
			Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])?.join()
			Robot.intake?.startWheelsOut()
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
