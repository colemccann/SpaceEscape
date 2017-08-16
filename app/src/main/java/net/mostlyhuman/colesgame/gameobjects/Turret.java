package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class Turret extends GameObject {

    private String TAG = "turret";

    private int turretID;
    private final TurretContract turretBase;

    int pixelsPerMeter;
    final LaserContract laser;

    private long firingTime;

    public Turret(Context context, float worldLocationX,
                  float worldLocationY, int pixelsPerMeter,
                  float facingAngle, int turretID,
                  TurretContract turretContract,
                  LaserContract laser) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;
        this.turretBase = turretContract;
        this.laser = laser;

        setFacingAngle(facingAngle);

        setTurretID(turretID);

        setType(Constants.Types.TURRET_GREEN);
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] turretVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(turretVertices);
    }

    public void reposition(GameObject gameObject) {
        switch (gameObject.getType()) {
            case Constants.Types.PLAYER:
                if (gameObject.getFacingAngle() == 360) {
                    // Facing up
                    gameObject.setWorldLocation(
                            gameObject.getWorldLocation().x,
                            getWorldLocation().y - pixelsPerMeter);
                } else if (gameObject.getFacingAngle() == 90) {
                    // Facing left
                    gameObject.setWorldLocation(
                            getWorldLocation().x + pixelsPerMeter,
                            gameObject.getWorldLocation().y);
                } else if (gameObject.getFacingAngle() == 180) {
                    // Facing down
                    gameObject.setWorldLocation(
                            gameObject.getWorldLocation().x,
                            getWorldLocation().y + pixelsPerMeter);
                } else if (gameObject.getFacingAngle() == 270) {
                    // Facing right
                    gameObject.setWorldLocation(
                            getWorldLocation().x - pixelsPerMeter,
                            gameObject.getWorldLocation().y);
                }
                break;
        }
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
                firingTime = System.currentTimeMillis();
            }
        }
    }

    public void update(PointF playerLocation) {
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
                fire();
            }
        }
    }

    private void fire() {
        laser.fireLaser(getTurretID(), getFacingAngle());
    }

    @Override
    public void destroy() {
        super.destroy();
        turretBase.destroyBase(getTurretID());
        //// TODO: 5/29/2017 add animation
    }

    public int getTurretID() {
        return turretID;
    }

    public void setTurretID(int turretID) {
        this.turretID = turretID;
    }

    interface TurretContract {
        void destroyBase(int id);
    }

    interface LaserContract {
        void fireLaser(int id, float facingAngle);
    }
}
