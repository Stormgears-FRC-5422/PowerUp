package org.stormgears.powerup.commands

import kotlinx.coroutines.experimental.Job
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.gripper.Gripper
import org.stormgears.utils.concurrency.TerminableSubsystem

object Commands : TerminableSubsystem() {
	private var job: Job? = null

	suspend fun prepareToPlaceCube(height: Int = Robot.elevator!!.SCALE_POSITIONS[2]) {
		Elevator.elevatorAutoMove(height)
		val angle = Robot.sensors!!.navX.theta
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
		Gripper.openGripperSuspend()
		println("Moving elevator down!")
		Elevator.elevatorAutoMove(2, slowly = true)
		println("Closing gripper!")
		Gripper.closeGripperSuspend()
		println("Moving elevator to SWITCH 0!")
		Elevator.elevatorAutoMove(Elevator.SWITCH_POSITIONS[0])
	}
}

