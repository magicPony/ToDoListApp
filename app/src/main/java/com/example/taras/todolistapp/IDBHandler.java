package com.example.taras.todolistapp;

/**
 * Created by Taras on 7/4/2017.
 */

public interface IDBHandler {
    boolean deleteNote(int id);
    boolean addNote(TodoModel todo);
}
