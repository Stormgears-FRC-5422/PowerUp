package org.stormgears.powerup.commands

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.TerminableSubsystem

object Commands : TerminableSubsystem() {
	private var job: Job? = null

	suspend fun prepareToPlaceCube(height: Int = Robot.elevator!!.SCALE_POSITIONS[2]) {
		Elevator.moveElevatorToPosition(height).join()
		val angle = Robot.sensors!!.navX.getTheta()
		val sideShiftSide: Int
		if (angle <= Math.PI / 2 || angle >= 3 * Math.PI / 2) { // Robot is facing away

		}

	}

	fun grabCube() {
		if (job != null) {
			job!!.cancel()
		}

		launch("Cube Grab") {
			grabCubeSuspend()
		}

	}

	suspend fun grabCubeSuspend() {
		println("Grabbing cube!\nOpening gripper!")
		Gripper.openGripper().join()
		println("Moving elevator down!")
		Elevator.moveElevatorToPosition(2).join()
		println("Closing gripper!")
		Gripper.closeGripper().join()
		println("Moving elevator to SWITCH 0!")
		Elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0]).join()
	}

	suspend fun finesseTheCube(side: Int) {
		Elevator.zeroElevatorEncoder()
		Intake.startWheelsIn()
		delay(100)
		Gripper.openGripper().join()
		Elevator.moveElevatorToPosition(-3)
		delay(200)
		Gripper.closeGripper(useTime = true, timeMs = 3000)
		delay(700)
		Elevator.moveElevatorToPosition(0).join()
		Elevator.moveElevatorToPosition(20).join()
		Intake.stopWheels()
//		Intake.moveIntakeToPosition(Intake.HORIZONTAL)
//		Elevator.moveSideShiftToPositionSuspend(side)
//		Gripper.openGripperSuspend()
//		Elevator.moveSideShiftToPositionSuspend(Elevator.CENTER)
//		Elevator.zeroElevator()
	}
}

