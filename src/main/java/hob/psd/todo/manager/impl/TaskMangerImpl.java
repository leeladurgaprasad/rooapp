package hob.psd.todo.manager.impl;

import hob.psd.todo.bean.Comment;
import hob.psd.todo.bean.Task;
import hob.psd.todo.dao.TaskDAO;
import hob.psd.todo.dao.UserDAO;
import hob.psd.todo.dto.CommentDTO;
import hob.psd.todo.dto.TaskDTO;
import hob.psd.todo.dto.UserDTO;
import hob.psd.todo.manager.TaskManager;
import hob.psd.todo.manager.UserManager;
import hob.psd.todo.mapper.config.CommentDTOMapper;
import hob.psd.todo.mapper.config.TaskDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgunti on 016, Nov 16.
 */
@Service("taskManager")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class TaskMangerImpl implements TaskManager {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer saveTask(Task task) {
        TaskDTO taskDTO = taskDTOMapper.map(task,TaskDTO.class);
        //taskDTO.setUsers(retrieveUserDTOs(taskDTO));
        Integer taskId = taskDAO.saveTask(taskDTO);
        return taskId;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Task updateTask(Task task) {
        TaskDTO taskDTO = taskDTOMapper.map(task,TaskDTO.class);
        taskDTO = taskDAO.updateTask(taskDTO);
        task = taskDTOMapper.map(taskDTO,Task.class);
        return task;
    }

    @Override
    public Task getTask(int taskId) {
        TaskDTO taskDTO = taskDAO.getTask(taskId);
        Task task = taskDTOMapper.map(taskDTO,Task.class);
        return task;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Task addComment(int taskId, Comment comment) {
        TaskDTO taskDTO = taskDAO.getTask(taskId);
        CommentDTO commentDTO = commentDTOMapper.map(comment,CommentDTO.class);
        UserDTO commentedBy = userDAO.getUserByUserId(commentDTO.getCommentedBy().getUserId());
        commentDTO.setCommentedBy(commentedBy);
        taskDTO.getComments().add(commentDTO);
        taskDTO = taskDAO.updateTask(taskDTO);
        Task task = taskDTOMapper.map(taskDTO,Task.class);
        return task;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private List<UserDTO> retrieveUserDTOs(TaskDTO taskDTO) {
        List<UserDTO> retrievedUserDTOs = new ArrayList<UserDTO>();
        for(UserDTO userDTO: taskDTO.getUsers()) {
            UserDTO retrievedUserDTO = new UserDTO();
            retrievedUserDTO = userDAO.getUserByUserId(userDTO.getUserId());
            retrievedUserDTO.getTasks().add(taskDTO);
            retrievedUserDTOs.add(retrievedUserDTO);
        }
        return retrievedUserDTOs;
    }

    @Override
    public List<Task> getTasksByUser(int userId,int offset) {
        List<TaskDTO> taskDTOs = taskDAO.getTasksByUser(userId,offset);
        List<Task> tasks = new ArrayList<Task>();
        for(TaskDTO taskDTO: taskDTOs) {
            tasks.add(taskDTOMapper.map(taskDTO,Task.class));
        }
        return tasks;
    }

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TaskDTOMapper taskDTOMapper;

    @Autowired
    private UserManager userManager;

    @Autowired
    private CommentDTOMapper commentDTOMapper;
}
