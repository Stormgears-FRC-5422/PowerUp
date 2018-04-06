package org.stormgears.powerup.commands

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
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

	suspend fun finesseTheCube(side: Int) {
		// TODO: New finesseTheCube needed since half the robot was removed

//		Intake.moveIntakeToPosition(Intake.HORIZONTAL)
//		Elevator.moveSideShiftToPositionSuspend(side)
//		Gripper.openGripperSuspend()
//		Elevator.moveSideShiftToPositionSuspend(Elevator.CENTER)
//		Elevator.zeroElevator()
	}
}

