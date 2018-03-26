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
	val navXInvert = if (getBoolean("isNavXInverted")) -1.0 else 1.0
	val navXPort = getString("navXPort")
	val frontLeftTalonId = getInt("frontLeftTalonId")
	val frontRightTalonId = getInt("frontRightTalonId")
	val rearLeftTalonId = getInt("rearLeftTalonId")
	val rearRightTalonId = getInt("rearRightTalonId")

	val velocityF = getDouble("velocityF")
	val velocityP = getDouble("velocityP")
	val velocityI = getDouble("velocityI")
	val velocityD = getDouble("velocityD")
	val velocityIzone = getInt("velocityIzone")

	val positionP = getDouble("positionP")
	val positionI = getDouble("positionI")
	val positionD = getDouble("positionD")
	val positionIzone = getInt("positionIzone")

	val elevatorRaiseP = getDouble("elevatorRaiseP")
	val elevatorRaiseI = getDouble("elevatorRaiseI")
	val elevatorRaiseD = getDouble("elevatorRaiseD")
	val elevatorLowerP = getDouble("elevatorLowerP")
	val elevatorLowerI = getDouble("elevatorLowerI")
	val elevatorLowerD = getDouble("elevatorLowerD")

	val encoderResolution = getInt("encoderResolution")

	val reverseJoystick = getBoolean("reverseJoystick")

	val wheelRadius = getDouble("wheelRadius")

	val enableSensors = getBoolean("enableSensors", true)
	val enableDrive = getBoolean("enableDrive", true)
	val enableIntake = getBoolean("enableIntake", true)
	val enableElevator = getBoolean("enableElevator", true)
	val enableClimber = getBoolean("enableClimber", true)
	val enableGripper = getBoolean("enableGripper", true)

	init {
		if (navXPort != "USB" && navXPort != "MXP") {
			throw IllegalStateException("navXPort must be USB or MXP")
		}
	}
}
