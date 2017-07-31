package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level7 extends LevelData {

    public Level7() {
        tiles = new ArrayList<>();
        this.tiles.add("##........");
        this.tiles.add(".....#....");
        this.tiles.add("..........");
        this.tiles.add("..........");
        this.tiles.add("....b..#..");
        this.tiles.add("....v..b..");
        this.tiles.add("....b.....");
        this.tiles.add("........p.");
        this.tiles.add("#....##.##");
        this.tiles.add("bh....h.he");
        this.tiles.add("#.....####");
        this.tiles.add("#.....h..b");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{
                true, false, false, true, false
        };

        doorKeys = new int[]{4, 5, 1, 3, 2};

        buttonStates = new boolean[]{
                false, false, true, true, false
        };

        buttonKeys = new int[]{1, 2, 3, 4, 5};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
