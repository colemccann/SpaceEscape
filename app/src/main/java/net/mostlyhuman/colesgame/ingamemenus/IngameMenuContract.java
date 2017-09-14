package net.mostlyhuman.colesgame.ingamemenus;


/**
 * Created by CaptainMcCann on 6/7/2017.
 */

public interface IngameMenuContract {

    interface View {
        void showMuteIcon(boolean muted);

        void resumeGame();

        void exitLevel();
    }

    interface UserActionsListener {
        void toggleMuted();

        void showMuted();
    }

    interface ActivityCallback {

        void mute();

        void unmute();

        void onResumePressed();

        void onExitPressed();
    }
}
