package org.stormgears.utils.talons

class ManagedTalon(val base: IBaseTalon, defaultConfig: TalonConfig = FactoryTalonConfig())
	: IBaseTalon by base, ITalon, GeneratedTalonManager(base, defaultConfig) {
	@Suppress("RedundantOverride")
	override fun setConfig(config: TalonConfig) = super.setConfig(config)
}
