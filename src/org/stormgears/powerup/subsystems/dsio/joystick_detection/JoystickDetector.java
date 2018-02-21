package org.stormgears.powerup.subsystems.dsio.joystick_detection;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stormgears.powerup.subsystems.dsio.ButtonBoard2017;
import org.stormgears.powerup.subsystems.dsio.ButtonBoard2018V1;
import org.stormgears.powerup.subsystems.dsio.IButtonBoard;
import org.stormgears.utils.dsio.IRawJoystick;
import org.stormgears.utils.dsio.LogitechJoystick;
import org.stormgears.utils.dsio.XboxJoystick;

import static org.apache.logging.log4j.util.Unbox.box;

public class JoystickDetector {
	private static final Logger logger = LogManager.getLogger(JoystickDetector.class);

	private DriverStation ds;

	private String[] names;
	private Joystick[] joysticks;
	private int drivingJoystickChannel = -1, mspChannel = -1, buttonBoard2018Channel = -1, xboxChannel = -1;

	public JoystickDetector() {
		ds = DriverStation.getInstance();
		names = new String[5];
		joysticks = new Joystick[5];
	}

	public void detect() {
		logger.trace("searching for joysticks");

		for (int channel = 0; channel < names.length; channel++) {
			names[channel] = ds.getJoystickName(channel);

			if (names[channel].length() > 1) {
				joysticks[channel] = new Joystick(channel);
			}
		}

		for (int i = 0; i < joysticks.length; i++) {
			Joystick joystick = joysticks[i];
			if (joystick != null) {
				if (joystick.getName().contains("MSP")) {    // Match MSP-430 board
					logger.info("MSP-430 guess: {}", box(i));
					mspChannel = i;
				} else if (joystick.getName().toUpperCase().contains("XBOX")) {
					logger.info("XBOX controller guess: {}", box(i));
					xboxChannel = i;
				} else if (joystick.getName().contains("Logitech")) {    // Match Logitech Extreme 3D joystick
					if (joystick.getX() < -0.98 && joystick.getY() < -0.98) {
						logger.info("Button board joystick guess: {}", box(i));
						buttonBoard2018Channel = i;
					} else {
						logger.info("Driving joystick guess: {}", box(i));
						drivingJoystickChannel = i;
					}
				}
			}
		}
	}


	public IRawJoystick getDrivingJoystick() {
		if (xboxChannel != -1) {
			logger.info("Selecting XBOX joystick");
			return new XboxJoystick(xboxChannel);
		} else {
			logger.info("Selecting Logitech joystick");
			return new LogitechJoystick(drivingJoystickChannel);
		}
	}

	public IButtonBoard getButtonBoard() {
		if (buttonBoard2018Channel != -1) {
			logger.info("Selecting ButtonBoard2018v1");
			return ButtonBoard2018V1.getInstance(new Joystick(mspChannel), new Joystick(buttonBoard2018Channel));
		} else {
			logger.info("Selecting ButtonBoard2017");
			return ButtonBoard2017.getInstance(new Joystick(mspChannel), new Joystick(drivingJoystickChannel));
		}
	}

	public String[] getNames() {
		return names;
	}
}
