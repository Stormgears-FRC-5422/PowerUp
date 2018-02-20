package org.stormgears.powerup.auto.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.subsystems.field.FieldPositions;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
	private static final Logger logger = LogManager.getLogger(AutonomousCommandGroup.class);
	public Command autoMoveCommand;
	public Command autoPlaceCubeCommand;

	public AutonomousCommandGroup() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}

	public AutonomousCommandGroup(FieldPositions.Alliance selectedAlliance,
	                              FieldPositions.StartingSpots selectedStartingSpot,
	                              FieldPositions.PlacementSpot selectedPlacementSpot,
	                              FieldPositions.LeftRight selectedOwnSwitchPlateAssignment,
	                              FieldPositions.LeftRight selectedScalePlateAssignment,
	                              FieldPositions.LeftRight selectedOpponentSwitchPlateAssignmentChooser) {
//		requires(Robot.navigatorSubsystem);
		logger.info("initiating autonomous command group");
		autoMoveCommand = new AutoMoveDriveCommand(selectedAlliance, selectedStartingSpot,
			selectedPlacementSpot, selectedOwnSwitchPlateAssignment,
			selectedScalePlateAssignment, selectedOpponentSwitchPlateAssignmentChooser);
		addSequential(autoMoveCommand);
//		addSequential(autoCloseGripperCommand);
	}
}
