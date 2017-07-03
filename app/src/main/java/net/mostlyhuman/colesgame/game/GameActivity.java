package net.mostlyhuman.colesgame.game;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.ingamemenus.IngameMenuContract;
import net.mostlyhuman.colesgame.ingamemenus.IngameMenuFragment;
import net.mostlyhuman.colesgame.ingamemenus.LevelCompleteMenuFragment;

public class GameActivity extends Activity implements InputController.PauseMenu,
        IngameMenuContract.ActivityCallback,
        GameRenderer.LevelCompleteContract,
        IngameMenuContract.LevelCompleted.ActivityCallback {

    private GLSurfaceView gameView;
    private GameRenderer gameRenderer;
    private GameManager gameManager;
    private SoundManager soundManager;
    private InputController inputController;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point resolution = new Point();
        display.getSize(resolution);

        gameManager = new GameManager(this, resolution.x, resolution.y);
        soundManager = new SoundManager();
        this.soundManager.loadSounds(this);
        inputController = new InputController(this, resolution.x, gameManager, this);
        gameRenderer = new GameRenderer(this, inputController, soundManager, gameManager, this);
        mediaPlayer = MediaPlayer.create(this, R.raw.music_1);

        gameView = new MyGLSurfaceView(this,
                gameRenderer, gameManager,
                soundManager, inputController);

        String levelTitle = getIntent().getStringExtra(Constants.LEVEL_TITLE);

        //// TODO: 7/2/2017 throw exception if levelID > 49
        int levelID = getIntent().getIntExtra(Constants.LEVEL_ID, 50);

        gameManager.setCurrentLevel(levelTitle);
        gameManager.setLevelID(levelID);

        startGame();
    }

    private void startGame() {
        gameManager.setPlaying(true);

        //mediaPlayer.setLooping(true);
        //mediaPlayer.start();

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
    public void onRestartPressed() {
        gameManager.switchPlayingStatus();
        // Use Interface method to control Game Renderer from here
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
    public void onLevelCompleted(String level) {
        Bundle args = new Bundle();
        args.putString(Constants.LEVEL_TITLE, level);

        FragmentManager fragmentManager = getFragmentManager();
        LevelCompleteMenuFragment dialog = new LevelCompleteMenuFragment();
        dialog.setArguments(args);

        dialog.show(fragmentManager, "level completed dialog");

    }

    @Override
    public void onNextLevelPressed(String level) {

    }

    @Override
    public void onMainMenuPressed() {

    }
}
