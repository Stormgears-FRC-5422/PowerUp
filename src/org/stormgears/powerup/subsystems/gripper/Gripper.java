package org.stormgears.powerup.subsystems.gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.utils.StormScheduler;
import org.stormgears.utils.StormTalon;

public class Gripper extends Subsystem {
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
		System.out.println("Gripper Opening");
		talon.set(ControlMode.PercentOutput, 0.5);
		SmartDashboard.putNumber("Gripper Open Current", talon.getOutputCurrent());
	}

	public void closeGripper() {
		StormScheduler.getInstance().queue(() -> {
			System.out.println("Gripper Closing");
			talon.set(ControlMode.PercentOutput, -0.5);
			SmartDashboard.putNumber("Gripper Close Current", talon.getOutputCurrent());

			while (talon.getOutputCurrent() <= 0.5) {
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
		System.out.println("Gripper Disabled");
		talon.set(ControlMode.PercentOutput, 0);
	}

	@Override
	protected void initDefaultCommand() {

	}

}
