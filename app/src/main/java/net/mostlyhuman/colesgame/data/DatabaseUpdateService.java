package net.mostlyhuman.colesgame.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import net.mostlyhuman.colesgame.mainmenu.MainActivity;


/**
 * Created by CaptainMcCann on 6/21/2017.
 */

public class DatabaseUpdateService extends IntentService {

    private static final String TAG = DatabaseUpdateService.class.getSimpleName();

    public static final String ACTION_BULK_INSERT = TAG + ".BULK_INSERT";
    public static final String ACTION_UPDATE = TAG + ".UPDATE";
    public static final String ACTION_DELETE = TAG + ".DELETE";

    public static final String EXTRA_VALUES = TAG + ".ContentValues";


    public DatabaseUpdateService() {
        super(TAG);
    }

    public static void createEntries(Context context) {
        Intent intent = new Intent(context, DatabaseUpdateService.class);
        intent.setAction(ACTION_BULK_INSERT);
        context.startService(intent);
    }

    public static void updateLevelStatus(Context context, Uri uri, ContentValues values) {
        Intent intent = new Intent(context, DatabaseUpdateService.class);
        intent.setAction(ACTION_UPDATE);
        intent.setData(uri);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            if (ACTION_BULK_INSERT.equals(intent.getAction())) {
                ContentValues[] values = InitialLevelData.createValues();
                performBulkInsert(values);
            } else if (ACTION_UPDATE.equals(intent.getAction())) {
                ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
                performUpdate(intent.getData(), values);
            }
        }
    }

    private void performBulkInsert(ContentValues[] values) {
        if (getContentResolver().bulkInsert(DatabaseContract.CONTENT_URI, values) != 0) {
            Log.d(TAG, "Inserted database entries");
            Intent intent = new Intent(MainActivity.INITIALIZATION_NOTIFICATION);
            intent.putExtra(MainActivity.INITIALIZATION_SUCCESSFUL, true);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        } else {
            Log.w(TAG, "Error inserting database entries");
            Intent intent = new Intent(MainActivity.INITIALIZATION_NOTIFICATION);
            intent.putExtra(MainActivity.INITIALIZATION_SUCCESSFUL, false);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    private void performUpdate(Uri uri, ContentValues values) {
        int count = getContentResolver().update(uri, values, null, null);
        Log.d(TAG, "Updated " + count + " task items");
    }

}
