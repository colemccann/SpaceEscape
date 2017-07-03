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

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;


/**
 * Created by CaptainMcCann on 7/3/2017.
 */

public class LevelCompleteMenuFragment extends DialogFragment implements View.OnClickListener {

    private Button nextLevelButton, mainMenuButton;

    private IngameMenuContract.LevelCompleted.ActivityCallback gameActivity;

    private String levelTitle;

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            gameActivity = (IngameMenuContract.LevelCompleted.ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "Must implement LevelCompleted.ActivityCallback");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        levelTitle = getArguments().getString(Constants.LEVEL_TITLE);

        final ViewGroup nullParent = null;
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_level_complete, nullParent);

        nextLevelButton = (Button) v.findViewById(R.id.next_level_button);
        nextLevelButton.setOnClickListener(this);

        mainMenuButton = (Button) v.findViewById(R.id.main_menu_button);
        mainMenuButton.setOnClickListener(this);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.level_complete)
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_level_button:
                gameActivity.onNextLevelPressed(levelTitle);
                break;
            case R.id.main_menu_button:
                gameActivity.onMainMenuPressed();
                break;
        }
    }

}
