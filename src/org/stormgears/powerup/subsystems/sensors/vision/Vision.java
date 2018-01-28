package org.stormgears.powerup.subsystems.sensors.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.Arrays;


public class Vision {

	public Vision() {


	}
	private static NetworkTableInstance table = NetworkTableInstance.getDefault();
	private NetworkTable visionTable = table.getTable("GRIP/Box Contours");



	public void getViaionCoordinatesFromNetworkTable() {
		double [] defaultXArray = new double[0];
		double [] defaultYArray = new double[0];

		NetworkTableEntry centerX = visionTable.getEntry("centerX");
		NetworkTableEntry centerY = visionTable.getEntry("centerY");

		double [] centerXArray = centerX.getDoubleArray(defaultXArray);
		double [] centerYArray = centerY.getDoubleArray(defaultYArray);

		double [][] centers = new double [2][];
		centers[0] = centerXArray;
		centers[1] = centerYArray;

		System.out.println("centerX: " + Arrays.toString(centerXArray));
		System.out.println("centerY: " + Arrays.toString(centerYArray));
	}



}
