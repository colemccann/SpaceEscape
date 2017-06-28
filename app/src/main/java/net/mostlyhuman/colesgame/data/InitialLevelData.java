package net.mostlyhuman.colesgame.data;

import android.content.ContentValues;

/**
 * Created by CaptainMcCann on 6/22/2017.
 */

class InitialLevelData {

    static ContentValues[] createValues() {
        ContentValues[] values = new ContentValues[50];
        Integer one = 1;
        Integer zero = 0;
        for (int i = 0; i < values.length; i++) {
            String levelTitle = "Level " + (i + 1);
            ContentValues tempValues = new ContentValues(4);
            tempValues.put(DatabaseContract.LevelColumns.LEVEL_TITLE, levelTitle);
            tempValues.put(DatabaseContract.LevelColumns.COMPLETED, zero);
            if (i == 0) {
                tempValues.put(DatabaseContract.LevelColumns.IS_AVAILABLE, one);
            } else {
                tempValues.put(DatabaseContract.LevelColumns.IS_AVAILABLE, zero);
            }
            values[i] = tempValues;
        }
        return values;
    }
}
