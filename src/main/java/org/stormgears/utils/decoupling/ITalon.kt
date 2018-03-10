package org.stormgears.utils.decoupling

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced
import edu.wpi.first.wpilibj.MotorSafety
import edu.wpi.first.wpilibj.SpeedController

interface ITalon : IMotorControllerEnhanced, SpeedController, MotorSafety,
	ITalonJavaHelpers.__DO_NOT_USE_OR_YOU_WILL_BE_FIRED.IControlModeGettable,
	ITalonJavaHelpers.__DO_NOT_USE_OR_YOU_WILL_BE_FIRED.IHandleGettable {
	val sensorCollection: ISensorCollection

	val dummy: Boolean;
}
