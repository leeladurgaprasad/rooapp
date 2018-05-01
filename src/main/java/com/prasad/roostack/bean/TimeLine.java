package com.prasad.roostack.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by lgunti on 024, Dec 24.
 */
public class TimeLine {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private List<User> users;
    private User taskOwner;
    private Date taskDeadLineDate;
    private Date taskAssignedDate;
    private String icon;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(User taskOwner) {
        this.taskOwner = taskOwner;
    }

    public Date getTaskDeadLineDate() {
        return taskDeadLineDate;
    }

    public void setTaskDeadLineDate(Date taskDeadLineDate) {
        this.taskDeadLineDate = taskDeadLineDate;
    }

    public Date getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(Date taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
