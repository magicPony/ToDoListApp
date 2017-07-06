package com.example.taras.todolistapp;

import android.content.ContentValues;

import java.util.Random;

/**
 * Created by Taras on 7/4/2017.
 */

public class TodoModel {

    private String title = "Title";
    private String message = "Message";
    private String date = "-1/-1/-1";
    private int id;

    public TodoModel() {

    }

    public TodoModel(String title, String message, String date, int id) {
        this.title = title;
        this.message = message;
        this.id = id;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void genId() {
        Random rand = new Random();
        id = rand.nextInt();
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ApiConst.TITLE_KEY, title);
        contentValues.put(ApiConst.MESSAGE_KEY, message);
        contentValues.put(ApiConst.ID_KEY, id);
        contentValues.put(ApiConst.DATE_KEY, date);
        return contentValues;
    }
}
