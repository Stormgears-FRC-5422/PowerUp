package org.stormgears.powerup.subsystems.dsio.joystickdetection

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.Joystick
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.util.Unbox.box
import org.stormgears.powerup.subsystems.dsio.ButtonBoard2018V1
import org.stormgears.powerup.subsystems.dsio.DummyButtonBoard
import org.stormgears.powerup.subsystems.dsio.IButtonBoard
import org.stormgears.utils.dsio.DummyJoystick
import org.stormgears.utils.dsio.IRawJoystick
import org.stormgears.utils.dsio.LogitechJoystick
import org.stormgears.utils.dsio.XboxJoystick

class JoystickDetector {
	companion object {
		private val logger = LogManager.getLogger(JoystickDetector::class.java)
	}

	private val ds = DriverStation.getInstance()
	val names: Array<String?> = arrayOfNulls(5)
	private val joysticks: Array<Joystick?> = arrayOfNulls(5)

	private var drivingJoystickChannel = -1
	private var mspChannel = -1
	private var buttonBoard2018Channel = -1
	private var xboxChannel = -1

	val drivingJoystick: IRawJoystick
		get() {
			when {
				xboxChannel != -1 -> {
					logger.info("Selecting XBOX joystick")
					return XboxJoystick(xboxChannel)
				}
				drivingJoystickChannel != -1 -> {
					logger.info("Selecting Logitech joystick")
					return LogitechJoystick(drivingJoystickChannel)
				}
				else -> {
					logger.warn("Joystick not found! Using dummy joystick.")
					return DummyJoystick()
				}
			}
		}

	val buttonBoard: IButtonBoard
		get() {
			return if (buttonBoard2018Channel != -1 && mspChannel != -1) {
				logger.info("Selecting ButtonBoard2018v1")
				val drivingJoystick = drivingJoystick
				ButtonBoard2018V1.getInstance(Joystick(mspChannel), Joystick(buttonBoard2018Channel), if (drivingJoystick is LogitechJoystick) drivingJoystick else null)
			} else {
				logger.warn("Matching combination of buttonboard and joystick not found! Using dummy button board.")
				DummyButtonBoard()
			}
		}

	fun detect() {
		logger.trace("searching for joysticks")

		for (channel in names.indices) {
			names[channel] = ds.getJoystickName(channel)

			if (names[channel]!!.length > 1) {
				joysticks[channel] = Joystick(channel)
			}
		}

		for (i in joysticks.indices) {
			val joystick = joysticks[i]
			if (joystick != null) {
				if (joystick.name.contains("MSP")) {    // Match MSP-430 board
					logger.info("MSP-430 guess: {}", box(i))
					mspChannel = i
				} else if (joystick.name.toUpperCase().contains("XBOX")) {
					logger.info("XBOX controller guess: {}", box(i))
					xboxChannel = i
				} else if (joystick.name.contains("Logitech")) {    // Match Logitech Extreme 3D joystick
					logger.trace("Axis 0: {}; Axis 1: {}", box(joystick.getRawAxis(0)), box(joystick.getRawAxis(1)))
					if (joystick.getRawAxis(0) < -0.9 && joystick.getRawAxis(2) > 0.9) {
						logger.info("Button board joystick guess: {}", box(i))
						buttonBoard2018Channel = i
					} else {
						logger.info("Driving joystick guess: {}", box(i))
						drivingJoystickChannel = i
					}
				}
			}
		}
	}
}
