package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level21 extends LevelData {

    public Level21(char version) {

        switch (version) {
            case 'a':

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("........");
                this.tiles.add(".e......");
                this.tiles.add("########");
                this.tiles.add(".w......");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("......p.");

                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_ONE_W};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

            case 'b':

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("........");
                this.tiles.add(".e....p.");
                this.tiles.add("########");
                this.tiles.add(".w......");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");

                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_ONE_W};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

            case 'w':

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("......");
                this.tiles.add("p....w");
                this.tiles.add("......");

                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_ONE_B};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
