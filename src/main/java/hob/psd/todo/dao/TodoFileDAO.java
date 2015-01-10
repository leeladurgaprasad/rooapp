package hob.psd.todo.dao;

import hob.psd.todo.dto.TodoFileDTO;

/**
 * Created by lgunti on 008, Dec 08.
 */
public interface TodoFileDAO {
    public Integer saveTodoFile(TodoFileDTO todoFileDTO);
    public TodoFileDTO getTodoFile(int fileId);
}
