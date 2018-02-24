package org.stormgears.powerup.subsystems.dsio

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.Robot.shooter
import org.stormgears.powerup.subsystems.dsio.joystick_detection.JoystickDetector
import org.stormgears.powerup.subsystems.elevator_climber.Elevator
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.powerup.subsystems.shooter.Shooter
import org.stormgears.utils.TerminatableSubsystem
import org.stormgears.utils.dsio.IRawJoystick
import org.stormgears.utils.dsio.ITernarySwitch

object DSIO {
	private val logger = LogManager.getLogger(DSIO::class.java)

	private var joystickEnabled = true

	var choosers = Choosers()

	private val joystick: IRawJoystick
	private val buttonBoard: IButtonBoard

	init {
		logger.trace("constructing DSIO")

		val detector = JoystickDetector()
		detector.detect()

		joystick = detector.drivingJoystick
		buttonBoard = detector.buttonBoard

		setupButtonsAndSwitches()
	}

	/*
     * If you want a button/switch to do something, write it in the appropriate Lambda block below.
     */
	private fun setupButtonsAndSwitches() {
		logger.trace("setting up buttons")

		for (i in 0 until buttonBoard.scaleButtons.size) {
			buttonBoard.scaleButtons[i].whenPressed { Robot.elevator.moveElevatorToPosition(Elevator.SCALE_POSITIONS[i]) }
		}

		buttonBoard.switch0Button.whenPressed { Robot.elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0]) }
		buttonBoard.switch1Button.whenPressed { Robot.elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1]) }

		buttonBoard.dropButton.whenPressed { Robot.gripper.openGripper() } // TODO: What does this button do?

		buttonBoard.sideLeftButton.whenPressed { Robot.elevator.moveSideShiftOverLeft() }
		buttonBoard.sideRightButton.whenPressed { Robot.elevator.moveSideShiftOverRight() }

		buttonBoard.intakeGrabButton.whenPressed { }


		val intakeWheelsSwitch = buttonBoard.intakeWheelsSwitch
		if (intakeWheelsSwitch is ITernarySwitch) {
			intakeWheelsSwitch.whenFlippedTernary({ state: ITernarySwitch.SwitchState ->
				when (state) {
					ITernarySwitch.SwitchState.Up -> Robot.intake.startWheelsOut()
					ITernarySwitch.SwitchState.Neutral -> Robot.intake.stopWheels()
					ITernarySwitch.SwitchState.Down -> Robot.intake.startWheelsIn()
				}
			})
		} else {
			logger.warn("Assuming this is the 2017 board!")
			intakeWheelsSwitch.whenFlipped({ on: Boolean ->
				when (on) {
					true ->  {logger.warn("Shooter flipped on!")
						      Shooter.getInstance().shoot()}
					false -> {logger.warn("Shooter flipped off!")
							  Shooter.getInstance().stop()}
				}
			})
		}

		val intakeLiftSwitch = buttonBoard.intakeLiftSwitch
		if (intakeLiftSwitch is ITernarySwitch) {
			intakeLiftSwitch.whenFlippedTernary({ state: ITernarySwitch.SwitchState ->
				when (state) {
					ITernarySwitch.SwitchState.Up -> Robot.intake.moveIntakeToPosition(Intake.HORIZONTAL)
					ITernarySwitch.SwitchState.Neutral -> Robot.intake.moveIntakeToPosition(Intake.DIAGONAL)
					ITernarySwitch.SwitchState.Down -> Robot.intake.moveIntakeToPosition(Intake.VERTICAL)
				}
			})
		} else {
			logger.warn("Assuming this is the 2017 board!")
			intakeLiftSwitch.whenFlipped({ on: Boolean ->
				when (on) {
					true ->  {logger.warn("Intake flipped on!")
						Shooter.getInstance().startIntake()}
					false -> {logger.warn("Intake flipped off!")
						Shooter.getInstance().stopIntake()}
				}
			})

		}

		buttonBoard.gripCloseButton.whenPressed { Robot.gripper.closeGripper() }
		buttonBoard.gripOpenButton.whenPressed { Robot.gripper.openGripper() }

		buttonBoard.climbUpButton.whenPressed { /* Raise Climber */ }
		buttonBoard.climbDownButton.whenPressed { /* Lower Climber */ }

		buttonBoard.overrideUp.whenPressed { Robot.elevator.moveUpManual() }
		buttonBoard.overrideDown.whenPressed { Robot.elevator.moveDownManual() }
		buttonBoard.overrideLeft.whenPressed { Robot.elevator.moveLeftManual() }
		buttonBoard.overrideRight.whenPressed { Robot.elevator.moveRightManual() }
		buttonBoard.overrideUp.whenReleased { Robot.elevator.stop() }
		buttonBoard.overrideDown.whenReleased { Robot.elevator.stop() }
		buttonBoard.overrideLeft.whenReleased { Robot.elevator.stop() }
		buttonBoard.overrideRight.whenReleased { Robot.elevator.stop() }

		buttonBoard.overrideSwitch.whenFlipped { on ->
//			if (on) {
//				TerminatableSubsystem.terminateCurrentLongRunningOperations()
//			} else {
//				TerminatableSubsystem.allowLongRunningOperations()
//			}
			when (on) {
				true ->  {logger.warn("Climber flipped on!")
					Shooter.getInstance().startClimber()}
				false -> {logger.warn("Climber flipped off!")
					Shooter.getInstance().stopClimber()}
			}


		}
	}

	// Joystick related methods

	private val X_NULLZONE = 0.2
	private val Y_NULLZONE = 0.2
	private val Z_NULLZONE = 0.1

	val joystickX: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickX, X_NULLZONE)

	val joystickY: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickY, Y_NULLZONE)

	val joystickZ: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickZ, Z_NULLZONE)

	fun enableDriveControls() {
		logger.info("Enabling drive controls")
		joystickEnabled = true
	}

	fun disableDriveControls() {
		logger.info("Disabling drive controls")
		joystickEnabled = false
	}

	private fun processJoystick(value: Double, nullzone: Double): Double {
		var value = value
		if (Math.abs(value) < nullzone) {
			return 0.0
		} else {
			value = (value - Math.copySign(nullzone, value)) / (1 - nullzone)
		}

		if (joystick.getRawButton(ButtonIds.Joystick.THUMB)) {
			return 0.2 * value
		}

		value *= Math.abs(value)

		return if (Robot.config.reverseJoystick) {
			-value
		} else {
			value
		}
	}
}
