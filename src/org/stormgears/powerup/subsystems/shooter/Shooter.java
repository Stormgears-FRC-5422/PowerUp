package org.stormgears.powerup.subsystems.shooter;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.StormUtils.SafeTalon;
import org.stormgears.utils.StormTalon;

public class Shooter  {

	public StormTalon shooterTalon;
	public StormTalon impellorTalon;

	boolean isPressed = false;

	double shootVelocity = 0.5;
	double impellorVelocity = 0.5; //TODO: Find velodity for impellor

	public Shooter(int shooterId, int impellorId){

		shooterTalon = new StormTalon(shooterId);
		impellorTalon = new StormTalon(impellorId);


	}

	public void initDefaultCommand(){
		shooterTalon.set(ControlMode.PercentOutput, 0.75);
		impellorTalon.set(ControlMode.PercentOutput, 0.75);

	}
	public boolean isPressed(){
		return isPressed;
	}

	public void shoot(){
		shooterTalon.set(ControlMode.PercentOutput, 0.75);

	}
	public void stop(){
		shooterTalon.set(ControlMode.PercentOutput,0);
	}
	public void startImpellor(){
		impellorTalon.set(ControlMode.PercentOutput, impellorVelocity);
	}
	public void stopImpellor(){
		impellorTalon.set(ControlMode.PercentOutput, 0);
	}
	public void reverseImpellor(){
		impellorTalon.set(ControlMode.PercentOutput, -impellorVelocity);
	}

}
