package net.mostlyhuman.colesgame.game;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.ingamemenus.IngameMenuContract;
import net.mostlyhuman.colesgame.ingamemenus.IngameMenuFragment;
import net.mostlyhuman.colesgame.ingamemenus.LevelCompleteDialogFragment;


public class GameActivity extends Activity implements InputController.PauseMenu,
        IngameMenuContract.ActivityCallback,
        GameRenderer.GameActivityContract {

    private static final String TAG = "GameActivity";

    private GLSurfaceView gameView;
    private GameRenderer gameRenderer;
    private GameManager gameManager;
    private SoundManager soundManager;
    private InputController inputController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point resolution = new Point();
        display.getSize(resolution);

        gameManager = new GameManager(this, resolution.x, resolution.y);

        soundManager = new SoundManager(this);
        this.soundManager.loadSounds(this);

        inputController = new InputController(this, resolution.x, gameManager, this);

        gameRenderer = new GameRenderer(this, inputController, soundManager, gameManager, this);

        gameView = new MyGLSurfaceView(this,
                gameRenderer, gameManager,
                soundManager, inputController);

        String levelTitle = getIntent().getStringExtra(Constants.LEVEL_TITLE);

        int levelID = getIntent().getIntExtra(Constants.LEVEL_ID, 50);

        gameManager.setGameRenderer(gameRenderer);
        gameManager.setCurrentLevel(levelTitle);
        gameManager.setLevelID(levelID);

        startGame();
    }

    private void startGame() {
        gameManager.setPlaying(true);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

    @Override
    public void onMenuButtonPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        IngameMenuFragment dialog = new IngameMenuFragment();
        dialog.show(fragmentManager, "pause dialog");
    }

    @Override
    public void mute() {
        soundManager.setMuted(true);
    }

    @Override
    public void unmute() {
        soundManager.setMuted(false);
    }

    @Override
    public void onResumePressed() {
        gameManager.switchPlayingStatus();
    }

    @Override
    public void onExitPressed() {
        this.finish();
    }

    @Override
    public void onLevelCompleted(int levelID) {
        Bundle args = new Bundle();
        args.putInt(Constants.LEVEL_ID, levelID);

        FragmentManager fragmentManager = getFragmentManager();
        LevelCompleteDialogFragment dialog = new LevelCompleteDialogFragment();
        dialog.setArguments(args);

        dialog.show(fragmentManager, "level completed dialog");

    }

    @Override
    public void exit() {
        this.finish();
    }
}
