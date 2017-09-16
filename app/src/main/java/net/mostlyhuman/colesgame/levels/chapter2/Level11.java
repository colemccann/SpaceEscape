package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level11 extends LevelData {

    public Level11() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("#p#");
        this.tiles.add("#a#");
        this.tiles.add("#.#");
        this.tiles.add("#.#");
        this.tiles.add("e..");
        this.tiles.add("#..");
        this.tiles.add("#u#");
        this.tiles.add("###");


        asteroidDirections = new int[]{0};

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
