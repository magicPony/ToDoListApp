package com.example.taras.todolistapp.rvAdapterModules;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.todolistapp.IDBHandler;
import com.example.taras.todolistapp.R;
import com.example.taras.todolistapp.ShowTodoFragment;
import com.example.taras.todolistapp.TodoModel;

import java.util.ArrayList;

/**
 * Created by Taras on 7/4/2017.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewHolder>  {

    private String DEBUG_TAG = "TODO_LIST_ADAPTER";

    private ArrayList<TodoModel> mTodos;
    private IDBHandler mDbHandler;
    private FragmentManager mFragmentManager;

    public TodoListAdapter(FragmentManager fragmentManager, IDBHandler dbHandler, ArrayList<TodoModel> todos) {
        super();
        mDbHandler = dbHandler;
        mFragmentManager = fragmentManager;
        mTodos = todos;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_todo_row, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, final int position) {
        final TodoModel todo = mTodos.get(position);

        holder.tvTitle.setText(todo.getTitle());
        holder.tvMessage.setText(todo.getMessage());
        holder.tvDate.setText(todo.getDate());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodos.remove(holder.getPosition());
                mDbHandler.deleteNote(todo.getId());
                notifyItemRemoved(holder.getPosition());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTodoFragment fragment = new ShowTodoFragment(todo);
                mFragmentManager.beginTransaction()
                        .add(R.id.container_AM, fragment)
                        .addToBackStack(todo.getTitle() + Integer.toString(todo.getId()))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    public void addNote(TodoModel todo) {
        mTodos.add(todo);
        notifyItemInserted(getItemCount() - 1);
    }
}
