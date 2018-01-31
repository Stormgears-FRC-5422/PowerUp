package org.stormgears.powerup.subsystems.shooter.shooter_thread;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.powerup.Robot;

/**
 * All the stuff that needs to happen on a separate thread.
 */
public class ShooterRunnable implements Runnable
{
	@Override
	public void run()
	{
		//to start the shooter before the impeller
		Robot.shooter.shoot();

		// Wait three seconds for wheel to spin up
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Robot.shooter.startImpellor();

		while (Robot.shooter.isPressed()) {
			Robot.shooter.shoot();

			// Loop will run 10 times a second
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		//	SmartDashboard.putNumber("Shooter velocity: ", Robot.shooter.shooterTalon.());
		}

		Robot.shooter.stop();
	}
}
