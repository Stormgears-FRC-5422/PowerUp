package org.stormgears.powerup.subsystems.gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.StormTalon;

public class Gripper extends Subsystem {
	private static final Logger logger = LogManager.getLogger(Gripper.class);
	private static Gripper instance;

	public static Gripper getInstance() {
		return instance;
	}

	//TODO: Change to correct value
	private static final int TALON_ID = 8;

	private static final double GRIPPER_POWER = 0.5;
	private static final double CLOSE_CURRENT_LIMIT = 3.0;
	private static final double OPEN_CURRENT_LIMIT = 1.5;
	private static final int CURRENT_CHECK_START_TIME = 100;

	private StormTalon talon;
	private final Object lock = new Object();

	private boolean gripperClosing = false;
	private boolean gripperOpening = false;
	private boolean shouldTerminate = false;

	private Runnable closeThread, openThread;

	private Gripper(int TalonId) {
		talon = new StormTalon(TalonId);

		closeThread = () -> {
			logger.info("Sending new close command");
			boolean shouldTerminate = false;

			logger.info("Gripper Closing");
			talon.set(ControlMode.PercentOutput, -1);
			boolean shouldTrackCurrent = false;
			int iteration = 0;

			while (!shouldTerminate && (talon.getOutputCurrent() <= CLOSE_CURRENT_LIMIT || !shouldTrackCurrent)) {
				iteration++;

				synchronized (lock) {
					shouldTerminate = this.shouldTerminate;
				}

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
				this.shouldTerminate = false;
			}
		};

		openThread = () -> {
			boolean shouldTerminate = false;

			logger.info("Gripper Opening");
			talon.set(ControlMode.PercentOutput, 1);
			boolean shouldTrackCurrent = false;
			int iteration = 0;

			while (!shouldTerminate && (talon.getOutputCurrent() <= OPEN_CURRENT_LIMIT || !shouldTrackCurrent)) {
				iteration++;

				synchronized (lock) {
					shouldTerminate = this.shouldTerminate;
				}

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
				this.shouldTerminate = false;
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

	public void disableGripper() {
		shouldTerminate = true;
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
