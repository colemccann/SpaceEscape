package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class Button extends GameObject {

    private int key;
    private boolean toggled;
    private boolean beingPressed;

    public Button(Context context, float worldLocationX,
                  float worldLocationY, int pixelsPerMeter,
                  boolean toggled, int key) {
        super(context);

        setType(Constants.Types.BUTTON);

        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        float halfSideLength = pixelsPerMeter / 3;
        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfSideLength);
        setCollisionPackage(collisionPackage);

        float[] switchVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(switchVertices);

        setKey(key);

        setToggled(toggled);
        setBitmap();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void toggle() {
        this.toggled = !toggled;
        setBitmap();
    }

    private void setBitmap() {
        float left;
        float right;
        float top;
        float bottom;

        if (isToggled()) {
            left = .8f;
            right = 1;
            top = 1;
            bottom = .8f;
            setTextureVertices(left, right, 1 - top, 1 - bottom);
        } else {
            left = 0;
            right = .2f;
            top = .8f;
            bottom = .6f;
            setTextureVertices(left, right, 1 - top, 1 - bottom);
        }
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public boolean isBeingPressed() {
        return beingPressed;
    }

    public void detectPress(CollisionPackage cp2) {
        beingPressed = false;
        if (getCollisionPackage().right > cp2.left &&
                getCollisionPackage().left < cp2.right) {
            // Intersecting on the x-axis
            if (getCollisionPackage().top > cp2.bottom &&
                    getCollisionPackage().bottom < cp2.top) {
                // Intersecting on the y-axis also
                // Collided!
                beingPressed = true;
            } else {
                beingPressed = false;
            }
        }
    }
}
