package org.stormgears.utils.sensor_drivers;

import org.stormgears.powerup.Robot;

public class NavX_Gyro implements Sensor {

    

    @Override
    public boolean isAvailable() {
        return Robot.config.hasNavX;
    }

}
