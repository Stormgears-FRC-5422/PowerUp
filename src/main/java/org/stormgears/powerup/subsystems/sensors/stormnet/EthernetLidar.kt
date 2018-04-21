package org.stormgears.powerup.subsystems.sensors.stormnet

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

class EthernetLidar(voice: StormNetVoice) : StormNetSensor(voice) {
	companion object {
		const val INCHES = 0
		const val MILLIMETERS = 1
	}

	private val sensorValues: ShortArray
	private val sensorPairValues: ShortArray
	private val addressValues: ByteArray

	private var threshold: Int = 0

	val isAligned: Boolean
		get() = Math.abs(sensorPairValues[0] - sensorPairValues[1]) <= threshold

	init {
		// TODO magic number
		sensorCount = 4
		sensorValues = ShortArray(m_numSensors)
		sensorPairValues = ShortArray(2)
		// TODO: 24 addresses are hardcoded on the Mega
		addressValues = ByteArray(24)

		this.m_deviceString = voice.deviceString

		threshold = 5
	}

	override fun test(sleep: Int): Boolean {
		//		boolean superResult = super.test(sleep);
		testAddresses(sleep)

		try {
			for (i in 0 until sleep) {
				pollDistance()
				log("Lidar test returned [ " +
					sensorValues[0] + " ] [ " +
					sensorValues[1] + " ] [ " +
					sensorValues[2] + " ] [ " +
					sensorValues[3] + " ] ")
				TimeUnit.SECONDS.sleep(1)
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}

		return true
	}

	private fun testAddresses(sleep: Int) {
		try {
			for (i in 0 until sleep) {
				pollAddress()
				log("Lidar Address test returned [ " +
					addressValues[0] + " ] [ " +
					addressValues[1] + " ] [ " +
					addressValues[2] + " ] [ " +
					addressValues[3] + " ]")
				TimeUnit.SECONDS.sleep(1)
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}

	}

	private fun pollDistance() {
		fetchShorts("L", "Lidar", sensorValues)
	}

	private fun pollAddress() {
		fetchBytes("A", "Address", addressValues)
	}

	private fun pollPairs(pairNumber: Int) {
		fetchPairs(0, sensorPairValues)
	}

	// Distance in millimeters
	fun getDistance(sensorNumber: Int): Int {
		pollDistance()
		return sensorValues[sensorNumber].toInt() // Java wants shorts to be signed.  We want unsigned value
	}

	fun getPair(pairNumber: Int): ShortArray {
		pollPairs(pairNumber)
		return sensorPairValues
	}

	fun changeThreshold(threshold: Int) {
		this.threshold = threshold
		fetchThreshold(threshold)
	}

	fun getAverageDistance(side: Int, unit: Int = INCHES): Double? {
		pollPairs(side)

		var distanceCount = 0
		var distanceTotal = 0
		sensorPairValues.forEach {
			if (it < 8190) {
				distanceTotal += it
				distanceCount++
			}
		}

		if (distanceCount == 0) return null

		val distance = distanceTotal.toDouble() / distanceCount

		return when (unit) {
			INCHES -> distance / 25.4
			else -> distance
		}
	}

	private fun fetchThreshold(threshold: Int): Boolean {
		val receiveBuffer = ByteArray(4)
		val buffer = ByteBuffer.allocate(5)
		buffer.order(ByteOrder.LITTLE_ENDIAN)

		buffer.put("T".toByteArray(StandardCharsets.US_ASCII)[0])
		buffer.putInt(threshold)
		return fetchCommand(buffer.array(), "Change Threshold", receiveBuffer)
	}

	private fun fetchPairs(pairNumber: Int, shortArray: ShortArray): Boolean {
		val receiveBuffer = ByteArray(shortArray.size * java.lang.Short.BYTES)
		val sendingBuffer = ByteBuffer.allocate(5)
		sendingBuffer.order(ByteOrder.LITTLE_ENDIAN)

		sendingBuffer.put("R".toByteArray(StandardCharsets.US_ASCII)[0])
		sendingBuffer.putInt(pairNumber)

		val result = fetchCommand(sendingBuffer.array(), "Report Pairs", receiveBuffer)

		val buffer = ByteBuffer.wrap(receiveBuffer)
		buffer.order(ByteOrder.LITTLE_ENDIAN)
		for (i in shortArray.indices) {
			shortArray[i] = buffer.short
			debug("short value: " + shortArray[i])
		}
		return result
	}

}
