package org.stormgears.powerup.subsystems.navigator

class SunProfileTest {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val sunProfile = SunProfile()

			for (i in 0..150 step 5) {
				val i = i + (Math.random() - 0.5) * 0.1
				println("$i," + sunProfile.profile(i.toDouble(), 150.0))
			}
		}
	}
}
