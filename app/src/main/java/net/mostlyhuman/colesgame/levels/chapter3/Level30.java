package net.mostlyhuman.colesgame.levels.chapter3;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level30 extends LevelData {

    public Level30() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("p...............................");
        this.tiles.add(".#...#.###.#.#..#...#.#.#...#.#.");
        this.tiles.add("..#.#..#.#.#.#..#...#.#.##..#.#.");
        this.tiles.add("...#...#.#.#.#..#...#.#.#.#.#.#.");
        this.tiles.add("...#...#.#.#.#..#.#.#.#.#..##...");
        this.tiles.add("...#...###.###...#.#..#.#...#.#.");
        this.tiles.add("...............................e");



        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;

        warpDimensionalTargets = null;

        warpTeleportTargets = null;

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
