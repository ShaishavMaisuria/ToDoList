package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DisplayTask extends AppCompatActivity {
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);

        if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().hasExtra(MainActivity.Name_key)){
            Task task = (Task) getIntent().getSerializableExtra(MainActivity.Name_key);
            Log.d("demo","displayTask"+task.toString());

            TextView tname= findViewById(R.id.textViewTaskName);
            tname.setText(task.TaskName.toString());

            TextView date=findViewById(R.id.textViewDate);
            date.setText(task.TaskDate.toString());

            TextView priority=findViewById(R.id.textViewPriorityInfo);
            priority.setText(task.priority.toString());

        }





        findViewById(R.id.buttonCancelDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}