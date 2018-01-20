package org.stormgears.powerup.subsystems.information;

import org.stormgears.utils.AbstractRobotConfiguration;

public class RobotConfiguration extends AbstractRobotConfiguration {
	private static RobotConfiguration instance = new RobotConfiguration();
	public static RobotConfiguration getInstance() {
		return instance;
	}

	public boolean hasNavX;

	/*
	 * WARNING: Do not set properties here. Only access ones from the file. If you want to set robot properties,
	 * SSH into the roboRIO and edit /home/lvuser/config.txt in vim. The whole point of having the file is to make
	 * properties _different_ on each physical robot.
	 */
	@Override
	protected void loadExtras() {
		hasNavX = Boolean.parseBoolean(properties.getProperty("hasNavX"));
	}
}
