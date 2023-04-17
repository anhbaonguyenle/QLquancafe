package com.example.qlquancafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_NAME = "user";

    // Column names
    private static final String COLUMN_SDT = "sdt";
    private static final String COLUMN_PASS = "pass";

    // Constructor
    public DBConnect(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_SDT + " TEXT PRIMARY KEY, " +
                COLUMN_PASS + " TEXT " +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    // Insert data
    public boolean insertData(String sdt, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SDT, sdt);
        values.put(COLUMN_PASS, pass);
        long result = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return result != -1;
    }

    // Check if sdt exists
    public boolean checkSdtExists(String sdt) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_SDT + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{sdt});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close(); // Closing database connection
        return exists;
    }

    // Check password for sdt
    public boolean checkPassword(String sdt, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_SDT + " = ? AND " + COLUMN_PASS + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{sdt, pass});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close(); // Closing database connection
        return exists;
    }
}
