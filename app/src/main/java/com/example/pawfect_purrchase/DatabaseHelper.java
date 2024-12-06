package com.example.pawfect_purrchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_FOOD = "petFood_table";
    private static final String TABLE_ACCOUNT = "Account_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "pawpur.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Food Table
        String query1 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_FOOD +
                " (IMAGE integer, NAME text, PRICE text, DESCRIPTION text);";

        db.execSQL(query1);

        //create Account Table
        String query =
                "CREATE TABLE IF NOT EXISTS " +
                        TABLE_ACCOUNT +
                        "(EMAIL text PRIMARY KEY, PASSWORD text);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_FOOD;
        db.execSQL(query);

        String query2= "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
        db.execSQL(query2);

        onCreate(db);
    }

    public boolean insertDataFood(int image, String name, String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", image);
        values.put("NAME", name);
        values.put("PRICE", price);
        values.put("DESCRIPTION", description);

        long status = db.insert(TABLE_FOOD, null, values);

        return status != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FOOD, null);
    }

    public boolean insertDataPerson(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("EMAIL", email);
        values.put("PASSWORD", password);

        long status = db.insert(TABLE_ACCOUNT, null, values);

        return status != -1;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNT +" WHERE email = ?", new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNT +" WHERE email = ? AND password = ?", new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
