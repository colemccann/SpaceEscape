package net.mostlyhuman.colesgame.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GameLevels.db";

    private static final String SQL_CREATE_TABLE_LEVELS = String.format("create table %s"
                    + " (%s integer primary key autoincrement, %s text, %s integer, %s integer)",
            DatabaseContract.TABLE_LEVELS,
            DatabaseContract.LevelColumns._ID,
            DatabaseContract.LevelColumns.LEVEL_TITLE,
            DatabaseContract.LevelColumns.COMPLETED,
            DatabaseContract.LevelColumns.IS_AVAILABLE
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_LEVELS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
