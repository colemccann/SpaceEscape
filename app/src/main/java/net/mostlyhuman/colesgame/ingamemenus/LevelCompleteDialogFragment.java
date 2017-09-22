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


/**
 * Created by CaptainMcCann on 7/3/2017.
 */

public class LevelCompleteDialogFragment extends DialogFragment implements View.OnClickListener {

    public interface finishActivityListener {
        public void onUserDismiss();
    }

    private finishActivityListener callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callback = (finishActivityListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final ViewGroup nullParent = null;
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_level_complete, nullParent);

        Button mainMenuButton = (Button) v.findViewById(R.id.main_menu_button);
        mainMenuButton.setOnClickListener(this);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.level_complete)
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu_button:
                dismiss();
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        callback.onUserDismiss();
    }
}
