package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Warp extends GameObject {

    public static final char DIMENSIONAL = 'd';
    public static final char TELEPORT = 't';

    private String warpDimensionTarget;
    private PointF warpTeleportTarget;
    private char warpType;

    public Warp(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter,
                char warpType) {
        super(context);

        setType(Constants.Types.WARP);
        setWarpType(warpType);

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

    public String getWarpDimensionTarget() {
        return warpDimensionTarget;
    }

    public void setWarpDimensionTarget(String warpDimensionTarget) {
        this.warpDimensionTarget = warpDimensionTarget;
    }

    public void setWarpTeleportTarget(PointF warpTeleportTarget) {
        this.warpTeleportTarget = warpTeleportTarget;
    }

    public PointF getWarpTeleportTarget() {
        return warpTeleportTarget;
    }

    public char getWarpType() {
        return warpType;
    }

    public void setWarpType(char warpType) {
        this.warpType = warpType;
    }
}
