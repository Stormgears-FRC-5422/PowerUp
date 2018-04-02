package org.stormgears.utils.talons

class TalonManager(val base: IBaseTalon, defaultConfig: TalonConfig = FactoryTalonConfig())
	: IBaseTalon by base, ITalon, GeneratedTalonManager(base, defaultConfig)
