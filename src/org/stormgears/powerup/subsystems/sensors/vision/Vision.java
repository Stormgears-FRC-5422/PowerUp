package org.stormgears.powerup.subsystems.sensors.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Vision {

	private static final Logger logger = LogManager.getLogger(Vision.class);

	public Vision() {


	}

	private static NetworkTableInstance table = NetworkTableInstance.getDefault();
	private NetworkTable visionTable = table.getTable("GRIP/Box Contours");


	public void getVisionCoordinatesFromNetworkTable() {
		double[] defaultXArray = new double[0];
		double[] defaultYArray = new double[0];

		NetworkTableEntry centerX = visionTable.getEntry("centerX");
		NetworkTableEntry centerY = visionTable.getEntry("centerY");

		double[] centerXArray = centerX.getDoubleArray(defaultXArray);
		double[] centerYArray = centerY.getDoubleArray(defaultYArray);

		// TODO: What is the purpose of centers?
//		double [][] centers = new double [2][];
//		centers[0] = centerXArray;
//		centers[1] = centerYArray;

		logger.debug("centerX: {}", centerXArray);
		logger.debug("centerY: {}", centerYArray);
	}


}
