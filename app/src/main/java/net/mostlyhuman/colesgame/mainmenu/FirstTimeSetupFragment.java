package net.mostlyhuman.colesgame.mainmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.mostlyhuman.colesgame.R;

/**
 * Created by CaptainMcCann on 7/18/2017.
 */

public class FirstTimeSetupFragment extends Fragment {

    TextView messageView;
    ProgressBar progressBar;

    public FirstTimeSetupFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_initial_startup, container, false);

        messageView = (TextView) v.findViewById(R.id.setupMessage);
        progressBar = (ProgressBar) v.findViewById(R.id.SetupProgressBar);

        messageView.setText(R.string.first_time_setup_message);

        return v;
    }

    public static Fragment newInstance() {
        return new FirstTimeSetupFragment();
    }

    public void proceedToMainMenu() {
        messageView.setText(R.string.setup_complete);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = MainMenuFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .commit();
    }

    public void reload() {
        messageView.setText(R.string.initialization_failed);
    }
}
