import edu.wpi.first.wpilibj.RobotBase
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@SuppressStaticInitializationFor("edu.wpi.first.wpilibj.hal.JNIWrapper")
class TestRobotLauncher {
	@Test
	fun launchRobot() {
		// TODO: Mock HAL

		RobotBase.main()
	}
}
