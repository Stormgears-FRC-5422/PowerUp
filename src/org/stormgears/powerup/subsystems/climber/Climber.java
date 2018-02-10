package org.stormgears.powerup.subsystems.climber;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.utils.StormTalon;

public class Climber extends Subsystem {

	public StormTalon climberTalon;


	/**
	 * Creates a new Talon for the climber
	 * @param ID is the Talon ID
	 */
	public Climber(int ID) {
		climberTalon = new StormTalon(ID);
	}

	/**
	 * Moves the motor however many encoder ticks inputted
	 * @param throttleValue is the encoder ticks for the Talon to go
	 */
	public void climb(double throttleValue) {
		climberTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		climberTalon.set(ControlMode.Velocity ,throttleValue);
	}

	/**
	 * Stops motor
	 */
	public void stop() {
		climberTalon.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		climberTalon.set(0);
	}
}
