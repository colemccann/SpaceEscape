package net.mostlyhuman.colesgame.game;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.data.DatabaseContract;
import net.mostlyhuman.colesgame.data.DatabaseUpdateService;
import net.mostlyhuman.colesgame.gameobjects.Asteroid;
import net.mostlyhuman.colesgame.gameobjects.Block;
import net.mostlyhuman.colesgame.gameobjects.BlueTurret;
import net.mostlyhuman.colesgame.gameobjects.Bomb;
import net.mostlyhuman.colesgame.gameobjects.Button;
import net.mostlyhuman.colesgame.gameobjects.Door;
import net.mostlyhuman.colesgame.gameobjects.EnemyLaser;
import net.mostlyhuman.colesgame.gameobjects.GreenTurret;
import net.mostlyhuman.colesgame.gameobjects.Laser;
import net.mostlyhuman.colesgame.gameobjects.RedTurret;
import net.mostlyhuman.colesgame.gameobjects.Redirect;
import net.mostlyhuman.colesgame.gameobjects.Turret;
import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.helpers.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.Matrix.orthoM;
import static android.opengl.GLES20.glViewport;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class GameRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = "Renderer";

    private Context context;
    private InputController ic;
    private SoundManager sm;
    private GameManager gm;
    private LevelCompleteContract gameActivity;

    private long frameCounter = 0;
    private long averageFPS = 0;
    private long fps;

    private final float[] viewportMatrix = new float[16];

    private TextureShaderProgram textureShaderProgram;
    private ColorShaderProgram colorShaderProgram;
    private int textureAtlas;

    private PointF utilPointF;

    private GameButton gameButton;

    interface LevelCompleteContract {
        void onLevelCompleted(int levelID);
        void exit();
    }

    public GameRenderer(Context context,
                        InputController inputController,
                        SoundManager soundManager,
                        GameManager gameManager,
                        LevelCompleteContract gameActivity) {

        this.context = context;
        this.ic = inputController;
        this.sm = soundManager;
        this.gm = gameManager;
        this.gameActivity = gameActivity;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        textureShaderProgram = new TextureShaderProgram(context);

        colorShaderProgram = new ColorShaderProgram(context);

        try {
            textureAtlas = TextureHelper.loadTexture(context, R.drawable.atlas);
            loadLevel();
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage());
            gameActivity.exit();
        }


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        glViewport(0, 0, width, height);

        orthoM(viewportMatrix, 0, 0, gm.metersToShowX,
                0, gm.metersToShowY, 0f, 1f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        long startTimeFrame = System.currentTimeMillis();

        if (fps == 0) {
            fps = 60;
        }

        if (gm.isPlaying()) {
            update(fps);
        }

        draw();

        long timeThisFrame = System.currentTimeMillis() - startTimeFrame;
        if (timeThisFrame >= 1) {
            fps = 1000 / timeThisFrame;
            frameCounter++;
            averageFPS = averageFPS + fps;
            if (frameCounter > 100) {
                averageFPS = averageFPS / frameCounter;
                frameCounter = 0;
                Log.e(TAG, "Average FPS: " + averageFPS);
            }
        }
    }

    private void loadLevel() {
        // Loads the level data and passes it on to the game manager
        gm.loadLevel(gm.getCurrentLevel());

        Log.d(TAG, "Level ID: " + gm.getLevelID());

        // Load the menu button
        Rect menuButton = ic.getMenuButton();
        gameButton = new GameButton(context, R.drawable.icon_menu, menuButton.top,
                menuButton.left, menuButton.bottom, menuButton.right, gm);

    }

    private void restartLevel() {
        loadLevel();
    }

    private void beatLevel() {
        gm.switchPlayingStatus();

        final Uri uri = ContentUris.withAppendedId(DatabaseContract.CONTENT_URI, gm.getLevelID());
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.LevelColumns.COMPLETED, 1);
        DatabaseUpdateService.updateLevelStatus(context, uri, values);

        if (gm.getLevelID() < 50) {
            final Uri uri2 = ContentUris.withAppendedId(DatabaseContract.CONTENT_URI, gm.getLevelID() + 1);
            ContentValues values2 = new ContentValues();
            values2.put(DatabaseContract.LevelColumns.IS_AVAILABLE, 1);
            DatabaseUpdateService.updateLevelStatus(context, uri2, values2);
        }

        gameActivity.onLevelCompleted(gm.getLevelID());
    }

    private void draw() {
        utilPointF = gm.player.getWorldLocation();

        orthoM(viewportMatrix, 0,
                utilPointF.x - gm.metersToShowX / 2,
                utilPointF.x + gm.metersToShowX / 2,
                utilPointF.y - gm.metersToShowY / 2,
                utilPointF.y + gm.metersToShowY / 2,
                0f, 1f);

        glClear(GL_COLOR_BUFFER_BIT);

        colorShaderProgram.useProgram();
        colorShaderProgram.setUniforms(gm.border.rotateMatrix(viewportMatrix));
        gm.border.bindColorData(colorShaderProgram);
        gm.border.draw();

        textureShaderProgram.useProgram();
        textureShaderProgram.setTexture(textureAtlas);

        // Draw the exit
        if (gm.hasExit()) {
            textureShaderProgram.setUniforms(gm.exit.rotateMatrix(viewportMatrix));
            gm.exit.bindTextureData(textureShaderProgram);
            gm.exit.draw();
        }

        // Draw the buttons
        for (int i = 0; i < gm.numButtons; i++) {
            textureShaderProgram.setUniforms(gm.buttons[i].rotateMatrix(viewportMatrix));
            gm.buttons[i].bindTextureData(textureShaderProgram);
            gm.buttons[i].draw();
        }

        // Draw the Blocks
        for (int i = 0; i < gm.numBlocks; i++) {
            if (gm.blocks[i].isActive()) {
                textureShaderProgram.setUniforms(gm.blocks[i].rotateMatrix(viewportMatrix));
                gm.blocks[i].bindTextureData(textureShaderProgram);
                gm.blocks[i].draw();
            }
        }

        // Draw the turret bases
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.turrets[i].isActive()) {
                textureShaderProgram.setUniforms(gm.turretBases[i].rotateMatrix(viewportMatrix));
                gm.turretBases[i].bindTextureData(textureShaderProgram);
                gm.turretBases[i].draw();
            }
        }

        // Draw the lasers
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.enemyLasers[i].isActive()) {
                textureShaderProgram.setUniforms(gm.enemyLasers[i].rotateMatrix(viewportMatrix));
                gm.enemyLasers[i].bindTextureData(textureShaderProgram);
                gm.enemyLasers[i].draw();
            }
        }

        // Draw the turrets
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.turrets[i].isActive()) {
                textureShaderProgram.setUniforms(gm.turrets[i].rotateMatrix(viewportMatrix));
                gm.turrets[i].bindTextureData(textureShaderProgram);
                gm.turrets[i].draw();
            }
        }

        // Draw the Redirects
        for (int i = 0; i < gm.numRedirects; i++) {
            if (gm.redirects[i].isActive()) {
                textureShaderProgram.setUniforms(gm.redirects[i].rotateMatrix(viewportMatrix));
                gm.redirects[i].bindTextureData(textureShaderProgram);
                gm.redirects[i].draw();
            }
        }

        // Draw the Bombs
        for (int i = 0; i < gm.numBombs; i++) {
            if (gm.bombs[i].isActive()) {
                textureShaderProgram.setUniforms(gm.bombs[i].rotateMatrix(viewportMatrix));
                gm.bombs[i].bindTextureData(textureShaderProgram);
                gm.bombs[i].draw();
            }
        }

        // Draw the Asteroids
        for (int i = 0; i < gm.numAsteroids; i++) {
            if (gm.asteroids[i].isActive()) {
                textureShaderProgram.setUniforms(gm.asteroids[i].rotateMatrix(viewportMatrix));
                gm.asteroids[i].bindTextureData(textureShaderProgram);
                gm.asteroids[i].draw();
            }
        }

        // Draw the doors
        for (int i = 0; i < gm.numDoors; i++) {
            textureShaderProgram.setUniforms(gm.doors[i].rotateMatrix(viewportMatrix));
            gm.doors[i].bindTextureData(textureShaderProgram);
            gm.doors[i].draw();
        }

        // Draw the warps
        for (int i = 0; i < gm.numWarps; i++) {
            textureShaderProgram.setUniforms(gm.warps[i].rotateMatrix(viewportMatrix));
            gm.warps[i].bindTextureData(textureShaderProgram);
            gm.warps[i].draw();
        }

        // Draw the player
        textureShaderProgram.setUniforms(gm.player.rotateMatrix(viewportMatrix));
        gm.player.bindTextureData(textureShaderProgram);
        gm.player.draw();

        // Draw the game button
        gameButton.draw(textureShaderProgram);

    }

    private void update(long fps) {
        // Update each relevant game object in succession

        // Update the player
        gm.player.update(fps);

        utilPointF = gm.player.getWorldLocation();
        CollisionPackage playerCP = gm.player.getCollisionPackage();

        if (gm.player.contain(gm.getMapWidth() - (gm.pixelsPerMeter / 2),
                gm.getMapHeight() - (gm.pixelsPerMeter / 2),
                gm.pixelsPerMeter / 2)) {
            sm.playSound(Constants.Sounds.BUMP);
        }


        // Update the asteroids
        if (gm.numAsteroids > 0) {
            for (Asteroid asteroid : gm.asteroids) {
                if (asteroid.isActive()) {
                    asteroid.update(fps);
                }
            }
        }

        // Update the turrets
        if (gm.numTurrets > 0) {
            for (Turret turret : gm.turrets) {
                if (turret.isActive()) {
                    switch (turret.getType()) {
                        case Constants.Types.TURRET_GREEN:
                            turret.update(fps);
                            break;
                        case Constants.Types.TURRET_BLUE:
                            turret.update(utilPointF, playerCP);
                            break;
                        case Constants.Types.TURRET_RED:
                            turret.update(utilPointF);
                            break;
                    }
                }
            }
        }

        // Update the lasers
        if (gm.numTurrets > 0) {
            for (Laser laser : gm.enemyLasers) {
                if (laser.isActive()) {
                    for (Turret turret : gm.turrets) {
                        if (turret.getTurretID() == laser.getLaserID()) {
                            laser.update(fps, turret.getWorldLocation());
                            break;
                        }
                    }
                }
            }
        }

        containLasers();

        checkPlayerCollisions();

        checkAsteroidCollisions();

        checkButtonCollisions();

        checkLaserCollisions();

    }

    private void containLasers() {
        PointF utilPointF2;
        // Contain the lasers
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.enemyLasers[i].isActive()
                    && !gm.enemyLasers[i].contain(gm.getMapWidth() - (gm.pixelsPerMeter / 2),
                    gm.getMapHeight() - (gm.pixelsPerMeter / 2),
                    gm.pixelsPerMeter / 2)) {

                utilPointF = gm.enemyLasers[i].getWorldLocation();
                utilPointF2 = gm.player.getWorldLocation();

                if (utilPointF.x > utilPointF2.x + gm.metersToShowX / 2) {
                    for (Turret turret : gm.turrets) {
                        if (gm.enemyLasers[i].getLaserID() == turret.getTurretID()) {
                            gm.enemyLasers[i].resetLaser();
                        }
                    }
                } else if (utilPointF.x < utilPointF2.x - gm.metersToShowX / 2) {
                    for (Turret turret : gm.turrets) {
                        if (gm.enemyLasers[i].getLaserID() == turret.getTurretID()) {
                            gm.enemyLasers[i].resetLaser();
                        }
                    }
                } else if (utilPointF.y > utilPointF2.y + gm.metersToShowY / 2) {
                    for (Turret turret : gm.turrets) {
                        if (gm.enemyLasers[i].getLaserID() == turret.getTurretID()) {
                            gm.enemyLasers[i].resetLaser();
                        }
                    }
                } else if (utilPointF.y < utilPointF2.y - gm.metersToShowY / 2) {
                    for (Turret turret : gm.turrets) {
                        if (gm.enemyLasers[i].getLaserID() == turret.getTurretID()) {
                            gm.enemyLasers[i].resetLaser();
                        }
                    }
                }
            }
        }
    }

    private void checkPlayerCollisions() {
        utilPointF = gm.player.getWorldLocation();
        // Check player collision

        if (gm.hasExit()) {
            boolean hitExit = gm.player.detectCollision(gm.exit.getCollisionPackage());
            if (hitExit) {
                beatLevel();
            }
        }

        if (gm.numBlocks > 0) {
            for (Block block : gm.blocks) {
                if (block.isActive()) {
                    boolean hit = gm.player.detectCollision(block.getCollisionPackage());
                    if (hit) {
                        gm.player.stop();
                        block.reposition(gm.player);
                        sm.playSound(Constants.Sounds.BUMP);
                    }
                }
            }
        }
        if (gm.numAsteroids > 0) {
            for (Asteroid asteroid : gm.asteroids) {
                if (asteroid.isActive()) {
                    boolean hit = gm.player.detectCollision(asteroid.getCollisionPackage());
                    if (hit) {
                        if (gm.player.isMoving()) {
                            if (asteroid.isInFrontOf(gm.player.getWorldLocation(),
                                    gm.player.getFacingAngle())) {
                                gm.player.stop();
                                asteroid.reposition(gm.player);
                                asteroid.setSpeed(gm.player.getMaxSpeed());
                                asteroid.redirect(gm.player.getFacingAngle());
                            } else {
                                asteroid.bounce();
                            }
                        } else {
                            asteroid.bounce();
                        }
                        sm.playSound(Constants.Sounds.BUMP);
                    }
                }
            }
        }
        if (gm.numRedirects > 0) {
            for (Redirect redirect : gm.redirects) {
                if (redirect.isActive()) {
                    boolean hit = gm.player.detectCollision(redirect.getCollisionPackage());
                    if (hit) {
                        redirect.redirect(gm.player);
                    }
                }
            }
        }
        if (gm.numBombs > 0) {
            for (Bomb bomb : gm.bombs) {
                if (bomb.isActive()) {
                    boolean hit = gm.player.detectCollision(bomb.getCollisionPackage());
                    if (hit) {
                        bomb.kill(gm.player);
                        sm.playSound(Constants.Sounds.EXPLOSION);
                        restartLevel();
                    }
                }
            }
        }
        if (gm.numTurrets > 0) {
            for (Turret turret : gm.turrets) {
                if (turret.isActive()) {
                    boolean hit = gm.player.detectCollision(turret.getCollisionPackage());
                    if (hit) {
                        gm.player.stop();
                        turret.reposition(gm.player);
                        sm.playSound(Constants.Sounds.BUMP);
                    }
                }
            }
        }
        if (gm.numButtons > 0) {
            for (Button button : gm.buttons) {
                boolean hit = gm.player.detectCollision(button.getCollisionPackage());
                if (hit) {
                    if (!button.isBeingPressed()) {
                        button.toggle();
                        for (Door door : gm.doors) {
                            if (door.getKey() == button.getKey()) {
                                door.toggleDoor();
                            }
                        }
                    }
                }
            }
        }
        if (gm.numWarps > 0) {
            for (Warp warp : gm.warps) {
                boolean hit = gm.player.detectCollision(warp.getCollisionPackage());
                if (hit) {
                    gm.loadLevel(warp.getWarpTarget());
                }
            }
        }
        if (gm.numDoors > 0) {
            for (Door door : gm.doors) {
                boolean hit = door.detectCollision(gm.player.getCollisionPackage());
                if (hit) {
                    gm.player.stop();
                    door.reposition(gm.player);
                    sm.playSound(Constants.Sounds.BUMP);
                }
            }
        }
    }

    private void checkAsteroidCollisions() {
        // Check asteroid collision
        if (gm.numAsteroids > 0) {
            for (Asteroid asteroid : gm.asteroids) {
                if (asteroid.isActive()) {
                    if (gm.numBlocks > 0) {
                        for (Block block : gm.blocks) {
                            boolean hit = asteroid.detectCollision(block.getCollisionPackage());
                            if (hit && !asteroid.isBeingRedirected()) {
                                asteroid.bounce();
                                break;
                            }
                        }
                    }
                    if (gm.numRedirects > 0) {
                        for (Redirect redirect : gm.redirects) {
                            boolean hit = asteroid.detectCollision(redirect.getCollisionPackage());
                            if (hit) {
                                asteroid.setBeingRedirected(true);
                                redirect.redirect(asteroid);
                            }
                            if (asteroid.getTravelingAngle() == redirect.getFacingAngle()) {
                                asteroid.setBeingRedirected(false);
                            }
                        }
                    }
                    if (gm.numTurrets > 0) {
                        for (Turret turret : gm.turrets) {
                            boolean hit = asteroid.detectCollision(turret.getCollisionPackage());
                            if (hit) {
                                asteroid.bounce();
                                break;
                            }
                        }
                    }
                    if (gm.numDoors > 0) {
                        for (Door door : gm.doors) {
                            boolean hit = door.detectCollision(asteroid.getCollisionPackage());
                            if (hit) {
                                asteroid.bounce();
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkButtonCollisions() {
        // Check button collisions
        if (gm.numButtons > 0) {
            for (Button button : gm.buttons) {
                button.detectPress(gm.player.getCollisionPackage());
            }
        }
    }

    private void checkLaserCollisions() {
        // Check laser collisions
        if (gm.numTurrets > 0) {
            for (EnemyLaser laser : gm.enemyLasers) {
                if (laser.isActive()) {
                    if (gm.numBlocks > 0) {
                        for (Block block : gm.blocks) {
                            boolean hit = laser.detectCollision(block.getCollisionPackage());
                            if (hit) {
                                laser.resetLaser();
                            }
                        }
                    }
                    if (gm.numAsteroids > 0) {
                        for (Asteroid asteroid : gm.asteroids) {
                            if (asteroid.isActive()) {
                                boolean hit = laser.detectCollision(asteroid.getCollisionPackage());
                                if (hit) {
                                    laser.resetLaser();
                                }
                            }
                        }
                    }
                    if (gm.numTurrets > 0) {
                        for (Turret turret : gm.turrets) {
                            if (turret.isActive() && turret.getTurretID() != laser.getLaserID()) {
                                boolean hit = laser.detectCollision(turret.getCollisionPackage());
                                if (hit) {
                                    turret.destroy();
                                    laser.resetLaser();
                                }
                            }
                        }
                    }
                    if (gm.numBombs > 0) {
                        for (Bomb bomb : gm.bombs) {
                            if (bomb.isActive()) {
                                boolean hit = laser.detectCollision(bomb.getCollisionPackage());
                                if (hit) {
                                    bomb.destroy();
                                    laser.resetLaser();
                                }
                            }
                        }
                    }
                    if (gm.numDoors > 0) {
                        for (Door door : gm.doors) {
                            boolean hit = door.detectCollision(laser.getCollisionPackage());
                            if (hit) {
                                laser.resetLaser();
                            }
                        }
                    }
                    boolean hit = laser.detectCollision(gm.player.getCollisionPackage());
                    if (hit) {
                        laser.resetLaser();
                        restartLevel();
                    }
                }
            }
        }
    }
}
