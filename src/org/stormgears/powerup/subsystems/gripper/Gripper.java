package org.stormgears.powerup.subsystems.gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

	private Gripper(int TalonId) {
		talon = new StormTalon(TalonId);
	}

	public static void init() {
		instance = new Gripper(TALON_ID);
	}


	public void openGripper() {
		while (talon.getOutputCurrent() <= 2.5) {
			talon.set(ControlMode.PercentOutput, 0.25);
			SmartDashboard.putNumber("Gripper Open Current", talon.getOutputCurrent());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		talon.set(ControlMode.PercentOutput, 0);
	}

	public void closeGripper() {
		StormScheduler.getInstance().queue(() -> {
			logger.info("Gripper Closing");

			while (talon.getOutputCurrent() <= 2.5) {
				talon.set(ControlMode.PercentOutput, -0.25);
				System.out.println("hi" + talon.getOutputCurrent());
				SmartDashboard.putNumber("Gripper Close Current", talon.getOutputCurrent());
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			talon.set(ControlMode.PercentOutput, 0);

		});
	}


	public void disableGripper() {
		logger.info("Gripper Disabled");
		talon.set(ControlMode.PercentOutput, 0);
	}

	@Override
	protected void initDefaultCommand() {

	}

}
