package com.example.postarticle.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.postarticle.ConfigApp;

import java.util.ArrayList;
import java.util.List;

public class DbUsersHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "users";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME +" TEXT," +
            EMAIL +" TEXT," +
            PASSWORD +" TEXT," +
            TOKEN +" TEXT)";

    public DbUsersHelper(@Nullable Context context) {
        super(context, ConfigApp.DB_NAME, null, ConfigApp.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insert(SQLiteDatabase database, String name, String email, String password, String token) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(NAME, name);
        contentValue.put(EMAIL, email);
        contentValue.put(PASSWORD, password);
        contentValue.put(TOKEN, token);
        database.insert(TABLE_NAME, null, contentValue);
    }

    public List fetch(SQLiteDatabase database) {
        List list = new ArrayList<>();
        String[] columns = new String[]{ _ID, NAME, EMAIL,TOKEN };
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            List newList = new ArrayList<>();
            newList.add(cursor.getLong(0));
            newList.add(cursor.getString(1));
            newList.add(cursor.getString(2));
            newList.add(cursor.getString(3));

            list.add(newList);
            cursor.moveToNext();
        }

        return list;
    }

    public void delete(SQLiteDatabase database){
        database.delete(TABLE_NAME, null, null);
    }
}
