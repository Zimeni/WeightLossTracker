package com.limore.zhibek.weightlosstracker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lowlite on 03/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "weightLossTracker.db";
    public static final int DB_VERSION = 1;

    private static final String CREATE_QUERY = "CREATE TABLE " + DatabaseEntry.WeightLossEntry.TABLE_NAME + " (" +
                                            DatabaseEntry.WeightLossEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_DATE + " INTEGER, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_CHEST + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_WAIST + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_BELLY + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_HIPS + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_THIGH + " REAL, " +
                                            DatabaseEntry.WeightLossEntry.COLUMN_ARM + " REAL " + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatabaseEntry.WeightLossEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
