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

    public Warp(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter,
                String warpDimensionTarget) {
        super(context);

        setType(Constants.Types.WARP);

        setWarpDimensionTarget(warpDimensionTarget);

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

    public Warp(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter,
                PointF warpTeleportTarget) {
        super(context);

        setType(Constants.Types.WARP);

        setWarpTeleportTarget(warpTeleportTarget);

        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] warpVertices = new float[]{
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

    private void setWarpDimensionTarget(String warpDimensionTarget) {
        this.warpDimensionTarget = warpDimensionTarget;
    }

    private void setWarpTeleportTarget(PointF warpTeleportTarget) {
        this.warpTeleportTarget = warpTeleportTarget;
    }

    public PointF getWarpTeleportTarget() {
        return warpTeleportTarget;
    }

}
