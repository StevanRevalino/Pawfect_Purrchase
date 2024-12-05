package com.example.pawfect_purrchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperAccount extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "signup_table";

    public DatabaseHelperAccount(@Nullable Context context) {
        super(context, "signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS " +
                        TABLE_NAME +
                        "(EMAIL text primary key, PASSWORD text);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertDataPerson(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("EMAIL", email);
        values.put("PASSWORD", password);

        long status = db.insert(TABLE_NAME, null, values);

        return status != -1;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE email = ?", new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE email = ? AND password = ?", new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
