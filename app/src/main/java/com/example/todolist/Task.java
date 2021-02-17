package com.example.todolist;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task implements Serializable {
    String taskName;
    //String taskDate;
    String priority;
    Calendar taskDueDate;

    public Task(String taskName, Calendar taskDate, String priority) {
        this.taskName = taskName;

        this.taskDueDate=taskDate;
        SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy");


        Log.d("taskdate","date= "+this.taskDueDate);
//
//        try {
//            this.taskDueDate =  new SimpleDateFormat("MM/dd/yyyy").parse(taskDate.toString());
//            Log.d("taskdate","date= "+this.taskDueDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Calendar gettaskDueDate() {
        return taskDueDate;
    }

    public void settaskDueDate(Calendar taskDate) {
        this.taskDueDate = taskDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String convertedDate(){String date1 = new SimpleDateFormat("MM/dd/YYYY").format(this.taskDueDate.getTime()); return date1.toString(); }
    @Override
    public String toString() {

        String date1 = new SimpleDateFormat("MM/dd/YYYY").format(taskDueDate.getTime());

                    return "TaskName= " + taskName +
                    "\nDate= " +     date1+                    "\n \t \t \t \t \t \t \t \t                \t \t \t   priority=" + priority
                    ;


    }



}
