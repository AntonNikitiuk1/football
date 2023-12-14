package com.example.football;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "football_res.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESULTS = "results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HOME_TEAM = "home_team";
    public static final String COLUMN_AWAY_TEAM = "away_team";
    public static final String COLUMN_HOME_GOALS = "home_goals";
    public static final String COLUMN_AWAY_GOALS = "away_goals";
    public static final String COLUMN_DATE = "date";

    private static final String CREATE_RESULTS_TABLE = "CREATE TABLE " + TABLE_RESULTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_HOME_TEAM + " TEXT, " +
            COLUMN_AWAY_TEAM + " TEXT, " +
            COLUMN_HOME_GOALS + " INTEGER, " +
            COLUMN_AWAY_GOALS + " INTEGER, " +
            COLUMN_DATE + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESULTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }

}
