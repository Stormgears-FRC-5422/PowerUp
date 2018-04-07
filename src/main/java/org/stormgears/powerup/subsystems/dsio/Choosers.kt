package org.stormgears.powerup.subsystems.dsio

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.stormgears.powerup.subsystems.field.FieldPositions

object Choosers {

	/*
	 * In this constructor:
	 * 	1) Initialise the chooser with the enum you want to list out
	 * 	2) Add values to it using addObject or addDefault (defaults are selected on startup of SmartDashboard)
	 * 	3) Add it to SmartDashboard using 'SmartDashboard.putData("title", chooser);'
	 *
	 * Remember to create a getter for the data. To access data from a chooser elsewhere,
	 * use Robot.dsio.choosers.getWhatever() where Whatever is what you want to get
	 */

	/*
     * Declare private choosers here
     */
	private val startingSpotChooser: SendableChooser<FieldPositions.StartingSpots> = SendableChooser()
	private val placementSpotChooser: SendableChooser<FieldPositions.PlacementSpot> = SendableChooser()
	private val allianceChooser: SendableChooser<FieldPositions.Alliance> = SendableChooser()

	init {
		startingSpotChooser.addDefault("Left Side of the Field", FieldPositions.StartingSpots.LEFT)
		startingSpotChooser.addObject("Center of the Field", FieldPositions.StartingSpots.CENTER)
		startingSpotChooser.addObject("Right Side of the Field", FieldPositions.StartingSpots.RIGHT)
		SmartDashboard.putData("Starting Spot", startingSpotChooser)

		placementSpotChooser.addDefault("Cross Base Line", FieldPositions.PlacementSpot.JUST_CROSS)
		placementSpotChooser.addObject("Place on Scale", FieldPositions.PlacementSpot.SCALE)
		placementSpotChooser.addObject("Place on Switch", FieldPositions.PlacementSpot.SWITCH)
		SmartDashboard.putData("Placement Spot", placementSpotChooser)

		allianceChooser.addDefault("Red Alliance", FieldPositions.Alliance.RED)
		allianceChooser.addObject("Blue Alliance", FieldPositions.Alliance.BLUE)
		SmartDashboard.putData("Alliance Side", allianceChooser)

	}

	// Getters go below here

	val startingSpot: FieldPositions.StartingSpots
		get() = startingSpotChooser.selected

	val placementSpot: FieldPositions.PlacementSpot
		get() = placementSpotChooser.selected

	val alliance: FieldPositions.Alliance
		get() = allianceChooser.selected
}
