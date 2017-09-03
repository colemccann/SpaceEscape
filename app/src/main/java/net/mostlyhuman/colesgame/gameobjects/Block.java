package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.Random;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Block extends GameObject {

    String TAG = "Block";

    private int pixelsPerMeter;

    public Block(Context context, float worldLocationX,
                 float worldLocationY, int pixelsPerMeter) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;

        setType(Constants.Types.BLOCK);
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] blockVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(blockVertices);

        float left;
        float right;
        float top;
        float bottom;

        left = .8f;
        right = 1f;
        top = .8f;
        bottom = .6f;
        setTextureVertices(left, right, 1 - top, 1 - bottom);

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
