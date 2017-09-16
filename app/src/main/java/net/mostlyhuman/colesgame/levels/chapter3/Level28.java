package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level28 extends LevelData {

    public Level28(int pixelsPerMeter) {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("d....ll....#.p");
        this.tiles.add("...#..........");
        this.tiles.add("........#.....");
        this.tiles.add(".d..........#.");
        this.tiles.add("d...#.....#...");
        this.tiles.add("w......#.#....");
        this.tiles.add("e.#...u......l");


        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = new char[]{Warp.TELEPORT};

        warpDimensionalTargets = null;

        warpTeleportTargets = new PointF[]{new PointF(13 * pixelsPerMeter, 0)};

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
