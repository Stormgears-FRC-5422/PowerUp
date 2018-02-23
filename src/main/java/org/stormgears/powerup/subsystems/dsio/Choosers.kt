package org.stormgears.powerup.subsystems.dsio

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.stormgears.powerup.subsystems.field.FieldPositions

class Choosers/*
	 * In this constructor:
	 * 	1) Initialise the chooser with the enum you want to list out
	 * 	2) Add values to it using addObject or addDefault (defaults are selected on startup of SmartDashboard)
	 * 	3) Add it to SmartDashboard using 'SmartDashboard.putData("title", chooser);'
	 *
	 * Remember to create a getter for the data. To access data from a chooser elsewhere,
	 * use Robot.dsio.choosers.getWhatever() where Whatever is what you want to get
	 */
internal constructor() {
	/*
     * Declare private choosers here
     */
	private val startingSpotChooser: SendableChooser<FieldPositions.StartingSpots> = SendableChooser()
	private val placementSpotChooser: SendableChooser<FieldPositions.PlacementSpot> = SendableChooser()
	private val allianceChooser: SendableChooser<FieldPositions.Alliance> = SendableChooser()
	private val ownSwitchPlateAssignmentChooser: SendableChooser<FieldPositions.LeftRight> = SendableChooser()
	private val scalePlateAssignmentChooser: SendableChooser<FieldPositions.LeftRight> = SendableChooser()
	private val opponentSwitchPlateAssignmentChooser: SendableChooser<FieldPositions.LeftRight> = SendableChooser()

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

		ownSwitchPlateAssignmentChooser.addDefault("Own Switch Plate Assignment: L", FieldPositions.LeftRight.L)
		ownSwitchPlateAssignmentChooser.addObject("Own Switch Plate Assignment: R", FieldPositions.LeftRight.R)
		SmartDashboard.putData("Own Switch Plate Assignment", ownSwitchPlateAssignmentChooser)

		scalePlateAssignmentChooser.addDefault("Scale Plate Assignment: L", FieldPositions.LeftRight.L)
		scalePlateAssignmentChooser.addObject("Scale Plate Assignment: R", FieldPositions.LeftRight.R)
		SmartDashboard.putData("Scale Plate Assignment", scalePlateAssignmentChooser)

		opponentSwitchPlateAssignmentChooser.addDefault("Opponent Switch Plate Assignment: L", FieldPositions.LeftRight.L)
		opponentSwitchPlateAssignmentChooser.addObject("Opponent Switch Plate Assignment: R", FieldPositions.LeftRight.R)
		SmartDashboard.putData("Opponent Switch Plate Assignment", opponentSwitchPlateAssignmentChooser)

	}

	// Getters go below here

	val startingSpot: FieldPositions.StartingSpots
		get() = startingSpotChooser.selected

	val placementSpot: FieldPositions.PlacementSpot
		get() = placementSpotChooser.selected

	val alliance: FieldPositions.Alliance
		get() = allianceChooser.selected

	val plateAssignmentData: String
		get() = ownSwitchPlateAssignmentChooser.selected.name +
			scalePlateAssignmentChooser.selected.name +
			opponentSwitchPlateAssignmentChooser.selected.name

	fun getOwnSwitchPlateAssignmentChooser(): FieldPositions.LeftRight {
		return ownSwitchPlateAssignmentChooser.selected

	}

	fun getScalePlateAssignmentChooser(): FieldPositions.LeftRight {
		return scalePlateAssignmentChooser.selected
	}

	fun getOpponentSwitchPlateAssignmentChooser(): FieldPositions.LeftRight {
		return opponentSwitchPlateAssignmentChooser.selected
	}
}
