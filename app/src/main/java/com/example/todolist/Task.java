package com.example.todolist;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    String TaskName;
    String TaskDate;
    Integer priority;

    public Task(String taskName, String taskDate, Integer priority) {
        TaskName = taskName;
        TaskDate = taskDate;
        this.priority = priority;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public void setTaskDate(String taskDate) {
        TaskDate = taskDate;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskDate() {
        return TaskDate;
    }

    public Integer getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task" +
                "TaskName='" + TaskName + '\'' +
                "\n , Date='" + TaskDate + '\'' +
                "\n , priority=" + priority
                ;
    }

public int compareTo(Task T)  {
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    Date date1= null;
    try {
        date1 = format.parse(T.TaskDate.toString());
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Date date2= null;
    try {
        date2 = format.parse(this.TaskDate.toString());
    } catch (ParseException e) {
        e.printStackTrace();
    }

    return date1.compareTo(date2);
}



}
