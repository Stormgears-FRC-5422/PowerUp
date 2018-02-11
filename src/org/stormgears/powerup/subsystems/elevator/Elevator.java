package org.stormgears.powerup.subsystems.elevator;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.stormgears.utils.StormTalon;

public class Elevator extends Subsystem{

	public StormTalon elevatorTalon1;
	public StormTalon elevatorTalon2;
	public static int elevatorCurrentPosition;

	public Elevator(int talonID1, int talonID2) {
		elevatorTalon1 = new StormTalon(talonID1);
		elevatorTalon2 = new StormTalon(talonID2);
	}

	public void move(int position) {
		if(position > elevatorCurrentPosition) {
			// go up
		}
		else if(position < elevatorCurrentPosition) {
			// go down
		}
		else {
			// stay
		}
	}

	public void reset() {
		// elevator goes to position 0
	}


	public void stop() {
		elevatorTalon1.set(0);
		elevatorTalon2.set(0);
	}

	protected void initDefaultCommand() {

	}

}
