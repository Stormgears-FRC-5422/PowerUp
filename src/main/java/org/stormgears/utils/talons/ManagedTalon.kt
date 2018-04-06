package org.stormgears.utils.talons

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType

class ManagedTalon(val base: IBaseTalon, defaultConfig: TalonConfig = FactoryTalonConfig())
	: IBaseTalon by base, ITalon, GeneratedTalonManager(base, defaultConfig) {
	@Suppress("RedundantOverride")
	override fun setConfig(config: TalonConfig) = super.setConfig(config)

	/*
	 * These methods decrease load on the CAN bus by ignoring identical calls to set.
	 */

	private var lastMode: ControlMode? = null
	private var lastDemand0 = Double.NaN
	private var lastDemand1Type: DemandType? = null
	private var lastDemand1 = Double.NaN

	override fun set(Mode: ControlMode?, demand0: Double, demand1: Double) {
		if (lastMode == Mode
			&& lastDemand0 == demand0
			&& lastDemand1 == demand1
			&& lastDemand1Type == null) {
			return
		}

		lastMode = Mode
		lastDemand0 = demand0
		lastDemand1Type = null
		lastDemand1 = demand1

		base.set(Mode, demand0, demand1)
	}

	override fun set(Mode: ControlMode?, demand0: Double, demand1Type: DemandType?, demand1: Double) {
		if (lastMode == Mode
			&& lastDemand0 == demand0
			&& lastDemand1 == demand1
			&& lastDemand1Type == demand1Type) {
			return
		}

		lastMode = Mode
		lastDemand0 = demand0
		lastDemand1Type = demand1Type
		lastDemand1 = demand1

		base.set(Mode, demand0, demand1Type, demand1)
	}

	override fun set(Mode: ControlMode?, demand: Double) {
		if (lastMode == Mode
			&& lastDemand0 == demand
			&& lastDemand1.isNaN()
			&& lastDemand1Type == null) {
			return
		}

		lastMode = Mode
		lastDemand0 = demand
		lastDemand1Type = null
		lastDemand1 = Double.NaN

		base.set(Mode, demand)
	}

	override fun set(speed: Double) {
		if (lastMode == ControlMode.PercentOutput
			&& lastDemand0 == speed
			&& lastDemand1Type == null
			&& lastDemand1.isNaN()) {
			return
		}

		lastMode = ControlMode.PercentOutput
		lastDemand0 = speed
		lastDemand1Type = null
		lastDemand1 = Double.NaN

		base.set(speed)
	}
}
