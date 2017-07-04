package net.mostlyhuman.colesgame.ingamemenus;

import android.content.Context;
import android.content.SharedPreferences;

import net.mostlyhuman.colesgame.R;

/**
 * Created by CaptainMcCann on 6/7/2017.
 */

public class IngameMenuPresenter implements IngameMenuContract.UserActionsListener {

    private IngameMenuContract.View ingameMenuView;
    private Context context;
    private SharedPreferences preferences;

    public IngameMenuPresenter(Context context, IngameMenuContract.View ingameMenuView) {
        this.ingameMenuView = ingameMenuView;
        this.context = context;
        preferences = context.getSharedPreferences(
                context.getString(R.string.pref_key_1),
                Context.MODE_PRIVATE
        );
    }

    @Override
    public void toggleMuted() {
        // write to preferences and mute / unmute sound
        boolean muted = !preferences.getBoolean(
                context.getString(R.string.pref_muted_key), false);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.pref_muted_key), muted);
        editor.apply();
    }

    @Override
    public void showMuted() {
            boolean isMuted = preferences.getBoolean(
                    context.getString(R.string.pref_muted_key), false);
            ingameMenuView.showMuteIcon(isMuted);
    }

}
