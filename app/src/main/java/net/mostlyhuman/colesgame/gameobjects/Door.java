package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class Door extends GameObject {

    private int pixelsPerMeter;
    private boolean open;
    private int key;

    public Door(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter,
                char type, boolean open, int key) {
        super(context);

        this.open = open;

        this.pixelsPerMeter = pixelsPerMeter;

        setType(type);
        switch (type) {
            case Constants.Types.DOOR_HORIZONTAL:
                setFacingAngle(90);
                break;
            case Constants.Types.DOOR_VERTICAL:
                setFacingAngle(360);
                break;
        }
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] doorVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };

        setVertices(doorVertices);
        setKey(key);
        setOpen(open);
        setBitmap();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void toggleDoor() {
        this.open = !this.open;
        setBitmap();
    }

    public boolean isOpen() {
        return open;
    }

    public void setBitmap() {
        float left;
        float right;
        float top;
        float bottom;

        if (isOpen()) {
            left = .4f;
            right = .6f;
            top = .8f;
            bottom = .6f;
            setTextureVertices(left, right, 1 - top, 1 - bottom);
        } else {
            left = .2f;
            right = .4f;
            top = .8f;
            bottom = .6f;
            setTextureVertices(left, right, 1 - top, 1 - bottom);
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
                if (!isOpen()) {
                    // The door is closed, no matter what we have collided
                    collided = true;
                } else if (getType() == Constants.Types.DOOR_HORIZONTAL) {
                    // Since the door is open, if the object is inside of the
                    // horizontal plane of the door, no collision occurs
                    if (cp2.top <= getCollisionPackage().top &&
                            cp2.bottom >= getCollisionPackage().bottom) {
                        return false;
                    } else collided = true;
                } else if (getType() == Constants.Types.DOOR_VERTICAL) {
                    // Since the door is open, if the object is inside of the
                    // vertical plane of the door, no collision occurs
                    if (cp2.left >= getCollisionPackage().left &&
                            cp2.right <= getCollisionPackage().right) {
                        return false;
                    } else collided = true;
                }
            }
        }
        return collided;
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
}
