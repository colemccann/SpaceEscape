package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level22 extends LevelData {

    public Level22(char version) {

        switch (version) {
            case 'a':
                tiles = new ArrayList<>();
                this.tiles.add("...#....");
                this.tiles.add(".......#");
                this.tiles.add("........");
                this.tiles.add(".w......");
                this.tiles.add("p.....#.");
                this.tiles.add("##..#...");
                this.tiles.add("e.#....#");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_TWO_W};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;
            case 'b':
                tiles = new ArrayList<>();
                this.tiles.add("...#....");
                this.tiles.add(".......#");
                this.tiles.add("........");
                this.tiles.add(".w......");
                this.tiles.add("......#.");
                this.tiles.add("##..#...");
                this.tiles.add("ep#....#");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_TWO_W};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;
            case 'w':
                tiles = new ArrayList<>();
                this.tiles.add("......");
                this.tiles.add("...p..");
                this.tiles.add("..w...");
                this.tiles.add(".#....");
                this.tiles.add(".....#");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_TWO_B};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
