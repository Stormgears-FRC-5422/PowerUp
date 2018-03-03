package org.stormgears.powerup.auto.command;

import edu.wpi.first.wpilibj.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.field.AutoRoutes;
import org.stormgears.powerup.subsystems.field.FieldPositions;
import org.stormgears.powerup.subsystems.field.Segment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.apache.logging.log4j.util.Unbox.box;

/**
 *
 */
public class AutoDriveMoveCommand extends Command {
	private static final Logger logger = LogManager.getLogger(AutonomousCommandGroup.class);
	private FieldPositions.Alliance selectedAlliance;
	private FieldPositions.StartingSpots selectedStartingSpot;
	private FieldPositions.PlacementSpot selectedPlacementSpot;
	private FieldPositions.LeftRight selectedOwnSwitchPlateAssignment;
	private FieldPositions.LeftRight selectedScalePlateAssignment;
	private FieldPositions.LeftRight selectedOpponentSwitchPlateAssignmentChooser;

	public AutoDriveMoveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	public AutoDriveMoveCommand(FieldPositions.Alliance selectedAlliance,
	                            FieldPositions.StartingSpots selectedStartingSpot,
	                            FieldPositions.PlacementSpot selectedPlacementSpot,
	                            FieldPositions.LeftRight selectedOwnSwitchPlateAssignment,
	                            FieldPositions.LeftRight selectedScalePlateAssignment,
	                            FieldPositions.LeftRight selectedOpponentSwitchPlateAssignmentChooser) {

		this.selectedAlliance = selectedAlliance;
		this.selectedStartingSpot = selectedStartingSpot;
		this.selectedPlacementSpot = selectedPlacementSpot;
		this.selectedOwnSwitchPlateAssignment = selectedOwnSwitchPlateAssignment;
		this.selectedScalePlateAssignment = selectedScalePlateAssignment;
		this.selectedOpponentSwitchPlateAssignmentChooser = selectedOpponentSwitchPlateAssignmentChooser;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Entering initialize method of AutoMoveCommand...");
	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
		if (selectedStartingSpot == FieldPositions.StartingSpots.LEFT) {
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				if (selectedScalePlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftScaleFromLeftSpot);
				} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightScaleFromLeftSpot);
				}
			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftSwitchFromLeftSpot);
				} else { //if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightSwitchFromLeftSpot);
				}
			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromLeftSpot);
			}
		} else if (selectedStartingSpot == FieldPositions.StartingSpots.RIGHT) {
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				if (selectedScalePlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftScaleFromRightSpot);
				} else { //if (selectedScalePlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightScaleFromRightSpot);
				}
			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.L) {
					move(AutoRoutes.path2LeftSwitchFromRightSpot);
				} else { //if (selectedOwnSwitchPlateAssignment == FieldPositions.LeftRight.R)
					move(AutoRoutes.path2RightSwitchFromRightSpot);
				}
			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromRightSpot);
			}
		} else { //if (selectedStartSpot == FieldPositions.StartingSpots.CENTER)
			if (selectedPlacementSpot == FieldPositions.PlacementSpot.SCALE) {
				logger.info("Moving from {} to {} on the {}",
					selectedStartingSpot, selectedPlacementSpot, selectedScalePlateAssignment);

			} else if (selectedPlacementSpot == FieldPositions.PlacementSpot.SWITCH) {
				logger.info("Moving from {} to {} on the {}",
					selectedStartingSpot, selectedPlacementSpot, selectedOwnSwitchPlateAssignment);

			} else { //if (selectedPlacementSpot == FieldPositions.PlacementSpot.JUST_CROSS)
				move(AutoRoutes.path2CrossLineFromCenterSpot);
			}
		}
	}

	public void move(ArrayList<Segment> path) {
		Iterator<Segment> segmentIterator = path.iterator();
		while (segmentIterator.hasNext()) {
			Segment segment = segmentIterator.next();

			logger.info("Moving from ({}, {}) to ({}, {})",
				box(segment.getStartPos().getX()), box(segment.getStartPos().getY()),
				box(segment.getEndPos().getX()), box(segment.getEndPos().getY()));

			Robot.drive.moveToPos(segment.getStartPos(), segment.getEndPos());

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		logger.info("Entering isFinished method of AutoMoveCommand...");

		//default value returned was "false"
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("Entering end method of AutoMoveCommand...");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
