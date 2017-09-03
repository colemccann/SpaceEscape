package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class InputController {

    private Rect menu;
    private String TAG = "Input Controller";

    private final GestureDetector gestureDetector;
    private final PauseMenu gameActivity;
    private GameManager gameManager;


    InputController(Context context, int screenWidth, GameManager gameManager,
                    PauseMenu mainActivity) {

        this.gameActivity = mainActivity;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.gameManager = gameManager;

        int buttonLength = screenWidth / 12;
        int buttonPadding = screenWidth / 80;

        // Left, Top, Right, Bottom
        menu = new Rect(buttonPadding,
                buttonPadding,
                buttonPadding + buttonLength,
                buttonPadding + buttonLength);
    }

    Rect getMenuButton() {
         return menu;
    }

    void handleInput(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 80;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            int x = (int) e.getX();
            int y = (int) e.getY();
            switch (e.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if (menu.contains(x, y)) {
                        //Log.e(TAG, "menu pressed");
                        gameManager.switchPlayingStatus();
                        gameActivity.onMenuButtonPressed();
                    }
                    break;
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    private void onSwipeRight() {
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(270);
            gameManager.player.boost();
        }
    }

    private void onSwipeLeft() {
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(90);
            gameManager.player.boost();
        }
    }

    private void onSwipeTop() {
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(360);
            gameManager.player.boost();
        }
    }

    private void onSwipeBottom() {
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(180);
            gameManager.player.boost();
        }
    }

    interface PauseMenu {
        void onMenuButtonPressed();
    }

}
