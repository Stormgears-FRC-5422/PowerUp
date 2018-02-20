package org.stormgears.powerup.subsystems.dsio.joystick_detection;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import org.stormgears.powerup.subsystems.dsio.ButtonBoard2017;
import org.stormgears.powerup.subsystems.dsio.ButtonBoard2018V1;
import org.stormgears.powerup.subsystems.dsio.IButtonBoard;
import org.stormgears.utils.dsio.IRawJoystick;
import org.stormgears.utils.dsio.LogitechJoystick;
import org.stormgears.utils.dsio.XboxJoystick;

public class JoystickDetector {
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
//						System.out.println("MSP-430 Guess: " + i);
					mspChannel = i;
				} else if (joystick.getName().toUpperCase().contains("XBOX")) {
//						System.out.println("XBOX Guess: " + i);
					xboxChannel = i;
				} else if (joystick.getName().contains("Logitech")) {    // Match Logitech Extreme 3D joystick
					if (joystick.getX() < -0.98 && joystick.getY() < -0.98) {
//							System.out.println("New Button Board Guess: " + i);
						buttonBoard2018Channel = i;
					} else {
//							System.out.println("Normal Joystick Guess: " + i);
						drivingJoystickChannel = i;
					}
				}
			}
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			return;
		}
	}


	public IRawJoystick getDrivingJoystick() {
		if (xboxChannel != -1) {
			return new XboxJoystick(xboxChannel);
		} else {
			return new LogitechJoystick(drivingJoystickChannel);
		}
	}

	public IButtonBoard getButtonBoard() {
		if (buttonBoard2018Channel != -1) {
			return new ButtonBoard2018V1(new Joystick(mspChannel), new Joystick(buttonBoard2018Channel));
		} else {
			return new ButtonBoard2017(new Joystick(mspChannel), new Joystick(drivingJoystickChannel));
		}
	}

	public String[] getNames() {
		return names;
	}
}
