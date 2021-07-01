package com.example.postarticle.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DbManager {
    private DbUsersHelper dbUsersHelper;
    private SQLiteDatabase database;
    private Context context;

    public DbManager(Context c) {
        context = c;
    }

    public DbManager open() throws SQLException {
        dbUsersHelper = new DbUsersHelper(context);
        database = dbUsersHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbUsersHelper.close();
    }

    public void insertUser(String name, String email, String password, String token) {
        dbUsersHelper.insert(database, name,  email, password, token);
    }

    public List getListUser(){
        return dbUsersHelper.fetch(database);
    }

    public void deleteUser(){
        dbUsersHelper.delete(database);
    }



}
