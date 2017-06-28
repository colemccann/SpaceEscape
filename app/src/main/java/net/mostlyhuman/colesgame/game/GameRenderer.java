package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.gameobjects.Asteroid;
import net.mostlyhuman.colesgame.gameobjects.Block;
import net.mostlyhuman.colesgame.gameobjects.Bomb;
import net.mostlyhuman.colesgame.gameobjects.Button;
import net.mostlyhuman.colesgame.gameobjects.Door;
import net.mostlyhuman.colesgame.gameobjects.EnemyLaser;
import net.mostlyhuman.colesgame.gameobjects.Laser;
import net.mostlyhuman.colesgame.gameobjects.Redirect;
import net.mostlyhuman.colesgame.gameobjects.Turret;
import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.helpers.RawResourceReader;

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

    long frameCounter = 0;
    long averageFPS = 0;
    private long fps;
    private int pixelsPerMeter;

    private final float[] viewportMatrix = new float[16];

    private PointF utilPointF;
    private PointF utilPointF2;

    private GameButton gameButton;

    private String currentLevel = Constants.Levels.ONE_A;


    public GameRenderer(Context context,
                        InputController inputController,
                        SoundManager soundManager,
                        GameManager gameManager) {
        this.context = context;
        this.ic = inputController;
        this.sm = soundManager;
        this.gm = gameManager;
        this.pixelsPerMeter = gm.pixelsPerMeter;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GLManager.setVertexShaderCode(RawResourceReader.readTextFileFromRawResource(
                context, R.raw.vertex_shader
        ));
        GLManager.setFragmentShaderCode(RawResourceReader.readTextFileFromRawResource(
                context, R.raw.fragment_shader
        ));
        GLManager.buildProgram();

        loadLevel(currentLevel);
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

    private void loadLevel(String level) {

        // Loads the level data and passes it on to the game manager
        gm.loadLevel(level);

        // Draws the buttons
        Rect boostButton = ic.getMenuButton();
        gameButton = new GameButton(context, R.drawable.icon_menu, boostButton.top,
                boostButton.left, boostButton.bottom, boostButton.right, gm);

    }

    protected void restartLevel(String level) {
        loadLevel(level);
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

        // Make sure to draw background first

        // Draw the buttons
        for (int i = 0; i < gm.numButtons; i++) {
            gm.buttons[i].draw(viewportMatrix);
        }

        // Draw the Blocks
        for (int i = 0; i < gm.numBlocks; i++) {
            if (gm.blocks[i].isActive()) {
                gm.blocks[i].draw(viewportMatrix);
            }
        }

        // Draw the turret bases
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.turrets[i].isActive()) {
                gm.turretBases[i].draw(viewportMatrix);
            }
        }

        // Draw the lasers
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.enemyLasers[i].isActive()) {
                gm.enemyLasers[i].draw(viewportMatrix);
            }
        }

        // Draw the turrets
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.turrets[i].isActive()) {
                gm.turrets[i].draw(viewportMatrix);
            }
        }

        // Draw the Redirects
        for (int i = 0; i < gm.numRedirects; i++) {
            if (gm.redirects[i].isActive()) {
                gm.redirects[i].draw(viewportMatrix);
            }
        }

        // Draw the Bombs
        for (int i = 0; i < gm.numBombs; i++) {
            if (gm.bombs[i].isActive()) {
                gm.bombs[i].draw(viewportMatrix);
            }
        }

        // Draw the Asteroids
        for (int i = 0; i < gm.numAsteroids; i++) {
            if (gm.asteroids[i].isActive()) {
                gm.asteroids[i].draw(viewportMatrix);
            }
        }

        // Draw the doors
        for (int i = 0; i < gm.numDoors; i++) {
            gm.doors[i].draw(viewportMatrix);
        }

        // Draw the warps
        for (int i = 0; i < gm.numWarps; i++) {
            gm.warps[i].draw(viewportMatrix);
        }

        // Draw the player
        gm.player.draw(viewportMatrix);

        // Draw the game buttons
           gameButton.draw();

    }

    private void update(long fps) {
        utilPointF = gm.player.getWorldLocation();

        // Update each relevant game object in succession

        // Update the player
        gm.player.update(fps);

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
                    turret.update(utilPointF);
                }
            }
        }

        // Update the lasers
        if (gm.numTurrets > 0) {
            for (Laser laser : gm.enemyLasers) {
                if (laser.isActive()) {
                    for (Turret turret : gm.turrets) {
                        // TODO: 6/15/2017 is this id check necessary
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
        // Contain the lasers
        for (int i = 0; i < gm.numTurrets; i++) {
            if (gm.enemyLasers[i].isActive()) {
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
        if (gm.numBlocks > 0) {
            for (Block block : gm.blocks) {
                if (block.isActive()) {
                    boolean hit = gm.player.detectCollision(block.getCollisionPackage());
                    if (hit) {
                        gm.player.setSpeed(0);
                        gm.player.setMoving(false);
                        gm.player.setBoosting(false);
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
                                asteroid.redirect(gm.player.getFacingAngle());
                                gm.player.setSpeed(0);
                                gm.player.setMoving(false);
                                gm.player.setBoosting(false);
                                asteroid.reposition(gm.player);
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
                        restartLevel(currentLevel);
                    }
                }
            }
        }
        if (gm.numTurrets > 0) {
            for (Turret turret : gm.turrets) {
                if (turret.isActive()) {
                    boolean hit = gm.player.detectCollision(turret.getCollisionPackage());
                    if (hit) {
                        gm.player.setSpeed(0);
                        gm.player.setMoving(false);
                        gm.player.setBoosting(false);
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
                if (!door.isOpen()) {
                    boolean hit = gm.player.detectCollision(door.getCollisionPackage());
                    if (hit) {
                        gm.player.setSpeed(0);
                        gm.player.setMoving(false);
                        gm.player.setBoosting(false);
                        door.reposition(gm.player);
                        sm.playSound(Constants.Sounds.BUMP);
                    }
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
                            if (!door.isOpen()) {
                                boolean hit = asteroid.detectCollision(door.getCollisionPackage());
                                if (hit) {
                                    asteroid.bounce();
                                }
                            }
                        }
                    }
                    if (gm.numButtons > 0) {
                        for (Button button : gm.buttons) {
                            boolean hit = asteroid.detectCollision(button.getCollisionPackage());
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
                }
            }
        }
    }

    private void checkButtonCollisions() {
        // Check button collisions
        if (gm.numButtons > 0) {
            for (Button button : gm.buttons) {
                if (gm.numAsteroids > 0) {
                    for (Asteroid asteroid : gm.asteroids) {
                            button.detectPress(asteroid.getCollisionPackage());
                        }
                }
                if (!button.isBeingPressed()) {
                    button.detectPress(gm.player.getCollisionPackage());
                }
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
                                    asteroid.destroy();
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
                            if (!door.isOpen()) {
                                boolean hit = laser.detectCollision(door.getCollisionPackage());
                                if (hit) {
                                    laser.resetLaser();
                                }
                            }
                        }
                    }
                    boolean hit = laser.detectCollision(gm.player.getCollisionPackage());
                    if (hit) {
                        laser.resetLaser();
                        restartLevel(Constants.Levels.ONE_A);
                    }
                }
            }
        }
    }
}
