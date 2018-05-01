package com.prasad.roostack.dto;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lgunti on 016, Nov 16.
 */
/*@Entity
@Table(name="tasks")*/
public class TaskDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taskId")
    private int taskId;

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "taskDescription", columnDefinition="text")
    private String taskDescription;

    @ManyToMany(mappedBy = "tasks" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserDTO> users = new ArrayList<UserDTO>();

    @ManyToOne
    private UserDTO taskOwner;

    @Column(name="taskDeadLineDate")
    private Date taskDeadLineDate;

    @Column(name="taskAssignedDate")
    private Date taskAssignedDate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable( name = "task_comments",joinColumns = { @JoinColumn(name = "taskId") }, inverseJoinColumns = { @JoinColumn(name = "commentId") })
    private List<CommentDTO> comments = new ArrayList<CommentDTO>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable( name = "task_tags",joinColumns = { @JoinColumn(name = "taskId") }, inverseJoinColumns = { @JoinColumn(name = "tagId") })
    private List<TagDTO> tags = new ArrayList<TagDTO>();

    @Column(name="percentageCompleted")
    private int percentageCompleted;

    @CollectionOfElements
    private Set<Integer> taskFiles = new HashSet<Integer>();

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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public UserDTO getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(UserDTO taskOwner) {
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public int getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(int percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    public Set<Integer> getTaskFiles() {
        return taskFiles;
    }

    public void setTaskFiles(Set<Integer> taskFiles) {
        this.taskFiles = taskFiles;
    }
}
