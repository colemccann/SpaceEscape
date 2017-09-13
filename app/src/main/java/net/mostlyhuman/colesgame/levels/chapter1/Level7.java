package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level7 extends LevelData {

    public Level7() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".....e");
        this.tiles.add("...GB.");
        this.tiles.add("...G..");
        this.tiles.add("#.....");
        this.tiles.add("..G...");
        this.tiles.add("..#..p");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{
                UP, RIGHT, LEFT, RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
