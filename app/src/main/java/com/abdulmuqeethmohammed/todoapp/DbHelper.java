package com.abdulmuqeethmohammed.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Abdul Muqeeth Mohammed.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public DbHelper (Context context) {
        super(context, AppConstants.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("Create TABLE %s (ID INTEGER PRIMARY KEY, AUTOINCREMENT, %s TEXT NOT NULL", AppConstants.TABLE_NAME, AppConstants.TASK_COLUMN_NAME);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s", AppConstants.TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertNewTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.TASK_COLUMN_NAME, task);
        db.insertWithOnConflict(AppConstants.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deletetask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(AppConstants.TABLE_NAME, AppConstants.TASK_COLUMN_NAME ,new String[] {task});
        db.close();
    }

    public ArrayList<String> getTaskList() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(AppConstants.TABLE_NAME, new String[] {AppConstants.TASK_COLUMN_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(AppConstants.TASK_COLUMN_NAME);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }
}
