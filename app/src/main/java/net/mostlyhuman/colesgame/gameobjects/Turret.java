package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.game.SoundManager;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class Turret extends GameObject {

    private String TAG = "turret";

    private int turretID;
    private final TurretContract turretBase;

    int pixelsPerMeter;
    private final LaserContract laser;

    Turret(Context context, float worldLocationX,
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

    void fire() {
        laser.fireLaser(getTurretID(), getFacingAngle());
    }

    @Override
    public void destroy(SoundManager sm) {
        super.destroy(sm);
        turretBase.destroyBase(getTurretID());
        sm.playSound(Constants.Sounds.EXPLOSION);
        //// TODO: 5/29/2017 add animation
    }

    public int getTurretID() {
        return turretID;
    }

    private void setTurretID(int turretID) {
        this.turretID = turretID;
    }

    interface TurretContract {
        void destroyBase(int id);
    }

    interface LaserContract {
        void fireLaser(int id, float facingAngle);
    }
}
