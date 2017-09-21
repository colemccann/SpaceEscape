package net.mostlyhuman.colesgame.ingamemenus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import net.mostlyhuman.colesgame.R;

/**
 * Created by CaptainMcCann on 6/7/2017.
 */

public class IngameMenuFragment extends DialogFragment implements IngameMenuContract.View {

    private Button muteButton;
    private Button resumeButton;
    private Button exitButton;

    private IngameMenuContract.UserActionsListener userActionsListener;
    private IngameMenuContract.ActivityCallback activityCallback;

    private boolean exit;
    private boolean resume;

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (IngameMenuContract.ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                        "Must implement ActivityCallback");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        userActionsListener = new IngameMenuPresenter(getActivity(), this);

        final ViewGroup nullParent = null;
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_ingame_menu, nullParent);

        muteButton = (Button) v.findViewById(R.id.muteButton);
        userActionsListener.showMuted();
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userActionsListener.toggleMuted();
                userActionsListener.showMuted();
            }
        });

        resumeButton = (Button) v.findViewById(R.id.resumeGameButton);
        resumeButton.setText(R.string.resume);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeGame();
            }
        });

        exitButton = (Button) v.findViewById(R.id.exitLevelButton);
        exitButton.setText(R.string.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLevel();
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.game_paused)
                .create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (resume) {
            activityCallback.onResumePressed();
        } else if (exit) {
            activityCallback.onExitPressed();
        } else {
            activityCallback.onResumePressed();
        }
    }

    @Override
    public void showMuteIcon(boolean muted) {
        if (muted) {
            muteButton.setText(R.string.unmute);
            activityCallback.mute();
        } else {
            muteButton.setText(R.string.mute);
            activityCallback.unmute();
        }
    }

    @Override
    public void resumeGame() {
        resume = true;
        this.dismiss();
    }

    @Override
    public void exitLevel() {
        exit = true;
        this.dismiss();
    }
}
