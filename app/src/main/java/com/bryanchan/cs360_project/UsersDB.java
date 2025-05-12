package com.bryanchan.cs360_project;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsersDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";
    private static final int DB_VER = 1;

    public UsersDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    // Users DB Table //
    private static final class UsersTB {
        private static final String TB = "users";
        private static final String COL_ID = "id";
        private static final String COL_EMAIL = "email";
        private static final String COL_PASSWORD = "password";
    }

    // add new TB to DB //
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "create table " +
            UsersTB.TB + " (" +
            UsersTB.COL_ID + " integer primary key autoincrement, " +
            UsersTB.COL_EMAIL + " text, " +
            UsersTB.COL_PASSWORD + " text)"
        );
    }

    // update TB in DB //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "drop table if exists " + UsersTB.TB
        );
        onCreate(db);
    }


    // methods //
    public void addUser(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UsersTB.COL_EMAIL, email);
        values.put(UsersTB.COL_PASSWORD, password);

        db.insert(UsersTB.TB, null, values);
        db.close();
    }

    public void getUsersByEmail(String email) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from " + UsersTB.TB + " where email = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { email });

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String email_ = cursor.getString(1);
                String password_ = cursor.getString(2);

                Log.d("tag// ", "email: " + email_ + " password: " + password_);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
