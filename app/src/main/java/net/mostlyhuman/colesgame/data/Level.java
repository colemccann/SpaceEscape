package net.mostlyhuman.colesgame.data;

import android.database.Cursor;

import static net.mostlyhuman.colesgame.data.DatabaseContract.getColumnString;
import static net.mostlyhuman.colesgame.data.DatabaseContract.getColumnInt;

/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class Level {

    private int id;
    private String title;
    private boolean completed;
    private boolean available;

    // Create a Level from a database Cursor object
    public Level(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.LevelColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.LevelColumns.LEVEL_TITLE);
        this.completed = getColumnInt(cursor, DatabaseContract.LevelColumns.COMPLETED) == 1;
        this.available = getColumnInt(cursor, DatabaseContract.LevelColumns.IS_AVAILABLE) == 1;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isAvailable() {
        return available;
    }
}
