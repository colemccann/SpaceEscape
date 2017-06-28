package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    String TAG = "GLSurfaceView";

    GameManager gameManager;
    private SoundManager soundManager;
    private InputController inputController;

    public MyGLSurfaceView(Context context,
                           GameRenderer gameRenderer,
                           GameManager gameManager,
                           SoundManager soundManager,
                           InputController inputController) {
        super(context);

        this.inputController = inputController;
        this.soundManager = soundManager;
        this.gameManager = gameManager;

        setEGLContextClientVersion(2);

        setRenderer(gameRenderer);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        inputController.handleInput(event);
        return true;
    }
}
