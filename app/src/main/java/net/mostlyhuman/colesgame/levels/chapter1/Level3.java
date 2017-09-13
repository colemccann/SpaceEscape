package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level3 extends LevelData {

    public Level3() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("p......");
        this.tiles.add(".......");
        this.tiles.add("...#...");
        this.tiles.add("..#eh..");
        this.tiles.add("...#..#");
        this.tiles.add("...b...");
        this.tiles.add(".....#.");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{false};

        doorKeys = new int[]{0};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{0};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }

    /*public Level3() {
        tiles = new ArrayList<>();
        this.tiles.add("p.#..");
        this.tiles.add("..h..");
        this.tiles.add("..e..");
        this.tiles.add("..b..");
        this.tiles.add(".....");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{false};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{1};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.HORIZONTAL;
    }*/
}
