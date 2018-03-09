package org.stormgears.powerup.subsystems.navigator

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import org.apache.commons.text.StringEscapeUtils
import org.apache.logging.log4j.LogManager
import org.stormgears.utils.concurrency.WithCoroutines
import java.io.PrintWriter
import java.time.LocalDateTime

fun dashboardify(talons: DriveTalons) {
	talons.talons.forEachIndexed { index, stormTalon ->
		val sensorCollection = stormTalon.sensorCollection
		SmartDashboard.putNumber("Talon $index (ID ${stormTalon.deviceID}) position", sensorCollection.quadraturePosition.toDouble())
		SmartDashboard.putNumber("Talon $index (ID ${stormTalon.deviceID}) velocity", sensorCollection.quadratureVelocity.toDouble())
	}
}

class TalonDebugger : WithCoroutines {
	companion object {
		val logger = LogManager.getLogger(TalonDebugger::class.java)
	}

	fun start(talons: Array<WPI_TalonSRX>): Job {
		val filename = "TalonDebug-${LocalDateTime.now()}.csv"
		logger.info("Starting TalonDebugger, writing to {}", filename);
		val writer = PrintWriter(filename, "UTF-8")
		writer.println("index,deviceID,get(),description,inverted,isAlive(),isSafetyEnabled(),activeTrajectoryHeading,activeTrajectoryPosition,activeTrajectoryVelocity,baseID,busVoltage,controlMode,firmwareVersion,handle,lastError,motionProfileTopLevelBufferCount,motorOutputPercent,motorOutputVoltage,outputCurrent,temperature,hasResetOccurred(),sensorCollection.analogIn,sensorCollection.analogInRaw,sensorCollection.analogInVel,sensorCollection.pinStateQuadA,sensorCollection.pinStateQuadB,sensorCollection.pinStateQuadIdx,sensorCollection.pulseWidthPosition,sensorCollection.pulseWidthRiseToFallUs,sensorCollection.pulseWidthRiseToRiseUs,sensorCollection.pulseWidthVelocity,sensorCollection.quadraturePosition,sensorCollection.quadratureVelocity,sensorCollection.isFwdLimitSwitchClosed(),sensorCollection.isRevLimitSwitchClosed()")

		fun add(str: Any) {
			writer.print(StringEscapeUtils.escapeCsv(str.toString()) + ",")
		}

		return launch {
			while (true) {
				talons.forEachIndexed { index, talon ->
					add(index)
					add(talon.deviceID)
					add(talon.get())
					add(talon.description)
					add(talon.inverted)
					add(talon.isAlive)
					add(talon.isSafetyEnabled)
					add(talon.activeTrajectoryHeading)
					add(talon.activeTrajectoryPosition)
					add(talon.activeTrajectoryVelocity)
					add(talon.baseID)
					add(talon.busVoltage)
					add(talon.controlMode)
					add(talon.firmwareVersion)
					add(talon.handle)
					add(talon.lastError)
					add(talon.motionProfileTopLevelBufferCount)
					add(talon.motorOutputPercent)
					add(talon.motorOutputVoltage)
					add(talon.outputCurrent)
					add(talon.temperature)
					add(talon.hasResetOccurred())
					add(talon.sensorCollection.analogIn)
					add(talon.sensorCollection.analogInRaw)
					add(talon.sensorCollection.analogInVel)
					add(talon.sensorCollection.pinStateQuadA)
					add(talon.sensorCollection.pinStateQuadB)
					add(talon.sensorCollection.pinStateQuadIdx)
					add(talon.sensorCollection.pulseWidthPosition)
					add(talon.sensorCollection.pulseWidthRiseToFallUs)
					add(talon.sensorCollection.pulseWidthRiseToRiseUs)
					add(talon.sensorCollection.pulseWidthVelocity)
					add(talon.sensorCollection.quadraturePosition)
					add(talon.sensorCollection.quadratureVelocity)
					add(talon.sensorCollection.isFwdLimitSwitchClosed())
					add(talon.sensorCollection.isRevLimitSwitchClosed())

					writer.println()
				}

				delay(50)
			}
		}
	}
}
