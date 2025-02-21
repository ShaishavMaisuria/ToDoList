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
import android.widget.Toast;

import java.text.DateFormat;
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
    String checkedValue;
    TextView dateCreateTask;
Calendar calendar;
    Date date=null;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        setTitle("Create Task");
        tName=findViewById(R.id.editTextTaskName);
        dateCreateTask=findViewById(R.id.textViewDateValue);
        calendar=Calendar.getInstance();
        taskName=tName.getText().toString();




        RadioGroup radioGroup= findViewById(R.id.radioGroupCreateTask);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonHigh){
                    checkedValue="High";
                }else  if(checkedId==R.id.radioButtonLow){
                    checkedValue="Low";
                }else  if(checkedId==R.id.radioButtonMedium){
                    checkedValue="Medium";
                }else{
                    Log.d("demo","not selected radio group");
                }
            }
        });


        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createTaskIntent = new Intent();
                taskName = tName.getText().toString();
                String dateString = dateCreateTask.getText().toString();

                if(dateString.equals("")){
                    Toast.makeText(CreateTaskActivity.this,"Please Pick a Date",Toast.LENGTH_SHORT).show();
                }else if(taskName.isEmpty()){
                    Toast.makeText(CreateTaskActivity.this,"Please write Name of Task",Toast.LENGTH_SHORT).show();
                } else if (radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(CreateTaskActivity.this,"Please select priority",Toast.LENGTH_SHORT).show();
                   Log.d("demo2", "value" +radioGroup.getCheckedRadioButtonId());
                }
                else {


//                    try {
//                        task = new Task(taskName, new SimpleDateFormat("MM/dd/yyyy").parse(dateString), checkedValue);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }

                    task=new Task(taskName,cal,checkedValue);

                    createTaskIntent.putExtra(Name_key, task);
                    setResult(RESULT_OK, createTaskIntent);
                    Log.d("demo3", " Submit button" +date );
                    finish();
                }
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

                       cal = Calendar.getInstance();
                       cal.setTimeInMillis(0);
                       cal.set(year, month , dayOfMonth, 0, 0, 0);
                       String date1 = new SimpleDateFormat("MM/dd/YYYY").format(cal.getTime());
                       Log.d("date", date1);


                       dateCreateTask.setText(date1);

//
//                       try {
//                           int monthvalue=month+1;
//                           String dateString=  year+"-"+monthvalue +"-"+dayOfMonth;
//                           date=(Date) parser.parse(dateString);
//
//                           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                           date  = format.parse(dateString);
//
//                       } catch (ParseException e) {
//                           e.printStackTrace();
//                       }
//
//                        dateCreateTask=findViewById(R.id.textViewDateValue);
//
//                       dateCreateTask.setText(date.toString());

                   }
               }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

               dialog.show();
            }
        });

    }



}