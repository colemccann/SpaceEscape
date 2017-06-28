package net.mostlyhuman.colesgame.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by CaptainMcCann on 6/15/2017.
 */

public class DatabaseContract {

    static final String TABLE_LEVELS = "levels";

    public static final class LevelColumns implements BaseColumns {

        public static final String LEVEL_TITLE = "title";

        public static final String COMPLETED = "completed";

        public static final String IS_AVAILABLE = "is_available";
    }

    public static final String CONTENT_AUTHORITY = "net.mostlyhuman.colesgame";

    public static final String DEFAULT_SORT = String.format("%s ASC",
            LevelColumns._ID);

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_LEVELS)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName) );
    }

}
