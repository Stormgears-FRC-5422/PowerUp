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

	val hasNavX = getBoolean("hasNavX")
	val frontLeftTalonId = getInt("frontLeftTalonId")
	val frontRightTalonId = getInt("frontRightTalonId")
	val rearLeftTalonId = getInt("rearLeftTalonId")
	val rearRightTalonId = getInt("rearRightTalonId")
	val velocityF = getDouble("velocityF")
	val velocityP = getDouble("velocityP")
	val velocityI = getDouble("velocityI")
	val velocityD = getDouble("velocityD")
	val positionP = getDouble("positionP")
	val positionI = getDouble("positionI")
	val positionD = getDouble("positionD")
	val velocityIzone = getInt("velocityIzone")
	val positionIzone = getInt("positionIzone")
	val encoderResolution = getInt("encoderResolution")
	val reverseJoystick = getBoolean("reverseJoystick")
	val wheelRadius = getDouble("wheelRadius")

	val enableSensors = getBoolean("enableSensors", true)
	val enableDrive = getBoolean("enableDrive", true)
	val enableIntake = getBoolean("enableIntake", true)
	val enableElevator = getBoolean("enableElevator", true)
	val enableClimber = getBoolean("enableClimber", true)
	val enableGripper = getBoolean("enableGripper", true)
}
