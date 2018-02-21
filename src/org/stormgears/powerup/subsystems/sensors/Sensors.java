package org.stormgears.powerup.subsystems.sensors;

import org.stormgears.powerup.subsystems.sensors.stormnet.StormNet;
import org.stormgears.powerup.subsystems.sensors.vision.Vision;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.sensor_drivers.NavX;

public class Sensors {
	private static Sensors instance;

	public static Sensors getInstance() {
		return instance;
	}

	private static RegisteredNotifier navXNotifier;
	private static RegisteredNotifier stormNetNotifier;
	private static RegisteredNotifier visionNotifier;

	private static Vision vision;
	private static NavX navX;
	private static StormNet stormNet;

	private static boolean _isPublishing = false;
	private static boolean _isInitiated = false;

	private Sensors() {
//		vision = new Vision();
		navX = new NavX();
		stormNet = new StormNet();

		stormNetNotifier = new RegisteredNotifier(stormNet, "StormNet");

		_isInitiated = true;
	}

	public static void init() {
		instance = new Sensors();
	}

	public Vision getVision() {
		return vision;
	}

	public NavX getNavX() {
		return navX;
	}

	public StormNet getStormNet() {
		return stormNet;
	}

	public static double getTheta() {
		return navX.getTheta();
	}

	public static boolean isPublishing(){
		return _isPublishing;
	}

	public static boolean isInitiated(){
		return _isInitiated;
	}

	public static void startPublishingToNetwork(){
		System.out.println("START PUBLISHNG!!!");
		stormNetNotifier.startPeriodic(0.1);
		System.out.println("PUBLISHNG!!!");
		_isPublishing = true;
	}
}
