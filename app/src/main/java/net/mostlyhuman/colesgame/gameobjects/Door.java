package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

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

    private void setKey(int key) {
        this.key = key;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void toggleDoor() {
        this.open = !this.open;
        setBitmap();
    }

    private boolean isOpen() {
        return open;
    }

    private void setBitmap() {
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
                    // HORIZONTAL plane of the door, no collision occurs
                    if (cp2.top <= getCollisionPackage().top &&
                            cp2.bottom >= getCollisionPackage().bottom) {
                        return false;
                    } else collided = true;
                } else if (getType() == Constants.Types.DOOR_VERTICAL) {
                    // Since the door is open, if the object is inside of the
                    // VERTICAL plane of the door, no collision occurs
                    if (cp2.left >= getCollisionPackage().left &&
                            cp2.right <= getCollisionPackage().right) {
                        return false;
                    } else collided = true;
                }
            }
        }
        return collided;
    }

    public PointF reposition(float objectFacingAngle, PointF objectLocation) {
        PointF newLocation = new PointF();

        if (objectFacingAngle == 180) {
            // Collision from above
            newLocation = new PointF(objectLocation.x, getWorldLocation().y + pixelsPerMeter);
        } else if (objectFacingAngle == 360) {
            // Collision from below
            newLocation = new PointF(objectLocation.x, getWorldLocation().y - pixelsPerMeter);
        } else if (objectFacingAngle == 90) {
            // Collision from the right
            newLocation = new PointF(getWorldLocation().x + pixelsPerMeter, objectLocation.y);
        } else if (objectFacingAngle == 270) {
            // Collision from the left
            newLocation = new PointF(getWorldLocation().x - pixelsPerMeter, objectLocation.y);
        }

        return newLocation;
    }
}
