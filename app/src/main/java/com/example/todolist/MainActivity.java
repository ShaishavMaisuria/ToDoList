package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    final static public int REQ_CODE = 100;
    final static public int REQ_CODE_Create_task = 101;
    ArrayList<Task> tasksList;

    TextView taskNumber;
    String[] taskNames;
    final static public String TASK_KEY = "TASK_KEY";
    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//                    String taskname = "Compelete Hw 1";
//                    String date = "2021-1-1";
//                    Integer priority = 0;

        tasksList = new ArrayList<>();

        format = new SimpleDateFormat("yyyy-MM-dd");



        taskNumber = findViewById(R.id.textViewNumberTasks);

        taskNumber.setText("You have " + tasksList.size() + " tasks");

        findViewById(R.id.buttonViewTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remaing tasks");

                if (tasksList.size() > 0) {
                    taskNames = new String[tasksList.size()];

                    for (int i = 0; i < tasksList.size(); i++) {
                        taskNames[i] = tasksList.get(i).getTaskName();
                    }

                    builder.setItems(taskNames, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Task selectedTask = tasksList.get(which);
                            Intent intent = new Intent(MainActivity.this, DisplayTaskActivity.class);
                            intent.putExtra(TASK_KEY, selectedTask);
                            startActivityForResult(intent, REQ_CODE);
                        }
                    });
                    builder.create().show();
                } else {

                }


            }
        });


        findViewById(R.id.buttonCreateTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
                startActivityForResult(intent, REQ_CODE_Create_task);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("demo", "mainActivity result ");
        if (resultCode == RESULT_OK) {
            Log.d("demo", " result okay ");

            if (requestCode == REQ_CODE_Create_task && data != null && data.hasExtra(CreateTaskActivity.Name_key)) {

                Task taskInfo = (Task) data.getSerializableExtra(CreateTaskActivity.Name_key);

                tasksList.add(taskInfo);
                Log.d("demo", String.valueOf(tasksList.size()));

                try {
                    Collections.sort(tasksList, Task::compareTo);
                    Log.d("task", tasksList.toString());
                } catch (Exception e) {
                    Log.d("demo", e.toString());
                }

                Log.d("demo", String.valueOf(tasksList.size()));


                taskNumber.setText("You have " + tasksList.size() + " tasks");


                TextView taskListView = findViewById(R.id.textViewTaskList);
                taskListView.setText(tasksList.get(tasksList.size() - 1).toString());


                Log.d("demo", "data" + taskInfo);

            }

            if (requestCode == REQ_CODE && data != null && data.hasExtra(DisplayTaskActivity.display_key)) {
                Task taskDelete = (Task) data.getSerializableExtra(DisplayTaskActivity.display_key);
                for (int i = 0; i < tasksList.size(); i++) {

                    Log.d("demo1", " Display going taskslist" + tasksList.toString());
                    if (taskDelete.getTaskName().equalsIgnoreCase(tasksList.get(i).getTaskName())) {

                        tasksList.remove(i);
                        Log.d("demo1", " Display going int" + tasksList.size());
                        taskNumber.setText("You have " + tasksList.size() + " tasks");


                        TextView taskListView = findViewById(R.id.textViewTaskList);
                        taskListView.setText(tasksList.get(tasksList.size() - 1).toString());

                    }

                }


            }

        } else if (resultCode == RESULT_CANCELED) {
            Log.d("demo", " result canceled ");
        }


    }
}