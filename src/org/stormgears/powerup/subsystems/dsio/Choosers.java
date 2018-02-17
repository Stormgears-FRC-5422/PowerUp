package org.stormgears.powerup.subsystems.dsio;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.powerup.subsystems.field.FieldPositions;

public class Choosers {
	/*
	 * Declare private choosers here
	 */
	private SendableChooser<FieldPositions.StartingSpots> startingSpotChooser;
	//	private SendableChooser<FieldPositions.StartingDirections> startingDirectionChooser;
	private SendableChooser<FieldPositions.PlacementSpot> placementSpotChooser;
	private SendableChooser<FieldPositions.Alliance> allianceChooser;
	private SendableChooser<FieldPositions.LeftRight> ownSwitchPlateAssignmentChooser;
	private SendableChooser<FieldPositions.LeftRight> scalePlateAssignmentChooser;
	private SendableChooser<FieldPositions.LeftRight> opponentSwitchPlateAssignmentChooser;

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
		startingSpotChooser.addDefault("Left Side of the Field", FieldPositions.StartingSpots.LEFT);
		startingSpotChooser.addObject("Center of the Field", FieldPositions.StartingSpots.CENTER);
		startingSpotChooser.addObject("Right Side of the Field", FieldPositions.StartingSpots.RIGHT);
		SmartDashboard.putData("Starting Spot", startingSpotChooser);

//		startingDirectionChooser = new SendableChooser<>();
//		startingDirectionChooser.addDefault("Turn Left Direction", FieldPositions.StartingDirections.LEFT);
//		startingDirectionChooser.addObject("Go Straight", FieldPositions.StartingDirections.STRAIGHT);
//		startingDirectionChooser.addObject("Turn Right Direction", FieldPositions.StartingDirections.RIGHT);
//		SmartDashboard.putData("Starting Direction", startingDirectionChooser);

		placementSpotChooser = new SendableChooser<>();
		placementSpotChooser.addDefault("Cross Base Line", FieldPositions.PlacementSpot.JUST_CROSS);
		placementSpotChooser.addObject("Place on Scale", FieldPositions.PlacementSpot.SCALE);
		placementSpotChooser.addObject("Place on Switch", FieldPositions.PlacementSpot.SWITCH);
		SmartDashboard.putData("Placement Spot", placementSpotChooser);

		allianceChooser = new SendableChooser<>();
		allianceChooser.addDefault("Red Alliance", FieldPositions.Alliance.RED);
		allianceChooser.addObject("Blue Alliance", FieldPositions.Alliance.BLUE);
		SmartDashboard.putData("Alliance Side", allianceChooser);

		ownSwitchPlateAssignmentChooser = new SendableChooser<>();
		ownSwitchPlateAssignmentChooser.addDefault("Own Switch Plate Assignment: L", FieldPositions.LeftRight.L);
		ownSwitchPlateAssignmentChooser.addObject("Own Switch Plate Assignment: R", FieldPositions.LeftRight.R);
		SmartDashboard.putData("Own Switch Plate Assignment", ownSwitchPlateAssignmentChooser);

		scalePlateAssignmentChooser = new SendableChooser<>();
		scalePlateAssignmentChooser.addDefault("Scale Plate Assignment: L", FieldPositions.LeftRight.L);
		scalePlateAssignmentChooser.addObject("Scale Plate Assignment: R", FieldPositions.LeftRight.R);
		SmartDashboard.putData("Scale Plate Assignment", scalePlateAssignmentChooser);

		opponentSwitchPlateAssignmentChooser = new SendableChooser<>();
		opponentSwitchPlateAssignmentChooser.addDefault("Opponent Switch Plate Assignment: L", FieldPositions.LeftRight.L);
		opponentSwitchPlateAssignmentChooser.addObject("Opponent Switch Plate Assignment: R", FieldPositions.LeftRight.R);
		SmartDashboard.putData("Opponent Switch Plate Assignment", opponentSwitchPlateAssignmentChooser);
	}

	// Getters go below here

	public FieldPositions.StartingSpots getStartingSpot() {
		return startingSpotChooser.getSelected();
	}

//	public FieldPositions.StartingDirections getStartingDirections() {
//		return startingDirectionChooser.getSelected();
//	}

	public FieldPositions.PlacementSpot getPlacementSpot() {
		return placementSpotChooser.getSelected();
	}

	public FieldPositions.Alliance getAlliance() {
		return allianceChooser.getSelected();
	}

	public String getPlateAssignmentData() {
		return ownSwitchPlateAssignmentChooser.getSelected().name() +
			scalePlateAssignmentChooser.getSelected().name() +
			opponentSwitchPlateAssignmentChooser.getSelected().name();
	}
}
