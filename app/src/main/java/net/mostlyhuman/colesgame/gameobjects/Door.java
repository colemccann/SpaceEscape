package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

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
                char type, int key) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;

        setType(type);
        switch (type) {
            case Constants.Types.DOOR_HORIZONTAL:
                setFacingAngle(360);
                break;
            case Constants.Types.DOOR_VERTICAL:
                setFacingAngle(90);
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

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);

        setOpen(open);
        setKey(key);
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
        if (isOpen()) {
            setTextureResource(R.drawable.dooropen);
        } else {
            setTextureResource(R.drawable.doorclosed);
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
}