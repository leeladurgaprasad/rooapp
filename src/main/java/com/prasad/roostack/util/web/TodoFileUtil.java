package com.prasad.roostack.util.web;

import com.prasad.roostack.bean.TodoFile;
import com.prasad.roostack.manager.TodoFileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Leeladurga Prasad Gunti on 04-Mar-2015 18:32.
 */
@Component
public class TodoFileUtil {

    private final Logger logger = LoggerFactory.getLogger(TodoFileUtil.class);


    public TodoFile saveTodoFile(MultipartFile inputFile){
        Integer fileId = null;
        TodoFile todoFile = null;
        try {
            MultipartFile multipartFile = inputFile;
            byte[] bytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            todoFile = new TodoFile();
            todoFile.setFileContent(bytes);
            todoFile.setFileName(fileName);

            if( null != todoFile ) {
                fileId = todoFileManager.saveTodoFile(todoFile);
                todoFile.setFileId(fileId);
            }

        } catch (Exception ex) {
            logger.error("Unable to save file into database",ex);
        }


        return todoFile;
    }

    @Autowired
    private TodoFileManager todoFileManager;
}
