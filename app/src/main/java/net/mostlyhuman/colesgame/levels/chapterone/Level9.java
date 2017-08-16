package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class Level9 extends LevelData {

    public Level9() {
        tiles = new ArrayList<>();
        this.tiles.add(".#..a.");
        this.tiles.add(".h..b#");
        this.tiles.add(".#.#..");
        this.tiles.add(".#....");
        this.tiles.add(".#....");
        this.tiles.add("e#.p..");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{CLOSED};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{1};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
