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

	private StormTalon talon;
	private final Object lock = new Object();

	private boolean gripperClosing = false;
	private boolean gripperOpening = false;
	private boolean shouldTerminate = false;

	private Gripper(int TalonId) {
		talon = new StormTalon(TalonId);
	}

	public static void init() {
		instance = new Gripper(TALON_ID);
	}

	public void openGripper() {
		if (!gripperOpening) {
			gripperOpening = true;

			StormScheduler.getInstance().async(() -> {
				boolean shouldTerminate = false;

				logger.info("Gripper Opening");
				talon.set(ControlMode.PercentOutput, 0.25);

				while (!shouldTerminate && talon.getOutputCurrent() <= 2.5) {
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
			});
		}
	}

	public void closeGripper() {
		if (!gripperClosing) {
			gripperClosing = true;

			StormScheduler.getInstance().async(() -> {
				logger.info("Sending new close command");
				boolean shouldTerminate = false;

				logger.info("Gripper Closing");
				talon.set(ControlMode.PercentOutput, -0.25);

				while (!shouldTerminate && talon.getOutputCurrent() <= 2.5) {
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
			});
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
