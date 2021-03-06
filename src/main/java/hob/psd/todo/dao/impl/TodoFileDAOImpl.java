package hob.psd.todo.dao.impl;

import hob.psd.todo.dao.TodoFileDAO;
import hob.psd.todo.dto.TodoFileDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lgunti on 008, Dec 08.
 */
@Repository("todoFileDAOImpl")
public class TodoFileDAOImpl implements TodoFileDAO {

    private Logger logger = LoggerFactory.getLogger(TodoFileDAOImpl.class);

    @Override
    public Integer saveTodoFile(TodoFileDTO todoFileDTO) {
        Integer todoFileId = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            todoFileId = (Integer) session.save(todoFileDTO);

        } catch (Exception ex) {
            logger.error("unable to save todo File "+todoFileDTO.getFileName(), ex);
        }
        return todoFileId;
    }

    @Override
    public TodoFileDTO getTodoFile(int fileId) {
        TodoFileDTO todoFileDTO = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            todoFileDTO = (TodoFileDTO) session.get(TodoFileDTO.class,fileId);

        } catch (Exception ex) {
            logger.error("unable to get file ",ex);
        }
        return todoFileDTO;
    }

    @Autowired
    private SessionFactory sessionFactory;
}
