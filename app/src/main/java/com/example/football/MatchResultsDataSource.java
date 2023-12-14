package com.example.football;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MatchResultsDataSource {

    private SQLiteDatabase database;
    private com.example.football.DBHelper dbHelper;

    public MatchResultsDataSource(Context context) {
        dbHelper = new com.example.football.DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addMatchResult(String homeTeam, String awayTeam, int homeGoals, int awayGoals, String date) {
        ContentValues values = new ContentValues();
        values.put(com.example.football.DBHelper.COLUMN_HOME_TEAM, homeTeam);
        values.put(com.example.football.DBHelper.COLUMN_AWAY_TEAM, awayTeam);
        values.put(com.example.football.DBHelper.COLUMN_HOME_GOALS, homeGoals);
        values.put(com.example.football.DBHelper.COLUMN_AWAY_GOALS, awayGoals);
        values.put(com.example.football.DBHelper.COLUMN_DATE, date);
        database.insert(com.example.football.DBHelper.TABLE_RESULTS, null, values);
    }

    public Cursor getAllMatchResults() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {
                com.example.football.DBHelper.COLUMN_ID,
                com.example.football.DBHelper.COLUMN_HOME_TEAM,
                com.example.football.DBHelper.COLUMN_AWAY_TEAM,
                com.example.football.DBHelper.COLUMN_HOME_GOALS,
                com.example.football.DBHelper.COLUMN_AWAY_GOALS,
                com.example.football.DBHelper.COLUMN_DATE
        };

        return database.query(
                com.example.football.DBHelper.TABLE_RESULTS,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public List<Result> getListMatch() {
        List<Result> matchResults = new ArrayList<>();
        Cursor cursor = getAllMatchResults();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String homeTeam = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_HOME_TEAM));
                String awayTeam = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_AWAY_TEAM));
                int homeGoals = cursor.getInt(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_HOME_GOALS));
                int awayGoals = cursor.getInt(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_AWAY_GOALS));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_DATE));

                Result matchResult = new Result(homeTeam, awayTeam, homeGoals, awayGoals, date);
                matchResults.add(matchResult);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return matchResults;
    }


    public List<Result> searchByTeam(String command_name) {
        List<Result> matchResults = new ArrayList<>();

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String selection = com.example.football.DBHelper.COLUMN_HOME_TEAM + " LIKE ? OR " +
                com.example.football.DBHelper.COLUMN_AWAY_TEAM + " LIKE ?";

        String[] selectionArgs = new String[]{"%" + command_name + "%", "%" + command_name + "%"};

        Cursor cursor = database.query(
                com.example.football.DBHelper.TABLE_RESULTS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String homeTeam = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_HOME_TEAM));
                String awayTeam = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_AWAY_TEAM));
                int homeGoals = cursor.getInt(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_HOME_GOALS));
                int awayGoals = cursor.getInt(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_AWAY_GOALS));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(com.example.football.DBHelper.COLUMN_DATE));

                Result matchResult = new Result(homeTeam, awayTeam, homeGoals, awayGoals, date);
                matchResults.add(matchResult);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return matchResults;
    }

}
