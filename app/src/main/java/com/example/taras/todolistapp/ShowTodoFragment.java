package com.example.taras.todolistapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Taras on 7/4/2017.
 */

@SuppressLint("ValidFragment")
public class ShowTodoFragment extends Fragment {

    private TodoModel mTodo;

    @SuppressLint("ValidFragment")
    public ShowTodoFragment(TodoModel todo) {
        super();
        mTodo = todo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        TextView tvMessage, tvTitle;
        view = inflater.inflate(R.layout.layout_show_todo, container, false);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_LST);
        tvMessage = (TextView) view.findViewById(R.id.tv_message_LST);
        tvTitle.setText(mTodo.getTitle());
        tvMessage.setText(mTodo.getMessage());
        return view;
    }
}
