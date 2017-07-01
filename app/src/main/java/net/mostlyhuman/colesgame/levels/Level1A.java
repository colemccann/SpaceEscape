package net.mostlyhuman.colesgame.levels;

import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Level1A extends LevelData {

    public Level1A() {
        tiles = new ArrayList<>();
        this.tiles.add("...#.##............#..");
        this.tiles.add("..#w#p..............#.");
        this.tiles.add("..#h##...............#");
        this.tiles.add("..#..................#");
        this.tiles.add("......................");
        this.tiles.add("......................");
        this.tiles.add(".#....r....r....d.....");
        this.tiles.add(".t.........a..........");
        this.tiles.add(".#.........b.........#");
        this.tiles.add("......................");
        this.tiles.add("...........u....l.....");
        this.tiles.add("......................");
        this.tiles.add("x...#.................");
        this.tiles.add("..#..................#");
        this.tiles.add("x.....#............##.");
        this.tiles.add("...#.#................");
        asteroidDirections = new int[]{
                0
        };

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{1};

        warpTargets = new String[]{Constants.Levels.ONE_B};

        turretMovement = new boolean[]{false};

        turretFacingAngles = new int[]{Constants.Directions.RIGHT};
    }


}
