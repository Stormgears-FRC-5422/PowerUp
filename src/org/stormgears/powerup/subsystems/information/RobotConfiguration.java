package org.stormgears.powerup.subsystems.information;

import org.stormgears.utils.AbstractRobotConfiguration;

public class RobotConfiguration extends AbstractRobotConfiguration {
	private static RobotConfiguration instance = new RobotConfiguration();
	public static RobotConfiguration getInstance() {
		return instance;
	}

	/*
	 * Public property fields: set these in loadExtras() so they can be updated from the config file.
	 * Remember, these will be set *once*. These values *will not* change throughout execution of the robot code.
	 */
	public boolean hasNavX;
	public int frontLeftTalonId, frontRightTalonId, rearLeftTalonId, rearRightTalonId;

	/*
	 * WARNING: Do not set properties here. Only access ones from the file and set their respective fields.
	 * If you want to set robot properties, SSH into the roboRIO and edit /home/lvuser/config.properties in vim.
	 * The whole point of having the file is to make properties _different_ on each physical robot.
	 *
	 * Also, parseInt() will return Integer.MIN_VALUE if it cannot successfully parse an int from the String provided
	 */
	@Override
	protected void loadExtras() {
		hasNavX = Boolean.parseBoolean(properties.getProperty("hasNavX"));
		frontLeftTalonId = parseInt(properties.getProperty("frontLeftTalonId"));
		frontRightTalonId = parseInt(properties.getProperty("frontRightTalonId"));
		rearLeftTalonId = parseInt(properties.getProperty("rearLeftTalonId"));
		rearRightTalonId = parseInt(properties.getProperty("rearRightTalonId"));
	}
}
