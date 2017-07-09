package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class Turret extends GameObject {

    private String TAG = "turret";
    private int pixelsPerMeter;
    private int turretID;
    private boolean moves;
    private final TurretContract turretBase;
    private final LaserContract laser;

    public Turret(Context context, float worldLocationX,
                  float worldLocationY, int pixelsPerMeter,
                  boolean moves, float facingAngle, int turretID,
                  TurretContract turretContract,
                  LaserContract laser) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;
        this.turretBase = turretContract;
        this.laser = laser;
        this.moves = moves;

        setFacingAngle(facingAngle);

        setTurretID(turretID);

        setType(Constants.Types.TURRET);
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

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);

        setTextureResource(R.drawable.turrettop2);
    }

    public void update(PointF playerLocation) {
        double angle;
        float distanceX = getWorldLocation().x - playerLocation.x;
        float distanceY = getWorldLocation().y - playerLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        double sinValue = distanceY / distanceH;
        angle = (Math.acos(sinValue) * (180 / Math.PI));

        if (doesMove()) {
            if (playerLocation.x >= getWorldLocation().x) {
                setFacingAngle(180 + (float) angle);
            } else if (playerLocation.x < getWorldLocation().x) {
                setFacingAngle(180 - (float) angle);
            }

            if (distanceH <= pixelsPerMeter * 2.2) {
                Log.e(TAG, "turret shooting...turret ID: " + getTurretID());
                shoot();
            }

        } else if (!doesMove()){
            if (distanceH <= pixelsPerMeter * 2.2) {
                if (getFacingAngle() == 360) {
                    if (playerLocation.y > getWorldLocation().y) {
                        if (playerLocation.x < getWorldLocation().x + pixelsPerMeter / 2 &&
                                playerLocation.x > getWorldLocation().x - pixelsPerMeter / 2)
                        {
                            Log.e(TAG, "turret shooting...turret ID: " + getTurretID());
                            shoot();
                        }
                    }

                } else if (getFacingAngle() == 90) {
                    if (playerLocation.x < getWorldLocation().x) {
                        if (playerLocation.y < getWorldLocation().y + pixelsPerMeter / 2 &&
                                playerLocation.y > getWorldLocation().y - pixelsPerMeter / 2)
                        {
                            Log.e(TAG, "turret shooting...turret ID: " + getTurretID());
                            shoot();
                        }
                    }

                } else if (getFacingAngle() == 180) {
                    if (playerLocation.y < getWorldLocation().y) {
                        if (playerLocation.x < getWorldLocation().x + pixelsPerMeter / 2 &&
                                playerLocation.x > getWorldLocation().x - pixelsPerMeter / 2)
                        {
                            Log.e(TAG, "turret shooting...turret ID: " + getTurretID());
                            shoot();
                        }
                    }

                } else if (getFacingAngle() == 270) {
                    if (playerLocation.x > getWorldLocation().x) {
                        if (playerLocation.y < getWorldLocation().y + pixelsPerMeter / 2 &&
                                playerLocation.y > getWorldLocation().y - pixelsPerMeter / 2)
                        {
                            Log.e(TAG, "turret shooting...turret ID: " + getTurretID());
                            shoot();
                        }
                    }
                }
            }
        }
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

    @Override
    public void destroy() {
        super.destroy();
        turretBase.destroyBase(getTurretID());
        //// TODO: 5/29/2017 add animation
    }

    public boolean shoot() {
        //// TODO: 6/13/2017 decide when to fire in the update method?
        //// TODO: 6/5/2017 control rate of fire
        laser.fireLaser(getTurretID(), getFacingAngle());
        return true;
    }

    public boolean doesMove() {
        return moves;
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
