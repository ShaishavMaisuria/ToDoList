package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CreateTask extends AppCompatActivity {
    final static public String Name_key ="Create Task";
    final static public int REQ_CODE=101;
    Task task;
    EditText tName;
    String taskName;
    Integer checkedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        tName=findViewById(R.id.editTextTaskName);
        taskName=tName.getText().toString();

        RadioGroup radioGroup= findViewById(R.id.radioGroupCreateTask);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonHigh){
                    checkedValue=3;
                }else  if(checkedId==R.id.radioButtonLow){
                    checkedValue=1;
                }else  if(checkedId==R.id.radioButtonMedium){
                    checkedValue=2;
                }else{
                    Log.d("demo","not selected radio group");
                }
            }
        });


        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayTaskIntent= new Intent();
                taskName=tName.getText().toString();

                task= new Task(taskName,"10/30/2010",checkedValue);
                displayTaskIntent.putExtra(Name_key,task);
                setResult(RESULT_OK,displayTaskIntent);
                Log.d("demo"," Submit button"+task);
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                Log.d("demo"," button cancel ");
                finish();
            }
        });


    }
}