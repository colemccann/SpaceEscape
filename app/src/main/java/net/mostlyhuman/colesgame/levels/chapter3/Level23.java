package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level23 extends LevelData {

    public Level23(String version, boolean doorState) {

        switch (version) {
            case "a":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("#.......");
                this.tiles.add(".....b..");
                this.tiles.add("...w....");
                this.tiles.add("########");
                this.tiles.add("...w....");
                this.tiles.add(".......p");
                this.tiles.add("......#v");
                this.tiles.add("..#...#e");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTargets = new String[]{Constants.Levels.TWENTY_THREE_WA,
                        Constants.Levels.TWENTY_THREE_WB};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

            case "b":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("#.......");
                this.tiles.add(".....b..");
                this.tiles.add("...w....");
                this.tiles.add("########");
                this.tiles.add("...wp...");
                this.tiles.add("........");
                this.tiles.add("......#v");
                this.tiles.add("..#...#e");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTargets = new String[]{Constants.Levels.TWENTY_THREE_WA,
                        Constants.Levels.TWENTY_THREE_WB};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

            case "c":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("#.......");
                this.tiles.add(".....b..");
                this.tiles.add("...wp...");
                this.tiles.add("########");
                this.tiles.add("...w....");
                this.tiles.add("........");
                this.tiles.add("......#v");
                this.tiles.add("..#...#e");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTargets = new String[]{Constants.Levels.TWENTY_THREE_WA,
                        Constants.Levels.TWENTY_THREE_WB};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

        }
    }

    public Level23(String version) {

        switch (version) {
            case "wa":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..G..");
                this.tiles.add("wp..w");
                this.tiles.add(".....");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_THREE_C,
                        Constants.Levels.TWENTY_THREE_B};

                turretFacingAngles = new int[]{DOWN};

                mapOrientation = HORIZONTAL;
                break;

            case "wb":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..G..");
                this.tiles.add("w..pw");
                this.tiles.add(".....");


                asteroidDirections = new int[]{};

                doorStates = new boolean[]{};

                doorKeys = new int[]{};

                buttonStates = new boolean[]{};

                buttonKeys = new int[]{};

                warpTargets = new String[]{Constants.Levels.TWENTY_THREE_C,
                        Constants.Levels.TWENTY_THREE_B};

                turretFacingAngles = new int[]{DOWN};

                mapOrientation = HORIZONTAL;
                break;

        }
    }
}
