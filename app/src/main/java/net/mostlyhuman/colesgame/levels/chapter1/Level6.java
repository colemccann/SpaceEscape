package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level6 extends LevelData {

    public Level6() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("....#e");
        this.tiles.add("#...G.");
        this.tiles.add("......");
        this.tiles.add(".G...#");
        this.tiles.add(".G...#");
        this.tiles.add(".....p");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{false, false};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{
                RIGHT, UP, DOWN};

        mapOrientation = HORIZONTAL;
    }
}
