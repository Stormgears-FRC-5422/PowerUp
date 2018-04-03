package org.stormgears.utils.talons

/**
 * Talon + Config management
 */
interface ITalon : IBaseTalon {
	fun setConfig(config: TalonConfig)
}
