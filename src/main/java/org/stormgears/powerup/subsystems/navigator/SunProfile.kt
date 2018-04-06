package org.stormgears.powerup.subsystems.navigator

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

// See: https://www.desmos.com/calculator/djcgmeucjb

/**
 * Target maximum velocity in ticks/100ms
 */
val vTarget = 5000.0

/**
 * Maximum initial velocity in ticks per 100ms
 */
val vI = 500.0

/**
 * Absolute minimum velocity in ticks per 100ms
 */
val vMin = 100.0

/**
 * Maximum acceleration in ticks per 100ms per inch given distance [d] in inches
 */
fun maxA(d: Double): Double { // ticks per 100ms per inch???
	return min(300.0, d * 6.0 + 50.0)
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
fun mX(y: Double, d: Double): Double {
	return min(PI * y / (2 * maxA(d)), d / 2)
}

/**
 * Calculates rBase target y given [x] and distance [d]
 */
fun mY(x: Double, d: Double): Double {
	return 2 * maxA(d) * x / PI
}

/**
 * Calculates the PID velocity of a motor given movement progress [x] in inches and target distance [d] in inches
 */
fun sunProfile(x: Double, d: Double): Double {
	val mX = mX(vTarget, d)
	val mYmX = mY(mX, d)
	return when {
		x < mX -> rBase(x, mX, mYmX)
		x in mX..(d - mX) -> vTarget
		x in (d - mX)..d -> rBase(d - x, mX, mYmX)
		else -> 0.0
	}
}
