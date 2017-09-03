package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level18 extends LevelData {


    public Level18() {

        tiles = new ArrayList<>();
        this.tiles.add("....#.");
        this.tiles.add("....e.");
        this.tiles.add(".G.ax.");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("....pa");
        this.tiles.add("a.....");


        asteroidDirections = new int[]{0, 0, 0, 0};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
