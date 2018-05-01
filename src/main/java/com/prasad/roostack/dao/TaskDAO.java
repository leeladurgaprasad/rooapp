package com.prasad.roostack.dao;

import com.prasad.roostack.dto.TaskDTO;

import java.util.List;

/**
 * Created by lgunti on 016, Nov 16.
 */
public interface TaskDAO {
    public Integer saveTask(TaskDTO taskDTO);
    public TaskDTO getTask(int taskId);
    public TaskDTO updateTask(TaskDTO taskDTO);
    public List<TaskDTO> getTasksByUser(int userId,int offset);
}
