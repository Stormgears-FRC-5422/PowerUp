package org.stormgears.utils.talons

class TalonManager(val base: IBaseTalon) : IBaseTalon by base, ITalon {
	override fun setConfig(config: TalonConfig) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
