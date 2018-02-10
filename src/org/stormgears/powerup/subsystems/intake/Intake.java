package org.stormgears.powerup.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.StormTalon;

import static org.stormgears.powerup.Robot.drive;
import static org.stormgears.powerup.Robot.driveTalons;

public class Intake extends Subsystem {
		private static Intake instance = new Intake();
		public static Intake getInstance(){
			return instance;
		}

		protected void initDefaultCommand(){

		}

		public void enableIntake(){
			System.out.println("intake on");
			StormTalon talon = Robot.driveTalons.getTalons()[1];
			talon.set(ControlMode.Velocity, 2000 );
		}

		public void disableIntake(){
			System.out.println("intake off");
		}

}
