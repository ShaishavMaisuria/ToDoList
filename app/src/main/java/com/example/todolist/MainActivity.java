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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
final static public int REQ_CODE=100;
    ArrayList<Task> tasksActivity;
    List<CharSequence> charSequenceList;
    CharSequence[] c;
    String[] Names={"ram","ravi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String taskname="Compelete Hw 1";
        String date="2021-1-1";
        Integer priority=0;

        tasksActivity = new ArrayList<>();

        tasksActivity.add(new Task("Hw1","2021-9-3",priority));
        charSequenceList= new ArrayList<>();
        charSequenceList.add(new String("hw1"));


        findViewById(R.id.buttonViewTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remaing tasks");
                builder.setItems(Names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", Names[which]);
                        Intent intent = new Intent(MainActivity.this, DisplayTask.class);
                        startActivityForResult(intent,REQ_CODE);

                    }
                });
builder.create().show();

            }
        });





        findViewById(R.id.buttonCreateTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTask.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("demo","mainActivity result ");
        if(resultCode==RESULT_OK){
            Log.d("demo"," result okay ");

            if(data!=null && data.hasExtra(CreateTask.Name_key)){

               Task taskInfo=(Task) data.getSerializableExtra(CreateTask.Name_key);
                tasksActivity.add(new Task(taskInfo.TaskName,taskInfo.TaskDate,taskInfo.priority));
                Log.d("demo", String.valueOf(tasksActivity.size()));

                try {
                    Collections.sort(tasksActivity, Task::compareTo);
                }catch (Exception e){
                    Log.d("demo",e.toString());
                }

                Log.d("demo", String.valueOf(tasksActivity.size()));
                TextView taskNumber = findViewById(R.id.textViewNumberTasks);

                taskNumber.setText("You have "+tasksActivity.size()+" tasks");



                TextView taskListView =findViewById(R.id.textViewTaskList);
                taskListView.setText(tasksActivity.get(tasksActivity.size()-1).toString());






                Log.d("demo","data"+taskInfo);
                if(taskInfo != null){
                    Log.d("demo", String.valueOf(taskInfo));
                }

            }
        }else if(resultCode==RESULT_CANCELED){
            Log.d("demo"," result canceled ");
        }
    }
}