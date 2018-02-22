package org.stormgears.powerup.subsystems.dsio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.dsio.joystick_detection.JoystickDetector;
import org.stormgears.powerup.subsystems.elevator_climber.Elevator;
import org.stormgears.powerup.subsystems.intake.Intake;
import org.stormgears.utils.TerminatableSubsystem;
import org.stormgears.utils.dsio.IRawJoystick;
import org.stormgears.utils.dsio.ITernarySwitch;
import org.stormgears.utils.dsio.TernarySwitch;

public class DSIO {
	private static final Logger logger = LogManager.getLogger(DSIO.class);

	private static DSIO instance;

	public static DSIO getInstance() {
		return instance;
	}

	public static void init() {
		if (instance == null) {
			instance = new DSIO();
		}
	}

	private boolean joystickEnabled = true;

	public Choosers choosers = new Choosers();

	private IRawJoystick joystick;
	private IButtonBoard buttonBoard;

	private DSIO() {
		logger.trace("constructing DSIO");

		JoystickDetector detector = new JoystickDetector();
		detector.detect();

		joystick = detector.getDrivingJoystick();
		buttonBoard = detector.getButtonBoard();

		setupButtonsAndSwitches();
	}

	/*
	 * If you want a button/switch to do something, write it in the appropriate Lambda block below.
	 */
	private void setupButtonsAndSwitches() {
		logger.trace("setting up buttons");

		for (int i = 0; i < buttonBoard.getScaleButtons().length; i++) {
			int finalI = i;
			buttonBoard.getScaleButtons()[i].whenPressed(() -> Robot.elevator.moveElevatorToPosition(Elevator.SCALE_POSITIONS[finalI]));
		}

		buttonBoard.getSwitch0Button().whenPressed(() -> Robot.elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[0]));
		buttonBoard.getSwitch1Button().whenPressed(() -> Robot.elevator.moveElevatorToPosition(Elevator.SWITCH_POSITIONS[1]));

		buttonBoard.getDropButton().whenPressed(() -> Robot.gripper.openGripper()); // TODO: What does this button do?

		buttonBoard.getSideLeftButton().whenPressed(() -> Robot.elevator.moveSideShiftOverLeft());
		buttonBoard.getSideRightButton().whenPressed(() -> Robot.elevator.moveSideShiftOverRight());

		buttonBoard.getIntakeGrabButton().whenPressed(() -> { });
		((ITernarySwitch) buttonBoard.getIntakeWheelsSwitch()).whenFlippedTernary((TernarySwitch.SwitchState state) -> {
			switch (state) {
				case Up: Robot.intake.startWheelsOut(); break;
				case Neutral: Robot.intake.stopWheels(); break;
				case Down: Robot.intake.startWheelsIn(); break;
			}
		});
		((ITernarySwitch) buttonBoard.getIntakeLiftSwitch()).whenFlippedTernary((TernarySwitch.SwitchState state) -> {
			switch (state) {
				case Up: Robot.intake.moveIntakeToPosition(Intake.HORIZONTAL); break;
				case Neutral: Robot.intake.moveIntakeToPosition(Intake.DIAGONAL); break;
				case Down: Robot.intake.moveIntakeToPosition(Intake.VERTICAL); break;
			}
		});

		buttonBoard.getGripCloseButton().whenPressed(() -> Robot.gripper.closeGripper());
		buttonBoard.getGripOpenButton().whenPressed(() -> Robot.gripper.openGripper());

		buttonBoard.getClimbUpButton().whenPressed(() -> { /* Raise Climber */ });
		buttonBoard.getClimbDownButton().whenPressed(() -> { /* Lower Climber */ });

		buttonBoard.getOverrideUp().whenPressed(() -> Robot.elevator.moveUpManual());
		buttonBoard.getOverrideDown().whenPressed(() -> Robot.elevator.moveDownManual());
		buttonBoard.getOverrideLeft().whenPressed(() -> Robot.elevator.moveLeftManual());
		buttonBoard.getOverrideRight().whenPressed(() -> Robot.elevator.moveRightManual());
		buttonBoard.getOverrideUp().whenReleased(() -> Robot.elevator.stop());
		buttonBoard.getOverrideDown().whenReleased(() -> Robot.elevator.stop());
		buttonBoard.getOverrideLeft().whenReleased(() -> Robot.elevator.stop());
		buttonBoard.getOverrideRight().whenReleased(() -> Robot.elevator.stop());

		buttonBoard.getOverrideSwitch().whenFlipped(on -> {
			if (on) {
				TerminatableSubsystem.terminateCurrentLongRunningOperations();
			} else {
				TerminatableSubsystem.allowLongRunningOperations();
			}
		});
	}


	// Joystick related methods

	private static final double X_NULLZONE = 0.2;
	private static final double Y_NULLZONE = 0.2;
	private static final double Z_NULLZONE = 0.1;

	public void enableDriveControls() {
		logger.info("Enabling drive controls");
		joystickEnabled = true;
	}

	public void disableDriveControls() {
		logger.info("Disabling drive controls");
		joystickEnabled = false;
	}

	public double getJoystickX() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickX(), X_NULLZONE);
	}

	public double getJoystickY() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickY(), Y_NULLZONE);
	}

	public double getJoystickZ() {
		if (!joystickEnabled) return 0;

		return processJoystick(joystick.getJoystickZ(), Z_NULLZONE);
	}

	private double processJoystick(double val, final double nullzone) {
		if (Math.abs(val) < nullzone) {
			return 0;
		} else {
			val = ((val - Math.copySign(nullzone, val)) / (1 - nullzone));
		}

		if (joystick.getRawButton(ButtonIds.Joystick.THUMB)) {
			return 0.2 * val;
		}

		val *= Math.abs(val);

		if (Robot.config.reverseJoystick) {
			return -val;
		} else {
			return val;
		}
	}
}
