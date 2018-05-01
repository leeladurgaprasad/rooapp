package com.prasad.roostack.dao.impl;


import static com.prasad.roostack.constants.CommonConstants.*;
import com.prasad.roostack.dao.TaskDAO;
import com.prasad.roostack.dto.TagDTO;
import com.prasad.roostack.dto.TaskDTO;
import com.prasad.roostack.dto.UserDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgunti on 016, Nov 16.
 */
@Repository("taskDAOImpl")
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public Integer saveTask(TaskDTO taskDTO) {
        Integer identifier = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            for(int i=0 ; i < taskDTO.getTags().size(); i++) {
                TagDTO tagDTO = taskDTO.getTags().get(i);
                TagDTO data = (TagDTO) session.createCriteria(TagDTO.class).add(Restrictions.eq("tagName", tagDTO.getTagName())).uniqueResult();
                if(null == data) {
                    taskDTO.getTags().set(i,tagDTO);
                } else {
                    taskDTO.getTags().set(i,data);
                }
            }
            session.beginTransaction();
            for(int i = 0 ; i < taskDTO.getUsers().size() ; i++) {
                UserDTO userDTO = taskDTO.getUsers().get(i);
                userDTO = (UserDTO) session.get(UserDTO.class,userDTO.getUserId());
                //userDTO.getTasks().add(taskDTO);
                taskDTO.getUsers().set(i,userDTO);
            }
            UserDTO taskOwner = taskDTO.getTaskOwner();
            taskOwner= (UserDTO) session.get(UserDTO.class,taskOwner.getUserId());
            taskDTO.setTaskOwner(taskOwner);
            identifier = (Integer) session.save(taskDTO);
            return identifier;
        } catch (Exception ex) {
            logger.error("unable to save task ", ex);
        }
        return null;
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        try {
            Session session = sessionFactory.getCurrentSession();
            for(int i=0 ; i < taskDTO.getTags().size(); i++) {
                TagDTO tagDTO = taskDTO.getTags().get(i);
                TagDTO data = (TagDTO) session.createCriteria(TagDTO.class).add(Restrictions.eq("tagName", tagDTO.getTagName())).uniqueResult();
                if(null == data) {
                    taskDTO.getTags().set(i,tagDTO);
                } else {
                    taskDTO.getTags().set(i,data);
                }
            }
            session.beginTransaction();
            session.update(taskDTO);
            return taskDTO;
        } catch (Exception ex) {
            logger.error("unable to update task ",ex);
        }
        return null;
    }

    @Override
    public TaskDTO getTask(int taskId) {
        TaskDTO taskDTO = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            taskDTO = (TaskDTO) session.get(TaskDTO.class,taskId);
        } catch (Exception ex) {
            logger.error("unable to get task ",ex);
        }
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getTasksByUser(int userId,int offset) {
        List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            UserDTO userDTO = (UserDTO)session.get(UserDTO.class,userId);
            Criteria criteria = session.createCriteria(TaskDTO.class,"task");

            criteria.createAlias("users", "user");
            criteria.add(Restrictions.like("user.userId", userId) );
            criteria.addOrder(Order.desc("taskDeadLineDate"));
            criteria.setMaxResults(NUMBER_OF_PIXELS_LOAD);
            criteria.setFirstResult(offset);
            taskDTOs = criteria.list();
        } catch (Exception ex) {

        }
        return taskDTOs;
    }
}
