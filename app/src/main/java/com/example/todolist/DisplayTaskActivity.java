package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DisplayTaskActivity extends AppCompatActivity {
    Task task;
    final static public String display_key ="DisplayTask Acitivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);

        if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().hasExtra(MainActivity.TASK_KEY)){
            task = (Task) getIntent().getSerializableExtra(MainActivity.TASK_KEY);
            Log.d("demo","displayTask"+task.toString());

            TextView tname= findViewById(R.id.textViewTaskName);
            tname.setText(task.taskName.toString());

            TextView date=findViewById(R.id.textViewDate);
            date.setText(task.taskDueDate.toString());

            TextView priority=findViewById(R.id.textViewPriorityInfo);
            priority.setText(task.priority.toString());

        }


        findViewById(R.id.buttonDeleteDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent= new Intent();
                deleteIntent.putExtra(display_key,task);
                setResult(RESULT_OK,deleteIntent);
                Log.d("demo1"," delete button"+task);
                finish();
            }
        });




        findViewById(R.id.buttonCancelDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}