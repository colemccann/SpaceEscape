package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class Level9 extends LevelData {

    public Level9() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".#..a.");
        this.tiles.add(".h...#");
        this.tiles.add(".#..b.");
        this.tiles.add(".#.#..");
        this.tiles.add(".#....");
        this.tiles.add("e#.p.a");


        asteroidDirections = new int[]{0, 0};

        doorStates = new boolean[]{CLOSED};

        doorKeys = new int[]{0};

        buttonStates = new boolean[]{CLOSED};

        buttonKeys = new int[]{0};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}
