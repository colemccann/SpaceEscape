package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class GreenTurret extends Turret {

    // Stays in place and shoots periodically

    private long firingTime;

    public GreenTurret(Context context, float worldLocationX,
                       float worldLocationY, int pixelsPerMeter,
                       float facingAngle, int turretID,
                       TurretContract turretContract,
                       LaserContract laser) {
        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        // Put actual texture vertices here
        float left = .8f;
        float right = 1;
        float top = .75f;
        float bottom = .5f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    public void update() {
        // Fire once per second
        long interval = 1000;

        if (firingTime == 0) {
            fire();
        } else {

            long time = System.currentTimeMillis();

            if (time - firingTime >= interval) {
                fire();
            }
        }
    }

    private void fire() {

        laser.fireLaser(getTurretID(), getFacingAngle());

        firingTime = System.currentTimeMillis();
    }
}
