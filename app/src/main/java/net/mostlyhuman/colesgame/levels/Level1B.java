package net.mostlyhuman.colesgame.levels;


import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Level1B extends LevelData {

    public Level1B() {
        tiles = new ArrayList<>();
        this.tiles.add("..........");
        this.tiles.add("..........");
        this.tiles.add("...#####..");
        this.tiles.add("..#.....#.");
        this.tiles.add("..#..#..#.");
        this.tiles.add("..#.#t#.#.");
        this.tiles.add("..#p.#..#.");
        this.tiles.add("..#.....#.");
        this.tiles.add("...#####..");
        this.tiles.add("..........");

        asteroidDirections = new int[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{};

        warpTargets = new String[]{Constants.Levels.ONE_A};

        turretMovement = new boolean[]{true};

        turretFacingAngles = new int[]{Constants.Directions.LEFT};
    }
}
