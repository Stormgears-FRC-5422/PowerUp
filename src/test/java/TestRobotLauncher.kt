import edu.wpi.cscore.CameraServerJNI
import edu.wpi.first.networktables.NetworkTablesJNI
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.hal.HAL
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@SuppressStaticInitializationFor(
	"edu.wpi.first.wpilibj.hal.JNIWrapper",
	"edu.wpi.first.networktables.NetworkTablesJNI",
	"edu.wpi.cscore.CameraServerJNI"
)
@PrepareForTest(HAL::class)
class TestRobotLauncher {
	@Test
	fun launchRobot() {
		// TODO: Mock HAL
		PowerMockito.mockStatic(HAL::class.java)
		PowerMockito.mockStatic(NetworkTablesJNI::class.java)
		PowerMockito.mockStatic(CameraServerJNI::class.java)

		Mockito.`when`(HAL.initialize(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true)

		RobotBase.main()
	}
}
