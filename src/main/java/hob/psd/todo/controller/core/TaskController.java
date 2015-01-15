package hob.psd.todo.controller.core;

import hob.psd.todo.bean.*;
import hob.psd.todo.bean.form.ShowTaskForm;
import hob.psd.todo.bean.form.TimeLineForm;
import hob.psd.todo.controller.SuperController;
import hob.psd.todo.manager.TaskManager;
import hob.psd.todo.mapper.config.TimeLineTaskMapper;
import hob.psd.todo.util.web.SessionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hob.psd.todo.constants.CommonConstants.FORWARD_SLASH;
import static hob.psd.todo.constants.CommonConstants.PATH_PARAM;
import static hob.psd.todo.constants.CommonConstants.RESULT_MESSAGES;
import static hob.psd.todo.constants.URLConstants.*;
import static hob.psd.todo.constants.PageConstants.*;
import static hob.psd.todo.constants.FormConstants.*;
/**
 * Created by lgunti on 002, Dec 02.
 */
@Controller
@RequestMapping(TASK)
public class TaskController extends SuperController {

    @RequestMapping("/show/{taskId}")
    public String showTask(HttpServletRequest request,@PathVariable String taskId,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm, ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        Task task = taskManager.getTask(Integer.valueOf(taskId));
        showTaskForm.setTask(task);
        showResultMessages(session,modelMap);
        return SHOW_TASK_PAGE;
    }

    @RequestMapping("/edit/{taskId}")
    public String editTask(@PathVariable String taskId,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm, ModelMap modelMap) {
        Task task = taskManager.getTask(Integer.valueOf(taskId));
        showTaskForm.setTask(task);
        return EDIT_TASK_PAGE;
    }

    @RequestMapping(method = {RequestMethod.POST}, value ={"/update/{taskId}"})
    public String updateTask(HttpServletRequest request,HttpServletResponse response,
                             @PathVariable String taskId,@ModelAttribute(SHOW_TASK_FORM) ShowTaskForm showTaskForm, ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        Task task = taskManager.getTask(Integer.valueOf(taskId));
        Task updatedTask = showTaskForm.getTask();
        if(null != updatedTask.getTaskName() && !task.getTaskName().equals(updatedTask.getTaskName())) {
            // update notification
            task.setTaskName(updatedTask.getTaskName());
        }
        if(null != updatedTask.getTaskDescription() && !task.getTaskDescription().equals(updatedTask.getTaskDescription())) {
            // update notification
            task.setTaskDescription(updatedTask.getTaskDescription());
        }

        if(task.getPercentageCompleted() != updatedTask.getPercentageCompleted()) {
            task.setPercentageCompleted(updatedTask.getPercentageCompleted());
        }

        if(null != updatedTask.getTags() && !updatedTask.getTags().isEmpty()) {
            task.setTags(updatedTask.getTags());
        }

        task = taskManager.updateTask(task);
        showTaskForm.setTask(task);

        if(null != taskId) {
            resultMessages.successResultMessage("update.task.success", taskId);
            session.setAttribute(RESULT_MESSAGES, resultMessages.getResultMessages());
            request.setAttribute(PATH_PARAM,FORWARD_SLASH+taskId);
        } else {
            resultMessages.errorResultMessage("add.task.failure");
            showResultMessages(resultMessages,modelMap);
            return SHOW_TASK_PAGE;
        }
        return REDIRECT_SHOW_TASK_PAGE;
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
            if(timeLine.getTaskDescription().length() > 25)
            timeLine.setTaskDescription(timeLine.getTaskDescription().substring(0,25)+"...");
            timeLineList.add(timeLine);
        }
        return timeLineList;
    }

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TimeLineTaskMapper timeLineTaskMapper;

    @Autowired
    private ResultMessages resultMessages;
}
