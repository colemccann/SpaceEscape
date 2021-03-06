package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level4 extends LevelData {

    public Level4() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".#......");
        this.tiles.add("b.......");
        this.tiles.add("#.......");
        this.tiles.add("....#...");
        this.tiles.add(".......p");
        this.tiles.add("#.....##");
        this.tiles.add("#.....he");


        asteroidDirections = null;

        doorStates = new boolean[]{false};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{1};

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = new String[]{};

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
