package org.stormgears.powerup.subsystems.field;

import edu.wpi.first.wpilibj.DriverStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.util.Unbox.box;

public class FmsInterface {
	private static final Logger logger = LogManager.getLogger(FmsInterface.class);
	private static FmsInterface instance = new FmsInterface();

	public static FmsInterface getInstance() {
		return instance;
	}

	private Thread poller;
	private String data = "";

	private FmsInterface() {
		poller = new Thread(() -> {
			int tryNum = 0;
			while (data == null || data.equals("")) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				logger.info("Try #{} getting data from the FMS.", box(++tryNum));

				data = DriverStation.getInstance().getGameSpecificMessage();
			}

			parseRandomSidesFromData();
		});
	}

	public void startPollingForData() {
		poller.start();
	}

	public void sendTestData(String data) {
		poller.interrupt();
		this.data = data;
		parseRandomSidesFromData();
	}

	private void parseRandomSidesFromData() {
		// Own switch
		switch (data.charAt(0)) {
			case 'L':
				FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT = FieldPositions.OWN_SWITCH_LEFT_PLATE;
				break;
			case 'R':
				FieldPositions.OWN_SWITCH_PLATE_ASSIGNMENT = FieldPositions.OWN_SWITCH_RIGHT_PLATE;
				break;
		}

		// Own switch
		switch (data.charAt(1)) {
			case 'L':
				FieldPositions.SCALE_PLATE_ASSIGNMENT = FieldPositions.SCALE_LEFT_PLATE;
				break;
			case 'R':
				FieldPositions.SCALE_PLATE_ASSIGNMENT = FieldPositions.SCALE_RIGHT_PLATE;
				break;
		}

		// Own switch
		switch (data.charAt(2)) {
			case 'L':
				FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT = FieldPositions.OPPONENT_SWITCH_LEFT_PLATE;
				break;
			case 'R':
				FieldPositions.OPPONENT_SWITCH_PLATE_ASSIGNMENT = FieldPositions.OPPONENT_SWITCH_RIGHT_PLATE;
				break;
		}

		logger.info("FMS Data: {}", data);
	}
}
