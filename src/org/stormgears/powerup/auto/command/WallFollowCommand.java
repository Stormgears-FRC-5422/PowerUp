package org.stormgears.powerup.auto.command;
import edu.wpi.first.wpilibj.command.Command;
import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;

public class WallFollowCommand extends Command{

	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;


//This program is meant to be run when following a wall on the robot's left side


	public LIDAR() {

		i2c = new I2C(Port.kMXP, LIDAR_ADDR);

		distance = new byte[2];

		updater = new java.util.Timer();

	}
	public LIDAR2() {

		i2c = new I2C(Port.kMXP, LIDAR_ADDR);

		distance = new byte[2];

		updater = new java.util.Timer();

	}

	public int getDistance() {

		return (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);

	}

	public int getDistance2() {

		return (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);

	}






	public double getDistanceIn() {


		return (double) getDistance() * 0.393701;

	}

	public double getDistanceIn2() {


		return (double) getDistance() * 0.393701;

	}




	/**

	 * Start 10Hz polling of LIDAR sensor, in a background task. Only allow 10 Hz. polling at the

	 * moment.

	 */

	public void start() {

		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);

	}





	/**

	 * Stop the background sensor-polling task.

	 */

	public void stop() {

		updater.cancel();

		updater = new java.util.Timer();

	}





	/**

	 * Read from the sensor and update the internal "distance" variable with the result.

	 */

	private void update() {

		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement

		Timer.delay(0.04); // Delay for measurement to be taken

		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement

		Timer.delay(0.005); // Delay to prevent over polling

	}



	/**

	 * Timer task to keep distance updated

	 *

	 */

	private class LIDARUpdater extends TimerTask {

		public void run() {

			while (true) {

				update();

				try {

					Thread.sleep(10);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

				//90 can be replaced with desired distance from the wall
				if(getDistance() == 90) {
					if(getDistance2() == 90) {
						System.out.println("move encoder forward for 100 ticks");
					}
					if(getDistance2() < 90){
						System.out.println("move left side robot motors until getDistance2 = getDistance");
					}
					if(getDistance2() > 90){
						System.out.println("move right side robot motors until getDistance2 = getDistance");
					}
					}

				if(getDistance() > 90) {
					System.out.println("strafe left until getDistance is 90");
					if(getDistance2() == 90) {
						System.out.println("move encoder forward for 100 ticks");
					}
					if(getDistance2() < 90){
						System.out.println("move left side robot motors until getDistance2 = getDistance");
					}
					if(getDistance2() > 90){
						System.out.println("move right side robot motors until getDistance2 = getDistance");
					}
				}

				if(getDistance() < 90) {
					System.out.println("strafe right until getDistance is 90");
					if(getDistance2() == 90) {
						System.out.println("move encoder forward for 100 ticks");
					}
					if(getDistance2() < 90){
						System.out.println("move left side robot motors until getDistance2 = getDistance");
					}
					if(getDistance2() > 90){
						System.out.println("move right side robot motors until getDistance2 = getDistance");
					}
				}


			}

		}

	}

}
	public WallFollowCommand() {
		// Use requires() here to declare subsystem dependencies
//		requires(Robot.exampleSubsystem);


	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}}
