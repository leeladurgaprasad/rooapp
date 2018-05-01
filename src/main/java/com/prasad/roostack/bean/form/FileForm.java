package com.prasad.roostack.bean.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Leeladurga Prasad Gunti on 09-Mar-2015 16:54.
 */
public class FileForm {
    private MultipartFile todoFile;
    private String fileAccessLevel;

    public MultipartFile getTodoFile() {
        return todoFile;
    }

    public void setTodoFile(MultipartFile todoFile) {
        this.todoFile = todoFile;
    }

    public String getFileAccessLevel() {
        return fileAccessLevel;
    }

    public void setFileAccessLevel(String fileAccessLevel) {
        this.fileAccessLevel = fileAccessLevel;
    }
}
