package org.stormgears.powerup.commands

import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator

suspend fun prepareToPlaceCube(height: Int = Robot.elevator.SCALE_POSITIONS[2]) {
	Elevator.elevatorAutoMove(height)
	val angle = Robot.sensors.navX.theta
	val sideShiftSide: Int
	if (angle <= Math.PI / 2 || angle >= 3 * Math.PI / 2) { // Robot is facing away

	}

}
