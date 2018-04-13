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
	private val startingSpotChooser = SendableChooser<FieldPositions.StartingSpots>().apply {
		addDefault("Left Side of the Field", FieldPositions.StartingSpots.LEFT)
		addObject("Center of the Field", FieldPositions.StartingSpots.CENTER)
		addObject("Right Side of the Field", FieldPositions.StartingSpots.RIGHT)
		SmartDashboard.putData("Starting Spot", this)
	}

	private val placementSpotChooser = SendableChooser<FieldPositions.PlacementSpot>().apply {
		addDefault("Cross Base Line", FieldPositions.PlacementSpot.JUST_CROSS)
		addObject("Place on Scale", FieldPositions.PlacementSpot.SCALE)
		addObject("Place on Switch", FieldPositions.PlacementSpot.SWITCH)
		SmartDashboard.putData("Placement Spot", this)
	}
	private val allianceChooser = SendableChooser<FieldPositions.Alliance>().apply {
		addDefault("Red Alliance", FieldPositions.Alliance.RED)
		addObject("Blue Alliance", FieldPositions.Alliance.BLUE)
		SmartDashboard.putData("Alliance Side", this)
	}

	private val crossFieldChooser = SendableChooser<Boolean>().apply {
		addDefault("Cross Field", true)
		addObject("DO NOT Cross Field", false)
		SmartDashboard.putData("Cross Field", this)
	}

	// Getters go below here

	val startingSpot: FieldPositions.StartingSpots
		get() = startingSpotChooser.selected

	val placementSpot: FieldPositions.PlacementSpot
		get() = placementSpotChooser.selected

	val alliance: FieldPositions.Alliance
		get() = allianceChooser.selected

	val crossField: Boolean
		get() = crossFieldChooser.selected
}
