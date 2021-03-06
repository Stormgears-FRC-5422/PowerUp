package org.stormgears.powerup.subsystems.navigator

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

// See: https://www.desmos.com/calculator/djcgmeucjb

class SunProfile(
	/**
	 * Target maximum velocity in ticks/100ms
	 */
	val vTarget: Double = 5000.0,

	/**
	 * Maximum initial velocity in ticks per 100ms
	 */
	val vI: Double = 500.0,

	/**
	 * Absolute minimum velocity in ticks per 100ms
	 */
	val vMin: Double = 100.0) {

	/**
	 * Maximum acceleration in ticks per 100ms per inch given distance [d] in inches
	 */
	fun maxA(d: Double): Double { // ticks per 100ms per inch???
		return min(125.0, d * 6.0 + 50.0)
	}

	/**
	 * Sine curve correction factor for [rBase]
	 */
	fun vI(y: Double): Double {
		return min(y * 0.2, vI)
	}

	/**
	 * Function of the ramp up and ramp down curves
	 */
	fun rBase(x: Double, xT: Double, yT: Double): Double {
		val vI1 = vI(yT)
		return max(sin(PI / 2 * (x / xT - 4)) * (yT - vI1) + vI1, vMin)
	}

	/**
	 * Calculates rBase target x given [y] and distance [d]
	 */
	fun mX(y: Double, d: Double, maxA: Double): Double {
		return min(PI * y / (2 * maxA), d / 2)
	}

	/**
	 * Calculates rBase target y given [x] and distance [d]
	 */
	fun mY(x: Double, d: Double, maxA: Double): Double {
		return 2 * maxA * x / PI
	}

	/**
	 * Calculates the PID velocity of a motor given movement progress [x] in inches and target distance [d] in inches
	 */
	fun profile(x: Double, d: Double, maxAMultiplier: Double = 1.0): Double {
		val mX = mX(vTarget, d, maxA(d) * maxAMultiplier)
		val mX2 = mX(vTarget, d, maxA(d) * 0.7 * maxAMultiplier)
		val mYmX = mY(mX, d, maxA(d) * maxAMultiplier)
//		val mYmX2 = mY(mX, d, maxA(d) * 0.7 * maxAMultiplier)
		return when {
			x < mX -> rBase(x, mX, mYmX)
			x in mX..(d - mX2) -> vTarget
			x in (d - mX2)..d -> rBase(d - x, mX2, mYmX - 500) + 500
			else -> 0.0
		}
	}

}
