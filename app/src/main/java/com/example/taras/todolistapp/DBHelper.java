package com.example.taras.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Taras on 7/4/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private String mName;

    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
        mName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + mName + " (" +
                "id integer primary key autoincrement, " +
                ApiConst.ID_KEY + " integer," +
                ApiConst.DATE_KEY + " text," +
                ApiConst.TITLE_KEY + " text," +
                ApiConst.MESSAGE_KEY + " text);");
    }

    public boolean insert(ContentValues contentValues) {
        return getWritableDatabase().insert(mName, null, contentValues) > 0;
    }

    public boolean delete(int id) {
        return getWritableDatabase().delete(mName, ApiConst.ID_KEY + "=" + Integer.toString(id), null) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<TodoModel> get() {
        ArrayList<TodoModel> res = new ArrayList<>();
        Cursor c = getReadableDatabase().query(mName, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int idCol, titleCol, messCol, dateCol;
            idCol = c.getColumnIndex(ApiConst.ID_KEY);
            titleCol = c.getColumnIndex(ApiConst.TITLE_KEY);
            messCol = c.getColumnIndex(ApiConst.MESSAGE_KEY);
            dateCol = c.getColumnIndex(ApiConst.DATE_KEY);

            do {
                int id;
                String title, message, date;
                id = c.getInt(idCol);
                title = c.getString(titleCol);
                message = c.getString(messCol);
                date = c.getString(dateCol);
                res.add(new TodoModel(title, message, date, id));
            } while (c.moveToNext());
        }

        return res;
    }
}
