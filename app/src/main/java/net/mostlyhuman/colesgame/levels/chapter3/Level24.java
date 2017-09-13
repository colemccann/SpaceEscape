package net.mostlyhuman.colesgame.levels.chapter3;


import android.util.Log;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level24 extends LevelData {

    private static final String TAG = "Level24";

    public Level24(String version, boolean buttonOneState, boolean buttonTwoState) {

        switch (version) {

            case "a":

                Log.d(TAG, "Button states: " + buttonOneState + ", " + buttonTwoState);

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..B.#...#e.");
                this.tiles.add("ra..#....#v");
                this.tiles.add("...p#.w....");
                this.tiles.add("..w########");
                this.tiles.add("v###......b");
                this.tiles.add("..R.aG.....");
                this.tiles.add(".b.........");
                this.tiles.add("......#....");


                asteroidDirections = new int[]{RIGHT, 0};

                doorStates = new boolean[]{buttonOneState, buttonTwoState};

                doorKeys = new int[]{0, 1};

                buttonStates = new boolean[]{buttonOneState, buttonTwoState};

                buttonKeys = new int[]{0, 1};

                warpTargets = new String[]{Constants.Levels.TWENTY_FOUR_WB,
                        Constants.Levels.TWENTY_FOUR_WA};

                turretFacingAngles = new int[]{DOWN, UP, LEFT};

                mapOrientation = HORIZONTAL;
                break;

            case "b":

                Log.d(TAG, "Button states: " + buttonOneState + ", " + buttonTwoState);

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..B.#...#e.");
                this.tiles.add("ra..#....#v");
                this.tiles.add("....#.w....");
                this.tiles.add("..w########");
                this.tiles.add("v###......b");
                this.tiles.add("..R.aG..p..");
                this.tiles.add(".b.........");
                this.tiles.add("......#....");


                asteroidDirections = new int[]{RIGHT, 0};

                doorStates = new boolean[]{buttonOneState, buttonTwoState};

                doorKeys = new int[]{0, 1};

                buttonStates = new boolean[]{buttonOneState, buttonTwoState};

                buttonKeys = new int[]{0, 1};

                warpTargets = new String[]{Constants.Levels.TWENTY_FOUR_WB,
                        Constants.Levels.TWENTY_FOUR_WA};

                turretFacingAngles = new int[]{DOWN, UP, LEFT};

                mapOrientation = HORIZONTAL;
                break;

            case "c":

                Log.d(TAG, "Button states: " + buttonOneState + ", " + buttonTwoState);

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..B.#...#e.");
                this.tiles.add("ra..#.p..#v");
                this.tiles.add("....#.w....");
                this.tiles.add("..w########");
                this.tiles.add("v###......b");
                this.tiles.add("..R.aG.....");
                this.tiles.add(".b.........");
                this.tiles.add("......#....");


                asteroidDirections = new int[]{RIGHT, 0};

                doorStates = new boolean[]{buttonOneState, buttonTwoState};

                doorKeys = new int[]{0, 1};

                buttonStates = new boolean[]{buttonOneState, buttonTwoState};

                buttonKeys = new int[]{0, 1};

                warpTargets = new String[]{Constants.Levels.TWENTY_FOUR_WB,
                        Constants.Levels.TWENTY_FOUR_WA};

                turretFacingAngles = new int[]{DOWN, UP, LEFT};

                mapOrientation = HORIZONTAL;
                break;
        }
    }

    public Level24(String version) {

        switch (version) {

            case "wa":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("#.#......w");
                tiles.add(".p.....#.#");
                tiles.add("........hw");
                tiles.add("....b....#");
                tiles.add(".....#..hb");

                asteroidDirections = new int[]{};

                doorStates = new boolean[]{false, false};

                doorKeys = new int[]{1, 0};

                buttonStates = new boolean[]{false, false};

                buttonKeys = new int[]{0, 1};

                warpTargets = new String[]{Constants.Levels.TWENTY_FOUR_C,
                        Constants.Levels.TWENTY_FOUR_B};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;

            case "wb":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("#.#.....pw");
                tiles.add(".......#.#");
                tiles.add("........hw");
                tiles.add("....b....#");
                tiles.add(".....#..hb");

                asteroidDirections = new int[]{};

                doorStates = new boolean[]{false, false};

                doorKeys = new int[]{1, 0};

                buttonStates = new boolean[]{false, false};

                buttonKeys = new int[]{0, 1};

                warpTargets = new String[]{Constants.Levels.TWENTY_FOUR_C,
                        Constants.Levels.TWENTY_FOUR_B};

                turretFacingAngles = new int[]{};

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
