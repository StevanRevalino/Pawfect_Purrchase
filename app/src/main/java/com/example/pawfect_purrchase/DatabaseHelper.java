package com.example.pawfect_purrchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "petFood_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS " +
                        TABLE_NAME +
                        " (IMAGE int, NAME text, PRICE text, DESCRIPTION text);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertData(int image, String name, String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", image);
        values.put("NAME", name);
        values.put("PRICE", price);
        values.put("DESCRIPTION", description);

        long status = db.insert(TABLE_NAME, null, values);

        return status != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
