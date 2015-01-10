package hob.psd.todo.bean.form;

import hob.psd.todo.bean.Task;

/**
 * Created by lgunti on 006, Dec 06.
 */
public class ShowTaskForm {
    private Task task;

    private String commentContent;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
