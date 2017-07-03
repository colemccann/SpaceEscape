package net.mostlyhuman.colesgame.ingamemenus;


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

    interface LevelCompleted {

        interface ActivityCallback {

            void onNextLevelPressed(String level);

            void onMainMenuPressed();
        }
    }
}
