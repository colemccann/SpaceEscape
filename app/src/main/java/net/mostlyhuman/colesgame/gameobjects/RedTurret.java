package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class RedTurret extends Turret {
    // Follows player and shoots when player gets close enough
    // Perhaps increase laser speed

    public RedTurret(Context context, float worldLocationX,
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

    public void update(PointF playerLocation) {
        //// TODO: 8/6/2017 Define behavior

        double angle;
        float distanceX = getWorldLocation().x - playerLocation.x;
        float distanceY = getWorldLocation().y - playerLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        double sinValue = distanceY / distanceH;
        angle = (Math.acos(sinValue) * (180 / Math.PI));

        // Follow the player
        if (playerLocation.x >= getWorldLocation().x) {
            setFacingAngle(180 + (float) angle);
        } else if (playerLocation.x < getWorldLocation().x) {
            setFacingAngle(180 - (float) angle);
        }

        // If the player is close enough, fire
        if (distanceH <= pixelsPerMeter * 2.2) {

            fire();
        }
    }

    private void fire() {
        laser.fireLaser(getTurretID(), getFacingAngle());
    }
}
