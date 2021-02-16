package com.example.todolist;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    String taskName;
    //String taskDate;
    Integer priority;
    Date taskDueDate;

    public Task(String taskName, Date taskDate, Integer priority) {
        this.taskName = taskName;


        try {
            this.taskDueDate =  new SimpleDateFormat("MM/dd/yyyy").parse(taskDate.toString());
            Log.d("taskdate","date= "+this.taskDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date gettaskDueDate() {
        return taskDueDate;
    }

    public void settaskDueDate(Date taskDate) {
        this.taskDueDate = taskDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {



                    return "TaskName= " + taskName + '\'' +
                    "\nDate= " +     taskDueDate+                    "\n \t \t \t \t \t \t \t \t                \t \t \t   priority=" + priority
                    ;


    }



}
