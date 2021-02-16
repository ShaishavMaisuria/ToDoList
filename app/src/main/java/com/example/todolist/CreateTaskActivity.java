package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTaskActivity extends AppCompatActivity {
    final static public String Name_key ="Create Task";
    final static public int REQ_CODE=101;
    Task task;
    EditText tName;
    String taskName;
    Integer checkedValue;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        tName=findViewById(R.id.editTextTaskName);
        taskName=tName.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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
                Intent createTaskIntent= new Intent();
                taskName=tName.getText().toString();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                task= new Task(taskName,date,checkedValue);



                createTaskIntent.putExtra(Name_key,task);
                setResult(RESULT_OK,createTaskIntent);
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



        findViewById(R.id.buttonSetDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DatePickerDialog dialog = new DatePickerDialog(CreateTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       Log.d("demo", "onDateSet: "+year);
                       Log.d("demo", "onDateSet: "+month);
                       Log.d("demo", "onDateSet: "+dayOfMonth);

                       try {
                           date=format.parse(year+"-"+month+"-"+dayOfMonth);
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                       TextView dateCreateTask=findViewById(R.id.textViewDateValue);

                       dateCreateTask.setText(date.toString());

                   }
               }, 2021, 2, 15);

               dialog.show();
            }
        });

    }



}