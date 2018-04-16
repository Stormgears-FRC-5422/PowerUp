package org.stormgears.powerup.auto.command

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
	private suspend fun backOffAndRetractElevator(dist: Double = -24.0, maxAMult: Double = 0.7, delay: Int = 2800, joinElevator: Boolean = true, elevatorPos: Int = 1) {
		val j = launch { Robot.drive?.moveStraightNavX(dist, maxAMultiplier = maxAMult) }
		delay(delay)
		val ej = Robot.elevator?.moveElevatorToPosition(elevatorPos)
		if (joinElevator) {
			ej?.join()
		}
		j.join()
	}

	object FromLeft : AutoRoute {
		override suspend fun leftScale() {
			Robot.drive?.moveStraightNavX(254.0 /* for good luck */)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			println("before turn")
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.94)
			delay(700)
			println("before movestraight 2")
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)

			delay(400)
			println("before joining elevator")
			elevatorJob?.join()
//			Robot.drive?.moveStraightNavX(5.0)
			Robot.intake?.eject(output = 0.4)
			delay(750)

			backOffAndRetractElevator()

			Robot.drive?.turnNavX(PI / 2 + PI / 6)
			Robot.intake?.startWheelsIn(0.7)
			Robot.drive?.moveStraightNavX(60.0)
		}

		override suspend fun rightScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

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
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}
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
			Robot.drive?.moveStraightNavX(254.0 /* for good luck */)
			println("before moveintake")
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)//?.join()
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			println("before turn")
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.94)
			delay(700)
			println("before movestraight 2")
			Robot.drive?.moveStraightNavX(42.0, maxAMultiplier = 0.7)

			delay(400)
			println("before joining elevator")
			elevatorJob?.join()
//			Robot.drive?.moveStraightNavX(5.0)
			Robot.intake?.eject(output = 0.4)
			delay(750)

			backOffAndRetractElevator()
		}

		override suspend fun leftScale() {
			if (!Choosers.crossField) {
				crossBaseline()
				return
			}

			Robot.drive?.moveStraightNavX(235.0)
//			Robot.drive?.strafeNavX(242.0)
			Robot.drive?.strafeNavX(-258.0) // WITH cable protector in the middle

			val eJ = Robot.elevator?.moveElevatorToPosition(Elevator.SCALE_POSITIONS[4])
			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
			delay(1200)
			Robot.drive?.moveStraightNavX(48.0, maxAMultiplier = 0.7)
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
			Robot.drive?.turnNavX(-Math.PI / 6.0 * 0.8)
			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.drive?.moveStraightNavX(120.0)
			Robot.intake?.eject(output = 1.0)
			Robot.drive?.turnNavX(Math.PI / 6.0 * 0.8)

			launch { delay(750); Robot.intake?.stopWheels() }

			backOffAndRetractElevator(-57.0, 1.0, 600, false)

			Robot.drive?.strafeNavX(64.0)
			var grabJob = Robot.intake?.grab()
			Robot.drive?.joystickMove(0.0, -0.2, 0.0)
			grabJob?.join()
			Robot.drive?.joystickMove(0.0, 0.0, 0.0)
//			delay(750)
			var elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0] + 6)
			Robot.drive?.strafeNavX(-64.0)
			Robot.drive?.moveStraightNavX(32.0)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)

			backOffAndRetractElevator(-24.0, 1.0, 600, false)

			Robot.drive?.turnNavX(PI / 4)
			grabJob = launch { delay(400); Robot.intake?.grab()?.join() }
			Robot.drive?.joystickMove(0.0, -0.2, 0.0)
			grabJob.join()
			Robot.drive?.joystickMove(0.0, 0.0, 0.0)

			elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0] + 6)
			Robot.drive?.moveStraightNavX(-8.0)
			Robot.drive?.turnNavX(-PI / 2 * 0.9)
			elevatorJob?.join()
			Robot.intake?.eject(1.0)
		}

		override suspend fun rightSwitch() {
			Robot.drive?.turnNavX(Math.PI / 12.0)
// 			Robot.drive?.moveStraightNavX(50.0)
//			Robot.drive?.strafeNavX(69.0)
			Robot.drive?.moveStraightNavX(121.0)
			//	val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
//			Intake.moveIntakeToPosition(Intake.HORIZONTAL)
//			Robot.drive?.moveStraightNavX(70.0)
			//	elevatorJob?.join()
			Robot.intake?.eject(output = 0.6)

			launch { delay(750); Robot.intake?.stopWheels() }

			backOffAndRetractElevator(-48.0, 0.8, 600)
			return
			Robot.drive?.strafeNavX(-49.0)
//			return
//			Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
			Robot.intake?.startWheelsIn(0.7)
			Robot.drive?.moveStraightNavX(34.0)
//			delay(750)
			val elevatorJob = Robot.elevator?.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1])
			Robot.drive?.strafeNavX(49.0)
			elevatorJob?.join()
			Robot.drive?.moveStraightNavX(48.0)
			Robot.intake?.eject(0.4)

			backOffAndRetractElevator()
		}

		override suspend fun crossBaseline() {
			// TODO: Not implemented
		}
	}
}
