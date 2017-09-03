package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.game.SoundManager;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class BlueTurret extends Turret {

    // Stationary and shoots when player crosses firing path

    private long lastShot;

    public BlueTurret(Context context, float worldLocationX,
                      float worldLocationY, int pixelsPerMeter,
                      float facingAngle, int turretID,
                      TurretContract turretContract,
                      LaserContract laser) {

        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        setType(Constants.Types.TURRET_BLUE);

        float left = .6f;
        float right = .8f;
        float top = .4f;
        float bottom = .2f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    public void update(PointF playerLocation, CollisionPackage playerCP) {
        boolean playerIsInSight = false;

        float distanceX = getWorldLocation().x - playerLocation.x;
        float distanceY = getWorldLocation().y - playerLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // If the player is close enough and in sight, fire
        if (distanceH <= pixelsPerMeter * 2.2) {
            if (getFacingAngle() == Constants.Directions.RIGHT) {
                if (playerCP.left >= getCollisionPackage().right &&
                        playerCP.top > getCollisionPackage().bottom &&
                        playerCP.bottom < getCollisionPackage().top) {

                    playerIsInSight = true;
                }
            } else if (getFacingAngle() == Constants.Directions.LEFT) {
                if (playerCP.right <= getCollisionPackage().left &&
                        playerCP.top > getCollisionPackage().bottom &&
                        playerCP.bottom < getCollisionPackage().top) {

                    playerIsInSight = true;
                }
            } else if (getFacingAngle() == Constants.Directions.UP) {
                if (playerCP.bottom >= getCollisionPackage().top &&
                        playerCP.right > getCollisionPackage().left &&
                        playerCP.left < getCollisionPackage().right) {

                    playerIsInSight = true;
                }
            } else if (getFacingAngle() == Constants.Directions.DOWN) {
                if (playerCP.top <= getCollisionPackage().bottom &&
                        playerCP.right > getCollisionPackage().left &&
                        playerCP.left < getCollisionPackage().right) {

                    playerIsInSight = true;
                }
            }

            if (playerIsInSight) {
                if (System.currentTimeMillis() > lastShot + 500) {
                    fire();
                    lastShot = System.currentTimeMillis();
                }
            }
        }
    }
}
