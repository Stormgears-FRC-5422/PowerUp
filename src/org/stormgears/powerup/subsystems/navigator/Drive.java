package org.stormgears.powerup.subsystems.navigator;

public class Drive {
    private static Drive instance = new Drive();
    public static Drive getInstance() {
        return instance;
    }

    private Drive() {

    }

    public void move() {

//        double theta = Math.atan2(joy.getX(), joy.getY());
//        if(theta < 0) theta = 2 * Math.PI + theta;
//
//        if(Math.abs(joy.getX()) > 0.1 || Math.abs(joy.getY()) > 0.1 || Math.abs(joy.getZ()) > 0.2)
//            mecMove(6300 * Math.sqrt(joy.getX() * joy.getX() + joy.getY() *
//                    joy.getY() + joy.getZ() * joy.getZ()), theta, joy.getZ());
//        else {
//            setDriveTalonsZeroVelocity();
//        }
    }

}
