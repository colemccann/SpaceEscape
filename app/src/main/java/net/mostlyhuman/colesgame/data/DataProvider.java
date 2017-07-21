package net.mostlyhuman.colesgame.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class DataProvider extends ContentProvider {

    private String TAG = "DataProvider";

    private DatabaseHelper databaseHelper;

    private static final int LEVELS = 100;
    private static final int LEVELS_WITH_ID = 101;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_LEVELS, LEVELS);

        uriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_LEVELS + "/#",
                LEVELS_WITH_ID);
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        switch (uriMatcher.match(uri)) {
            case LEVELS:
                Log.d(TAG, sortOrder);
                break;
            case LEVELS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = String.format("%s = ?", DatabaseContract.LevelColumns._ID);
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                //// TODO: 7/21/2017 Where to catch the exception?
                throw new IllegalArgumentException("Unknown URI " + uri);
        }


        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

                Cursor cursor = db.query(
                        DatabaseContract.TABLE_LEVELS,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                if (getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                }

                return cursor;

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        throw new SQLException("Failed to query from " + uri);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (uriMatcher.match(uri)) {
            case LEVELS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = String.format("%s = ?", DatabaseContract.LevelColumns._ID);
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("Illegal URI update");
        }


        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (db != null) {
            int count = db.update(DatabaseContract.TABLE_LEVELS, values, selection, selectionArgs);

            if (count > 0) {

                if (getContext() != null) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }
            return count;
        }

        throw new SQLException("Failed to update record at URI " + uri);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (db != null) {
            long rowID = db.insert(DatabaseContract.TABLE_LEVELS, null, values);

            if (rowID > 0) {
                Uri mUri = ContentUris.withAppendedId(DatabaseContract.CONTENT_URI, rowID);

                if (getContext() != null) {
                    getContext().getContentResolver().notifyChange(mUri, null);
                }

                return mUri;
            }
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        switch (uriMatcher.match(uri)) {
            case LEVELS:
                selection = (selection == null) ? "1" : selection;
                break;
            case LEVELS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = String.format("%s = ?", DatabaseContract.LevelColumns._ID);
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("Illegal delete URI");
        }


        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (db != null) {
            int count = db.delete(DatabaseContract.TABLE_LEVELS, selection, selectionArgs);

            if (count > 0) {
                if (getContext() != null) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }

            return count;
        }

        throw new SQLException("Failed to delete URI " + uri);
    }


}
