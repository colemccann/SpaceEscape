package net.mostlyhuman.colesgame.mainmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.mostlyhuman.colesgame.R;


public class MainMenuFragment extends Fragment {

    Button startButton;
    Button howToPlayButton;
    View.OnClickListener buttonListener;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.startButton:
                        openChapterSelection();
                        break;
                    case R.id.howToPlayButton:
                        showInstructions();
                        break;
                }
            }
        };

        startButton = (Button) v.findViewById(R.id.startButton);
        startButton.setOnClickListener(buttonListener);

        howToPlayButton = (Button) v.findViewById(R.id.howToPlayButton);
        howToPlayButton.setOnClickListener(buttonListener);

        return v;
    }

    public static Fragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void openChapterSelection() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = ChapterSelectionFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .addToBackStack(null)
                .commit();
    }


    private void showInstructions() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = InstructionsFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

}
