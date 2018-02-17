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
		logger.info("Gripper Opening");
		x = 0;
		talon.set(ControlMode.PercentOutput, 0.5);
		SmartDashboard.putNumber("Gripper Open Current", talon.getOutputCurrent());
	}

	static int x = 0;

	public void resetX() {
		x = 0;
	}

	public void closeGripper() {
		StormScheduler.getInstance().queue(() -> {
			logger.info("Gripper Closing");

			if (talon.getOutputCurrent() <= 4 & x == 0) {
				System.out.println("Gripper Closing");
				talon.set(ControlMode.PercentOutput, -0.5);
				System.out.println(talon.getOutputCurrent());
				SmartDashboard.putNumber("Gripper Close Current", talon.getOutputCurrent());
				while (talon.getOutputCurrent() <= 0.5) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				talon.set(ControlMode.PercentOutput, 0);
			}
			if (x == 0) {
				x++;
				System.out.println(talon.getOutputCurrent());
			}
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
