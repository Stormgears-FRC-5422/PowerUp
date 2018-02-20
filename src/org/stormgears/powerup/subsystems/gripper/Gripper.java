package org.stormgears.powerup.subsystems.gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.StormTalon;
import org.stormgears.utils.TerminatableSubsystem;

public class Gripper extends TerminatableSubsystem {
	private static final Logger logger = LogManager.getLogger(Gripper.class);
	private static Gripper instance;

	public static Gripper getInstance() {
		return instance;
	}

	//TODO: Change to correct value
	private static final int TALON_ID = 11;

	private static final double GRIPPER_POWER = 0.25;
	private static final double CLOSE_CURRENT_LIMIT = 4.5;
	private static final double OPEN_CURRENT_LIMIT = 3.0;
	private static final int CURRENT_CHECK_START_TIME = 12;
	private static final double BREAK_JAM_SPEED = 0.75;
	private static final double SLOW_DOWN_FACTOR = 0.005;

	private StormTalon talon;
	private final Object lock = new Object();

	private boolean gripperClosing = false;
	private boolean gripperOpening = false;

	private Runnable closeThread, openThread;

	private Gripper(int TalonId) {
		talon = new StormTalon(TalonId);

		closeThread = () -> {
			logger.info("Gripper Closing");
			talon.set(ControlMode.PercentOutput, -BREAK_JAM_SPEED);
			boolean shouldTrackCurrent = false;
			int iteration = 0;

			while (isAllowed() && (talon.getOutputCurrent() <= CLOSE_CURRENT_LIMIT || !shouldTrackCurrent)) {
				iteration++;

				if (iteration > CURRENT_CHECK_START_TIME) {
					shouldTrackCurrent = true;
					talon.set(ControlMode.PercentOutput, -GRIPPER_POWER);
				}

				waitMs(20);
			}

			logger.info("Cube is being hugged or terminated early");
			synchronized (lock) {
				talon.set(ControlMode.PercentOutput, 0);

				gripperClosing = false;
			}
		};

		openThread = () -> {
			logger.info("Gripper Opening");
			talon.set(ControlMode.PercentOutput, BREAK_JAM_SPEED);
			boolean shouldTrackCurrent = false;
			int iteration = 0;

			while (isAllowed() && talon.getSensorCollection().isRevLimitSwitchClosed() &&
				(talon.getOutputCurrent() <= OPEN_CURRENT_LIMIT || !shouldTrackCurrent)) {
				iteration++;

				if (iteration > CURRENT_CHECK_START_TIME) {
					shouldTrackCurrent = true;
					talon.set(ControlMode.PercentOutput, GRIPPER_POWER);
				}

				waitMs(20);
			}

			logger.info("Gripper limit is reached or terminated early");
			synchronized (lock) {
				talon.set(ControlMode.PercentOutput, 0);

				gripperOpening = false;
			}
		};
	}

	public static void init() {
		instance = new Gripper(TALON_ID);
	}

	public void openGripper() {
		if (!gripperOpening) {
			gripperOpening = true;

			StormScheduler.getInstance().async(openThread);
		}
	}

	public void closeGripper() {
		if (!gripperClosing) {
			gripperClosing = true;

			StormScheduler.getInstance().async(closeThread);
		}
	}

	private void waitMs(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {

	}
}
