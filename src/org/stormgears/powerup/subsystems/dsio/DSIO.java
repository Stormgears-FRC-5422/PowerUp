package org.stormgears.powerup.subsystems.dsio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.dsio.joystick_detection.JoystickDetector;
import org.stormgears.utils.TerminatableSubsystem;
import org.stormgears.utils.dsio.IRawJoystick;

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
		buttonBoard.getGripCloseButton().whenPressed(() -> Robot.gripper.closeGripper());

		buttonBoard.getGripOpenButton().whenPressed(() -> Robot.gripper.openGripper());




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
		joystickEnabled = true;
	}

	public void disableDriveControls() {
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
