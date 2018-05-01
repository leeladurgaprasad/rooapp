package com.prasad.roostack.controller.util;

import com.prasad.roostack.bean.TodoFile;
import com.prasad.roostack.bean.form.FileForm;
import com.prasad.roostack.controller.SuperController;
import com.prasad.roostack.manager.TodoFileManager;
import com.prasad.roostack.util.web.TodoFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import static com.prasad.roostack.constants.CommonConstants.*;
/**
 * Created by lgunti on 009, Dec 09.
 */
@Controller
@RequestMapping("/file")
public class TodoFileController extends SuperController {


    private final Logger logger = LoggerFactory.getLogger(TodoFileController.class);


    @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
    private String getFile(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!NumberUtils.isNumber(fileId)) {
                return null;
            }

            Integer fileIdInt = Integer.parseInt(fileId);
            TodoFile todoFile = null;
            if (fileIdInt == 0) {
                String realPath = request.getSession().getServletContext().getRealPath("/");
                realPath = realPath.replace(BACKWARD_SLASH, FORWARD_SLASH);
                File defaultFile = new File(realPath + DEFAULT_IMAGE_PATH);
                byte fileContent[] = FileUtils.readFileToByteArray(defaultFile);
                todoFile = new TodoFile();
                todoFile.setFileContent(fileContent);
            } else {
                todoFile = todoFileManager.getTodoFile(fileIdInt);
            }
            response.setContentType("application/octet-stream");
            response.setContentLength(todoFile.getFileContent().length);
            response.setHeader("Content-Disposition", "attachment; filename=" + todoFile.getFileName());


            FileCopyUtils.copy(todoFile.getFileContent(), response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Exception ex) {
            logger.error("unable to get file ", ex);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String putFile(HttpServletRequest request, @ModelAttribute("todoFileForm") FileForm fileForm) {
        try {
            todoFileUtil.saveTodoFile(fileForm.getTodoFile());
        } catch (Exception ex) {
            logger.error("unable to save file into database..", ex);
        }
        return "ok";
    }

    @Autowired
    private TodoFileManager todoFileManager;

    @Autowired
    private TodoFileUtil todoFileUtil;

}
