package org.stormgears.powerup.subsystems.navigator.motionprofile;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.Robot;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.talons.ITalon;

import static org.apache.logging.log4j.util.Unbox.box;

public class MotionControl {
	private static final Logger logger = LogManager.getLogger(MotionControl.class);

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
			synchronized (this) {
				if (stopNotifier) return;
			}

			if (++runCount % runReportInterval == 0) {
				logger.trace("In MotionControl run() with numPoints = {} and runCount = {}", box(numPoints), box(runCount));
			}

			synchronized (this) {
//				if (numPoints > 0) {
				for (ITalon t : Robot.getDriveTalons().getTalons()) {
					t.clearMotionProfileHasUnderrun(10);
					t.processMotionProfileBuffer();
//						t.clearMotionProfileHasUnderrun();
				}
				logger.trace("HERE1");
				Robot.getDriveTalons().getTalons()[0].getMotionProfileStatus(statuses[0]);
				logger.trace("HERE2");
				btmBufferPoints = statuses[0].btmBufferCnt;
				numPoints = --numPoints < 0 ? 0 : numPoints;
//				}
			}
		}
	}

	public MotionControl() {
		int i = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.getSensorCollection().setQuadraturePosition(0, 10);
			t.set(0);
			statuses[i++] = new MotionProfileStatus();
		}
	}

	public void stopControlThread() {
		synchronized (this) {
			stopNotifier = true;
			notifier.stop();
		}
	}

	public void startControlThread() {
		int i = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.clearMotionProfileTrajectories();
			t.changeMotionControlFramePeriod(5);
			t.getSensorCollection().setQuadraturePosition(0, 10);
			t.set(0);
			statuses[i++] = new MotionProfileStatus();
		}

		synchronized (this) {
			stopNotifier = false;
			notifier.startPeriodic(notifierRate);
		}
	}

	public void printStatus() {
		int i = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.getMotionProfileStatus(statuses[i++]);
		}
	}

	public void shutDownProfiling() {
		numPoints = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.clearMotionProfileTrajectories();
			t.clearMotionProfileHasUnderrun(10);
		}
	}

	public void clearMotionProfileTrajectories() {
		numPoints = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.clearMotionProfileTrajectories();
		}
	}

	public synchronized int getPointsRemaining() {
		logger.debug("numPoints: {}, btmBufferPoints: {}", box(numPoints), box(btmBufferPoints));
		if (numPoints > 0)
			return numPoints;
		else
			return btmBufferPoints;
	}

	// wrapper functions for talon
	public synchronized ErrorCode pushMotionProfileTrajectory(int talonIndex, TrajectoryPoint pt) {
		if (talonIndex == Robot.getDriveTalons().getTalons().length - 1)
			numPoints++;

		return Robot.getDriveTalons().getTalons()[talonIndex].pushMotionProfileTrajectory(pt);
	}

	public int getEncVel(int talonIndex) {
		return Robot.getDriveTalons().getTalons()[talonIndex].getSensorCollection().getQuadratureVelocity();
	}

	public int getEncPos(int talonIndex) {
		return Robot.getDriveTalons().getTalons()[talonIndex].getSensorCollection().getQuadratureVelocity();
	}

	//TODO: add in some edge case error checking
	public void enable() {
		int i = 0;
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.getMotionProfileStatus(statuses[i++]);
			t.set(1);
		}
	}

	public void disable() {
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
			t.set(0);
		}
	}

	public void holdProfile() {
		for (ITalon t : Robot.getDriveTalons().getTalons()) {
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
