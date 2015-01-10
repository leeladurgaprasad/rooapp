package hob.psd.todo.manager;

import hob.psd.todo.bean.TodoFile;

/**
 * Created by lgunti on 008, Dec 08.
 */
public interface TodoFileManager {
    public Integer saveTodoFile(TodoFile todoFile);
    public TodoFile getTodoFile(int id);
}
