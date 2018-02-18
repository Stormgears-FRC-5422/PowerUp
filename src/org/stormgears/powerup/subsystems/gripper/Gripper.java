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
	private static final int TALON_ID = 4;

	private static final double GRIPPER_POWER = 0.5;
	private static final double CURRENT_LIMIT = 5.0;

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
			talon.set(ControlMode.PercentOutput, -GRIPPER_POWER);

			while (!shouldTerminate && talon.getOutputCurrent() <= CURRENT_LIMIT) {
				synchronized (lock) {
					shouldTerminate = this.shouldTerminate;
//						logger.info("shouldTerminate: {}", shouldTerminate);
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
			talon.set(ControlMode.PercentOutput, GRIPPER_POWER);

			while (!shouldTerminate && talon.getOutputCurrent() <= CURRENT_LIMIT) {
				synchronized (lock) {
					shouldTerminate = this.shouldTerminate;
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
