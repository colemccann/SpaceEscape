package net.mostlyhuman.colesgame.game;

import android.content.Context;

import net.mostlyhuman.colesgame.gameobjects.Asteroid;
import net.mostlyhuman.colesgame.gameobjects.Block;
import net.mostlyhuman.colesgame.gameobjects.BlueTurret;
import net.mostlyhuman.colesgame.gameobjects.Bomb;
import net.mostlyhuman.colesgame.gameobjects.Border;
import net.mostlyhuman.colesgame.gameobjects.Button;
import net.mostlyhuman.colesgame.gameobjects.Door;
import net.mostlyhuman.colesgame.gameobjects.EnemyLaser;
import net.mostlyhuman.colesgame.gameobjects.Exit;
import net.mostlyhuman.colesgame.gameobjects.GreenTurret;
import net.mostlyhuman.colesgame.gameobjects.Player;
import net.mostlyhuman.colesgame.gameobjects.RedTurret;
import net.mostlyhuman.colesgame.gameobjects.Redirect;
import net.mostlyhuman.colesgame.gameobjects.Star;
import net.mostlyhuman.colesgame.gameobjects.TurretBase;
import net.mostlyhuman.colesgame.gameobjects.Turret;
import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.chapter1.Level1;
import net.mostlyhuman.colesgame.levels.chapter1.Level10;
import net.mostlyhuman.colesgame.levels.chapter1.Level2;
import net.mostlyhuman.colesgame.levels.chapter1.Level3;
import net.mostlyhuman.colesgame.levels.chapter1.Level4;
import net.mostlyhuman.colesgame.levels.chapter1.Level5;
import net.mostlyhuman.colesgame.levels.chapter1.Level6;
import net.mostlyhuman.colesgame.levels.LevelData;
import net.mostlyhuman.colesgame.levels.chapter1.Level7;
import net.mostlyhuman.colesgame.levels.chapter1.Level8;
import net.mostlyhuman.colesgame.levels.chapter1.Level9;
import net.mostlyhuman.colesgame.levels.chapter2.Level11;
import net.mostlyhuman.colesgame.levels.chapter2.Level12;
import net.mostlyhuman.colesgame.levels.chapter2.Level13;
import net.mostlyhuman.colesgame.levels.chapter2.Level14;
import net.mostlyhuman.colesgame.levels.chapter2.Level15;
import net.mostlyhuman.colesgame.levels.chapter2.Level16;
import net.mostlyhuman.colesgame.levels.chapter2.Level17;
import net.mostlyhuman.colesgame.levels.chapter2.Level18;
import net.mostlyhuman.colesgame.levels.chapter2.Level19;
import net.mostlyhuman.colesgame.levels.chapter2.Level20;
import net.mostlyhuman.colesgame.levels.chapter3.Level21;
import net.mostlyhuman.colesgame.levels.chapter3.Level22;
import net.mostlyhuman.colesgame.levels.chapter3.Level23;
import net.mostlyhuman.colesgame.levels.chapter3.Level24;
import net.mostlyhuman.colesgame.levels.chapter3.Level25;
import net.mostlyhuman.colesgame.levels.chapter3.Level26;
import net.mostlyhuman.colesgame.levels.chapter3.Level27;
import net.mostlyhuman.colesgame.levels.chapter3.Level28;
import net.mostlyhuman.colesgame.levels.chapter3.Level29;
import net.mostlyhuman.colesgame.levels.chapter3.Level30;
import net.mostlyhuman.colesgame.levels.chapter4.Level31;
import net.mostlyhuman.colesgame.levels.chapter4.Level32;
import net.mostlyhuman.colesgame.levels.chapter4.Level33;
import net.mostlyhuman.colesgame.levels.chapter4.Level34;
import net.mostlyhuman.colesgame.levels.chapter4.Level35;
import net.mostlyhuman.colesgame.levels.chapter4.Level36;
import net.mostlyhuman.colesgame.levels.chapter4.Level37;
import net.mostlyhuman.colesgame.levels.chapter4.Level38;
import net.mostlyhuman.colesgame.levels.chapter4.Level39;
import net.mostlyhuman.colesgame.levels.chapter4.Level40;
import net.mostlyhuman.colesgame.levels.chapter5.Level41;
import net.mostlyhuman.colesgame.levels.chapter5.Level42;
import net.mostlyhuman.colesgame.levels.chapter5.Level43;
import net.mostlyhuman.colesgame.levels.chapter5.Level44;
import net.mostlyhuman.colesgame.levels.chapter5.Level45;
import net.mostlyhuman.colesgame.levels.chapter5.Level46;
import net.mostlyhuman.colesgame.levels.chapter5.Level47;
import net.mostlyhuman.colesgame.levels.chapter5.Level48;
import net.mostlyhuman.colesgame.levels.chapter5.Level49;
import net.mostlyhuman.colesgame.levels.chapter5.Level50;

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

    Border border;

    Star[] stars = new Star[5];

    public Player player;

    Asteroid[] asteroids;
    int numAsteroids;

    Block[] blocks;
    int numBlocks;

    Bomb[] bombs;
    int numBombs;

    Redirect[] redirects;
    int numRedirects;

    Turret[] turrets;
    TurretBase[] turretBases;
    int numTurrets;

    Door[] doors;
    int numDoors;

    Button[] buttons;
    int numButtons;

    Warp[] warps;
    int numWarps;

    EnemyLaser[] enemyLasers;

    Exit exit;
    private boolean hasExit;

    private boolean playing = false;

    int screenWidth;
    int screenHeight;
    public int pixelsPerMeter;

    // These values are based on ratio of screen width to screen height.
    // Use percentage values so that the game view remains the same no matter what device
    // the game is running on. That way, the device the player is using does not
    // affect the difficulty of the game
    float metersToShowX;
    float metersToShowY;

    GameManager(Context context, int x, int y) {
        this.context = context;

        screenWidth = x;
        screenHeight = y;
        metersToShowX = x / 3;
        metersToShowY = y / 3;

        pixelsPerMeter = screenHeight / 32;
    }

    void loadLevel(String level) {

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
            case Constants.Levels.THREE:
                mLevelData = new Level3();
                break;
            case Constants.Levels.FOUR:
                mLevelData = new Level4();
                break;
            case Constants.Levels.FIVE:
                mLevelData = new Level5();
                break;
            case Constants.Levels.SIX:
                mLevelData = new Level6();
                break;
            case Constants.Levels.SEVEN:
                mLevelData = new Level7();
                break;
            case Constants.Levels.EIGHT:
                mLevelData = new Level8();
                break;
            case Constants.Levels.NINE:
                mLevelData = new Level9();
                break;
            case Constants.Levels.TEN:
                mLevelData = new Level10();
                break;
            case Constants.Levels.ELEVEN:
                mLevelData = new Level11();
                break;
            case Constants.Levels.TWELVE:
                mLevelData = new Level12();
                break;
            case Constants.Levels.THIRTEEN:
                mLevelData = new Level13();
                break;
            case Constants.Levels.FOURTEEN:
                mLevelData = new Level14();
                break;
            case Constants.Levels.FIFTEEN:
                mLevelData = new Level15();
                break;
            case Constants.Levels.SIXTEEN:
                mLevelData = new Level16();
                break;
            case Constants.Levels.SEVENTEEN:
                mLevelData = new Level17();
                break;
            case Constants.Levels.EIGHTEEN:
                mLevelData = new Level18();
                break;
            case Constants.Levels.NINETEEN:
                mLevelData = new Level19();
                break;
            case Constants.Levels.TWENTY:
                mLevelData = new Level20();
                break;
            case Constants.Levels.TWENTY_ONE:
                mLevelData = new Level21();
                break;
            case Constants.Levels.TWENTY_TWO:
                mLevelData = new Level22();
                break;
            case Constants.Levels.TWENTY_THREE:
                mLevelData = new Level23();
                break;
            case Constants.Levels.TWENTY_FOUR:
                mLevelData = new Level24();
                break;
            case Constants.Levels.TWENTY_FIVE:
                mLevelData = new Level25();
                break;
            case Constants.Levels.TWENTY_SIX:
                mLevelData = new Level26();
                break;
            case Constants.Levels.TWENTY_SEVEN:
                mLevelData = new Level27();
                break;
            case Constants.Levels.TWENTY_EIGHT:
                mLevelData = new Level28();
                break;
            case Constants.Levels.TWENTY_NINE:
                mLevelData = new Level29();
                break;
            case Constants.Levels.THIRTY:
                mLevelData = new Level30();
                break;
            case Constants.Levels.THIRTY_ONE:
                mLevelData = new Level31();
                break;
            case Constants.Levels.THIRTY_TWO:
                mLevelData = new Level32();
                break;
            case Constants.Levels.THIRTY_THREE:
                mLevelData = new Level33();
                break;
            case Constants.Levels.THIRTY_FOUR:
                mLevelData = new Level34();
                break;
            case Constants.Levels.THIRTY_FIVE:
                mLevelData = new Level35();
                break;
            case Constants.Levels.THIRTY_SIX:
                mLevelData = new Level36();
                break;
            case Constants.Levels.THIRTY_SEVEN:
                mLevelData = new Level37();
                break;
            case Constants.Levels.THIRTY_EIGHT:
                mLevelData = new Level38();
                break;
            case Constants.Levels.THIRTY_NINE:
                mLevelData = new Level39();
                break;
            case Constants.Levels.FORTY:
                mLevelData = new Level40();
                break;
            case Constants.Levels.FORTY_ONE:
                mLevelData = new Level41();
                break;
            case Constants.Levels.FORTY_TWO:
                mLevelData = new Level42();
                break;
            case Constants.Levels.FORTY_THREE:
                mLevelData = new Level43();
                break;
            case Constants.Levels.FORTY_FOUR:
                mLevelData = new Level44();
                break;
            case Constants.Levels.FORTY_FIVE:
                mLevelData = new Level45();
                break;
            case Constants.Levels.FORTY_SIX:
                mLevelData = new Level46();
                break;
            case Constants.Levels.FORTY_SEVEN:
                mLevelData = new Level47();
                break;
            case Constants.Levels.FORTY_EIGHT:
                mLevelData = new Level48();
                break;
            case Constants.Levels.FORTY_NINE:
                mLevelData = new Level49();
                break;
            case Constants.Levels.FIFTY:
                mLevelData = new Level50();
                break;

        }

        initializeObjects();
    }

    private void initializeObjects() {

        setMapSize();

        countGameObjects();

        createGameObjects();
    }

    private void setMapSize() {
        setMapHeight(mLevelData.tiles.size() * pixelsPerMeter);
        setMapWidth(mLevelData.tiles.get(0).length() * pixelsPerMeter);
    }

    private void countGameObjects() {
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
                    case Constants.Types.TURRET_GREEN:
                        numTurrets++;
                        break;
                    case Constants.Types.TURRET_BLUE:
                        numTurrets++;
                        break;
                    case Constants.Types.TURRET_RED:
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
    }

    private void createGameObjects() {
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

        if (numTurrets > 0) {
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

        // Iterate through level data and create all game objects

        border = new Border(context, getMapWidth(), getMapHeight(), pixelsPerMeter,
                mLevelData.mapOrientation);

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
                        case Constants.Types.TURRET_GREEN:
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
                            turrets[turretIndex] = new GreenTurret(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    mLevelData.turretFacingAngles[turretIndex],
                                    turretIndex,
                                    turretBases[turretIndex],
                                    enemyLasers[turretIndex]);
                            break;
                        case Constants.Types.TURRET_BLUE:
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
                            turrets[turretIndex] = new BlueTurret(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    mLevelData.turretFacingAngles[turretIndex],
                                    turretIndex,
                                    turretBases[turretIndex],
                                    enemyLasers[turretIndex]);
                            break;
                        case Constants.Types.TURRET_RED:
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
                            turrets[turretIndex] = new RedTurret(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
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
                                    mLevelData.doorStates[doorIndex],
                                    mLevelData.doorKeys[doorIndex]);
                            break;
                        case Constants.Types.DOOR_VERTICAL:
                            doorIndex++;
                            doors[doorIndex] = new Door(context,
                                    j * pixelsPerMeter,
                                    -i * pixelsPerMeter,
                                    pixelsPerMeter,
                                    c,
                                    mLevelData.doorStates[doorIndex],
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
    }

    boolean hasExit() {
        return hasExit;
    }

    private void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    private void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    int getMapWidth() {
        return mapWidth;
    }

    int getMapHeight() {
        return mapHeight;
    }

    void switchPlayingStatus() {
        playing = !playing;
    }

    void setPlaying(boolean playing) {
        this.playing = playing;
    }

    boolean isPlaying() {
        return playing;
    }

    String getCurrentLevel() {
        return currentLevel;
    }

    void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    int getLevelID() {
        return levelID;
    }

    void setLevelID(int levelID) {
        this.levelID = levelID;
    }
}
