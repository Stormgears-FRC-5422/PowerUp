package org.stormgears.powerup.subsystems.navigator.motionprofile;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TrapezoidalProfile {

	/*
	 * Precondition: distance in rotations, max vel in RPM
	 * Postcondition: returns profile [] in the form of {velocity, theta}
	 */

//	public static void main(String [] args) {
//		double [][] profileTest = getTrapezoidZero(5,25,0, 0);
//		double position = 0;
//		for(int i = 0; i < profileTest.length; i ++) {
//			position += profileTest[i][0] * 0.01/60;
//			System.out.println(profileTest[i][0] + "," + profileTest[i][1] + "," + position);
//		}
//	}

	public static double[][] getTrapezoidZero(double rotations, double maxVel, double theta, double vStart) {
		return generateZeroProfile(maxVel / 60.0, maxVel / 60.0, rotations, theta, vStart); //should want starting ticks as 0 here

	}

	private static double[][] generateZeroProfile(double maxAccel, double maxVel, double distance, double theta, double vStart) {
		maxAccel = maxAccel * 8192.0;
		maxVel = maxVel * 8192.0;
		distance = distance * 8192.0;

		SmartDashboard.putNumber("Dist: ", distance);

		double timeTotal = getTotalTimeZero(maxAccel, maxVel, distance, vStart);
		int length = (int) (timeTotal * 100) + 2;
		double[][] profile = new double[length][];
		double time = 0;
		for (int i = 0; i < length; i++) {
			profile[i] = generatePointZero(maxVel, maxAccel, distance, time, theta, vStart); //testing with changing back to RPS
			time += 0.01;
		}
		SmartDashboard.putNumber("Length: ", profile.length);
		return profile;
	}

	private static double[] generatePointZero(double v, double a, double d, double t, double theta, double vs) {
		boolean isNeg = false;
		boolean goDown = vs > v;
		if (v < 0) isNeg = true;
		v = Math.abs(v);
		a = Math.abs(a);
		d = Math.abs(d);

		double tscv = Math.abs((v - vs) / a);
		double xscv = 0.5 * a * tscv * tscv;
//			System.out.println("t: " + tscv + " x: " + xscv);

		double tTotal = 0;

		double xd = v * v / (2.0 * a); //d travelled decell
		double xc = d - xscv - xd;       //d travelled @ const
		double xsd = d - xd;
		double tsd = tscv + xc / v;
		tTotal = tsd + v / a;

		if (xsd < xscv) {
			tsd = (Math.sqrt((2 * a * d + vs * vs) / 2.0) - vs) / a;
			xsd = 0.5 * a * tsd * tsd + vs * t;
			tTotal = 2 * Math.sqrt((2 * a * d + vs * vs) / 2.0) / a - vs / a;
		}

		double velocity = 0;
		if (t < tscv && t < tsd) {
			if (!goDown) velocity = a * t + vs;
			else velocity = -a * t + vs;
		} else if (t >= tscv && t < tsd) velocity = v;
		else if (t >= tsd && t < tTotal) velocity = a * (tTotal - t);
		else velocity = 0;

		double[] array = {velocity * 60.0 / 8192.0, theta};
		if (isNeg) array[0] = -velocity * 60.0 / 8192.0;
		return array;
	}

	private static double getTotalTimeZero(double a, double v, double d, double vs) {
		a = Math.abs(a);
		v = Math.abs(v);
		d = Math.abs(d);
		double tscv = Math.abs((v - vs) / a);
		double xscv = 0.5 * a * tscv * tscv;

		double tTotal = 0;

		double xd = v * v / (2.0 * a); //d travelled decell
		double xc = d - xscv - xd;       //d travelled @ const
		double xsd = d - xd;
		double tsd = tscv + xc / v;
		tTotal = tsd + v / a;

		if (xsd < xscv) {
			//Fixed math errors in triangular profile
			tsd = (Math.sqrt((2 * a * (d/2.0) + vs * vs) ) - vs) / a;
			xsd = 0.5 * a * tsd * tsd;
			tTotal = 2 * tsd;
		}

		return tTotal;
	}

}
