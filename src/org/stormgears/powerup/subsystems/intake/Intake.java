package org.stormgears.powerup.subsystems.intake;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.subsystems.navigator.GlobalMapping;
import org.stormgears.utils.StormTalon;

public class Intake extends Subsystem {
	private static final Logger logger = LogManager.getLogger(Intake.class);
	private static Intake instance;

	public static Intake getInstance() {
		return instance;
	}

	//TODO: Change these to correct values
	private static final int LEFT_RELAY_ID = 0;
	private static final int RIGHT_RELAY_ID = 0;

	//TODO: Change these to correct values
	private static final int LEFT_TALON_ID = 0;
	private static final int RIGHT_TALON_ID = 0;

	// TODO: Rename these if needed.
	private StormTalon leftTalon, rightTalon;
	private Relay leftRelay, rightRelay;

	private Intake(int leftRelayId, int rightRelayId, int leftTalonId, int rightTalonId) {
		// TODO: Properly configure relays and talons
//		leftRelay = new Relay(leftRelayId);
//		rightRelay = new Relay(rightRelayId);
//
//		leftTalon = new StormTalon(leftTalonId);
//		rightTalon = new StormTalon(rightTalonId);
	}

	public static void init() {
		instance = new Intake(LEFT_RELAY_ID, RIGHT_RELAY_ID, LEFT_TALON_ID, RIGHT_TALON_ID);
	}

	public void enableIntake() {
		// TODO: Implement
		logger.info("Intake on");
		GlobalMapping.resetPosition(0, 0, 0);

	}

	public void disableIntake() {
		// TODO: Implement
		logger.info("Intake off");
	}

	@Override
	protected void initDefaultCommand() {

	}
}
