package com.example.taras.todolistapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.taras.todolistapp.rvAdapterModules.TodoListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IDBHandler {

    private static final String DEBUG_TAG = "MainActivity";

    private DBHelper mDbHelper;
    private TodoListAdapter mTodoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new DBHelper(this, ApiConst.DB_NAME);
        initUI();
    }

    private void initUI() {
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fab_create_AM);
        fabAdd.setOnClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_AM);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        mTodoListAdapter = new TodoListAdapter(getSupportFragmentManager(), this, mDbHelper.get());
        recyclerView.setAdapter(mTodoListAdapter);
        mTodoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        CreateFragment fragment = new CreateFragment(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_AM, fragment)
                .addToBackStack(ApiConst.CREATE_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public boolean deleteNote(int id) {
        return mDbHelper.delete(id);
    }

    @Override
    public boolean addNote(TodoModel todo) {
        if (mDbHelper.insert(todo.toContentValues())) {
            if (mTodoListAdapter != null) {
                mTodoListAdapter.addNote(todo);
            }

            return true;
        }

        return false;
    }
}
