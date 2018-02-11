package org.stormgears.powerup.auto.command;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.CANTalon;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.stormgears.powerup.Robot;
import org.stormgears.powerup.subsystems.dsio.controls.Switch;
import org.stormgears.powerup.subsystems.field.FieldPositions;
import org.stormgears.utils.RegisteredNotifier;
import org.stormgears.utils.StormTalon;

import java.util.Map;

public class Path {
		static int mapLayout;
		static int startingPosition;
		static Alliance allianceColor;
		static String objective;
		static int switc = 0;
		public Path(int MapLayout, int Position, Alliance AllianceColor, String Objective) {
			mapLayout = MapLayout;
			startingPosition = Position;
			allianceColor = AllianceColor;
			objective = Objective;
			// TODO: make a int that has all the parameters of the match, to be used for the case/switch
		}

		public static void determineRoute()
		{
			// 1 = the left gate, 2 = the middle gate, 3 = the right gate
			if(startingPosition == 1)
			{
				if(objective == "Scale")
				{
					if(mapLayout == 1 || mapLayout == 4)
					{
						System.out.println("Moving straight to the scale");
					}
					if(mapLayout == 2 || mapLayout == 3)
					{
						System.out.println("Move straight, turn right into the weird area and then head for scale");
					}
				}
				if(objective == "Switch")
				{
					if(mapLayout == 1 || mapLayout == 3)
					{
						System.out.println("Turn right, then move straight until the switch is reached");
					}
					if(mapLayout == 2 || mapLayout == 4)
					{
						System.out.println("Move straight for the switch");
					}
				}
			}

			if(startingPosition == 2)
			{
				if(objective == "Scale")
				{
					if(mapLayout )
				}
				if(objective == "Switch")
				{

				}
			}
			if(startingPosition == 3)
			{
				if(objective == "Scale")
				{

				}
				if(objective == "Switch")
				{

				}
			}
		}




	}
