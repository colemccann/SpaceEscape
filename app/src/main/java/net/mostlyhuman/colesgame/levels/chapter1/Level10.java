package net.mostlyhuman.colesgame.levels.chapter1;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class Level10 extends LevelData {

    public Level10() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("...rd.");
        this.tiles.add("..#...");
        this.tiles.add("erp..d");
        this.tiles.add("ul#...");
        this.tiles.add("d...l.");
        this.tiles.add("ru.u.l");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}