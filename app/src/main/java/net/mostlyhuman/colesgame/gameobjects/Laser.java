package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.CollisionPackage;

/**
 * Created by CaptainMcCann on 5/22/2017.
 */

public class Laser extends GameObject implements Turret.LaserContract {

    private int laserID;
    private PointF masterLocation;

    public Laser(Context context, float worldLocationX,
                 float worldLocationY, int pixelsPerMeter) {
        super(context);

        setActive(false);

        setWorldLocation(worldLocationX, worldLocationY);
        setMasterLocation(new PointF(worldLocationX, worldLocationY));

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] laserVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(laserVertices);

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);
    }

    public void shoot(float masterFacingAngle) {
        setActive(true);
        setFacingAngle(masterFacingAngle);
        setSpeed(400);
    }

    public void resetLaser() {
        setActive(false);
        setxVelocity(0);
        setyVelocity(0);
        setSpeed(0);
        setWorldLocation(getMasterLocation().x, getMasterLocation().y);
    }

    public void update(long fps, PointF masterLocation) {
        if (isActive()) {
            setxVelocity((float) (getSpeed() * Math.cos(Math.toRadians(getFacingAngle() + 90))));
            setyVelocity((float) (getSpeed() * Math.sin(Math.toRadians(getFacingAngle() + 90))));
        } else {
            setWorldLocation(masterLocation.x, masterLocation.y);
        }
        move(fps);

        getCollisionPackage().updateHitbox(getWorldLocation());
    }

    public boolean detectCollision(CollisionPackage cp2) {
        boolean collided = false;

        if (getCollisionPackage().right > cp2.left &&
                getCollisionPackage().left < cp2.right) {
            // Intersecting on the x-axis
            if (getCollisionPackage().top > cp2.bottom &&
                    getCollisionPackage().bottom < cp2.top) {
                // Intersecting on the y-axis also
                // Collided!
                collided = true;
            }
        }
        return collided;
    }

    @Override
    public void fireLaser(int id, float facingAngle) {
        if (id == getLaserID() && !this.isActive()) {
            shoot(facingAngle);
        }
    }

    public int getLaserID() {
        return laserID;
    }

    void setLaserID(int laserID) {
        this.laserID = laserID;
    }

    public PointF getMasterLocation() {
        return masterLocation;
    }

    public void setMasterLocation(PointF masterLocation) {
        this.masterLocation = masterLocation;
    }
}