package hob.psd.todo.bean.form;

import hob.psd.todo.bean.User;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lgunti on 016, Nov 16.
 */
public class AddTaskForm {
    private String taskName;
    private String taskDescription;
    private List<User> users;
    private LinkedHashMap<Integer,String> usersList;
    private User taskOwner;
    private String taskDeadLineDate;
    private List<String> tags;

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

    public LinkedHashMap<Integer, String> getUsersList() {
        return usersList;
    }

    public void setUsersList(LinkedHashMap<Integer, String> usersList) {
        this.usersList = usersList;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public User getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(User taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskDeadLineDate() {
        return taskDeadLineDate;
    }

    public void setTaskDeadLineDate(String taskDeadLineDate) {
        this.taskDeadLineDate = taskDeadLineDate;
    }
}
