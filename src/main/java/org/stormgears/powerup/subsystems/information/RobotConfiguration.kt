package org.stormgears.powerup.subsystems.information

import org.stormgears.utils.configurationfile.BaseRobotConfiguration

object RobotConfiguration : BaseRobotConfiguration(useBackupIfFileNotAvailable = false) {

	/*
     * Public property fields: set these in loadExtras() so they can be updated from the config file.
     * Remember, these will be set *once*. These values *will not* change throughout execution of the robot code.
     */
	val robotWidth = getDouble("robotWidth")
	val robotLength = getDouble("robotLength")
	val robotHeight = getDouble("robotHeight")

	val hasNavX: Boolean = getBoolean("hasNavX")
	val frontLeftTalonId: Int = getInt("frontLeftTalonId")
	val frontRightTalonId: Int = getInt("frontRightTalonId")
	val rearLeftTalonId: Int = getInt("rearLeftTalonId")
	val rearRightTalonId: Int = getInt("rearRightTalonId")
	val velocityF: Double = getDouble("velocityF")
	val velocityP: Double = getDouble("velocityP")
	val velocityI: Double = getDouble("velocityI")
	val velocityD: Double = getDouble("velocityD")
	val positionP: Double = getDouble("positionP")
	val positionI: Double = getDouble("positionI")
	val positionD: Double = getDouble("positionD")
	val velocityIzone: Int = getInt("velocityIzone")
	val positionIzone: Int = getInt("positionIzone")
	val encoderResolution: Int = getInt("encoderResolution")
	val reverseJoystick: Boolean = getBoolean("reverseJoystick")
	val wheelRadius: Double = getDouble("wheelRadius")
}
