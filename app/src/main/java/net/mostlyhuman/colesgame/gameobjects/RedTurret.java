package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import net.mostlyhuman.colesgame.game.SoundManager;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class RedTurret extends Turret {

    private static final String TAG = "RedTurret";

    private long lastShot;

    // Follows player and shoots when player gets close enough
    // Perhaps increase laser speed

    public RedTurret(Context context, float worldLocationX,
                     float worldLocationY, int pixelsPerMeter,
                     float facingAngle, int turretID,
                     TurretContract turretContract,
                     LaserContract laser) {
        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        setType(Constants.Types.TURRET_RED);

        float left = .8f;
        float right = 1;
        float top = .4f;
        float bottom = .2f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    public void update(PointF playerLocation) {
        double angle;
        float distanceX = getWorldLocation().x - playerLocation.x;
        float distanceY = getWorldLocation().y - playerLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double sinValue = distanceY / distanceH;
        angle = (Math.acos(sinValue) * (180 / Math.PI));

        // Follow the player
        if (playerLocation.x > getWorldLocation().x) {
            setFacingAngle(180 + (float) angle);
        } else if (playerLocation.x < getWorldLocation().x) {
            setFacingAngle(180 - (float) angle);
        }

        // If the player is close enough, fire
        if (distanceH <= pixelsPerMeter * 2.5) {
            if (System.currentTimeMillis() > lastShot + 500) {
                fire();
                lastShot = System.currentTimeMillis();
            }
            Log.d(TAG, "Red turret firing. ID: " + getTurretID());
        }
    }
}
