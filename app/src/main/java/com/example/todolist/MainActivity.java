package com.example.todolist;
/*
* @author Shaishav Maisuria
* 
 */
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    final static public int REQ_CODE_Display_task = 100;
    final static public int REQ_CODE_Create_task = 101;
    ArrayList<Task> tasksList;
    TextView taskListView;
    TextView taskNumber;
    String[] taskNames;
    final static public String TASK_KEY = "TASK_KEY";
    SimpleDateFormat format;
    TextView taskNameView;
    TextView taskDateView;
    TextView taskPriorityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksList = new ArrayList<>();
//        format = new SimpleDateFormat("MM/dd/yyyy");
        taskNumber = findViewById(R.id.textViewNumberTasks);
//       taskNumber.setText("You have " + tasksList.size() + " tasks");
        taskListView = findViewById(R.id.textViewTaskList);
        taskNameView=findViewById(R.id.textViewTaskNameMain);
        taskDateView=findViewById(R.id.textViewDateMain);
        taskPriorityView=findViewById(R.id.textViewPriorityMain);
        updateListView();



        findViewById(R.id.buttonViewTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remaining tasks").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                if (tasksList.size() > 0) {

                    try {
                        Collections.sort(tasksList, new Comparator<Task>() {
                            @Override
                            public int compare(Task o1, Task o2) {
                                return o1.gettaskDueDate().compareTo(o2.gettaskDueDate());
                            }
                        });
                        Log.d("task", tasksList.toString());
                    } catch (Exception e) {
                        Log.d("demo", e.toString());
                    }

                        taskNames = new String[tasksList.size()];

                        for (int i = 0; i < tasksList.size(); i++) {
                            taskNames[i] = tasksList.get(i).getTaskName();
                            Log.d("demo1",taskNames[i]);
                        }
//                        for(int i=tasksList.size()-1;i>=0;i--){
//                            taskNames[i] = tasksList.get(i).getTaskName();
//                        }

                        builder.setItems(taskNames, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Task selectedTask = tasksList.get(which);
                                Intent intent = new Intent(MainActivity.this, DisplayTaskActivity.class);
                                intent.putExtra(TASK_KEY, selectedTask);
                                startActivityForResult(intent, REQ_CODE_Display_task);
                            }
                        });



                        builder.create().show();
                } else {

                    Toast.makeText(MainActivity.this,"Please Create New Task",Toast.LENGTH_LONG).show();
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

                try {
                    Collections.sort(tasksList, new Comparator<Task>() {
                        @Override
                        public int compare(Task o1, Task o2) {
                            return o1.gettaskDueDate().compareTo(o2.gettaskDueDate());
                        }
                    });
                    Log.d("task", tasksList.toString());
                } catch (Exception e) {
                    Log.d("demo", e.toString());
                }

                updateListView();

            }

            if (requestCode == REQ_CODE_Display_task && data != null && data.hasExtra(DisplayTaskActivity.display_key)) {
                Task taskDelete = (Task) data.getSerializableExtra(DisplayTaskActivity.display_key);
                for (int i = 0; i < tasksList.size(); i++) {

                    Log.d("demo1", " Display going taskslist" + tasksList.toString());
                    if ( (taskDelete.getTaskName().equalsIgnoreCase(tasksList.get(i).getTaskName())) && (taskDelete.gettaskDueDate().toString().equalsIgnoreCase(tasksList.get(i).taskDueDate.toString())) &&
                            (taskDelete.priority.equalsIgnoreCase(tasksList.get(i).priority))) {

                        tasksList.remove(i);
                        updateListView();

                    }

                }


            }

        } else if (resultCode == RESULT_CANCELED) {
            Log.d("demo", " result canceled ");
        }

    }

    private void updateListView(){
        Log.d("demo1", " Display going int" + tasksList.size());

        if(tasksList.size()>0) {

            taskNumber.setText("You have " + tasksList.size() + " tasks");


            taskDateView.setText(tasksList.get(0).convertedDate());
            taskNameView.setText(tasksList.get(0).taskName);
            taskPriorityView.setText(tasksList.get(0).priority);
        }else{
            taskNumber.setText("You have " + tasksList.size() + " tasks");
            taskDateView.setText("");
            taskNameView.setText("None");
            taskPriorityView.setText("");


        }
    }

}