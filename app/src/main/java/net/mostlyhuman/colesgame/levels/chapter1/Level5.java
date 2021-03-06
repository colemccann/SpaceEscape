package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level5 extends LevelData {

    public Level5() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".#..#p#e");
        this.tiles.add(".......v");
        this.tiles.add("........");
        this.tiles.add("..#b#...");
        this.tiles.add("...v....");
        this.tiles.add("....#...");
        this.tiles.add("......#.");
        this.tiles.add("..#...#b");

        asteroidDirections = null;

        doorStates = new boolean[]{false, false};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{false, false};

        buttonKeys = new int[]{1, 2};

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = new String[]{};

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
