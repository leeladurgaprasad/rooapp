package com.prasad.roostack.manager;

import com.prasad.roostack.bean.Comment;
import com.prasad.roostack.bean.Task;

import java.util.List;

/**
 * Created by lgunti on 016, Nov 16.
 */
public interface TaskManager {
    public Integer saveTask(Task task);
    public Task updateTask(Task task);
    public Task getTask(int taskId);
    public Task addComment(int taskId,Comment comment);
    public List<Task> getTasksByUser(int userId,int offset);
}
