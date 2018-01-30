package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.powerup.subsystems.field.FieldPositions;

public class Choosers {
	/*
	 * Declare private choosers here
	 */
	private SendableChooser<FieldPositions.StartingSpots> startingSpotChooser;

	/*
	 * In this constructor:
	 * 	1) Initialise the chooser with the enum you want to list out
	 * 	2) Add values to it using addObject or addDefault (defaults are selected on startup of SmartDashboard)
	 * 	3) Add it to SmartDashboard using 'SmartDashboard.putData("title", chooser);'
	 *
	 * Remember to create a getter for the data. To access data from a chooser elsewhere,
	 * use Robot.dsio.choosers.getWhatever() where Whatever is what you want to get
	 */
	Choosers() {
		startingSpotChooser = new SendableChooser<>();
		startingSpotChooser.addDefault("Left", FieldPositions.StartingSpots.LEFT);
		startingSpotChooser.addObject("Center", FieldPositions.StartingSpots.CENTER);
		startingSpotChooser.addObject("Right", FieldPositions.StartingSpots.RIGHT);
		SmartDashboard.putData("Starting Spot", startingSpotChooser);

		
	}

	// Getters go below here

	public FieldPositions.StartingSpots getStartingSpot() {
		return startingSpotChooser.getSelected();
	}
}
