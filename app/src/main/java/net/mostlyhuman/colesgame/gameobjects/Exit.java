package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 7/2/2017.
 */

public class Exit extends GameObject {

    public Exit(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter) {
        super(context);

        setType(Constants.Types.EXIT);

        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);

        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        float halfSideLength = pixelsPerMeter / 4;
        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfSideLength);
        setCollisionPackage(collisionPackage);

        float[] exitVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(exitVertices);

        // Set Texture Vertices here
        float left = .2f;
        float right = .4f;
        float top = .25f;
        float bottom = 0;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }
}
