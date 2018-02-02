package org.usfirst.frc.team5422.robot.subsystems.shooter.shooter_thread;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.WebDashboard.Diagnostics.Diagnostics;
import org.usfirst.frc.team5422.robot.Robot;

/**
 * All the stuff that needs to happen on a separate thread.
 */
public class ShooterRunnable implements Runnable
{
	@Override
	public void run()
	{
		//to start the shooter before the impeller
		Robot.shooterSubsystem.shoot();

		// Wait three seconds for wheel to spin up
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Robot.shooterSubsystem.startImpeller();

		while (Robot.shooterSubsystem.isEnabled()) {
			Robot.shooterSubsystem.shoot();

			// Loop will run 10 times a second
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			SmartDashboard.putNumber("Shooter velocity: ", Robot.shooterSubsystem.motor.getEncVelocity());
		}

		Robot.shooterSubsystem.stop();
	}
}
