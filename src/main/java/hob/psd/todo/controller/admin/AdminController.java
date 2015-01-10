package hob.psd.todo.controller.admin;

import static hob.psd.todo.constants.FormConstants.*;
import static hob.psd.todo.constants.PageConstants.*;
import static hob.psd.todo.constants.CommonConstants.*;
import static hob.psd.todo.constants.URLConstants.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hob.psd.todo.bean.*;
import hob.psd.todo.bean.form.AddTaskForm;
import hob.psd.todo.bean.form.AddUserForm;
import hob.psd.todo.config.MessageConfig;
import hob.psd.todo.controller.SuperController;
import hob.psd.todo.manager.TaskManager;
import hob.psd.todo.manager.TodoFileManager;
import hob.psd.todo.manager.UserManager;
import hob.psd.todo.mapper.config.AddTaskFormTaskMapper;
import hob.psd.todo.mapper.config.AddUserFormUserMapper;
import hob.psd.todo.util.web.ControlUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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
     */
	@RequestMapping(method = { RequestMethod.GET }, value = { ADD_USER })
	public String addUserShowForm(
			@ModelAttribute(ADD_USER_FORM) AddUserForm addUserForm,
			ModelMap modelMap, HttpServletRequest request, HttpSession session) {

        showResultMessages(session,modelMap);
		return ADD_USER_PAGE;
	}

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
	@RequestMapping(method = { RequestMethod.POST }, value = { ADD_USER })
	public String processAddUser(
			@ModelAttribute(ADD_USER_FORM) AddUserForm addUserForm,ModelMap modelMap,
			BindingResult result, HttpServletRequest request,HttpSession session) {

        User user = addUserFormUserMapper.map(addUserForm, User.class);

        // TODO : need to move this try block to custom mapper
        TodoFile todoFile = null;
        try {
            MultipartFile multipartFile = addUserForm.getProfileImage();
            byte[] bytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            todoFile = new TodoFile();
            todoFile.setFileContent(bytes);
            todoFile.setFileName(fileName);
        } catch (Exception ex) {

        }

        if( null != todoFile ) {
            Integer profilePicId = todoFileManager.saveTodoFile(todoFile);
            todoFile.setFileId(profilePicId);
            user.setProfilePicId(profilePicId);
        }

		User isExistingUser = userManager.getUserByUserName(user.getUserName());

		if (null != isExistingUser) {
			resultMessages.errorResultMessage("add.user.invalid",user.getUserName());
            showResultMessages(resultMessages,modelMap);
			return ADD_USER_PAGE;
		}

		boolean isUserSaved = userManager.saveUser(user);

        //ResultMessages resultMessages = new ResultMessages();

        if(isUserSaved) {
            resultMessages.successResultMessage("add.user.success",user.getUserName());
        }
        session.setAttribute(RESULT_MESSAGES, resultMessages.getResultMessages());


		return REDIRECT_ADD_USER_PAGE;
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

    @Autowired
    private ResultMessages resultMessages;
}
