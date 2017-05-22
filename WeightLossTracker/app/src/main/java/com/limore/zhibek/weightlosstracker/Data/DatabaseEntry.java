package com.limore.zhibek.weightlosstracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Lowlite on 03/05/2017.
 */

public class DatabaseEntry {
    private DatabaseEntry(){}

    public static final class WeightLossEntry implements BaseColumns {
        public static final String TABLE_NAME = "weightLossTable";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_CHEST = "chest";
        public static final String COLUMN_WAIST = "waist";
        public static final String COLUMN_BELLY = "belly";
        public static final String COLUMN_HIPS = "hips";
        public static final String COLUMN_THIGH = "thigh";
        public static final String COLUMN_ARM = "arm";

    }
}
