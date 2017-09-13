package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level19 extends LevelData {


    public Level19() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("....BGe#x");
        this.tiles.add("........x");
        this.tiles.add("..p.#...x");
        this.tiles.add(".......ax");
        this.tiles.add("........x");
        this.tiles.add("..#.....x");
        this.tiles.add("#.......x");
        this.tiles.add("#..a.#.#x");


        asteroidDirections = new int[]{DOWN, RIGHT};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{LEFT, DOWN};

        mapOrientation = HORIZONTAL;
    }
}
