package org.stormgears.utils.sensordrivers

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SerialPort
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.Robot

class NavX {
	enum class AngleUnit {
		Degrees, Radians
	}

    val ahrs: AHRS = when (Robot.config.navXPort) {
		"MXP" -> AHRS(SPI.Port.kMXP, 127)
		"USB" -> AHRS(SerialPort.Port.kUSB, AHRS.SerialDataType.kProcessedData, 127)
		else -> {
			throw IllegalStateException()
		}
	}

    private var velocityX: Float = 0.0f
    private var displacementX: Float = 0.0f
    private var initialTheta: Double = 0.0
    private var thetaIsSet = false

	/**
	 * Gets the angle of the robot in radians
	 *
	 *
	 * Make sure that you wrap calls to this method in "if (navX.thetaIsSet())"
	 *
	 * @return the angle of the robot in radians, throws IllegalStateException if theta is not set
	 */
	fun getTheta(unit: AngleUnit = AngleUnit.Radians, wrap: Boolean = true): Double {
		if (!thetaIsSet) {
			throw IllegalStateException("NavX theta is not set. Cannot get theta.")
		}

		var theta = ahrs.angle - initialTheta // degrees

		if (unit == AngleUnit.Radians) {
			theta = Math.toRadians(theta) // radians
		}

		if (wrap) {
			theta = theta % (2 * Math.PI)
			if (theta < 0) theta += 2 * Math.PI
		}

		return Robot.config.navXInvert * theta
	}

    val velocityY: Float
        get() = ahrs.velocityY

    val isCalibrating: Boolean
        get() = ahrs.isCalibrating

    init {
        //		System.out.println(ahrs.getFirmwareVersion());

        //		if (!ahrs.isConnected()) throw new RuntimeException("NavX is not available! Oopsie!");
    }

    /**
     * TODO: This method is sketchy at times, we need to fix it
     *
     *
     * Gets the displacement in the X direction of the robot from 0,0
     *
     * @return the displacement in the X direction of the robot
     */
    fun getDisplacementX(): Float {
        velocityX += (ahrs.rawAccelX.toDouble() * 9.8 * 0.02).toFloat()
        displacementX += (velocityX * 0.02).toFloat()
        return displacementX
    }

    fun getVelocityX(): Float {
        return ahrs.velocityX
    }

    fun debug() {
        if (thetaIsSet()) logger.debug("Theta: {}", box(getTheta()))
        logger.debug("DisplacementX: {}", box(getDisplacementX()))
    }

    fun thetaIsSet(): Boolean {
        return thetaIsSet
    }

    fun setInitialTheta() {
        initialTheta = ahrs.angle
        thetaIsSet = true
    }

	val compassHeading: Float
		get() = ahrs.compassHeading

	val isMagneticDisturbance: Boolean
		get() = ahrs.isMagneticDisturbance

	fun test(): Boolean {
        debug()
        return true
    }

    companion object {
        private val logger = LogManager.getLogger(NavX::class.java)
    }
}
