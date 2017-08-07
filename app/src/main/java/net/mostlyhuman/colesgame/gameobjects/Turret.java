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
