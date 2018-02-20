package org.stormgears.powerup.subsystems.dsio.joystick_detection;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickDetector {
	private Thread detectionThread;
	private DriverStation ds;
	private boolean shouldStop;

	private String[] names;
	private Joystick[] joysticks;
	private int normalJoystickChannel = -1, mspChannel = -1, buttonBoard2018Channel = -1;

	public JoystickDetector() {
		detectionThread = new Thread(this::detect);
		ds = DriverStation.getInstance();
		names = new String[5];
		joysticks = new Joystick[5];
	}

	public void start() {
		System.out.println("Starting joystick detection!");
		shouldStop = false;
		detectionThread.run();
	}

	public void stop() {
		System.out.println("Stopping joystick detection!");
		shouldStop = true;
	}

	public void forceStop() {
		detectionThread.interrupt();
		System.out.println("Force stopping joystick detection!");
	}

	private void detect() {

		while (!shouldStop && ds.isDisabled()) {
			if (Thread.interrupted()) return;	// Exit immediately

			for (int channel = 0; channel < names.length; channel++) {
				names[channel] = ds.getJoystickName(channel);

				if (names[channel].length() > 1) {
					joysticks[channel] = new Joystick(channel);
				}
			}

			for (int i = 0; i < joysticks.length; i++) {
				if (joysticks[i] != null) {
					if (joysticks[i].getName().contains("MSP")) {	// Match MSP-430 board
						System.out.println("MSP-430 Guess: " + i);
						mspChannel = i;
					} else if (joysticks[i].getName().contains("Log")) {	// Match Logitech Extreme 3D joystick
						if (joysticks[i].getX() < -0.98 && joysticks[i].getY() < -0.98) {
							System.out.println("New Button Board Guess: " + i);
							buttonBoard2018Channel = i;
						} else if (Math.abs(joysticks[i].getX()) < 0.5 || Math.abs(joysticks[i].getY()) < 0.5) {
							System.out.println("Normal Joystick Guess: " + i);
							normalJoystickChannel = i;
						}
					}
				}
			}
		}
	}

	public int getNormalJoystickChannel() {
		return normalJoystickChannel;
	}

	public int getMspChannel() {
		return mspChannel;
	}

	public int getButtonBoard2018Channel() {
		return buttonBoard2018Channel;
	}

	public String[] getNames() {
		return names;
	}
}
