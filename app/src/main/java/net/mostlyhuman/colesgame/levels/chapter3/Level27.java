package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level27 extends LevelData {

    public Level27(int pixelsPerMeter) {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("p....#w..#.#w.d.#");
        this.tiles.add(".....#ra..##.....");
        this.tiles.add("..b..#.....#..v..");
        this.tiles.add("....##G..b.#.....");
        this.tiles.add("#...w#.#...#.#wu.");
        this.tiles.add("#################");
        this.tiles.add("w....#w....#w....");
        this.tiles.add("....w#.....#.....");
        this.tiles.add(".....#..e..#.#..w");
        this.tiles.add(".....#xxxx##.....");
        this.tiles.add("#w.h.#xw.x##w....");

        asteroidDirections = new int[]{RIGHT};

        doorStates = new boolean[]{CLOSED, CLOSED};

        doorKeys = new int[]{1, 0};

        buttonStates = new boolean[]{CLOSED, CLOSED};

        buttonKeys = new int[]{0, 1};

        warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,
                Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT, Warp.TELEPORT,};

        warpDimensionalTargets = null;

        warpTeleportTargets = new PointF[]{new PointF(2 * pixelsPerMeter, -10 * pixelsPerMeter),
                new PointF(4 * pixelsPerMeter, -6 * pixelsPerMeter),
                new PointF(13 * pixelsPerMeter, -6 * pixelsPerMeter),
                new PointF(7 * pixelsPerMeter, -6 * pixelsPerMeter),
                new PointF(15 * pixelsPerMeter, -8 * pixelsPerMeter),
                new PointF(14 * pixelsPerMeter, -3 * pixelsPerMeter),
                new PointF(3 * pixelsPerMeter, -4 * pixelsPerMeter),
                new PointF(12 * pixelsPerMeter, -pixelsPerMeter),
                new PointF(0, -7 * pixelsPerMeter), new PointF(7 * pixelsPerMeter, 0),
                new PointF(13 * pixelsPerMeter, -10 * pixelsPerMeter),
                new PointF(8 * pixelsPerMeter, -10 * pixelsPerMeter)};

        turretFacingAngles = new int[]{RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
