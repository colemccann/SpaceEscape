package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.util.Log;

import net.mostlyhuman.colesgame.gameobjects.Asteroid;
import net.mostlyhuman.colesgame.gameobjects.Block;
import net.mostlyhuman.colesgame.gameobjects.Bomb;
import net.mostlyhuman.colesgame.gameobjects.Border;
import net.mostlyhuman.colesgame.gameobjects.Button;
import net.mostlyhuman.colesgame.gameobjects.Door;
import net.mostlyhuman.colesgame.gameobjects.EnemyLaser;
import net.mostlyhuman.colesgame.gameobjects.Exit;
import net.mostlyhuman.colesgame.gameobjects.Player;
import net.mostlyhuman.colesgame.gameobjects.Redirect;
import net.mostlyhuman.colesgame.gameobjects.Star;
import net.mostlyhuman.colesgame.gameobjects.TurretBase;
import net.mostlyhuman.colesgame.gameobjects.Turret;
import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.Level1;
import net.mostlyhuman.colesgame.levels.Level2;
import net.mostlyhuman.colesgame.levels.LevelData;

/**
 * Created by CaptainMcCann on 4/17/2017.
 */

public class GameManager {

    private final String TAG = "GM";

    private Context context;
    private LevelData mLevelData;
    private String currentLevel;
    private int levelID;

    private int mapWidth;
    private int mapHeight;

    public Border border;

    public Star[] stars = new Star[5];

    public Player player;

    public Asteroid[] asteroids;
    int numAsteroids;

    public Block[] blocks;
    int numBlocks;

    public Bomb[] bombs;
    int numBombs;

    public Redirect[] redirects;
    int numRedirects;

    public Turret[] turrets;
    public TurretBase[] turretBases;
    int numTurrets;

    public Door[] doors;
    int numDoors;

    public Button[] buttons;
    int numButtons;

    public Warp[] warps;
    int numWarps;

    public EnemyLaser[] enemyLasers;

    public Exit exit;
    boolean hasExit;

    private boolean playing = false;

    public int screenWidth;
    public int screenHeight;
    public int pixelsPerMeter;

    // These values are based on ratio of screen width to screen height.
    // Use percentage values so that the game view remains the same no matter what device
    // the game is running on. That way, the device the player is using does not
    // affect the difficulty of the game
    float metersToShowX;
    float metersToShowY;

    public GameManager(Context context, int x, int y) {
        this.context = context;

        screenWidth = x;
        screenHeight = y;
        metersToShowX = x / 3;
        metersToShowY = y / 3;

        pixelsPerMeter = screenHeight / 32;
    }

    public void loadLevel(String level) {

        mLevelData = null;
        numBlocks = 0;
        numAsteroids = 0;
        numRedirects = 0;
        numBombs = 0;
        numTurrets = 0;
        numDoors = 0;
        numButtons = 0;
        numWarps = 0;

        switch (level) {
            case Constants.Levels.ONE:
                mLevelData = new Level1();
                break;
            case Constants.Levels.TWO:
                mLevelData = new Level2();
                break;
        }

        initializeObjects();
    }

    public void initializeObjects() {
        setMapHeight(mLevelData.tiles.size() * pixelsPerMeter);
        setMapWidth(mLevelData.tiles.get(0).length() * pixelsPerMeter);

        for (int i = 0; i < mLevelData.tiles.size(); i++) {
            for (int j = 0; j < mLevelData.tiles.get(i).length(); j++) {
                char c = mLevelData.tiles.get(i).charAt(j);
                switch (c) {
                    case Constants.Types.ASTEROID:
                        numAsteroids++;
                        break;
                    case Constants.Types.BLOCK:
                        numBlocks++;
                        break;
                    case Constants.Types.BOMB:
                        numBombs++;
                        break;
                    case Constants.Types.REDIRECT_DOWN:
                        numRedirects++;
                        break;
                    case Constants.Types.REDIRECT_LEFT:
                        numRedirects++;
                        break;
                    case Constants.Types.REDIRECT_RIGHT:
                        numRedirects++;
                        break;
                    case Constants.Types.REDIRECT_UP:
                        numRedirects++;
                        break;
                    case Constants.Types.TURRET:
                        numTurrets++;
                        break;
                    case Constants.Types.DOOR_HORIZONTAL:
                        numDoors++;
                        break;
                    case Constants.Types.DOOR_VERTICAL:
                        numDoors++;
                        break;
                    case Constants.Types.BUTTON:
                        numButtons++;
                        break;
                    case Constants.Types.WARP:
                        numWarps++;
                        break;
                }
            }
        }

        if (numAsteroids > 0) {
            asteroids = new Asteroid[numAsteroids];
        }
        if (numBlocks > 0) {
            blocks = new Block[numBlocks];
        }
        if (numBombs > 0) {
            bombs = new Bomb[numBombs];
        }
        if (numRedirects > 0) {
            redirects = new Redirect[numRedirects];
        }
        if (numTurrets > 0) {
            turrets = new Turret[numTurrets];
            turretBases = new TurretBase[numTurrets];
            enemyLasers = new EnemyLaser[numTurrets];
        }
        if (numDoors > 0) {
            doors = new Door[numDoors];
        }
        if (numButtons > 0) {
            buttons = new Button[numButtons];
        }
        if (numWarps > 0) {
            warps = new Warp[numWarps];
        }


        char c;

        int asteroidIndex = -1;
        int blockIndex = -1;
        int bombIndex = -1;
        int redirectIndex = -1;
        int turretIndex = -1;
        int doorIndex = -1;
        int buttonIndex = -1;
        int warpIndex = -1;

        for (int i = 0; i < mLevelData.tiles.size(); i++) {
            for (int j = 0; j < mLevelData.tiles.get(i).length(); j++) {
                c = mLevelData.tiles.get(i).charAt(j);
                if (c != '.') {
                    switch (c) {
                        case Constants.Types.PLAYER:
                            player = new Player(context, j * pixelsPerMeter,
                                    -i * pixelsPerMeter, pixelsPerMeter);
                            break;
                        case Constants.Types.ASTEROID:
                            asteroidIndex++;
                            asteroids[asteroidIndex] = new Asteroid(context,
                                    j * pixelsPerMeter, -i * pixelsPerMeter,
                                    pixelsPerMeter);
                            break;
                        case Constants.Types.BLOCK:
                            blockIndex++;
                            blocks[blockIndex] = new Block(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter);
                            break;
                        case Constants.Types.BOMB:
                            bombIndex++;
                            bombs[bombIndex] = new Bomb(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter);
                            break;
                        case Constants.Types.REDIRECT_UP:
                            redirectIndex++;
                            redirects[redirectIndex] = new Redirect(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c);
                            break;
                        case Constants.Types.REDIRECT_DOWN:
                            redirectIndex++;
                            redirects[redirectIndex] = new Redirect(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c);
                            break;
                        case Constants.Types.REDIRECT_RIGHT:
                            redirectIndex++;
                            redirects[redirectIndex] = new Redirect(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c);
                            break;
                        case Constants.Types.REDIRECT_LEFT:
                            redirectIndex++;
                            redirects[redirectIndex] = new Redirect(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c);
                            break;
                        case Constants.Types.TURRET:
                            turretIndex++;
                            turretBases[turretIndex] = new TurretBase(context,
                                    j * pixelsPerMeter, -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    turretIndex);
                            enemyLasers[turretIndex] = new EnemyLaser(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    turretIndex);
                            turrets[turretIndex] = new Turret(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    mLevelData.turretMovement[turretIndex],
                                    mLevelData.turretFacingAngles[turretIndex],
                                    turretIndex,
                                    turretBases[turretIndex],
                                    enemyLasers[turretIndex]);
                            break;
                        case Constants.Types.DOOR_HORIZONTAL:
                            doorIndex++;
                            doors[doorIndex] = new Door(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c,
                                    mLevelData.doorKeys[doorIndex]);
                            break;
                        case Constants.Types.DOOR_VERTICAL:
                            doorIndex++;
                            doors[doorIndex] = new Door(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c,
                                    mLevelData.doorKeys[doorIndex]);
                            break;
                        case Constants.Types.BUTTON:
                            buttonIndex++;
                            buttons[buttonIndex] = new Button(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    mLevelData.buttonStates[buttonIndex],
                                    mLevelData.buttonKeys[buttonIndex]);
                            break;
                        case Constants.Types.WARP:
                            warpIndex++;
                            warps[warpIndex] = new Warp(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    mLevelData.warpTargets[warpIndex]);
                            break;
                        case Constants.Types.EXIT:
                            hasExit = true;
                            exit = new Exit(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter);
                            break;
                    }
                }
            }
        }

        if (numDoors > 0) {
            for (Door door : doors) {
                for (Button button : buttons) {
                    if (button.getKey() == door.getKey()) {
                        door.setOpen(button.isToggled());
                    }
                }
            }
        }

        for (int i = 0; i < numAsteroids; i++) {
            if (mLevelData.asteroidDirections[i] > 0) {
                asteroids[i].setSpeed(asteroids[i].getMaxSpeed());
                asteroids[i].setTravelingAngle(mLevelData.asteroidDirections[i]);
            }
        }

        border = new Border(context, mapWidth, mapHeight, pixelsPerMeter);

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(context, mapWidth, mapHeight);
        }
    }

    public boolean hasExit() {
        return hasExit;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void switchPlayingStatus() {
        playing = !playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPlaying() {
        return playing;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }
}
