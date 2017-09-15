package net.mostlyhuman.colesgame.levels.chapter1;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Level1 extends LevelData {

    public Level1() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("p.#e");
        this.tiles.add("....");
        this.tiles.add(".#.#");
        this.tiles.add("....");


        asteroidDirections = null;

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
