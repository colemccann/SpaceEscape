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

    Rect menu;
    private String TAG = "Input Controller";

    private final GestureDetector gestureDetector;
    private final PauseMenu mainActivity;
    private GameManager gameManager;

    private int screenWidth;
    private int screenHeight;

    InputController(Context context, int screenWidth, int screenHeight,
                    GameManager gameManager, PauseMenu mainActivity) {

        this.mainActivity = mainActivity;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.gameManager = gameManager;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;


        int buttonLength = screenWidth / 12;
        int buttonPadding = screenWidth / 80;

        // Left, Top, Right, Bottom
        menu = new Rect(buttonPadding,
                buttonPadding,
                buttonPadding + buttonLength,
                buttonPadding + buttonLength);
    }

    public Rect getMenuButton() {
         return menu;
    }

    public void handleInput(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        // ACTION_DOWN: Initial finger touch
        // ACTION_POINTER_DOWN: Non-primary touch event

        /*
        int startX = (int) event.getX(0);
        int startY = (int) event.getY(0);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (menu.contains(startX, startY)) {
                    gameManager.player.menu();
                } else if (swivel.contains(startX, startY)) {
                    gameManager.player.swivel();
                }
                break;

            }
            */
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
                        Log.e(TAG, "menu pressed");
                        gameManager.switchPlayingStatus();
                        mainActivity.onMenuButtonPressed();
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
                    if (Math.abs(diffX) > SWIPE_THRESHOLD /*&& Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD*/) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD /*&& Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD*/) {
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

    public void onSwipeRight() {
        Log.e(TAG, "Swiping right");
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(270);
            gameManager.player.boost();
        }
    }

    public void onSwipeLeft() {
        Log.e(TAG, "Swiping left");
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(90);
            gameManager.player.boost();
        }
    }

    public void onSwipeTop() {
        Log.e(TAG, "Swiping top");
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(360);
            gameManager.player.boost();
        }
    }

    public void onSwipeBottom() {
        Log.e(TAG, "Swiping bottom");
        if (!gameManager.player.isMoving()) {
            gameManager.player.setFacingAngle(180);
            gameManager.player.boost();
        }
    }

    public interface PauseMenu {
        void onMenuButtonPressed();
    }

}
