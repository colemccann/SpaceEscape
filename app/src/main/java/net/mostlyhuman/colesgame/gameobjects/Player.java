package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.game.SoundManager;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;


/**
 * Created by CaptainMcCann on 4/6/2017.
 */

public class Player extends GameObject {

    String TAG = "Player";

    private boolean isBoosting;

    public Player(Context context, float worldLocationX,
                  float worldLocationY, int pixelsPerMeter) {
        super(context);

        setType(Constants.Types.PLAYER);
        setWorldLocation(worldLocationX, worldLocationY);

        setActive(true);

        setxVelocity(0);
        setyVelocity(0);

        setMaxSpeed(300);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;
        float hitboxSideLength = pixelsPerMeter / 2f;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(),
                hitboxSideLength);
        setCollisionPackage(collisionPackage);

        float[] playerVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(playerVertices);

        float left = .2f;
        float right = .4f;
        float top = .6f;
        float bottom = .4f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    private void setBoosting(boolean boosting) {
        isBoosting = boosting;
    }

    @Override
    public void update(float fps) {
        super.update(fps);

        float speed = getSpeed();
        if (isBoosting) {
            if (speed < getMaxSpeed()) {
                setSpeed(getMaxSpeed());
            }
        }
        setxVelocity((float) (speed * Math.cos(Math.toRadians(getFacingAngle() + 90))));
        setyVelocity((float) (speed * Math.sin(Math.toRadians(getFacingAngle() + 90))));

        move(fps);

        getCollisionPackage().updateHitbox(getWorldLocation());

    }

    public void boost() {
        if (!this.isMoving()) {
            isBoosting = true;
            setMoving(true);
        }
    }

    public void stop() {
        setSpeed(0);
        setMoving(false);
        setBoosting(false);
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

    public boolean contain(float mapWidth, float mapHeight, int halfSideLength) {
        boolean collision = false;

        if (getCollisionPackage().right > mapWidth) {
            collision = true;
            stop();
            setWorldLocation(mapWidth - halfSideLength, getWorldLocation().y);
        } else if (getCollisionPackage().left < -halfSideLength) {
            collision = true;
            stop();
            setWorldLocation(0, getWorldLocation().y);
        } else if (getCollisionPackage().top > halfSideLength) {
            collision = true;
            stop();
            setWorldLocation(getWorldLocation().x, 0);
        } else if (getCollisionPackage().bottom < -mapHeight) {
            collision = true;
            stop();
            setWorldLocation(getWorldLocation().x, -mapHeight + halfSideLength);
        }

        return collision;
    }

    @Override
    public void destroy(SoundManager sm) {
        super.destroy(sm);
        sm.playSound(Constants.Sounds.EXPLOSION);
    }
}
