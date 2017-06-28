package net.mostlyhuman.colesgame.data;

import android.database.Cursor;

import static net.mostlyhuman.colesgame.data.DatabaseContract.getColumnString;
import static net.mostlyhuman.colesgame.data.DatabaseContract.getColumnInt;

/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class Level {

    private int number;
    private String title;
    private boolean completed;
    private boolean available;

    // Create a Level from discrete items
    public Level(int number, boolean completed, boolean available) {
        this.number = number;
        this.title = String.valueOf(number);
        this.completed = completed;
        this.available = available;
    }

    // Create a Level from a database Cursor object
    public Level(Cursor cursor) {
        //this.number = getColumnInt(cursor, DatabaseContract.LevelColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.LevelColumns.LEVEL_TITLE);
        this.completed = getColumnInt(cursor, DatabaseContract.LevelColumns.COMPLETED) == 1;
        this.available = getColumnInt(cursor, DatabaseContract.LevelColumns.IS_AVAILABLE) == 1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
