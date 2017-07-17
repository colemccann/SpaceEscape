package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

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

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
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
        float top = .5f;
        float bottom = .25f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
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

    public void setBoosting(boolean boosting) {
        isBoosting = boosting;
    }

}
