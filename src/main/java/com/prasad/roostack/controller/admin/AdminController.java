package com.prasad.roostack.controller.admin;

import static com.prasad.roostack.constants.FormConstants.*;
import static com.prasad.roostack.constants.PageConstants.*;
import static com.prasad.roostack.constants.CommonConstants.*;
import static com.prasad.roostack.constants.URLConstants.*;
import static javax.servlet.http.HttpServletResponse.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prasad.roostack.bean.*;
import com.prasad.roostack.bean.form.AddTaskForm;
import com.prasad.roostack.bean.form.AddUserForm;
import com.prasad.roostack.config.MessageConfig;
import com.prasad.roostack.constants.MessageType;
import com.prasad.roostack.controller.SuperController;
import com.prasad.roostack.manager.TaskManager;
import com.prasad.roostack.manager.TodoFileManager;
import com.prasad.roostack.manager.UserManager;
import com.prasad.roostack.mapper.config.AddTaskFormTaskMapper;
import com.prasad.roostack.mapper.config.AddUserFormUserMapper;
import com.prasad.roostack.util.web.ControlUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends SuperController{


    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ModelAttribute(ADD_USER_FORM)
    public AddUserForm buildAddUserForm(){
        return populateAddUserForm();
    }

    @ModelAttribute(ADD_TASK_FORM)
    public AddTaskForm buildAddTaskForm(){
        return populateAddTaskForm();
    }




    /**
     * Method to show addUser form
     * @param addUserForm
     * @param modelMap
     * @param request
     * @param session
     * @return
     *//*
	@RequestMapping(method = { RequestMethod.GET }, value = { ADD_USER })
	public String addUserShowForm(
			@ModelAttribute(ADD_USER_FORM) AddUserForm addUserForm,
			ModelMap modelMap, HttpServletRequest request, HttpSession session) {

        showResultMessages(session,modelMap);
		return ADD_USER_PAGE;
	}*/

    /**
     * Method to show addTask form
     * @param addTaskForm
     * @param modelMap
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(method={RequestMethod.GET},value = {ADD_TASK})
    public String addTaskShowForm(@ModelAttribute(ADD_TASK_FORM) AddTaskForm addTaskForm,
                                  ModelMap modelMap, HttpServletRequest request,HttpSession session) {
        showResultMessages(session,modelMap);
        String todayDate = new SimpleDateFormat(DEFAULT_DATE_PATTEN).format(new Date());
        addTaskForm.setTaskDeadLineDate(todayDate);
        return ADD_TASK_PAGE;
    }

    /**
     * Method to process AddUser form
     * @param addUserForm
     * @param result
     * @param request
     * @param modelMap
     * @return
     */
	@RequestMapping(method = { RequestMethod.GET,RequestMethod.POST }, value = { ADD_USER })
	public String processAddUser(
			@ModelAttribute(ADD_USER_FORM) AddUserForm addUserForm,ModelMap modelMap,
            @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
            @ModelAttribute(USER_MODEL)User sessionUser,
			BindingResult result, HttpServletRequest request) {

        //Response<Object> objectResponse = new Response<Object>();

        try {
            User user = addUserFormUserMapper.map(addUserForm, User.class);

            User isExistingUser = userManager.getUserByUserName(user.getUserName());

            if (null != isExistingUser) {
                resultMessages.errorResultMessage("add.user.invalid", user.getUserName());
                objectResponse.setStatusCode(SC_BAD_REQUEST);
            } else {

                boolean isUserSaved = userManager.saveUser(user);

                if (isUserSaved) {
                    objectResponse.setStatusCode(SC_ACCEPTED);
                    resultMessages.successResultMessage("add.user.success", user.getUserName());
                }
            }
        } catch (Exception e) {
            objectResponse.setStatusCode(SC_INTERNAL_SERVER_ERROR);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            Message message = new Message(MessageType.ERROR.toString(),"Exception occured "+ exceptionAsString);
            resultMessages.getResultMessages().add(message);
        }
        /*objectResponse.setMessages(resultMessages.getResultMessages());
        modelMap.put(RESPONSE_KEY,objectResponse);*/

		return GLOBAL_JSON_VIEW;
	}

    @RequestMapping(method = {RequestMethod.POST},value = { ADD_TASK })
    public String processAddTask(@ModelAttribute(ADD_TASK_FORM) AddTaskForm addTaskForm,ModelMap modelMap,
                                 BindingResult result, HttpServletRequest request,HttpSession session) {

        Task task = addTaskFormTaskMapper.map(addTaskForm,Task.class);
        User sessionUser = (User) session.getAttribute(SESSION_USER);
        task.setTaskOwner(sessionUser);
        task.setTaskAssignedDate(new Date());
        task.getUsers().set(0,addTaskForm.getUsers().get(0));

        // validations
        Integer taskId = taskManager.saveTask(task);
        if(null != taskId) {
            resultMessages.successResultMessage("add.task.success", taskId, task.getUsers().get(0).getUserId());
            session.setAttribute(RESULT_MESSAGES, resultMessages.getResultMessages());
        } else {
            resultMessages.errorResultMessage("add.task.failure");
            showResultMessages(resultMessages,modelMap);
            return ADD_TASK_PAGE;
        }
        return REDIRECT_ADD_TASK_PAGE;
    }

	private void populateAddUserForm(AddUserForm addUserForm) {
		addUserForm.setLevels(ControlUtil.getLevelsList());
	}

    private AddUserForm populateAddUserForm() {
        AddUserForm addUserForm = new AddUserForm();
        addUserForm.setLevels(ControlUtil.getLevelsList());
        return addUserForm;
    }

    private AddTaskForm populateAddTaskForm() {
        AddTaskForm addTaskForm = new AddTaskForm();
        User user = new User();
        user.setUserId(1);
        List<User> users = new ArrayList<User>();
        users.add(user);

        List<User> usersData = userManager.getAllUsers();
        //users = usersData;
        addTaskForm.setUsers(users);
        addTaskForm.setUsersList(ControlUtil.getUsersLinkedHashMap(usersData));
        return addTaskForm;
    }


	@Autowired
	private AddUserFormUserMapper addUserFormUserMapper;

    @Autowired
    private AddTaskFormTaskMapper addTaskFormTaskMapper;

	@Autowired
	private UserManager userManager;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TodoFileManager todoFileManager;

    @Autowired
    private MessageConfig messageConfig;

}
