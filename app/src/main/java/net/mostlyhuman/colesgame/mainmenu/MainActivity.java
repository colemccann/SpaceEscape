package net.mostlyhuman.colesgame.mainmenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;


import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.data.DatabaseUpdateService;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    public static final String INITIALIZATION_NOTIFICATION =
            "net.mostlyhuman.colesgame.initialization";
    public static final String INITIALIZATION_SUCCESSFUL =
            "net.mostlyhuman.colesgame.initialization_successful";


    private SharedPreferences preferences;

    private BroadcastReceiver DataInitializationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            FirstTimeSetupFragment setupFragment = (FirstTimeSetupFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_frame);

            boolean success = intent.getBooleanExtra(INITIALIZATION_SUCCESSFUL, false);
            if (success) {
                if (setupFragment != null) {
                    setupFragment.proceedToMainMenu();
                    Log.d(TAG, "Database creation successful.");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(getString(R.string.pref_initial_setup_key), false);
                    editor.apply();
                }

            } else {
                if (setupFragment != null) {
                    setupFragment.reload();
                    DatabaseUpdateService.createEntries(MainActivity.this);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(getString(R.string.pref_key_initial), Context.MODE_PRIVATE);
        boolean initialSetup = preferences.getBoolean(
                getString(R.string.pref_initial_setup_key), true);
        if (initialSetup) {
            initFragment(FirstTimeSetupFragment.newInstance());
            DatabaseUpdateService.createEntries(this);
        } else {
            initFragment(MainMenuFragment.newInstance());
        }

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(DataInitializationReceiver,
                        new IntentFilter(INITIALIZATION_NOTIFICATION));
    }

    private void initFragment(Fragment newInstance) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_frame, newInstance);
        transaction.commit();
    }

}
