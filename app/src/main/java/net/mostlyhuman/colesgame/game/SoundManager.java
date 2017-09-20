package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.io.IOException;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class SoundManager {

    private static final String TAG = "SoundManager error";

    private int bump = -1;
    private int redirect = -1;
    private int explosion = -1;
    private int warp = -1;
    private int door = -1;

    private SoundPool soundPool;

    private SharedPreferences preferences;

    private boolean muted;

    public SoundManager(Context context) {

        preferences = context.getSharedPreferences(
                context.getString(R.string.pref_key_sound),
                Context.MODE_PRIVATE
        );

        muted = preferences.getBoolean(context.getString(R.string.pref_muted_key), false);
    }

    void loadSounds(Context context) {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd(context.getString(R.string.bump_ogg));
            bump = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd(context.getString(R.string.redirect_ogg));
            redirect = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd(context.getString(R.string.explosion_ogg));
            explosion = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("warp.ogg");
            warp = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("door.ogg");
            door = soundPool.load(descriptor, 0);

        } catch (IOException e) {
            Log.e(TAG, "failed to load sound files");
        }
    }

    public void playSound(String sound) {

        if (!muted) {
            switch (sound) {
                case Constants.Sounds.BUMP:
                    soundPool.play(bump, 1, 1, 0, 0, 1);
                    break;
                case Constants.Sounds.REDIRECT:
                    soundPool.play(redirect, 1, 1, 0, 0, 1);
                    break;
                case Constants.Sounds.EXPLOSION:
                    soundPool.play(explosion, 1, 1, 0, 0, 1);
                    break;
                case Constants.Sounds.WARP:
                    soundPool.play(warp, 1, 1, 0, 0, 1);
                    break;
                case Constants.Sounds.DOOR:
                    soundPool.play(door, 1, 1, 0, 0, 1);
                    break;
            }
        }
    }

    public boolean isMuted() {
        return muted;
    }

    void setMuted(boolean muted) {
        this.muted = muted;
    }
}
