package com.example.qlquancafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBConnect(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB){
        MyDB.execSQL("create Table user(sdt TEXT primary key, password TEXT)");
    }
    @Override
    public void onUpgrade (SQLiteDatabase MyDB, int i, int i1){
        MyDB.execSQL("drop Table if exists user");
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("sdt", username);
        contentValues.put("password", password);
        long result = MyDB.insert("user", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean checksdt(String sdt){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where sdt = ?", new String[] {sdt});
        if(cursor.getCount()>0){return true;}
        else {return false;}
    }
    public Boolean checkusernamepassword(String sdt, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where sdt = ? and password = ?", new String[] {sdt,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
