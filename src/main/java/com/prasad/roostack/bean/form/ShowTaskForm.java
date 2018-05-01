package com.prasad.roostack.bean.form;

import com.prasad.roostack.bean.Task;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lgunti on 006, Dec 06.
 */
public class ShowTaskForm {
    private Task task;

    private String commentContent;

    private MultipartFile taskFile;

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

    public MultipartFile getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(MultipartFile taskFile) {
        this.taskFile = taskFile;
    }
}
