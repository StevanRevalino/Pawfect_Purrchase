package com.example.pawfect_purrchase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_ACCOUNT = "Account_table";

    public static final String TABLE_CART = "Cart_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "pawpur.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Account Table
        String query4 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_ACCOUNT +
                " (EMAIL text PRIMARY KEY, PASSWORD text);";

        db.execSQL(query4);

        String query5 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_CART +
                " (IMAGE INT, NAME TEXT, PRICE TEXT, DESCRIPTION TEXT);";

        db.execSQL(query5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query4 = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
        db.execSQL(query4);

        String query5 = "DROP TABLE IF EXISTS " + TABLE_CART;
        db.execSQL(query5);

        onCreate(db);
    }

    public boolean insertDataPerson(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("EMAIL", email);
        values.put("PASSWORD", password);

        long status = db.insert(TABLE_ACCOUNT, null, values);

        return status != -1;
    }

    public boolean insertDataCart(int image, String name, String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", image);
        values.put("NAME", name);
        values.put("PRICE", price);
        values.put("DESCRIPTION", description);

        long status = db.insert(TABLE_CART, null, values);
        return status != -1;

    }

    public Cursor getAllDataCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT IMAGE, NAME, PRICE, DESCRIPTION FROM " + TABLE_CART, null);
    }

    public boolean deleteAllDataCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(TABLE_CART,null,null);
        return rowDeleted > 0;
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
