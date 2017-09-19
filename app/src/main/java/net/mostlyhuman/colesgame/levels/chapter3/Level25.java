package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level25 extends LevelData {

    public Level25(int pixelsPerMeter) {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".#p..w");
        this.tiles.add("w....#");
        this.tiles.add("..#..w");
        this.tiles.add("w...#.");
        this.tiles.add(".#...w");
        this.tiles.add("w..#..");
        this.tiles.add("#....w");
        this.tiles.add("w.#...");
        this.tiles.add("....#w");
        this.tiles.add("w#....");
        this.tiles.add("####.w");
        this.tiles.add("e..#..");


        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT};

        warpDimensionalTargets = null;

        warpTeleportTargets = new PointF[]{
                new PointF(pixelsPerMeter, -5 * pixelsPerMeter),
                new PointF(5 * pixelsPerMeter, -11 * pixelsPerMeter),
                new PointF(5 * pixelsPerMeter, -9 * pixelsPerMeter),
                new PointF(4 * pixelsPerMeter, -6 * pixelsPerMeter),
                new PointF(pixelsPerMeter, -8 * pixelsPerMeter),
                new PointF(4 * pixelsPerMeter, 0),
                new PointF(pixelsPerMeter, -3 * pixelsPerMeter),
                new PointF(2 * pixelsPerMeter, -11 * pixelsPerMeter),
                new PointF(4 * pixelsPerMeter, -2 * pixelsPerMeter),
                new PointF(4 * pixelsPerMeter, -4 * pixelsPerMeter),
                new PointF(pixelsPerMeter, -pixelsPerMeter),
        };

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }


}
