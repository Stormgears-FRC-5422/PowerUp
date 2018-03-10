package org.stormgears.utils.decoupling

import com.ctre.phoenix.ErrorCode
import com.ctre.phoenix.motorcontrol.SensorCollection

interface ISensorCollection {
	/**
	 * Get the position of whatever is in the analog pin of the Talon, regardless of
	 * whether it is actually being used for feedback.
	 *
	 * @return  the 24bit analog value.  The bottom ten bits is the ADC (0 - 1023)
	 * on the analog pin of the Talon. The upper 14 bits tracks the overflows and underflows
	 * (continuous sensor).
	 */
	val analogIn: Int

	/**
	 * Get the position of whatever is in the analog pin of the Talon, regardless of whether
	 * it is actually being used for feedback.
	 *
	 * @return  the ADC (0 - 1023) on analog pin of the Talon.
	 */
	val analogInRaw: Int

	/**
	 * Get the velocity of whatever is in the analog pin of the Talon, regardless of
	 * whether it is actually being used for feedback.
	 *
	 * @return  the speed in units per 100ms where 1024 units is one rotation.
	 */
	val analogInVel: Int

	/**
	 * Get the quadrature position of the Talon, regardless of whether
	 * it is actually being used for feedback.
	 *
	 * @return  the quadrature position.
	 */
	val quadraturePosition: Int

	/**
	 * Get the quadrature velocity, regardless of whether
	 * it is actually being used for feedback.
	 *
	 * @return  the quadrature velocity in units per 100ms.
	 */
	val quadratureVelocity: Int

	/**
	 * Gets pulse width position, regardless of whether
	 * it is actually being used for feedback.
	 *
	 * @return  the pulse width position.
	 */
	val pulseWidthPosition: Int

	/**
	 * Gets pulse width velocity, regardless of whether
	 * it is actually being used for feedback.
	 *
	 * @return  the pulse width velocity in units per 100ms (where 4096 units is 1 rotation).
	 */
	val pulseWidthVelocity: Int

	/**
	 * Gets pulse width rise to fall time.
	 *
	 * @return  the pulse width rise to fall time in microseconds.
	 */
	val pulseWidthRiseToFallUs: Int

	/**
	 * Gets pulse width rise to rise time.
	 *
	 * @return  the pulse width rise to rise time in microseconds.
	 */
	val pulseWidthRiseToRiseUs: Int

	/**
	 * Gets pin state quad a.
	 *
	 * @return  the pin state of quad a (1 if asserted, 0 if not asserted).
	 */
	val pinStateQuadA: Boolean

	/**
	 * Gets pin state quad b.
	 *
	 * @return  Digital level of QUADB pin (1 if asserted, 0 if not asserted).
	 */
	val pinStateQuadB: Boolean

	/**
	 * Gets pin state quad index.
	 *
	 * @return  Digital level of QUAD Index pin (1 if asserted, 0 if not asserted).
	 */
	val pinStateQuadIdx: Boolean

	/**
	 * Is forward limit switch closed.
	 *
	 * @return  '1' iff forward limit switch is closed, 0 iff switch is open. This function works
	 * regardless if limit switch feature is enabled.
	 */
	val isFwdLimitSwitchClosed: Boolean

	/**
	 * Is reverse limit switch closed.
	 *
	 * @return  '1' iff reverse limit switch is closed, 0 iff switch is open. This function works
	 * regardless if limit switch feature is enabled.
	 */
	val isRevLimitSwitchClosed: Boolean

	/**
	 * Sets analog position.
	 *
	 * @param   newPosition The new position.
	 * @param   timeoutMs
	 * Timeout value in ms. If nonzero, function will wait for
	 * config success and report an error if it times out.
	 * If zero, no blocking or checking is performed.
	 *
	 * @return  an ErrorCode.
	 */
	fun setAnalogPosition(newPosition: Int, timeoutMs: Int): ErrorCode

	/**
	 * Change the quadrature reported position.  Typically this is used to "zero" the
	 * sensor. This only works with Quadrature sensor.  To set the selected sensor position
	 * regardless of what type it is, see SetSelectedSensorPosition in the motor controller class.
	 *
	 * @param   newPosition The position value to apply to the sensor.
	 * @param   timeoutMs
	 * Timeout value in ms. If nonzero, function will wait for
	 * config success and report an error if it times out.
	 * If zero, no blocking or checking is performed.
	 *
	 * @return  error code.
	 */
	fun setQuadraturePosition(newPosition: Int, timeoutMs: Int): ErrorCode

	/**
	 * Sets pulse width position.
	 *
	 * @param   newPosition The position value to apply to the sensor.
	 * @param   timeoutMs
	 * Timeout value in ms. If nonzero, function will wait for
	 * config success and report an error if it times out.
	 * If zero, no blocking or checking is performed.
	 *
	 * @return  an ErrErrorCode
	 */
	fun setPulseWidthPosition(newPosition: Int, timeoutMs: Int): ErrorCode

	class SensorCollectionAdapter(private val sensorCollection: SensorCollection) : ISensorCollection {
		override val analogIn
			get() = sensorCollection.analogIn
		override val analogInRaw
			get() = sensorCollection.analogInRaw
		override val analogInVel
			get() = sensorCollection.analogInVel
		override val quadraturePosition
			get() = sensorCollection.quadraturePosition
		override val quadratureVelocity
			get() = sensorCollection.quadratureVelocity
		override val pulseWidthPosition
			get() = sensorCollection.pulseWidthPosition
		override val pulseWidthVelocity
			get() = sensorCollection.pulseWidthVelocity
		override val pulseWidthRiseToFallUs
			get() = sensorCollection.pulseWidthRiseToFallUs
		override val pulseWidthRiseToRiseUs
			get() = sensorCollection.pulseWidthRiseToRiseUs
		override val pinStateQuadA
			get() = sensorCollection.pinStateQuadA
		override val pinStateQuadB
			get() = sensorCollection.pinStateQuadB
		override val pinStateQuadIdx
			get() = sensorCollection.pinStateQuadIdx
		override val isFwdLimitSwitchClosed
			get() = sensorCollection.isFwdLimitSwitchClosed
		override val isRevLimitSwitchClosed
			get() = sensorCollection.isRevLimitSwitchClosed

		override fun setAnalogPosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return sensorCollection.setAnalogPosition(newPosition, timeoutMs)
		}

		override fun setQuadraturePosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return sensorCollection.setQuadraturePosition(newPosition, timeoutMs)
		}

		override fun setPulseWidthPosition(newPosition: Int, timeoutMs: Int): ErrorCode {
			return sensorCollection.setPulseWidthPosition(newPosition, timeoutMs)
		}
	}
}
