package hob.psd.todo.manager.impl;

import hob.psd.todo.bean.TodoFile;
import hob.psd.todo.dao.TodoFileDAO;
import hob.psd.todo.dto.TodoFileDTO;
import hob.psd.todo.manager.TodoFileManager;
import hob.psd.todo.mapper.config.TodoFileDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lgunti on 008, Dec 08.
 */
@Service("todoFileManager")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class TodoFileManagerImpl implements TodoFileManager{

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer saveTodoFile(TodoFile todoFile) {
        TodoFileDTO todoFileDTO = todoFileDTOMapper.map(todoFile,TodoFileDTO.class);
        Integer todoFileId = todoFileDAO.saveTodoFile(todoFileDTO);
        return todoFileId;
    }

    @Override
    public TodoFile getTodoFile(int id) {
        TodoFileDTO todoFileDTO = todoFileDAO.getTodoFile(id);
        TodoFile todoFile = todoFileDTOMapper.map(todoFileDTO,TodoFile.class);
        return todoFile;
    }

    @Autowired
    private TodoFileDAO todoFileDAO;

    @Autowired
    private TodoFileDTOMapper todoFileDTOMapper;

}
