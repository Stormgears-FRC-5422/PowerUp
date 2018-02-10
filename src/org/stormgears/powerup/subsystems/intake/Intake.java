package org.stormgears.powerup.subsystems.intake;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
		private static Intake instance = new Intake();
		public static Intake getInstance(){
			return instance;
		}

		protected void initDefaultCommand(){

		}

		public void enableIntake(){
			System.out.println("intake on");
		}

		public void disableIntake(){
			System.out.println("intake off");
		}

}
