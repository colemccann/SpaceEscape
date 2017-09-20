package net.mostlyhuman.colesgame.mainmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.mostlyhuman.colesgame.R;


/**
 * Created by CaptainMcCann on 9/18/2017.
 */

public class InstructionsFragment extends Fragment {

    Button gotItButton;

    public InstructionsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new InstructionsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_instructions, container, false);

        gotItButton = (Button) v.findViewById(R.id.button_gotit);
        gotItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return v;
    }
}
