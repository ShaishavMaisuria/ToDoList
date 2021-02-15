package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CreateTask extends AppCompatActivity {
    final static public String Name_key ="Create Task";
    final static public int REQ_CODE=101;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayTaskIntent= new Intent();
                task= new Task("Hello","10/30/2010",3);
                displayTaskIntent.putExtra(Name_key,task);
                setResult(RESULT_OK,displayTaskIntent);
                Log.d("demo","Create Task");
                finish();
            }
        });



    }
}