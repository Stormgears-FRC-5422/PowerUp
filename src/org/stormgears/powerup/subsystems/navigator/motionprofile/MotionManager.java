package org.stormgears.powerup.subsystems.navigator.motionprofile;

import com.ctre.CANTalon.TrajectoryPoint;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.stormgears.StormUtils.SafeTalon;
import org.stormgears.powerup.subsystems.navigator.motionprofile.MotionControl;
import org.stormgears.powerup.subsystems

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

//import com.ctre.CANTalon;

public class MotionManager {
	private List<double[][]> paths = new ArrayList<>();
	private List<ProfileDetails> profileDetails = new ArrayList<>();
	private boolean loading = false, interrupt = false;
	private int batchSize = 256 * 4;
	private int currIndex = 0;
	private MotionControl control;
	private int numTalons;
	private static Instrumentation instrumentation;

	private RegisteredNotifier notifier = new RegisteredNotifier(new PeriodicRunnable(), "MotionManager");
	private final double[][] table = generateTable();
	private static final double deltaT = 0.01 / 60;
	private static final double scale = 500d / Math.PI;
	// TODO - make 1000 and 500 not magic numbers

	private boolean isLoaded = false;

	class PeriodicRunnable implements java.lang.Runnable {

		// The job of this thread is to push data into the top api buffer.
		// it pushes up to 'batchSize' points at a time (but never points
		// from more than one path in a single pass)
		public void run() {
			// synchronize to avoid MT conflicts with the input of profiles

			// called from synchronized methods - which effectively sync(this)
			synchronized (this) {
//				System.out.println("In MotionManager run. INT = " + interrupt + " loading = " + loading + " isLoaded = " + isLoaded);
//				System.out.println("there are " + paths.size() + " paths");
//				System.out.println("currIndex = " + currIndex);
//				SmartDashboard.putNumber("Val 0: ", control.getEncVel(0));
//				SmartDashboard.putNumber("Val 1: ", control.getEncVel(1));
				SmartDashboard.putNumber("Val 2: ", control.getEncVel(2));
//				SmartDashboard.putNumber("Val 3: ", control.getEncVel(3));

//				SmartDashboard.putNumber("Pos 0: ", control.getEncPos(0));
//				SmartDashboard.putNumber("Pos 1: ", control.getEncPos(1));
				SmartDashboard.putNumber("Pos 2: ", control.getEncPos(2));
//				SmartDashboard.putNumber("Pos 3: ", control.getEncPos(3));

				for (SafeTalon talon : control.talons) {
					//	Instrumentation.process(control.statuses[i], control.talons[i]);
				}


				if (isLoaded) {
//					System.out.println("Ready to react to loaded buffers");
					control.enable();
				}

				if (!loading) return;

				// Are we done?
				if (paths.isEmpty()) {  // TODO: need a more elegant stop condition??
//					System.out.println("Stop pushing points because paths are loaded. loading is " + loading);
//					for(int i = 0; i < controls.length; i ++) {
//						controls[i].stopControlThread();
//					}
//					notifier.stop();
					loading = false;
					isLoaded = true;
					// anything else? disable talons?
					return;
				}

				// If the last profile was marked "immediate" we need to abandon the current path and clean up
				if (interrupt) {
					currIndex = 0;
					control.clearMotionProfileTrajectories();
					//remove all other profiles from the list except the most recent one
					while (paths.size() > 1) {
						paths.remove(0);
						profileDetails.remove(0);
					}
					interrupt = false;
				}

				// Push the next section
				if (profileDetails.get(0).turn) pushTurn();
				else pushLinear();

				// If we have pushed the entire path, remove it and let the next path run on the next time through
				// this could lead to a short cycle, but that is probably OK since we push points more quickly
				// than they can run anyway.
				if (currIndex >= paths.get(0).length) {
					currIndex = 0;
					paths.remove(0);
					profileDetails.remove(0);
				}
			}
		}

	}

	// information that needs to be stored per profile path
	class ProfileDetails {
		boolean turn;
		double theta;
		boolean direction;
		boolean done;
	}

	public MotionManager(SafeTalon[] talons) {
		control = new MotionControl(talons);
		numTalons = talons.length;
	}

	/*
	 * Preconditions: All talons must be set to the following
	 *
	 * reverseOutput(true)
	 * feedbackDevice = quadEncoder
	 * configEncoderCodesPerRev(2048)
	 * P = 0.08
	 * I = 0.0002
	 * D = 10.24
	 * IZone = 1500
	 * F = 0.16
	 *
	 */

	public synchronized void pushProfile(double[][] pathArray, boolean immediate, boolean done) {
		ProfileDetails d = new ProfileDetails();
		d.done = done;
		d.turn = false;
		// other details ignored if turn is false
		profileDetails.add(d);

		interrupt = immediate;
		paths.add(pathArray);
		loading = true;

//		System.out.println("Starting controlThreads in pushProfile");
		notifier.startPeriodic(0.05);  // pushing batchsize points at a time
		control.startControlThread();
	}

	public synchronized void pushTurn(double theta, boolean immediate, boolean done) {
		ProfileDetails d = new ProfileDetails();
		d.done = done;
		d.theta = theta;
		d.turn = true;
		profileDetails.add(d);

		interrupt = immediate;
		paths.add(getTurnProfile(d));
		loading = true;

//		System.out.println("Starting controlThreads in pushTurn");
		notifier.startPeriodic(0.05);
		control.startControlThread();
	}

	/*
	 * Precondition: path array a path in the form of v(velocity of center of robot), theta(direction of motion of center of robot)
	 *
	 * immediate = whether profile should start immediately or wait for other guy to end
	 * done = whether profile is last part(if robot should stop after or not)
	 */

	public double[][] getTurnProfile(ProfileDetails d) {
		double robotRadius = 15.00; //TODO: make constant, in inches (14.25 x 13.25)
		double wheelRadius = 3; //TODO: make constant, in inches
		double maxVel = 240; //RPM
		d.theta %= (2 * Math.PI);
		double tTheta = d.theta - Math.PI;
		if (tTheta > 0) {
			d.theta = Math.PI - tTheta;
			d.direction = true;
		}
		d.direction = false;
		double dist = robotRadius * d.theta / (2.0 * Math.PI * wheelRadius);
		return TrapezoidalProfile.getTrapezoidZero(dist, maxVel, d.theta, getRobotRPM());
	}

	public void pushTurn() {
		System.out.println("pushTurn started");
		//clear existing profiles
		double[] positions = new double[4];
		TrajectoryPoint pt = new TrajectoryPoint();
		double[][] pathArray = paths.get(0);
		boolean direc = profileDetails.get(0).direction;
		boolean done = profileDetails.get(0).done;

		for (int i = currIndex; i < currIndex + batchSize; i++) {
			if (i >= pathArray.length) break;

//			if(interrupt) {
//				interrupt = false;
//				return;
//			}

			int colIndex = (int) (pathArray[i][1] * 500 / Math.PI);

			for (int j = 0; j < numTalons; j++) {
				pt.position = 0;
				pt.timeDurMs = 10;
				pt.velocityOnly = false;
				pt.zeroPos = (i == currIndex); //needed for successive profiles, only first pt should be set to true
				pt.velocity = pathArray[i][0] * table[j][colIndex]; //TODO: change signs as appropriate for turning
				if ((j == 0 || j == 2) && direc) pt.velocity = -pt.velocity;
				else if ((j == 1 || j == 3) && !direc) pt.velocity = -pt.velocity;
				positions[j] += pt.velocity * deltaT;
				pt.position = positions[j];
				System.out.println("PT POS: " + pt.position);
				System.out.println("ZERO PT: " + pt.zeroPos + "\n");
				pt.isLastPoint = false;//(done && (i + 1 == pathArray.length));  // TODO
				control.pushMotionProfileTrajectory(j, pt);
			}
		}

		if (currIndex == 0) startProfile();
		currIndex += batchSize;

	}

	public void pushLinear() {
//		System.out.println("pushLinear started");
		double[][] pathArray = paths.get(0);
		TrajectoryPoint pt = new TrajectoryPoint();
		double[] positions = new double[4];
		boolean done = profileDetails.get(0).done;

//		long starttime = System.nanoTime();
		for (int i = currIndex; i < currIndex + batchSize; i++) {
			if (i >= pathArray.length) break;

//			if(interrupt) {
//				interrupt = false;
//				return;
//			}

			int colIndex = (int) (((pathArray[i][1] + 2 * Math.PI) % (2 * Math.PI)) * 500 / Math.PI);

			//System.out.println("i is " + i + ", pathArray[i][1] is " + pathArray[i][1] + ", colIndex is " + colIndex);

			for (int j = 0; j < numTalons; j++) {
				pt.position = 0;
				pt.timeDurMs = 10;
				pt.velocityOnly = false;
				pt.zeroPos = (i == currIndex); //needed for successive profiles, only first pt should be set to true
				pt.velocity = pathArray[i][0] * table[j][colIndex];
				positions[j] += pt.velocity * deltaT;
				pt.position = positions[j];
				// TODO - probably want the commented setting, but need to test it.
				pt.isLastPoint = false; //(done && ( (i + 1) == pathArray.length));  //TODO
				control.pushMotionProfileTrajectory(j, pt);
			}
		}

//		long elapsed = System.nanoTime() - starttime;
//		System.out.println("Total time computing: " + elapsed);

		//if (currIndex == 0) startProfile();

		currIndex += batchSize;
	}

	public void startProfile() {
		control.enable();
	}

	public void endProfile() {
		control.disable();
	}

	public void shutDownProfiling() {
		control.shutDownProfiling();
	}

	public double getRobotRPM() {
		int[] vels = getEncVels();
		double root = Math.sqrt(2);
		double x = 0;
		double y = 0;
		for (int i = 0; i < vels.length; i++) {
			if (i == 0 || i == 3) x -= vels[i] / root;
			else x += vels[i] / root;
			y += vels[i] / root;
		}
		x /= vels.length;
		y /= vels.length;
		//double[] solution = {Math.sqrt(x * x + y * y) * 10.0 * 60.0 / 8192.0, Math.atan(y / x)};
		return Math.sqrt(x * x + y * y) * 10.0 * 60.0 / 8192.0;
	}

	private int[] getEncVels() {
		int[] vels = new int[numTalons];
		for (int i = 0; i < numTalons; i++) {
			vels[i] = control.getEncVel(i);
		}
		return vels;
	}


	//helper methods for generating table
	private double[][] generateTable() {
		double[][] table = new double[4][1000];
		table[0] = getFuncs1(true);
		table[1] = getFuncs2(true);  //FOR TURN: false
		table[2] = getFuncs2(false); //FOR TURN: true
		table[3] = getFuncs1(false);
		return table;
	}

	private double[] getFuncs1(boolean neg) {
		double[] temp = new double[1000];
		for (int i = 0; i < 1000; i++) {
			if (neg)
				temp[i] = -Math.sqrt(2) * (Math.sin(2 * Math.PI * i / 1000.0 + Math.PI / 2.0) - Math.cos(2 * Math.PI * i / 1000.0 + Math.PI / 2.0));
			else
				temp[i] = Math.sqrt(2) * (Math.sin(2 * Math.PI * i / 1000.0 + Math.PI / 2.0) - Math.cos(2 * Math.PI * i / 1000.0 + Math.PI / 2.0));
		}
		return temp;

	}

	private double[] getFuncs2(boolean neg) {
		double[] temp = new double[1000];
		for (int i = 0; i < 1000; i++) {
			if (neg)
				temp[i] = -Math.sqrt(2) * (Math.sin(2 * Math.PI * i / 1000.0 + Math.PI / 2.0) + Math.cos(2 * Math.PI * i / 1000.0 + Math.PI / 2.0));
			else
				temp[i] = Math.sqrt(2) * (Math.sin(2 * Math.PI * i / 1000.0 + Math.PI / 2.0) + Math.cos(2 * Math.PI * i / 1000.0 + Math.PI / 2.0));
		}
		return temp;
	}

}
