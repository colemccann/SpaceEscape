package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Warp extends GameObject {

    private String warpTarget;

    public Warp(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter,
                String warpTarget) {
        super(context);

        setType(Constants.Types.WARP);

        this.warpTarget = warpTarget;

        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] warpVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(warpVertices);

        float left = .8f;
        float right = 1;
        float top = .6f;
        float bottom = .4f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    public String getWarpTarget() {
        return warpTarget;
    }
}
