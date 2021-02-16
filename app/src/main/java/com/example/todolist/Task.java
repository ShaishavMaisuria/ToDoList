package com.example.todolist;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    String taskName;
    //String taskDate;
    Integer priority;
    Date taskDueDate;

    public Task(String taskName, Date taskDueDate, Integer priority) {
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
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
        return "TaskName='" + taskName + '\'' +
                "\n  Date='" + taskDueDate + '\'' +
                "\n \t \t priority=" + priority
                ;
    }

    public int compareTo(Task T) {
        if(this.taskDueDate.before((T.taskDueDate))){
            return 1;
        }

        if(this.taskDueDate.after(T.taskDueDate)){
            return  -1;
        }

        return  0;
    }


}
