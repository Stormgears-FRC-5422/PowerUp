package org.stormgears.powerup.commands

import kotlinx.coroutines.experimental.Job
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.gripper.Gripper
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
}

