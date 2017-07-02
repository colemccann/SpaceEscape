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
import net.mostlyhuman.colesgame.ingamemenu.IngameMenuContract;
import net.mostlyhuman.colesgame.ingamemenu.IngameMenuFragment;

public class GameActivity extends Activity implements InputController.PauseMenu,
        IngameMenuContract.ActivityCallback {

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
        inputController = new InputController(this, resolution.x, resolution.y, gameManager, this);
        gameRenderer = new GameRenderer(this, inputController, soundManager, gameManager);
        mediaPlayer = MediaPlayer.create(this, R.raw.music_1);

        gameView = new MyGLSurfaceView(this,
                gameRenderer, gameManager,
                soundManager, inputController);

        String levelTitle = getIntent().getStringExtra(Constants.LEVEL_TITLE);

        //// TODO: 7/2/2017 throw exception if levelID > 49
        int levelID = getIntent().getIntExtra(Constants.LEVEL_ID, 50);

        startGame(Constants.Levels.ONE_A);
    }

    private void startGame(String level/*, int levelID*/) {
        gameManager.setPlaying(true);
        gameManager.setCurrentLevel(level);
        //gameManager.setLevelID(levelID);
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
        dialog.show(fragmentManager, "dialog");
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
        // We will need 2 Activities
        this.finish();
    }
}
