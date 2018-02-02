package org.stormgears.powerup.subsystems.navigator.motionprofile;

import com.ctre.CANTalon;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.StormTalon;

public class MotionControl {
	private static final double notifierRate = 0.005;
	private boolean stopNotifier = false;
	public MotionProfileStatus[] statuses = new MotionProfileStatus[4];
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
				for (StormTalon t : Robot.driveTalons.getTalons()) {
					t.clearMotionProfileHasUnderrun(10);
					t.processMotionProfileBuffer();
//						t.clearMotionProfileHasUnderrun();
				}
				Robot.driveTalons.getTalons()[0].getMotionProfileStatus(statuses[0]);
				btmBufferPoints = statuses[0].btmBufferCnt;
				numPoints = --numPoints < 0 ? 0 : numPoints;
//				}
			}
		}
	}

	public MotionControl() {
		int i = 0;
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.getSensorCollection().setQuadraturePosition(0, 10);
			t.set(0);
			statuses[i++] = new MotionProfileStatus();
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
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.getSensorCollection().setQuadraturePosition(0, 10);
			t.set(0);
			statuses[i++] = new MotionProfileStatus();
		}

		synchronized(this) {
			stopNotifier = false;
			notifier.startPeriodic(notifierRate);
		}
	}

	public void printStatus() {
		int i = 0;
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.getMotionProfileStatus(statuses[i++]);
		}
	}

	public void shutDownProfiling() {
		numPoints = 0;
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.clearMotionProfileTrajectories();
			t.clearMotionProfileHasUnderrun(10);
		}
	}

	public void clearMotionProfileTrajectories() {
		numPoints = 0;
		for (StormTalon t : Robot.driveTalons.getTalons()) {
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
	public synchronized ErrorCode pushMotionProfileTrajectory(int talonIndex, TrajectoryPoint pt) {
		if (talonIndex == Robot.driveTalons.getTalons().length - 1)
			numPoints++;

		return Robot.driveTalons.getTalons()[talonIndex].pushMotionProfileTrajectory(pt);
	}

	public int getEncVel(int talonIndex) { return Robot.driveTalons.getTalons()[talonIndex].getSensorCollection().getQuadratureVelocity();	}

	public int getEncPos(int talonIndex) {	return Robot.driveTalons.getTalons()[talonIndex].getSensorCollection().getQuadratureVelocity();}
	//TODO: add in some edge case error checking
	public void enable() {
		int i = 0;
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.getMotionProfileStatus(statuses[i++]);
			t.set(1);
		}
	}

	public void disable() {
		for (StormTalon t : Robot.driveTalons.getTalons()) {
			t.set(0);
		}
	}

	public void holdProfile() {
		for (StormTalon t : Robot.driveTalons.getTalons()) {
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
