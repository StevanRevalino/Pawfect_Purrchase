package com.example.pawfect_purrchase.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_ACCOUNT = "Account_table";
    public static final String TABLE_CART = "Cart_table";
    public static final String TABLE_HISTORY = "History_Table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "pawpur.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Account Table
        String query1 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_ACCOUNT +
                " (EMAIL text PRIMARY KEY, PASSWORD text);";

        db.execSQL(query1);

        String query2 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_CART +
                " (IMAGE INT, NAME TEXT, PRICE TEXT, DESCRIPTION TEXT);";

        db.execSQL(query2);

        String query3 =
                "CREATE TABLE IF NOT EXISTS " +
                TABLE_HISTORY +
                " (TRANSACTIONCOUNT TEXT, TOTAL_SPENT TEXT);";

        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query1 = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABLE_CART;
        db.execSQL(query2);

        // Jika perlu memperbarui kolom atau tabel
        String query3 = "DROP TABLE IF EXISTS " + TABLE_HISTORY;
        db.execSQL(query3);


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

    public boolean deleteCartItem(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_CART, "NAME = ?", new String[]{name});
        return rowsDeleted > 0;
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

    // Menambahkan transaksi ke tabel History
    public boolean addTransaction(String totalSpent) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the latest transaction count
        Cursor cursor = db.rawQuery("SELECT MAX(CAST(TRANSACTIONCOUNT AS INTEGER)) FROM " + TABLE_HISTORY, null);
        int transactionCount = 1;  // Default to 1 if the table is empty

        if (cursor.moveToFirst()) {
            transactionCount = cursor.getInt(0) + 1;
        }
        cursor.close();

        // Insert the new transaction with the incremented transaction count
        ContentValues contentValues = new ContentValues();
        contentValues.put("TRANSACTIONCOUNT", transactionCount); // Simpan sebagai integer
        contentValues.put("TOTAL_SPENT", totalSpent);

        long result = db.insert(TABLE_HISTORY, null, contentValues);
        return result != -1;
    }



    // Mengambil semua data transaksi dari tabel History
    public Cursor getAllDataHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
    }

    public boolean deleteAllHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        int rowDeleted = db.delete(TABLE_HISTORY,null,null);
        return rowDeleted > 0;
    }

}
