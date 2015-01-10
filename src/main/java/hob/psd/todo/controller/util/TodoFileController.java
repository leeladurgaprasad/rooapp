package hob.psd.todo.controller.util;

import hob.psd.todo.bean.TodoFile;
import hob.psd.todo.controller.SuperController;
import hob.psd.todo.manager.TodoFileManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import static hob.psd.todo.constants.CommonConstants.*;
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

    @Autowired
    private TodoFileManager todoFileManager;

}
