package org.stormgears.powerup.subsystems.dsio

import org.apache.logging.log4j.LogManager
import org.stormgears.powerup.Robot
import org.stormgears.powerup.commands.Commands
import org.stormgears.powerup.subsystems.dsio.joystickdetection.JoystickDetector
import org.stormgears.powerup.subsystems.elevatorclimber.Elevator
import org.stormgears.powerup.subsystems.intake.Intake
import org.stormgears.utils.concurrency.Terminator
import org.stormgears.utils.dsio.IRawJoystick
import org.stormgears.utils.dsio.ITernarySwitch

object DSIO {
	private val logger = LogManager.getLogger(DSIO::class.java)

	private var joystickEnabled = true

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
			buttonBoard.scaleButtons[i].whenPressed {
				Commands.placeCube(Elevator.SCALE_POSITIONS[i], 1)
			}
		}

		buttonBoard.switch0Button.whenPressed {
			logger.trace("SWITCH BUTTON PRESSED 1")
			Commands.placeCube(Elevator.SWITCH_POSITIONS[0], 1)
		}
		buttonBoard.switch1Button.whenPressed {
			logger.trace("SWITCH BUTTON PRESSED 2")
			Commands.placeCube(Elevator.SWITCH_POSITIONS[1], 1)
		}

		buttonBoard.dropButton.whenPressed { Robot.intake?.eject(output = if (buttonBoard.speedSwitch.get()) 0.2 else 0.8, forceHorizontal = true) }

		// THIS BUTTON WILL BE USED AS THE RESET BUTTON
		buttonBoard.sideLeftButton.whenPressed {
			Commands.reset()
		}

		buttonBoard.intakeGrabButton.whenPressed { Intake.grab() }

		buttonBoard.forceZeroElevatorButton.whenPressed { Robot.elevator?.launchZeroElevator() }
		buttonBoard.zeroElevatorButton.whenPressed { Robot.elevator?.zeroElevatorEncoder() }

		val intakeWheelsSwitch = buttonBoard.intakeWheelsSwitch
		if (intakeWheelsSwitch is ITernarySwitch) {
			intakeWheelsSwitch.whenFlippedTernary({ state: ITernarySwitch.SwitchState ->
				when (state) {
					ITernarySwitch.SwitchState.Up -> Robot.intake?.startWheelsOut(0.4)
					ITernarySwitch.SwitchState.Neutral -> Robot.intake?.stopWheels()
					ITernarySwitch.SwitchState.Down -> Robot.intake?.startWheelsIn(1.0)
				}
			})
		} else {
			logger.warn("Intake wheels switch is not ternary, not sure what to do!")
		}

		val intakeLiftSwitch = buttonBoard.intakeLiftSwitch
		if (intakeLiftSwitch is ITernarySwitch) {
			intakeLiftSwitch.whenFlippedTernary({ state: ITernarySwitch.SwitchState ->
				when (state) {
					ITernarySwitch.SwitchState.Up -> Robot.intake?.moveIntakeToPosition(Intake.HORIZONTAL)
					ITernarySwitch.SwitchState.Down -> Robot.intake?.moveIntakeToPosition(Intake.VERTICAL)
					ITernarySwitch.SwitchState.Neutral -> {
					}
				}
			})
		} else {
			logger.warn("Intake lift switch is not ternary, not sure what to do!")
		}

		buttonBoard.overrideUp.whileHeld { Robot.elevator?.moveUpManual() }
		buttonBoard.overrideDown.whileHeld { Robot.elevator?.moveDownManual() }
		buttonBoard.overrideUp.whenReleased { Robot.elevator?.stop() }
		buttonBoard.overrideDown.whenReleased { Robot.elevator?.stop() }

		buttonBoard.overrideSwitch.whenFlipped { on -> Terminator.disabled = on }

		buttonBoard.intakeTrigger.whenPressed { Robot.intake?.grab(forceVertical = false) }
		buttonBoard.intakeTrigger.whenReleased { Robot.intake?.eject(output = if (buttonBoard.speedSwitch.get()) 0.2 else 0.8, checkLimitSwitch = true, forceHorizontal = false) }
	}

	val intakeSpeed: Double
		get(): Double = 1.0 // (buttonBoard.speedPot.value - 0.5) * 2

	// Joystick related methods

	private const val X_NULLZONE = 0.2
	private const val Y_NULLZONE = 0.2
	private const val Z_NULLZONE = 0.1

	val joystickX: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickX, X_NULLZONE)

	val joystickY: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickY, Y_NULLZONE)

	val joystickZ: Double
		get() = if (!joystickEnabled) 0.0 else processJoystick(joystick.joystickZ, Z_NULLZONE, 0.5)

	fun enableDriveControls() {
		logger.info("Enabling drive controls")
		joystickEnabled = true
	}

	fun disableDriveControls() {
		logger.info("Disabling drive controls")
		joystickEnabled = false
	}

	fun tick() {
		Robot.intake?.setVelocity(intakeSpeed)
	}

	private fun processJoystick(value: Double, nullzone: Double, thumbMultiplier: Double = 0.2): Double {
		var processedValue = value
		if (Math.abs(processedValue) < nullzone) {
			return 0.0
		} else {
			processedValue = (processedValue - Math.copySign(nullzone, processedValue)) / (1 - nullzone)
		}

		if (joystick.getRawButton(ButtonIds.Joystick.THUMB)) {
			return thumbMultiplier * processedValue
		}

//		processedValue *= Math.abs(processedValue)

		return if (Robot.config.reverseJoystick) {
			-processedValue
		} else {
			processedValue
		}
	}
}
