package com.example.taras.todolistapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Taras on 7/4/2017.
 */

public class CreateFragment extends Fragment {

    private IDBHandler mDbHandler;

    public CreateFragment(IDBHandler dbHandler) {
        super();
        mDbHandler = dbHandler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create, container, false);
        final EditText etTitle, etMessage;
        etTitle = (EditText) view.findViewById(R.id.et_title_LC);
        etMessage = (EditText) view.findViewById(R.id.et_message_LC);

        view.findViewById(R.id.btn_ok_LC).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                TodoModel todo = new TodoModel();
                todo.genId();

                Calendar c = Calendar.getInstance();
                todo.setDate(c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));

                if (!TextUtils.isEmpty(etTitle.getText())) {
                    todo.setTitle(etTitle.getText().toString());
                }

                if (!TextUtils.isEmpty(etMessage.getText())) {
                    todo.setMessage(etMessage.getText().toString());
                }

                mDbHandler.addNote(todo);
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}
