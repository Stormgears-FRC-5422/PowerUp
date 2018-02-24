package org.stormgears.powerup.subsystems.shooter;

import edu.wpi.first.wpilibj.Relay;
import org.stormgears.utils.StormTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
/*
This is a bit of a hack-and-slash class that works for turning on and off key elements
of the Steamworks robot. It would require some work to use in anything like a competition,
but enables the Climber, Intake, and Shooter switches to function
 */


public class Shooter  {
	private static Shooter instance;
	private static StormTalon shooterTalon;
	private static StormTalon climberTalon;
	private static StormTalon intakeTalon;
	private static Relay impeller;

	boolean isPressed = false;

	double shootVelocity = 0.5;
	double impellorVelocity = 0.5; //TODO: Find velodity for impellor

//	public Shooter(int shooterId, int impellorId){
//	}

	public static Shooter getInstance() {
		return instance;
	}

	private Shooter(){
		shooterTalon = new StormTalon(ShooterConstants.SHOOTER_TALON);
		shooterTalon.setInverted(true);
		impeller = new Relay(0);
		climberTalon = new StormTalon(ShooterConstants.CLIMBER_TALON);
		intakeTalon = new StormTalon(ShooterConstants.INTAKE_TALON);
		intakeTalon.setInverted(true);
	}

	public static void init() {
		instance = new Shooter();
	}

	public void shoot(){
		shooterTalon.set(ControlMode.PercentOutput, 0.75);

		// Wait three seconds for wheel to spin up
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		startImpeller();
	}

	public boolean isPressed() {return isPressed;}

	public void stop(){
		shooterTalon.set(ControlMode.PercentOutput, 0);
		stopImpeller();
	}


	public void startImpeller(){
		impeller.set(Relay.Value.kReverse);
	}

	public void stopImpeller(){
		impeller.set(Relay.Value.kOff);
	}

	public void reverseImpellor(){
		impeller.set(Relay.Value.kForward);
	}

	public void startClimber(){
		climberTalon.set(ControlMode.PercentOutput, 0.75);
	}

	public void stopClimber(){
		climberTalon.set(ControlMode.PercentOutput, 0.0);
	}

	public void startIntake(){
		intakeTalon.set(ControlMode.PercentOutput, 0.75);
	}

	public void stopIntake(){
		intakeTalon.set(ControlMode.PercentOutput, 0.0);
	}

}
