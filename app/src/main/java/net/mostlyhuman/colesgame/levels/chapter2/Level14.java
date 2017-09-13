package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level14 extends LevelData {


    public Level14() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("........");
        this.tiles.add("....ae..");
        this.tiles.add(".a....a.");
        this.tiles.add(".....a..");
        this.tiles.add("...a....");
        this.tiles.add("a.......");
        this.tiles.add("a....a.p");
        this.tiles.add("..a.....");


        asteroidDirections = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}
