package net.mostlyhuman.colesgame.levels.chapter2;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level20 extends LevelData {


    public Level20() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("#...#...");
        this.tiles.add("......a.");
        this.tiles.add("......b.");
        this.tiles.add(".p......");
        this.tiles.add("a.......");
        this.tiles.add("...G....");
        this.tiles.add("...a..##");
        this.tiles.add("B..G.hxe");


        asteroidDirections = new int[]{0, DOWN, 0};

        doorStates = new boolean[]{CLOSED};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{CLOSED};

        buttonKeys = new int[]{1};
        
        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = new int[]{DOWN, UP, RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
