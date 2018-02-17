package org.stormgears.powerup.subsystems.gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		talon.set(ControlMode.PercentOutput, 0.5);
		SmartDashboard.putNumber("Gripper Open Current", talon.getOutputCurrent());
	}

	public void closeGripper() {
		if (talon.getOutputCurrent() <= 0.5) {
			logger.info("Gripper Closing");
			talon.set(ControlMode.PercentOutput, -0.5);
			SmartDashboard.putNumber("Gripper Close Current", talon.getOutputCurrent());
		} else {
			talon.set(ControlMode.PercentOutput, 0);
		}
	}


	public void disableGripper() {
		logger.info("Gripper Disabled");
		talon.set(ControlMode.PercentOutput, 0);
	}

	@Override
	protected void initDefaultCommand() {

	}

}
