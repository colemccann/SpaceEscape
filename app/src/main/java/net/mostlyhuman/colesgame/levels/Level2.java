package net.mostlyhuman.colesgame.levels;


import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Level2 extends LevelData {

    public Level2() {
        tiles = new ArrayList<>();
        this.tiles.add("..........");
        this.tiles.add("..........");
        this.tiles.add("...####...");
        this.tiles.add("..#....a#.");
        this.tiles.add("..#..#..#.");
        this.tiles.add("..#.#t#.#.");
        this.tiles.add("..#p.#..#.");
        this.tiles.add("..#.....#.");
        this.tiles.add("...#####..");
        this.tiles.add("..........");

        asteroidDirections = new int[]{0};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{true};

        turretFacingAngles = new int[]{Constants.Directions.LEFT};
    }
}
