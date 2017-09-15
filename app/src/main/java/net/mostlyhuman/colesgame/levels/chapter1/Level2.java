package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Level2 extends LevelData {

    public Level2() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("...#e");
        this.tiles.add("#....");
        this.tiles.add("..#.#");
        this.tiles.add(".....");
        this.tiles.add(".#..p");

        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = new String[]{};

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
