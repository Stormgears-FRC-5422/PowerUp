package org.stormgears.powerup.subsystems.navigator.motionprofile;


import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import org.stormgears.StormUtils.SafeTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionControl {
	private static final double notifierRate = 0.005;
	private boolean stopNotifier = false;
	public SafeTalon[] talons;
	public CANTalon.MotionProfileStatus[] statuses = new CANTalon.MotionProfileStatus[4];
	private int numPoints = 0;
	private int btmBufferPoints = 0;
	private int runCount = 0;
	private int runReportInterval = 100;

	private RegisteredNotifier notifier = new RegisteredNotifier(new PeriodicRunnable(), "MotionControl");

	class PeriodicRunnable implements java.lang.Runnable {
		// The purpose of this thread is just to push data into the firmware buffer
		// other details of the control come through the motion manager thread.
		public void run() {
			synchronized(this) {
				if (stopNotifier) return;
			}

			if (++runCount % runReportInterval == 0) {
				System.out.println("In MotionControl run() with numPoints = " + numPoints + " and runCount = " + runCount);
			}

			synchronized(this) {
//				if (numPoints > 0) {
				for (SafeTalon t : talons) {
					t.clearMotionProfileHasUnderrun();
					t.processMotionProfileBuffer();
//						t.clearMotionProfileHasUnderrun();
				}
				talons[0].getMotionProfileStatus(statuses[0]);
				btmBufferPoints = statuses[0].btmBufferCnt;
				numPoints = --numPoints < 0 ? 0 : numPoints;
//				}
			}
		}
	}

	public MotionControl(SafeTalon[] talons) {
		this.talons = talons;
		int i = 0;
		for (SafeTalon t : talons) {
			t.changeControlMode(TalonControlMode.MotionProfile);
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.setEncPosition(0);
			t.set(0);
			statuses[i++] = new CANTalon.MotionProfileStatus();
		}
	}

	public void stopControlThread() {
		synchronized(this) {
			stopNotifier = true;
			notifier.stop();
		}
	}

	public void startControlThread() {
		int i = 0;
		for (SafeTalon t : talons) {
			t.changeControlMode(TalonControlMode.MotionProfile);
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.setEncPosition(0);
			t.set(0);
			statuses[i++] = new CANTalon.MotionProfileStatus();
		}

		synchronized(this) {
			stopNotifier = false;
			notifier.startPeriodic(notifierRate);
		}
	}

	public void printStatus() {
		int i = 0;
		for (SafeTalon t : talons) {
			t.getMotionProfileStatus(statuses[i++]);
		}
	}

	public void shutDownProfiling() {
		numPoints = 0;
		for (SafeTalon t : talons) {
			t.clearMotionProfileTrajectories();
			t.clearMotionProfileHasUnderrun();
			t.changeControlMode(TalonControlMode.Speed); //may need to be vbus
		}
	}

	public void clearMotionProfileTrajectories() {
		numPoints = 0;
		for (SafeTalon t : talons) {
			t.clearMotionProfileTrajectories();
		}
	}

	public synchronized int getPointsRemaining() {
		System.out.println("numPoints : " + numPoints + " , btmBufferPoints : " + btmBufferPoints);
		if (numPoints > 0)
			return numPoints;
		else
			return btmBufferPoints;
	}

	// wrapper functions for talon
	public synchronized boolean pushMotionProfileTrajectory(int talonIndex, TrajectoryPoint pt) {
		if (talonIndex == talons.length - 1)
			numPoints++;

		return talons[talonIndex].pushMotionProfileTrajectory(pt);
	}

	public int getEncVel(int talonIndex) { return talons[talonIndex].getEncVelocity();	}

	public int getEncPos(int talonIndex) {	return talons[talonIndex].getEncPosition();}
	//TODO: add in some edge case error checking
	public void enable() {
		int i = 0;
		for (SafeTalon t : talons) {
			t.getMotionProfileStatus(statuses[i++]);
			t.set(1);
		}
	}

	public void disable() {
		for (SafeTalon t : talons) {
			t.set(0);
		}
	}

	public void holdProfile() {
		for (SafeTalon t : talons) {
			t.set(2);
		}
	}

	//all the end cases need to be monitored
	//enable()
	//disable()
	//clear profile(from bottom buffer)
	//pushToTopBuffer(point) --> Wrapper (name same as talon function)
	//pushToBottomBuffer(point) --> runnable
}
