package com.prasad.roostack.dao;

import com.prasad.roostack.dto.TodoFileDTO;

/**
 * Created by lgunti on 008, Dec 08.
 */
public interface TodoFileDAO {
    public Integer saveTodoFile(TodoFileDTO todoFileDTO);
    public TodoFileDTO getTodoFile(int fileId);
}
