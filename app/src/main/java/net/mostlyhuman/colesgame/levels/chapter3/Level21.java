package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level21 extends LevelData {

    public Level21() {

        tiles = new ArrayList<>();
        this.tiles.add("#.a.#");
        this.tiles.add(".....");
        this.tiles.add("a.p.a");
        this.tiles.add(".....");
        this.tiles.add("#.a.#");


        asteroidDirections = new int[]{RIGHT, DOWN, UP, LEFT};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}
