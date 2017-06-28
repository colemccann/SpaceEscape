package net.mostlyhuman.colesgame.ingamemenu;


/**
 * Created by CaptainMcCann on 6/7/2017.
 */

public interface IngameMenuContract {

    interface View {
        void showRemainingFuel(int fuelRemaining);

        void showMuteIcon(boolean muted);

        void restartLevel();

        void resumeGame();

        void exitLevel();
    }

    interface UserActionsListener {
        void toggleMuted();

        void showMuted();

        void getRemainingFuel();
    }

    interface ActivityCallback {
        void onRestartPressed();

        void onResumePressed();

        void onExitPressed();
    }
}
