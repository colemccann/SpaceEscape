package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level29 extends LevelData {

    public Level29(int pixelsPerMeter) {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("#p#B..#");
        this.tiles.add("w...##e");
        this.tiles.add("....##.");
        this.tiles.add("w..#.w#");
        this.tiles.add("w.w#..w");
        this.tiles.add(".#..#.w");
        this.tiles.add("w.w#...");
        this.tiles.add("......w");
        this.tiles.add("#.#w.w#");
        this.tiles.add("w#.###w");
        this.tiles.add(".......");
        this.tiles.add("w.w#..w");


        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,};

        warpDimensionalTargets = null;

        warpTeleportTargets = new PointF[]{
                new PointF(pixelsPerMeter, 0),
                new PointF(pixelsPerMeter, -8 * pixelsPerMeter),
                new PointF(5 * pixelsPerMeter, 0),
                new PointF(5 * pixelsPerMeter, 0),
                new PointF(3 * pixelsPerMeter, -2 * pixelsPerMeter),
                new PointF(pixelsPerMeter, 0),
                new PointF(3 * pixelsPerMeter, -10 * pixelsPerMeter),
                new PointF(3 * pixelsPerMeter, -10 * pixelsPerMeter),
                new PointF(pixelsPerMeter, 0),
                new PointF(6 * pixelsPerMeter, -2 * pixelsPerMeter),
                new PointF(5 * pixelsPerMeter, 0),
                new PointF(pixelsPerMeter, 0),
                new PointF(4 * pixelsPerMeter, -6 * pixelsPerMeter),
                new PointF(pixelsPerMeter, 0),
                new PointF(5 * pixelsPerMeter, 0),
                new PointF(pixelsPerMeter, 0),
                new PointF(4 * pixelsPerMeter, -4 * pixelsPerMeter),
        };

        turretFacingAngles = new int[]{RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
