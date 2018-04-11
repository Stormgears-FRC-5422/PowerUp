package org.stormgears.powerup.subsystems.sensors.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Vision {

	private static final Logger logger = LogManager.getLogger(Vision.class);
	private final double HEIGHT = 38.8;
	private final double degreesPerPixel = 62.2 / 160; //0.35

	public Vision() {


	}

	private static NetworkTableInstance table = NetworkTableInstance.getDefault();
	private NetworkTable visionTable = table.getTable("GRIP/cubeContoursReport");


	public double[][] getVisionCoordinatesFromNetworkTable() {
		double[] defaultXArray = new double[0];
		double[] defaultYArray = new double[0];

		NetworkTableEntry centerX = visionTable.getEntry("centerX");
		NetworkTableEntry centerY = visionTable.getEntry("centerY");

		double[] centerXArray = centerX.getDoubleArray(defaultXArray);
		double[] centerYArray = centerY.getDoubleArray(defaultYArray);

		int i = 0;
		while (centerXArray.length == 0 || centerYArray.length == 0) {

			centerXArray = centerX.getDoubleArray(defaultXArray);
			centerYArray = centerY.getDoubleArray(defaultYArray);

			if (i > 100) {
				System.out.println("BROKEN CRAPPPPP");
				break;
			}
			i++;
		}

		// TODO: What is the purpose of centers?
//    double [][] centers = new double [2][];
//    centers[0] = centerXArray;
//    centers[1] = centerYArray;

		logger.debug("centerX: {}", centerXArray);
		logger.debug("centerY: {}", centerYArray);

		double[][] centersArray = new double[2][];
		centersArray[0] = centerXArray;
		centersArray[1] = centerYArray;

		return centersArray;

	}

	public double[] findClosestCube() {

		final double gripperCenterX = 65.0;
		final double gripperCenterY = 104.0;

		System.out.println("findClosestCube");
		double[][] centers = getVisionCoordinatesFromNetworkTable();
		double[][] displacement = new double[2][centers[0].length];

		System.out.println("LENGTH: " + centers[0].length);

		System.out.println("CENTERS FOUND: " + centers[0][0] + ", " + centers[1][0]);

		for (int i = 0; i < displacement[0].length; i++)
			displacement[0][i] = Math.abs(gripperCenterX - centers[0][i]);
		for (int i = 0; i < displacement[1].length; i++)
			displacement[1][i] = Math.abs(gripperCenterY - centers[1][i]);

		System.out.println("CENTERS RECALCULATED");

		double[] centerDistances = new double[centers[0].length];

		double minDistance = Double.MAX_VALUE;
		for (int i = 0; i < centerDistances.length; i++) {
			centerDistances[i] = Math.sqrt(Math.pow(displacement[0][i], 2) +
				Math.pow(displacement[1][i], 2));
			System.out.println("Center Distances: " + centerDistances[i]);
			if (centerDistances[i] < minDistance) {
				minDistance = centerDistances[i];
			}
		}

		System.out.println("CENTERS DISTANCE CALCULATED");
		System.out.println("MIN DISTANCE: " + minDistance);

		for (int i = 0; i < centerDistances.length; i++) {
			if (centerDistances[i] == minDistance) {
				System.out.println("MIN DISTANCES COORDINATES: " + centers[0][i] + ", " + centers[1][i]);

				return new double[]{centers[0][i], centers[1][i]};
			}
		}
		System.out.println("UHH PROBLEMO: WHY ARE WE HERE");

		return null;
	}

	public double calculateTheta(double x1, double y1, double x2, double y2) {
		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y2 - y1, 2));

		System.out.println("Distance: " + distance);

		double theta = distance * degreesPerPixel;

		return theta;
	}

	public double convertAngle(double x1, double y1, double x2, double y2) {
		double theta = calculateTheta(x1, y1, x2, y2);

		System.out.println("theta: " + theta);

		double camToX1 = Math.pow(x1, 2) + Math.pow(y1, 2) + Math.pow(HEIGHT, 2);
		double camToX2 = Math.pow(x2, 2) + Math.pow(y2, 2) + Math.pow(HEIGHT, 2);

		System.out.println("Cam: " + camToX1 + ", " + camToX2);

		double commonLen = Math.sqrt(camToX1 + camToX2 + Math.cos(theta) * Math.sqrt(camToX1) * Math.sqrt(camToX2));

		System.out.println("Length: " + commonLen);

		double X1 = Math.pow(x1, 2) + Math.pow(y1, 2);
		double X2 = Math.pow(x2, 2) + Math.pow(y2, 2);

		System.out.println("Ground Distances: " + X1 + ", " + X2);

		double cos_alpha = (Math.pow(commonLen, 2) - (X1 + X2)) / (2 * Math.sqrt(X1) * Math.sqrt(X2));
		System.out.println("cos alpha!!!: " + cos_alpha);

		double alpha = Math.acos(cos_alpha) * 180 / Math.PI;

		System.out.println("rafaelpha: " + alpha);
		return alpha;
	}

}
