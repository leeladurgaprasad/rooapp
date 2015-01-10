package hob.psd.todo.controller.core;

import hob.psd.todo.bean.Comment;
import hob.psd.todo.bean.Task;
import hob.psd.todo.bean.TimeLine;
import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.ShowTaskForm;
import hob.psd.todo.bean.form.TimeLineForm;
import hob.psd.todo.manager.TaskManager;
import hob.psd.todo.mapper.config.TimeLineTaskMapper;
import hob.psd.todo.util.web.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hob.psd.todo.constants.URLConstants.*;
import static hob.psd.todo.constants.PageConstants.*;
import static hob.psd.todo.constants.FormConstants.*;
/**
 * Created by lgunti on 002, Dec 02.
 */
@Controller
@RequestMapping(TASK)
public class TaskController {

    @RequestMapping("/show/{taskId}")
    public String showTask(@PathVariable String taskId,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm, ModelMap modelMap) {
        Task task = taskManager.getTask(Integer.valueOf(taskId));
        showTaskForm.setTask(task);
        return SHOW_TASK_PAGE;
    }

    @RequestMapping("/edit/{taskId}")
    public String editTask(@PathVariable String taskId,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm, ModelMap modelMap) {
        Task task = taskManager.getTask(Integer.valueOf(taskId));
        showTaskForm.setTask(task);
        return EDIT_TASK_PAGE;
    }


    @RequestMapping(method = {RequestMethod.POST}, value = {"{taskId}/addcomment"})
    public String addComment(@PathVariable String taskId, HttpServletRequest request,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm) throws IOException{

        Comment comment = new Comment();
        User commentBy = SessionUtil.getUser(request);
        comment.setComment(showTaskForm.getCommentContent());
        comment.setCommentedBy(commentBy);
        comment.setCommentedTime(new Date());
        Task task = taskManager.addComment(Integer.valueOf(taskId), comment);
        showTaskForm.setTask(task);
        /*ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponse = jsonWriter.writeValueAsString(task.getComments());*/
        return SHOW_COMMENTS_PAGE;
    }


    @RequestMapping(method = {RequestMethod.GET},value = {TIME_LINE})
    public String taskTimeLine(HttpServletRequest request,@ModelAttribute("timeLineForm") TimeLineForm timeLineForm) {
        List<TimeLine> timeLineList = getTimeLineList(request,0);
        timeLineForm.setNextPixelFrom(timeLineForm.getNumberOfPixelsLoad());
        timeLineForm.setTimeLineList(timeLineList);
        return TASK_TIME_LINE_PAGE;
    }

    @RequestMapping(method = {RequestMethod.POST},value = {"/getTimeLinePixels"})
    public String getTimeLinePixels(HttpServletRequest request,@ModelAttribute("timeLineForm") TimeLineForm timeLineForm) {
        List<TimeLine> timeLineList = getTimeLineList(request,timeLineForm.getNextPixelFrom());
        timeLineForm.setTimeLineList(timeLineList);
        return TASK_TIME_LINE_PIXELS_PAGE;
    }

    private List<TimeLine> getTimeLineList(HttpServletRequest request,int offset) {
        List<Task> tasks;
        List<TimeLine> timeLineList = new ArrayList<TimeLine>();
        User sessionUser = SessionUtil.getUser(request);
        tasks = taskManager.getTasksByUser(sessionUser.getUserId(),offset);
        for(Task task : tasks) {
            TimeLine timeLine = timeLineTaskMapper.map(task,TimeLine.class);
            timeLine.setIcon("icon-file-text time-icon bg-info");
            timeLine.setTaskDescription(timeLine.getTaskDescription().substring(0,25)+"...");
            timeLineList.add(timeLine);
        }
        return timeLineList;
    }

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TimeLineTaskMapper timeLineTaskMapper;
}
