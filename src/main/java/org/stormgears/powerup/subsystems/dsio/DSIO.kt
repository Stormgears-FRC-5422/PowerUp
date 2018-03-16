package org.stormgears.powerup.subsystems.dsio

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.subsystems.dsio.joystickdetection.JoystickDetector
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.Terminator
import org.stormgears.utils.dsio.IRawJoystick
import org.stormgears.utils.dsio.ITernarySwitch

object DSIO {
	private val logger = LogManager.getLogger(DSIO::class.java)

	private var joystickEnabled = true

	var choosers = Choosers()

	val joystick: IRawJoystick
	val buttonBoard: IButtonBoard

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
			logger.warn("Intake wheels switch is not ternary, not sure what to do!") // TODO
		}

		val intakeLiftSwitch = buttonBoard.intakeLiftSwitch
		if (intakeLiftSwitch is ITernarySwitch) {
			intakeLiftSwitch.whenFlippedTernary({ state: ITernarySwitch.SwitchState ->
				when (state) {
					ITernarySwitch.SwitchState.Up -> Robot.intake.moveIntakeToPosition(Intake.HORIZONTAL)
					ITernarySwitch.SwitchState.Down -> Robot.intake.moveIntakeToPosition(Intake.VERTICAL)
					ITernarySwitch.SwitchState.Neutral -> {
					} // TODO
				}
			})
		} else {
			logger.warn("Intake lift switch is not ternary, not sure what to do!") // TODO
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
			Terminator.disabled = on
		}
	}

	val shouldOverride: Boolean
		get() = buttonBoard.overrideSwitch.get()

	// Joystick related methods

	private const val X_NULLZONE = 0.2
	private const val Y_NULLZONE = 0.2
	private const val Z_NULLZONE = 0.1

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
		var processedValue = value
		if (Math.abs(processedValue) < nullzone) {
			return 0.0
		} else {
			processedValue = (processedValue - Math.copySign(nullzone, processedValue)) / (1 - nullzone)
		}

		if (joystick.getRawButton(ButtonIds.Joystick.THUMB)) {
			return 0.2 * processedValue
		}

		processedValue *= Math.abs(processedValue)

		return if (Robot.config.reverseJoystick) {
			-processedValue
		} else {
			processedValue
		}
	}
}
